import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FetchMoviesDetailsInJsonFormat {
    public static final String baseUrl = "https://www.imdb.com";

    public static List<String> getListOfMovieUrls(String topIndianMoviesUrl) throws IOException {
        List<String> listOfUrls = new ArrayList<>();
        Connection conn = Jsoup.connect(topIndianMoviesUrl);
        Document doc = conn.get();
        Element result = doc.getElementsByClass("lister-list").get(0);
        for (Element link : result.children()) {
            listOfUrls.add(baseUrl + link.getElementsByClass("titleColumn").get(0)
                    .select("a").first().attr("href"));
        }
        return listOfUrls;
    }

    public static void main(String[] args) throws IOException {
        List<String> listOfUrls = getListOfMovieUrls(args[0]);
        int n = Integer.parseInt(args[1]);
        List<JSONObject> outputJson = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String url = listOfUrls.get(i);
            Connection conn = Jsoup.connect(url);
            Document doc = conn.get();
            Element result1 = doc.getElementsByClass("title_wrapper").get(0);
            String titleFromSite = result1.select("h1").text();
            String[] titleAndYear = titleFromSite.split("\\s+");
            StringBuilder titleBuilder = new StringBuilder();
            for (int j = 0; j < titleAndYear.length - 2; j++) {
                titleBuilder.append(titleAndYear[j]);
                titleBuilder.append(" ");
            }
            titleBuilder.append(titleAndYear[titleAndYear.length - 2]);
            //title
            String title = String.valueOf(titleBuilder);
            //movie_release_year
            String movie_release_year = titleAndYear[titleAndYear.length - 1];
            Element result2 = doc.getElementsByClass("ratingValue").get(0);
            //imdb_rating
            String imdb_rating = result2.select("strong").text();
            Element result3 = doc.getElementsByClass("summary_text").get(0);
            //summary
            String summary = result3.text();
            Element result4 = doc.getElementsByClass("subtext").get(0);
            //duration
            String duration = result4.select("time").text();
            Element result5 = doc.getElementsByClass("subtext").get(0);
            //genre
            String genre = result5.select("a").get(0).text();
            JSONObject obj = new JSONObject();
            try {
                Field changeMap = obj.getClass().getDeclaredField("map");
                changeMap.setAccessible(true);
                changeMap.set(obj, new LinkedHashMap<>());
                changeMap.setAccessible(false);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                System.out.println("Unknown Error");
            }
            obj.put("title", title);
            obj.put("movie_release_year", movie_release_year);
            obj.put("imdb_rating", imdb_rating);
            obj.put("summary", summary);
            obj.put("duration", duration);
            obj.put("genre", genre);
            outputJson.add(obj);
        }
        System.out.println(outputJson);
    }
}
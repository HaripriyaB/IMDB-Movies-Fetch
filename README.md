# IMDB-Movies-Fetch
This project fetches the IMDB top rates Tamil, Telugu and Indian Movies list along with it's brief details.
## Instructions to run the project
### Prerequisites
* Java
* Gradle
### Steps:
1. Clone the project
2. `cd` to the root of the project from terminal
3. run: `./gradlew run --args='<URL> <COUNT_OF_ITEMS>'`

  Example: `./gradlew run --args='https://www.imdb.com/india/top-rated-indian-movies/ 3'`
  
  Example: `./gradlew run --args='https://www.imdb.com/india/top-rated-tamil-movies/ 2'`
  
  Example: `./gradlew run --args='https://www.imdb.com/india/top-rated-telugu-movies/ 1'`
  
4. Find the output in the logs
5. Sample output:
<img width="1501" alt="Screenshot 2021-04-06 at 2 05 28 AM" src="https://user-images.githubusercontent.com/34762451/113623873-9089e780-967c-11eb-9b4e-fa8db194afe0.png">

## Tools used
* Java
* Gradle 
* JSoup

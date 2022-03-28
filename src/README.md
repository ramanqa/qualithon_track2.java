# Qualitest - Qualithon'22 - Track 2 Challenge

## Challenge Objective
1. Fix failing test scripts if the failure is not a defect. The web application under test is https://www.imdb.com/
2. Create new test cases for imdb application. Test requirement and test method stub is available in test class
3. Update tests code to execute tests on Rotten Tomatoes https://www.rottentomatoes.com/. Update page objects and functions for rotten tomatoes.

## Test Cases
### Implemented Tests 
- [MovieSearchTest#testSearchByExactMovieTitleReturnsMovieAsFirstResult](https://github.com/ramanqa/qualithon_track2.java/blob/0123e782442852535133652147e7de8bcedbe174/src/test/java/com/qt/qualithon/test/MovieSearchTest.java#L57) : Verify that movie search with exact name returns result list with 1st result as the correct movie
- [MovieSearchTest#testMovieMetadataOnWebHasCorrectDirectorName](https://github.com/ramanqa/qualithon_track2.java/blob/0123e782442852535133652147e7de8bcedbe174/src/test/java/com/qt/qualithon/test/MovieSearchTest.java#L95) : Verify that movie page renders correct Director Name compared to the Movie metadata on OMDb http://www.omdbapi.com/ 
- [MovieSearchTest#testMovieMetadataOnWebHasCorrectReleaseYear](https://github.com/ramanqa/qualithon_track2.java/blob/0123e782442852535133652147e7de8bcedbe174/src/test/java/com/qt/qualithon/test/MovieSearchTest.java#L75) : Verify that movie page renders correct Release Year compared to the Movie metadata on OMDb http://www.omdbapi.com/
- [MovieSearchTesttestMovieMetadataOnWebHasCorrectWriters](https://github.com/ramanqa/qualithon_track2.java/blob/0123e782442852535133652147e7de8bcedbe174/src/test/java/com/qt/qualithon/test/MovieSearchTest.java#L115) : Verify that movie page renders correct list of Writers compared to the Movie metadata on OMDb http://www.omdbapi.com/
- [MovieSearchTest#testMovieMetadataOnWebHasCorrectGenres](https://github.com/ramanqa/qualithon_track2.java/blob/0123e782442852535133652147e7de8bcedbe174/src/test/java/com/qt/qualithon/test/MovieSearchTest.java#L135) : Verify that movie page renders correct list of Movie Genres compared to the Movie metadata on OMDb http://www.omdbapi.com/

### Pending Tests to be implemented
- [MovieSearchTest#testMovieMetadataOnWebHasCorrectMaturityRating](https://github.com/ramanqa/qualithon_track2.java/blob/0123e782442852535133652147e7de8bcedbe174/src/test/java/com/qt/qualithon/test/MovieSearchTest.java#L155) : Verify that movie page renders correct movie Maturity Rating compared to the Movie metadata on OMDb http://www.omdbapi.com/
- [MovieSearchTest#testMovieMetadataOnWebHasCorrectMovieRatingScore](https://github.com/ramanqa/qualithon_track2.java/blob/0123e782442852535133652147e7de8bcedbe174/src/test/java/com/qt/qualithon/test/MovieSearchTest.java#L168) : Verify that movie page renders correct IMDb Rating/Tomatometer Score compared to the Movie metadata on OMDb http://www.omdbapi.com/


### System Requirements to attempt challenge
 - Maven - 3.3.x
 - JDk   - 1.8+

### Run Tests:
```
  $> mvn clean test
```

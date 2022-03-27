package com.qt.qualithon.test;

import java.io.UnsupportedEncodingException;
import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.*;
import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.imdb.*;
import com.qt.qualithon.model.Movie;
import com.qt.qualithon.api.omdb.*;

/**
 * Test movie web page on imdb and rottentomato to check if the movie data is correct 
 * compared to OMDb http://www.omdbapi.com/
 **/
public class MovieSearchTest {

    public TestSession testSession;

    @BeforeMethod
    public void testSessionSetUp(){
        // init browser test session
        this.testSession = TestSession.ChromeTestSession();
    }

    @AfterMethod
    public void testTearDown(){
        // close browser test session
        this.testSession.driver().quit();
    }

    /**
     * returns a popular movies names as data provider for tests
     *
     * @return    poular movie titles data provider object array
     *
     **/
    @DataProvider
    public Object[][] popularMovieTitles() {
        return new Object [][] {
            {"A Clockwork Orange"},
            {"The Dark Knight Rises"}
        };
    }

    // ***************************************
    // ***************************************
  
    // /**
    //  * test that user should be able to search movie titles by exact title name 
    //  * and see the movie metadata
    //  *
    //  * @param   title   movie title to search
    //  * @throws UnsupportedEncodingException
    //  *
    //  **/
    // @Test(dataProvider = "popularMovieTitles")
    // public void testSearchByExactMovieTitleReturnsMovieAsFirstResult(String title) throws UnsupportedEncodingException{
    //     // get MoviePage from imdb/rottentomato
    //     MoviePage movieOnImdbWeb = new WebApp(this.testSession)
    //         .launch()
    //         .search(title)
    //         .firstMovieResult();

    //     Movie movie= new OMDbAPI().getMovie(title);
    //     try {
    //         movie = new OMDbAPI().getMovie(title);
    //     } catch (UnsupportedEncodingException e) {
    //         System.out.println("\n\nMovie title not able to fetch from OMDb site.\n\n");
    //         e.printStackTrace();
    //     }
        
    //     //to test movie title
    //     System.out.println("\n\nTesting Movie Title");
    //     System.out.println("MovieOnImdbTitle:"+movieOnImdbWeb.title());
    //     System.out.println("MovieOnOmdbTitle:"+movie.title());
    //     System.out.println("Testcase:"+ movieOnImdbWeb.title().equals(movie.title())+"\n\n");



    //     assertThat(movieOnImdbWeb.title()).isEqualTo(movie.title());
    // }

    // /**
    //  * test that release year on movie page is correct compared to the 
    //  * movie metadata on OMDb Test Data
    //  *
    //  * @param   title   movie title to search
    //  *
    //  **/
    // @Test(dataProvider = "popularMovieTitles")
    // public void testMovieMetadataOnWebHasCorrectReleaseYear(String title) throws Exception {
    //     // get MoviePage from imdb/rottentomato
    //     MoviePage movieOnImdbWeb = new WebApp(this.testSession)
    //         .launch()
    //         .search(title)
    //         .firstMovieResult();

    //     // get Movie metadata from http://www.omdbapi.com/
    //     Movie movie = new OMDbAPI().getMovie(title);

    //     //to test movie ReleaseYear
    //     System.out.println("\n\nTesting Movie Title");
    //     System.out.println("MovieOnImdbRealeseYear:"+movieOnImdbWeb.title());
    //     System.out.println("MovieOnOmdbRealeseYear:"+movie.title());
    //     System.out.println("Testcase:"+movieOnImdbWeb.releaseYear().substring(0,4).equals(movie.releaseYear())+"\n\n");



    //     assertThat(movieOnImdbWeb.releaseYear().substring(0,4)).isEqualTo(movie.releaseYear());
    // }

    // /**
    //  * test that director name on movie page is correct compared to the 
    //  * movie metadata on OMDb Test Data
    //  *
    //  * @param   title   movie title to search
    //  *
    //  **/
    // @Test(dataProvider = "popularMovieTitles")
    // public void testMovieMetadataOnWebHasCorrectDirectorName(String title) throws Exception {
    //     // get MoviePage from imdb/rottentomato
    //     MoviePage movieOnImdbWeb = new WebApp(this.testSession)
    //         .launch()
    //         .search(title)
    //         .firstMovieResult();

    //     // get Movie metadata from http://www.omdbapi.com/
    //     Movie movie = new OMDbAPI().getMovie(title);

    //         // System.out.println("Movie on IMDB"+movieOnImdbWeb.director());
    //         // System.out.println("MOvie on OMDB"+movie.director());


    //         System.out.println("\n\nTesting Movie DirectorName");
    //         System.out.println("MovieOnImdbDirector:"+movieOnImdbWeb.director());
    //         System.out.println("MovieOnOmdbDirector:"+movie.director());
    //         System.out.println("Testcase:"+movieOnImdbWeb.director().equals(movie.director())+"\n\n");
    

    //     //error resolved here
    //     //error was omdb api was giving langiage instead of director
    //     assertThat(movieOnImdbWeb.director()).isEqualTo(movie.director());
    // }

    // /**
    //  * test that writers on movie page are correct compared to the 
    //  * movie metadata on OMDb Test Data
    //  *
    //  * @param   title   movie title to search
    //  *
    //  **/
    // @Test(dataProvider = "popularMovieTitles")
    // public void testMovieMetadataOnWebHasCorrectWriters(String title) throws Exception {
    //     // get MoviePage from imdb/rottentomato
    //     MoviePage movieOnImdbWeb = new WebApp(this.testSession)
    //         .launch()
    //         .search(title)
    //         .firstMovieResult();

    //     // get Movie metadata from http://www.omdbapi.com/
    //     Movie movie = new OMDbAPI().getMovie(title);

    //     System.out.println("\n\nTesting Movie WritersName");
    //         System.out.println("MovieOnImdbWriters:"+movieOnImdbWeb.writers());
    //         System.out.println("MovieOnOmdbWriters:"+movie.writers());
    //         System.out.println("Testcase:"+movieOnImdbWeb.writers().equals(movie.writers())+"\n\n");

        
    //     // System.out.println(movieOnImdbWeb.writers().equals(movie.writers()));
    //     assertThat(movieOnImdbWeb.writers()).isEqualTo(movie.writers());
    // }

    // /**
    //  * test that movie genres on movie page are correct compared to the 
    //  * movie metadata on OMDb Test Data
    //  *
    //  * @param   title   movie title to search
    //  *
    //  **/
    // @Test(dataProvider = "popularMovieTitles")
    // public void testMovieMetadataOnWebHasCorrectGenres(String title) throws Exception {
    //     // get MoviePage from imdb/rottentomato
    //     MoviePage movieOnImdbWeb = new WebApp(this.testSession)
    //         .launch()
    //         .search(title)
    //         .firstMovieResult();

    //     // get Movie metadata from http://www.omdbapi.com/
    //     Movie movie = new OMDbAPI().getMovie(title);

    //     //test to check Genres
    //     System.out.println("\n\nTesting Movie genres");
    //     System.out.println("MovieOnImdbGenres:"+movieOnImdbWeb.genres());
    //     System.out.println("MovieOnOmdbGenres:"+movie.genres());
    //     System.out.println("Testcase:"+movieOnImdbWeb.genres().equals(movie.genres())+"\n\n");


    //     assertThat(movieOnImdbWeb.genres()).isEqualTo(movie.genres());
    // }

    // /**
    //  * test that maturity rating on movie page is correct compared to the
    //  * maturity rating in OMDb Test Data API
    //  *
    //  * @param   title   movie title to search
    //  *
    //  **/
    // @Test(dataProvider = "popularMovieTitles")
    // public void testMovieMetadataOnWebHasCorrectMaturityRating(String title) throws Exception {
    //     // NOT IMPLEMENTED
    //     MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title)
    //     .firstMovieResult();

    //     Movie movie = new OMDbAPI().getMovie(title);

    //         //test to check maturity
    //    System.out.println("\n\nTesting Movie MaturityRating");
    //    System.out.println("MovieOnImdbMaturityRating:"+movieOnImdbWeb.maturityRating());
    //    System.out.println("MovieOnOmdbMaturityRating:"+movie.maturityRating());
    //    System.out.println("Testcase:"+movieOnImdbWeb.maturityRating().equals(movie.maturityRating())+"\n\n");

    //     assertThat(movie.maturityRating()).isEqualTo(movieOnImdbWeb.maturityRating());
    // }

    // /**
    //  * test that movie rating score on movie page (IMDB Rating, Tomatometer) is correct compared to the
    //  * movie rating score in OMDb Test Data API
    //  *
    //  * @param   title   movie title to search
    //  *
    //  **/
    // @Test(dataProvider = "popularMovieTitles")
    // public void testMovieMetadataOnWebHasCorrectMovieRatingScore(String title) throws Exception {
    //     // // NOT IMPLEMENTED
    //     // throw new Exception("Test Pending");
    //     MoviePage movieOnImdbWeb = new WebApp(this.testSession).launch().search(title)
    //     .firstMovieResult();

    //     Movie movie = new OMDbAPI().getMovie(title);

    //       //test to check Rating
    //       System.out.println("\n\nTesting Movie Rating");
    //       System.out.println("MovieOnImdbRating:"+movieOnImdbWeb.rating().substring(0,3));
    //       System.out.println("MovieOnOmdbRating:"+movie.rating());
    //       System.out.println("Testcase:"+(movieOnImdbWeb.rating().substring(0,3)).equals(movie.rating())+"\n\n");

    //     assertThat(movie.rating()).isEqualTo((movieOnImdbWeb.rating().substring(0,3)));
      
    // }
    

    // ********************************************
    // ********************************************

    //RottenTomatoes
    @Test(dataProvider = "popularMovieTitles")
    public void testSearchByExactMovieTitleReturnsMovieAsFirstResultOnRottenTomatoes(String title) throws UnsupportedEncodingException{
        // get MoviePage from imdb/rottentomato
        MoviePage movieOnRottenTomatoes = new WebApp(this.testSession)
            .launchRottenTomatoes()
            .searchRottenTomatoes(title)
            .firstMovieOnResultRottenTomatoes();

        Movie movie= new OMDbAPI().getMovie(title);
        try {
            movie = new OMDbAPI().getMovie(title);
        } catch (UnsupportedEncodingException e) {
            System.out.println("\n\nMovie title not able to fetch from OMDb site.\n\n");
            e.printStackTrace();
        }

        //to test movie title
        System.out.println("\n\nTesting Movie Title");
        System.out.println("MovieOnImdbTitle:"+movieOnRottenTomatoes.titleOnRottenTomatoes());
        System.out.println("MovieOnOmdbTitle:"+movie.title());
        System.out.println("Testcase:"+ movieOnRottenTomatoes.titleOnRottenTomatoes().toLowerCase().equals(movie.title().toLowerCase())+"\n\n");



        assertThat(movieOnRottenTomatoes.titleOnRottenTomatoes().toLowerCase()).isEqualTo(movie.title().toLowerCase());
    }

}

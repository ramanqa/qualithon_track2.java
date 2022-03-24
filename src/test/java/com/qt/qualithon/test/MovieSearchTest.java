package com.qt.qualithon.test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.*;
import org.testng.Reporter;

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
  
    /**
     * test that user should be able to search movie titles by exact title name 
     * and see the movie metadata
     *
     * @param   title   movie title to search
     *
     **/
    @Test(dataProvider = "popularMovieTitles")
    //This TestCase Worked absolutely fine.
    public void testSearchByExactMovieTitleReturnsMovieAsFirstResult(String title){
        // get MoviePage from imdb/rottentomato
        MoviePage movieOnImdbWeb = new WebApp(this.testSession)
            .launch()
            .search(title)
            .firstMovieResult();

        assertThat(movieOnImdbWeb.title()).isEqualTo(title);
    }

    /**
     * test that release year on movie page is correct compared to the 
     * movie metadata on OMDb Test Data
     *
     * @param   title   movie title to search
     *
     **/
    @Test(dataProvider = "popularMovieTitles")
    //This TestCase had one problem fixed on line number 88
    public void testMovieMetadataOnWebHasCorrectReleaseYear(String title) throws Exception {
        // get MoviePage from imdb/rottentomato
        MoviePage movieOnImdbWeb = new WebApp(this.testSession)
            .launch()
            .search(title)
            .firstMovieResult();

        // get Movie metadata from http://www.omdbapi.com/
        Movie movie = new OMDbAPI().getMovie(title);
        String[] years = movieOnImdbWeb.releaseYear().split("\n");// to fix the problem string had to be trimmed to find useful data
        assertThat(years[0]).isEqualTo(movie.releaseYear());
    }

    /**
     * test that director name on movie page is correct compared to the 
     * movie metadata on OMDb Test Data
     *
     * @param   title   movie title to search
     *
     **/
    @Test(dataProvider = "popularMovieTitles")
    // This testcase had a problem due to code written in OMDbAPI,java file 
    public void testMovieMetadataOnWebHasCorrectDirectorName(String title) throws Exception {
        // get MoviePage from imdb/rottentomato
        MoviePage movieOnImdbWeb = new WebApp(this.testSession)
            .launch()
            .search(title)
            .firstMovieResult();

        // get Movie metadata from http://www.omdbapi.com/
        Movie movie = new OMDbAPI().getMovie(title);
        assertThat(movieOnImdbWeb.director()).isEqualTo(movie.director());
    }

    /**
     * test that writers on movie page are correct compared to the 
     * movie metadata on OMDb Test Data
     *
     * @param   title   movie title to search
     *
     **/
    @Test(dataProvider = "popularMovieTitles")
    //This testcase had a problem that is fixed on line number 131
    public void testMovieMetadataOnWebHasCorrectWriters(String title) throws Exception {
        // get MoviePage from imdb/rottentomato
        MoviePage movieOnImdbWeb = new WebApp(this.testSession)
            .launch()
            .search(title)
            .firstMovieResult();

        // get Movie metadata from http://www.omdbapi.com/
        Movie movie = new OMDbAPI().getMovie(title);
        List<String> sortedList = movie.writers().stream().sorted().collect(Collectors.toList()); // here the list had to be sorted
        assertThat(movieOnImdbWeb.writers()).isEqualTo(sortedList);
    }

    /**
     * test that movie genres on movie page are correct compared to the 
     * movie metadata on OMDb Test Data
     *
     * @param   title   movie title to search
     *
     **/
    @Test(dataProvider = "popularMovieTitles")
    public void testMovieMetadataOnWebHasCorrectGenres(String title) throws Exception {
        // get MoviePage from imdb/rottentomato
    	MoviePage movieOnImdbWeb = new WebApp(this.testSession)
            .launch()
            .search(title)
            .firstMovieResult();
        // get Movie metadata from http://www.omdbapi.com/
        Movie movie = new OMDbAPI().getMovie(title);
        List<String> sortedList = movieOnImdbWeb.genres().stream().sorted().collect(Collectors.toList());
        assertThat(sortedList).isEqualTo(movie.genres());
    }

    /**
     * test that maturity rating on movie page is correct compared to the
     * maturity rating in OMDb Test Data API
     *
     * @param   title   movie title to search
     *
     **/
    @Test(dataProvider = "popularMovieTitles")
    //Newly created TestCase
    public void testMovieMetadataOnWebHasCorrectMaturityRating(String title) throws Exception {
        // get MoviePage from imdb/rottentomato
    	MoviePage movieOnImdbWeb = new WebApp(this.testSession)
    			.launch()
    			.search(title)
    			.firstMovieResult();
    	
    	//get Movie metadata from http://www.omdbapi.com/
    	Movie movie = new OMDbAPI().getMovie(title);
    	String[] maturity = movieOnImdbWeb.maturityRating().split("\n");
    	assertThat(maturity[1]).isEqualTo(movie.maturityRating());
    }

    /**
     * test that movie rating score on movie page (IMDB Rating, Tomatometer) is correct compared to the
     * movie rating score in OMDb Test Data API
     *
     * @param   title   movie title to search
     *
     **/
    @Test(dataProvider = "popularMovieTitles")
    //Newly Created TestCase
    public void testMovieMetadataOnWebHasCorrectMovieRatingScore(String title) throws Exception {
        // get MoviePage from imdb/rottentomato
    	MoviePage movieOnImdbWeb = new WebApp(this.testSession)
    			.launch()
    			.search(title)
    			.firstMovieResult();
    	

    	// get Movie metadata from http://www.omdbapi.com/
    	Movie movie = new OMDbAPI().getMovie(title);
    	String[] rating = movieOnImdbWeb.movieRating().split("\n");
    	assertThat(rating[1]).isEqualTo(movie.movieRating());
    }
}

/**
 * 
 */
package com.qt.qualithon.test;
import java.lang.reflect.Method;
import org.testng.annotations.*;
import org.testng.Reporter;
import static org.assertj.core.api.Assertions.*;
import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.*;
import com.qt.qualithon.ui.rotten.*;
import com.qt.qualithon.model.Movie;
import com.qt.qualithon.api.omdb.*;


/**
 * @author Harshit.Bhatnagar
 *
 */
public class MovieSearchTestRT {
	
	public TestSession testSession;
	
	
	 @BeforeMethod
	    public void testSessionSetUp(){
	        
	    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\harshit.bhatnagar\\Downloads\\qualithon_track2.java-main\\src\\test\\resources\\webdriver\\chromedriver.exe");
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
	    public void testSearchByExactMovieTitleReturnsMovieAsFirstResult(String title){
	        // get MoviePage from imdb/rottentomato
	        MoviePageRotten movieOnRtWeb = new WebAppRotten(this.testSession)
	            .launch()
	            .search(title)
	            .firstMovieResult();

	        assertThat(movieOnRtWeb.title()).isEqualToIgnoringCase(title);
	    }
	    
	    

	    /**
	     * test that release year on movie page is correct compared to the 
	     * movie metadata on OMDb Test Data
	     *
	     * @param   title   movie title to search
	     *
	     **/
	    @Test(dataProvider = "popularMovieTitles")
	    public void testMovieMetadataOnWebHasCorrectReleaseYear(String title) throws Exception {
	        // get MoviePage from imdb/rottentomato
	        MoviePageRotten movieOnRtWeb = new WebAppRotten(this.testSession)
	            .launch()
	            .search(title)
	            .firstMovieResult();

	        // get Movie metadata from http://www.omdbapi.com/
	        Movie movie = new OMDbAPI().getMovie(title);
	        assertThat(movieOnRtWeb.releaseYear()).isEqualTo(movie.releaseYear());
	    }

	    /**
	     * test that director name on movie page is correct compared to the 
	     * movie metadata on OMDb Test Data
	     *
	     * @param   title   movie title to search
	     *
	     **/
	    @Test(dataProvider = "popularMovieTitles")
	    public void testMovieMetadataOnWebHasCorrectDirectorName(String title) throws Exception {
	        // get MoviePage from imdb/rottentomato
	        MoviePageRotten movieOnRtWeb = (MoviePageRotten) new WebAppRotten(this.testSession)
	            .launch()
	            .search(title)
	            .firstMovieResult();

	        // get Movie metadata from http://www.omdbapi.com/
	        Movie movie = new OMDbAPI().getMovie(title);
	        assertThat(movieOnRtWeb.director()).isEqualTo(movie.director());
	    }

	    /**
	     * test that writers on movie page are correct compared to the 
	     * movie metadata on OMDb Test Data
	     *
	     * @param   title   movie title to search
	     *
	     **/
	    @Test(dataProvider = "popularMovieTitles")
	    public void testMovieMetadataOnWebHasCorrectWriters(String title) throws Exception {
	        // get MoviePage from imdb/rottentomato
	        MoviePageRotten movieOnRtWeb = new WebAppRotten(this.testSession)
	            .launch()
	            .search(title)
	            .firstMovieResult();

	        // get Movie metadata from http://www.omdbapi.com/
	        Movie movie = new OMDbAPI().getMovie(title);
	        assertThat(movieOnRtWeb.writers()).isEqualTo(movie.writers());
	    }

	   
}

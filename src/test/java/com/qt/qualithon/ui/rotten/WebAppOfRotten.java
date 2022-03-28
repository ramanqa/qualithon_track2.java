package com.qt.qualithon.ui.rotten;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;


/**
 * entry class to hold IMDB Web Application UI Model/Page Objects
 **/
public class WebAppOfRotten extends Page{

	   public WebAppOfRotten(TestSession testSession){
	        super(testSession);
	    }

	    /**
	     * launch IMDb landing page in browser test session
	     *
	     * @return    IMDb Web Home Page page object
	     **/
	    public HomePageOfRotten launch(){
	        this.testSession.driver().get("https://www.rottentomatoes.com/");
	        return new HomePageOfRotten(this.testSession);
    }
}

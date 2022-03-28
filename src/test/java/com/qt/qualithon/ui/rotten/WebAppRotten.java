package com.qt.qualithon.ui.rotten;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;
import com.qt.qualithon.ui.imdb.HomePage;

/**
 * entry class to hold Rotten Tomatoes Web Application UI Model/Page Objects
 **/
public class WebAppRotten extends Page{

    public WebAppRotten(TestSession testSession){
        super(testSession);
    }

    /**
     * launch Rotten Tomatoes landing page in browser test session
     *
     * @return    Rotten Tomatoes Web Home Page page object
     **/
    public HomePageRotten launch(){
        this.testSession.driver().get("https://www.rottentomatoes.com/");
        return new HomePageRotten(this.testSession);
    }
}

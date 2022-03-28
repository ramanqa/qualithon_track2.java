package com.qt.qualithon.ui.rotten;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;
import com.qt.qualithon.model.Movie;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * represents IMDb web search results page elements and actions (page object)
 **/
public class ResultsPageOfRotten extends Page{

    public ResultsPageOfRotten(TestSession testSession){
        super(testSession);
    }

    /**
     * get a list of result link elements
     * 
     * @return    list of movie link web elements from results page
     **/
    public List<WebElement> movieResultLinks(){
        List<WebElement> resultLinks = this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                
            	By.xpath("//search-page-result[@slot ='movie'] //ul[@slot ='list'] //a[@slot='title']")
            )
        );
        return resultLinks;
    }

    /**
     * open first movie result link from result page and return movie page page object
     *
     * @return    Movie Page imdb page object
     **/
    public MoviePageOfRotten firstMovieResult(){
        this.movieResultLinks().get(0).click();
        return new MoviePageOfRotten(this.testSession);
    }
}

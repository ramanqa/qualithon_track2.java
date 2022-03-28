package com.qt.qualithon.ui.rotten;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;


/**
 * represents IMDb Web Home Page elements and ui actions (page object)
 **/
public class HomePageOfRotten extends Page {

    public HomePageOfRotten(TestSession testSession){
        super(testSession);
    }

    /**
     * perform a search for movie title and return the resultlist page
     *
     * @param     movieTitle    movie name
     * @return    IMDb Results Page page object
     **/
 
        
        public ResultsPageOfRotten search(String movieTitle){
            WebElement searchInput = this.testSession.driverWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@class='search-text']")));

            WebElement searchBtn = this.testSession.driverWait().until(
                    ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[@class='search-submit']")));
            
            searchInput.sendKeys(movieTitle);
            searchBtn.click();
            
            
            return new ResultsPageOfRotten(this.testSession);
    }
}

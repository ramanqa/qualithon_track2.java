package com.qt.qualithon.ui.rotten;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;

import java.util.List;
import java.util.ArrayList;

/**
 * page object class represents elements and actions on the IMDb Movie Page
 **/
public class MoviePageOfRotten extends Page{

    public MoviePageOfRotten(TestSession testSession){
        super(testSession);

    }

    /**
     * get movie title
     *
     * @return    movie title
     **/
    public String title(){
        return this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1.scoreboard__title")
            ) 
        ).getText();
    }

    /**
     * get movie director name
     *
     * @return    movie director name
     **/
    public String director(){
        List<WebElement> credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
              By.cssSelector("ul.content-meta.info li")));

        // traverse credits sections to find the section with Directors
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.cssSelector("div.meta-label.subtle"))
                		.getText().replaceAll(":", "").equalsIgnoreCase("Director")){
                    // find director name from child element of section
                    return credit.findElement(By.cssSelector("a")).getText();
                }
            }catch(NoSuchElementException e){}

        }
        throw new NoSuchElementException("Failed to lookup Director on page");
    }

    /**
     * get list of movie genres
     *
     * @return    list of movie genres
     **/
    public List<String> genres(){
        List<String> genres = new ArrayList<>();
        String genresList[] = null;
        List<WebElement> credits = this.testSession.driverWait().until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                  By.cssSelector("ul.content-meta.info li")));

            // traverse credits sections to find the section with Directors
            for(WebElement credit:credits){
                try{
                    if(credit.findElement(By.cssSelector("div.meta-label.subtle"))
                    		.getText().replaceAll(":", "").equalsIgnoreCase("Genre")){
                        // find director name from child element of section
                       genresList = credit.findElement(By.cssSelector("div.meta-value.genre")).getText().trim().split(",");
                    }
                }catch(NoSuchElementException e){}
            }
        
            for(int i=0;i<genresList.length;i++) {
            	genres.add(genresList[i]);
            }
        
        // if genres list is empty throw exception
        if(genres.isEmpty()){
            throw new NoSuchElementException("Could not lookup genres on Movie page");
        }
        return genres;
    }
    
    /**
     * get movie release year
     *
     * @return    movie release year
     **/
    public String releaseYear(){
        String movieInfo =  this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(          		
                By.cssSelector("p.scoreboard__info")
            ) 
        ).getText();
        
        String movieInfoList[] = movieInfo.split(",");
        return movieInfoList[0] ;
    }

    /**
     * get list of movie writers
     *
     * @return    list of movie writer names
     **/
    public List<String> writers(){
        List<String> writers = new ArrayList<>();
        List<WebElement> credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
              By.cssSelector("ul.content-meta.info li")));
        // traverse credits sections to find the section with Writers
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.cssSelector("div.meta-label.subtle")).getText()
                		.replaceAll(":", "").equalsIgnoreCase("Writer")){
                    // traverse list of writers on page to add to writers list
                    List<WebElement> writersElements = credit.findElements(By.cssSelector("a"));
                    for(int i = 0 ; i < writersElements.size() ; i++){
                        writers.add(writersElements.get(i).getText());
                    }
                    
                    break;
                }
             
            }catch(NoSuchElementException e){}
        }

        // if writers list is empty throw exception
        if(writers.isEmpty()){
            throw new NoSuchElementException("Could not lookup Writers on movie page");
        }
        return writers;
    }
}
    
   
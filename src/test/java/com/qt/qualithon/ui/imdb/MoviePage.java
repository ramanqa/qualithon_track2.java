package com.qt.qualithon.ui.imdb;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;

/**
 * page object class represents elements and actions on the IMDb Movie Page
 **/
public class MoviePage extends Page{
    public MoviePage(TestSession testSession){
        super(testSession);

        // adjust page for tablet formfactor
        // String uRL = this.testSession.webDriver.getCurrentUrl();
        try{
            WebElement showMoreLink = this.testSession.driverWait().until(
                ExpectedConditions.presenceOfElementLocated(
                  By.cssSelector("a[data-testid='title-pc-expand-more-button']")));
            if(showMoreLink.isDisplayed()){
                showMoreLink.click();
            }
        }
        catch(Exception e){}

    }

    /**
     * get movie title
     *
     * @return    movie title
     **/
    public String title(){
        return this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[data-testid='hero-title-block__title']")
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
              By.cssSelector("li.ipc-metadata-list__item")));

        // traverse credits sections to find the section with Directors
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("Director")){
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
        List<WebElement> credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
              By.cssSelector("li.ipc-metadata-list__item")));
            //   System.out.println(credits);

        // traverse credits sections to find the section with Writers
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("Genres")){
                    // traverse list of genres on page to add to genres list
                    List<WebElement> genresElements = credit.findElements(By.cssSelector("a"));
                    for(int i = 0; i <  genresElements.size(); i++){
                        genres.add(genresElements.get(i).getText());
                            // System.out.println(writersElements.get(i).getText());
                    }
                // }
                break;
                }
            }catch(NoSuchElementException e){}
        }
        System.out.println(genres);
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
        return this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("ul[data-testid='hero-title-block__metadata']")
            ) 
        ).getText();
    }

    // here is rating from imdb
    public String rating()
    {
        return this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div[data-testid='hero-rating-bar__aggregate-rating__score']")
            ) 
        ).getText();
    }

    //here is rated from imdb
    public String maturityRating()
    { 
        return this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("ul[data-testid='hero-title-block__metadata'] li[role='presentation']:nth-child(2)")
            ) 
        ).getText();
        
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
              By.cssSelector("li.ipc-metadata-list__item")));

        // traverse credits sections to find the section with Writers
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.cssSelector("a")).getText().equalsIgnoreCase("Writers") || credit.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("Writers")){
                    // traverse list of writers on page to add to writers list
                    List<WebElement> writersElements = credit.findElements(By.cssSelector("a[class='ipc-metadata-list-item__list-content-item ipc-metadata-list-item__list-content-item--link']"));
                    for(int i = 0; i < writersElements.size() ; i++){
                            writers.add(writersElements.get(i).getText());
                            // System.out.println(writersElements.get(i).getText());
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



    //RottenTomatoes
    public String titleOnRottenTomatoes(){
        System.out.println("\n\nOnRottenTomatoes Title:"+this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[slot='title']")
            ) 
        ).getText()+"\n\n");
        return this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[slot='title']")
            ) 
        ).getText();
    }


    // /**
    //  * get movie director name ON Rotten Tomatoes
    //  *
    //  * @return    movie director name
    //  **/
    // public String directorOnRottenTomatoes(){
    //     List<WebElement> credits = this.testSession.driverWait().until(
    //         ExpectedConditions.presenceOfAllElementsLocatedBy(
    //           By.cssSelector("li.ipc-metadata-list__item")));

    //     // traverse credits sections to find the section with Directors
    //     for(WebElement credit:credits){
    //         try{
    //             if(credit.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("Director")){
    //                 // find director name from child element of section
    //                 return credit.findElement(By.cssSelector("a")).getText();
    //             }
    //         }catch(NoSuchElementException e){}
    //     }
    //     throw new NoSuchElementException("Failed to lookup Director on page");
    // }
}

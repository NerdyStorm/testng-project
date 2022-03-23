package com.techfios.pages;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TestPage {

    WebDriver driver;
    String categoryAddedName;
    String duplicate;


    public TestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "input[name='submit'][value='Add category']")
    WebElement addCategoryButton;
    @FindBy(how = How.NAME, using = "categorydata")
    WebElement categoryBox;
    @FindBy(how = How.CLASS_NAME, using = "controls")
    WebElement categoryDiv;
    @FindBy(how = How.TAG_NAME, using = "body")
    WebElement body;
    @FindBy(how = How.NAME, using = "due_month")
    WebElement month;



    public void addCategory(){

        categoryAddedName = "Md Hossain" + (new Random()).nextInt(123912);


        categoryBox.sendKeys(categoryAddedName);
        addCategoryButton.click();
        System.out.println("Category Added: "+ categoryAddedName);

    }

    public void verifyCategoryAdded(){
        
        List<WebElement> categories = categoryDiv.findElements(By.tagName("a"));

        System.out.println(categories.get(0).getText());
        categories.remove(0);

        String lastCategoryText = categories.get(categories.size()-1).getText();

        System.out.println("Last Category: " + lastCategoryText);
        Assert.assertEquals(categories.get(categories.size()-1).getText(), categoryAddedName, "Last category name doesn't match added name.");

    }

    
    /**
     * @param duplicate Duplicate category name
     */
    public void addDuplicateCategory(String duplicate){

        this.duplicate = duplicate;
        categoryBox.sendKeys(duplicate);

        addCategoryButton.click();
        System.out.println("Duplicate Category Added: "+ duplicate);

    }

    public void verifyDuplicateCategoryAdded(){

        String actual = body.getText().substring(0, 56);
        System.out.println(actual);
        Assert.assertEquals(actual, "The category you want to add already exists: " + this.duplicate +".", "Duplicate category not added.");

    }

    public void verifyDropdownMonths(){

        Select select = new Select(month);
        List<WebElement> options = select.getOptions();

        List<String> possibleMonths = Arrays.asList("None", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        int c = 0;

        for(WebElement e: options){
            System.out.println(e.getText());

            if(possibleMonths.contains(e.getText())){
                c++;
            }
            
        }

        Assert.assertTrue(c==13, "Not all months are present as expected.");

    }
}

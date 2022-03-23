package com.techfios.test;

import com.techfios.pages.TestPage;
import com.techfios.utils.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TechfiosTest {
    
    WebDriver driver;
    TestPage testPage;


    @BeforeTest
    public void beforeTest(){

        driver = BrowserFactory.initDriver();

        testPage = new TestPage(driver);

    }

    @Test
    public void categoryAddTest(){

        testPage.addCategory();
        testPage.verifyCategoryAdded();

    }

    @Test
    public void CannotAddDuplicateCategoryTest(){

        testPage.addDuplicateCategory("Md Hossain");
        testPage.verifyDuplicateCategoryAdded();

    }

    @Test
    public void verifyDropdownMonths(){

        testPage.verifyDropdownMonths();

    }

}

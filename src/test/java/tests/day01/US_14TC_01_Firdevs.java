package tests.day01;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminDashboard;
import pages.UserDashboard;
import pages.UserHomepage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import javax.swing.*;

public class US_14TC_01_Firdevs {

    @Test

    public void US_14_TC_01_Test(){

        UserHomepage userHomepage= new UserHomepage();

        //The user logins to "https://qa.hauseheaven.com/admin/login" page.

        Driver.getDriver().get(ConfigReader.getProperty("userUrl"));
        userHomepage.linkSignIn.click();

       UserDashboard userDashboard = new UserDashboard();

       userDashboard.textboxUserLoginUserName.sendKeys(ConfigReader.getProperty("userName"));
       userDashboard.textboxUserLoginPassWord.sendKeys(ConfigReader.getProperty("userPassword"));
       userDashboard.textboxUserLogin.click();


        //It is directed to the account page.
        userDashboard.headerUserName.click();

        ReusableMethods.bekle(2);

        //Click on the relevant section to view property ads.


        JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver.getDriver();




        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", userDashboard.dashBoard);



        ReusableMethods.bekle(2);
        jsExecutor.executeScript("arguments[0].click();", userDashboard.properties);





        //Click the "Add New Ad" button.
        userDashboard.addProperty.click();




        //Enters the advertisement information (eg title, description, content, location).

        String expectedUrl = "https://qa.hauseheaven.com/account/properties/create";
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(),expectedUrl);

        String expectedTitle="House for Sale";

        userDashboard.titleInput.sendKeys(expectedTitle);
        userDashboard.descriptionInput.sendKeys("deneme");
        userDashboard.contentTextInput.sendKeys("deneme");
        userDashboard.propertyInput.sendKeys("Canada");



        //Click the "Save" button to publish the ad.

        userDashboard.saveAndExitButton.click();

        // Verify the process.
        String actualTitle = userDashboard.nameRecord1.getText();
        Assert.assertEquals(actualTitle,expectedTitle);


        //Click on the relevant advertisement to edit property ads.

        userDashboard.editButton1.click();
        //Updates the advertisement information (eg title, description, price, location).
        expectedTitle="House for Sale1";
        userDashboard.titleInput.sendKeys("1");



        //Click the "Update" button to save the changes.
        userDashboard.saveAndExitButton.click();

        //Verify the process.

        actualTitle = userDashboard.nameRecord1.getText();
        Assert.assertEquals(actualTitle,expectedTitle);

        ReusableMethods.bekle(2);


        //Click on the advertisement he wants to delete.
        userDashboard.deleteButton1.click();

        ReusableMethods.bekle(2);


        //Click the "Delete" button and confirm the process.
        userDashboard.buttonDeleteConfirm.click();

        ReusableMethods.bekle(2);

        //Verify that the property is deleted.
        actualTitle = userDashboard.nameRecord1.getText();
        Assert.assertNotEquals(actualTitle,expectedTitle);


        //Close driver

        Driver.closeDriver();
    }





}

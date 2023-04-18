package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest
{
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setBaseUrl()
    {
        openBrowser(baseUrl); // open the browser and set up the url
    }

    @Test

    public void userShouldLoginSuccessfullyWithValidCredentials()
    {
        //finding the username and password element and enterng the values in respective fields
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

        // verifying user is logged in successfully or not by verifying the text displayed on the page
        String expectedMessage="Secure Area";
        WebElement actualMessage = driver.findElement(By.xpath("//h2[contains(text(),' Secure Area')]"));
        String actualMessageText = actualMessage.getText();

        Assert.assertEquals("User is not logged in successful",expectedMessage,actualMessageText);
    }
    @Test
    public void verifyTheUsernameErrorMessage()
    {
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //verifying the error message is displayed or not
        String expectedMessage = "Your username is invalid!";
        WebElement actualMessage = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessageText = actualMessage.getText();

        Assert.assertEquals("Your username is invalid",expectedMessage,actualMessageText);


    }

    @Test

    public void verifyThePasswordErrorMessage()
    {
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //verifying the error message for invalid password
        String expectedMessage = "Your password is invalid!";
        WebElement actualMessage = driver.findElement(By.xpath("//div[@class='row']//div[@id='flash']"));
        String actualMessageText = actualMessage.getText().substring(0,25);

        Assert.assertEquals("Your password is invalid",expectedMessage,actualMessageText);


    }

    @After
    public void tearDown()
    {
        closeBrowser(); // closing browser
    }

}

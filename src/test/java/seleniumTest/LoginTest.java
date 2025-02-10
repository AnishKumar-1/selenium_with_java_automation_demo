package seleniumTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

    @ParameterizedTest
    @CsvSource({
        "standard_user, secret_sauce, https://www.saucedemo.com/inventory.html",
    })
    public void loginAndCheckCredentials(String username, String password, String expectedUrl) {
        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        
        try {
            // Open the login page
            driver.get("https://www.saucedemo.com/");
            
            // Find username, password fields, and login button
            WebElement usernameElement = driver.findElement(By.id("user-name"));
            WebElement passwordElement = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));
            
            // Enter login credentials
            usernameElement.clear();
            usernameElement.sendKeys(username);
            passwordElement.clear();
            passwordElement.sendKeys(password);
            loginButton.click();
            
            // Verify if logged in by checking the URL
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.equals(expectedUrl)) {
                // Failed login: store wrong credentials
                File fileObject = new File("C:\\Users\\kumar\\Desktop\\LoginTestWrongCredentionals.txt");
                if (!fileObject.exists()) {
                    fileObject.createNewFile();
                }
                // Open FileWriter in append mode
                FileWriter writeObject = new FileWriter(fileObject,true);
                writeObject.write(username + " , " + password + System.lineSeparator());
                writeObject.close();
                
                // Check for the error message on the page after a failed login
                WebElement errorMessage = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface')]"));
                Assertions.assertTrue(errorMessage.isDisplayed(), "Error message not displayed for wrong credentials");

            } else {
                // Successful login: assert the URL is as expected
                Assertions.assertEquals(expectedUrl, currentUrl, "Unexpected URL after login");
               //item 1 find 
                WebElement item1=driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
                //  item 2
                item1.click();
                WebElement item2=driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
                item2.click();
                //got to cart
                WebElement cartButton=driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
                cartButton.click();
                //verify url if we are at cart page or not
                String cartExpectedUrl="https://www.saucedemo.com/cart.html";
                String cartCurrentUrl=driver.getCurrentUrl();
                Assertions.assertEquals(cartExpectedUrl,cartCurrentUrl,"We didn't reach to cart page");
                // click on checkout button 
                WebElement checkOutButton=driver.findElement(By.xpath("//button[@id='checkout']"));
                 Assertions.assertEquals(checkOutButton.isDisplayed(),true,"checkout button is not displayed");
                 checkOutButton.click();
                 
                 //verify after checkout url to fill the data in form
                 String afterCheckoutFormUrlExpected=driver.getCurrentUrl();
                 Assertions.assertEquals(afterCheckoutFormUrlExpected,"https://www.saucedemo.com/checkout-step-one.html","we couldn't wrong url found");
                //firstname,
                 WebElement firstName=driver.findElement(By.xpath("//input[@id='first-name']"));
                 firstName.sendKeys("anish");
                 //latname,
                 WebElement lastname=driver.findElement(By.xpath("//input[@id='last-name']"));
                 lastname.sendKeys("kumar");
                 //zipcode
                 WebElement zipcode=driver.findElement(By.xpath("//input[@id='postal-code']"));
                 zipcode.sendKeys("123");
                 
                 //input[@id='continue'] button to place order
                 WebElement continueButton=driver.findElement(By.xpath("//input[@id='continue']"));

                 //verify button is there or not
                 Assertions.assertEquals(continueButton.isDisplayed(),true,"continue button is not displayed");
                 continueButton.click();
                
                 String orderPlacedExpectedUrl="https://www.saucedemo.com/checkout-step-two.html";
                 
                // verify url to verify details
                 Assertions.assertEquals(orderPlacedExpectedUrl,driver.getCurrentUrl(),"we are not at the order place page");
                 WebElement finishButton=driver.findElement(By.xpath("//button[@id='finish']"));
                 Assertions.assertEquals(finishButton.isDisplayed(),true,"finish button is not displayed");
                 finishButton.click();
                // verify  final order placed url
                 Assertions.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-complete.html","we are not at order placed url");
                     
                //  //button[@id='back-to-products'] back to home page 
                 WebElement backToHome=driver.findElement(By.xpath("https://www.saucedemo.com/inventory.html"));
                //  verify url of home page
                 //check it is visible or not
                 Assertions.assertEquals(backToHome.isDisplayed(),true,"back to home page button is not displayed");
                 backToHome.click();
                //logout
                
                
                
                
                // Proceed with actions after successful login (e.g., navigating, checking elements)
                WebElement menuBar = driver.findElement(By.id("react-burger-menu-btn"));
                menuBar.click();
                
                // Wait for the logout button to be clickable and click it
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
                logoutButton.click();
                
                // Verify if redirected back to the login page
                String loginPageUrl = driver.getCurrentUrl();
                Assertions.assertEquals("https://www.saucedemo.com/", loginPageUrl, "Failed to logout");
            }
        } catch (IOException io) {
            System.out.println("Something went wrong with the site.");
            io.printStackTrace();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while handling login.");
            e.printStackTrace();
        } finally {
            // Close the WebDriver
            driver.quit();
        }
    }
}

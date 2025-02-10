package seleniumTest;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartAutomation {
    WebDriver driver;

    // Login method
    @Test
    public void loginTest() {
        driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");

        // Click on the login button
        WebElement loginButton = driver.findElement(By.xpath("//span[text()='Login']"));
        loginButton.click();

        // Updated XPath to locate the mobile number input box
        WebElement MobileNumberInputBox = driver.findElement(By.xpath("//div[@id='container']//div[@class='VCR99n']//form//input"));
        MobileNumberInputBox.sendKeys("8279210815");

        // Locate the 'Request OTP' button and click
        WebElement requestOtpButton = driver.findElement(By.xpath("//div[@id='container']//div[@class='VCR99n']//form//button[text()='Request OTP']"));
        requestOtpButton.click();

        // Minimize the browser window after sending the OTP
        driver.manage().window().minimize();
        // Prompt user to enter OTP manually into the console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the OTP received on your mobile: ");
        String otp = scanner.nextLine();
        scanner.close();
        driver.manage().window().maximize();
        // Reopen the browser window after entering the OTP

        // Locate OTP input fields
        List<WebElement> otpInputs = driver.findElements(By.xpath("//input[@class='r4vIwl IX3CMV']"));

        // Check if OTP fields are found
        if (otpInputs.size() != otp.length()) {
            System.out.println("Mismatch in OTP length and input fields detected.");
            return;
        }

        // Enter OTP into the fields
        for (int i = 0; i < otp.length(); i++) {
            otpInputs.get(i).sendKeys(Character.toString(otp.charAt(i)));
        }

        // Print confirmation message
        System.out.println("OTP entered successfully!");

        // Wait until the OTP submit button is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement otpSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@type='submit' and contains(@class, 'QqFHMw') and contains(@class, 'llMuju') and contains(@class, 'M5XAsp')]"))
        );

        // Click on the submit button
        otpSubmitButton.click();

        // Keep the browser open for visual confirmation; comment out the following line if you want to close it manually
//         
        WebDriverWait waitToQuite = new WebDriverWait(driver, Duration.ofSeconds(20));
        waitToQuite.until(ExpectedConditions.urlToBe("https://www.flipkart.com/"));
//        driver.quit();
    }

}

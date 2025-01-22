package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumCaseStudy {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
       
        try {
            driver.get("https://www.ebay.com");

            driver.manage().window().maximize();

            WebElement search = driver.findElement(By.id("gh-ac"));
            search.sendKeys("book");
            search.submit();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".s-item")));

            WebElement book = driver.findElement(By.cssSelector(".s-item .s-item__link"));
            book.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("atcRedesignId_btn")));

            WebElement addToCartButton = driver.findElement(By.id("atcRedesignId_btn"));
            addToCartButton.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cart-preview")));

            WebElement cartIcon = driver.findElement(By.id("gh-cart-i"));
            String cartText = cartIcon.getText();

            if (cartText.contains("1")) {
                System.out.println("Test Passed: Item successfully added to the cart.");
            } else {
                System.out.println("Test Failed: Item was not added to the cart.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

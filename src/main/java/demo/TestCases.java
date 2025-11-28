package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    static ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.google.com");
        String url = "https://leetcode.com/";
        driver.get(url);
        if(driver.getCurrentUrl().contains("leetcode")){
            System.out.println("Test Case 01: PASS - URL contains 'leetcode'");
        }else{
            System.out.println("Test Case 01: FAIL - URL does not contain 'leetcode'");
        }
        System.out.println("end Test case: testCase01");
    }
    public static void testCase02() {

    driver.get("https://leetcode.com/");

    WebElement questionsLink = driver.findElement(By.xpath("//a[@href='/problemset/']"));
    questionsLink.click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlContains("problemset"));

    List<WebElement> questionElements = driver.findElements(
            By.cssSelector("div.relative.flex.h-full.w-full.cursor-pointer.items-center")
    );

    System.out.println("First 5 Questions:");

    // Store titles
    List<String> titles = new ArrayList<>();

    // ⭐ Skip index 0 — start from index 1 to 5
    for (int i = 1; i <= 5; i++) {
        WebElement titleElement = questionElements.get(i)
                .findElement(By.cssSelector("div.ellipsis.line-clamp-1"));

        String title = titleElement.getText();
        titles.add(title);
        System.out.println(title);  // <-- Prints all 5 (including Longest Palindromic Substring)
    }

    // ⭐ Verify first problem (Two Sum) at index 1
    if (titles.get(0).contains("Two Sum")) {
        System.out.println("First question Verified Successfully");
    } else {
        System.out.println("First question verification FAILED");
    }

    // ⭐ Click Two Sum (index 1)
    questionElements.get(1).click();

    wait.until(ExpectedConditions.urlContains("two-sum"));

    if (driver.getCurrentUrl().contains("two-sum")) {
        System.out.println("Test Case 03: PASS - URL contains 'two-sum'");
    } else {
        System.out.println("Test Case 03: FAIL - URL does not contain 'two-sum'");
    }
}



    public static void testCase03(){
        String c_url = driver.getCurrentUrl();
        String url = "https://leetcode.com/problemset/";
        /* if(!c_url.equals(url)){
            driver.get(url);
        }
        List<WebElement> q_urls = driver.findElements(By.xpath("//div[@role='rowgroup']/div/div[2]//descendant::a"));

        for(int i=0;i<q_urls.size();i++){
            String title = q_urls.get(i).getText();
            if(title.contains("Two Sum")){
                q_urls.get(i).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                
            }
        } */
//        driver.switchTo().frame(0);
//        driver.findElement(By.xpath("//div[@class='modal-container']/span[text()='I don't want to subscribe!']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        if(driver.getCurrentUrl().contains("two-sum")){
            System.out.println("Test Case 03: PASS - URL contains 'two-sum'");
            System.out.println("The URL of the problem contains two-sum");
            System.out.println("Verified Successfully...");
        }

    }

    public static void testCase04() throws InterruptedException {

    String expectedUrl = "https://leetcode.com/problems/two-sum/";
    String currentUrl = driver.getCurrentUrl();

    // Ensure correct URL
    if (!currentUrl.contains("two-sum")) {
        driver.get(expectedUrl);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Click Submissions tab
    WebElement submissionsTab = wait.until(
        ExpectedConditions.elementToBeClickable(By.id("submissions_tab"))
    );
    submissionsTab.click();

    // Wait for the login/register message to appear
    WebElement btn = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Register') or contains(text(),'Sign In') or contains(text(),'Login')]"))
    );

    String text = btn.getText();

    // Assessment expects "Register or Login"
    if (text.contains("Register or Log in") ) {
        System.out.println("Test Case 04: PASS - 'Register or Login' is displayed when you click on the submission tab.");
    } else {
        System.out.println("Test Case 04: FAIL - Expected message not found. Found: " + text);
    }
}


}//testt
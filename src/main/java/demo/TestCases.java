package demo;

import java.time.Duration;
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
    public static void testCase02() throws InterruptedException {
        driver.get("https://leetcode.com/");
        WebElement questionsLink = driver.findElement(By.xpath("//a[@href='/problemset/']"));
        questionsLink.click();
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("problemset")) {
            System.out.println("Test Case 02: PASS - URL contains 'problemset'");
        } else {
            System.out.println("Test Case 02: FAIL - URL does not contain 'problemset'");
        }
        //WebElement ele = driver.findElement(By.xpath("//div[@class='inline-block min-w-full']"));

        //scrollToElement(driver,ele);
        Thread.sleep(5000);
        // Retrieve details of the first 5 questions and print them
       List<WebElement> questionElements = driver.findElements(
        By.cssSelector("div.relative.flex.h-full.w-full.cursor-pointer.items-center")
);

        System.out.println("First 5 Questions:");
        for (int i = 1; i <= 5; i++) {
            WebElement questionTitleElement = questionElements.get(i)
        .findElement(By.cssSelector("div.ellipsis.line-clamp-1"));
            String questionTitle = questionTitleElement.getText();
            if(i==1){
                if(questionTitle.contains("Two Sum")){
                    System.out.println("Test Case 03: PASS - URL contains 'two-sum'");

                    System.out.println("First question is Verified Successfully");
                    questionElements.get(i).click();

                }else{
                    System.out.println("Failed to verify first question");
                }
                 break;
            }
            System.out.println(questionTitle);
        }
    }

    public static void testCase03(){
        String c_url = driver.getCurrentUrl();
        String url = "https://leetcode.com/problemset/";
       /*  if(!c_url.equals(url)){
            driver.get(url);
        }
        List<WebElement> q_urls = driver.findElements(By.xpath("//div[@role='rowgroup']/div/div[2]//descendant::a"));

        for(int i=0;i<q_urls.size();i++){
            String title = q_urls.get(i).getText();
            if(title.contains("Two Sum")){
                q_urls.get(i).click();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                
            }
        }*/
//        driver.switchTo().frame(0);
//        driver.findElement(By.xpath("//div[@class='modal-container']/span[text()='I don't want to subscribe!']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        if(driver.getCurrentUrl().contains("two-sum")){
            System.out.println("Test Case 03: PASS - URL contains 'two-sum'");
            System.out.println("The URL of the problem contains two-sum");
            System.out.println("Verified Successfully...");
        }

    }

    public static void testCase04() throws InterruptedException{
        String c_url = driver.getCurrentUrl();
        String url = "https://leetcode.com/problems/two-sum/description/";
        if(!c_url.equals(url)){
            driver.get(url);
        }
        driver.findElement(By.id("submissions_tab")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        Thread.sleep(2000);

            WebElement btn = driver.findElement(By.xpath("//a[normalize-space()='Register or Log in']"));
            if(btn.getText().equals("Register or Log in")){
                System.out.println("The message Register or Sign In is displayed when you click on the submissions tab.");
            }else{
                System.out.println("Register or Sign In button Verfication Failed ...");
            }

    }

}//test
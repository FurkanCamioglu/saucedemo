package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {

    /*
    we use this class by reaching getDriver() method so we wont create any object from this class
    To prevent others creating new object from this class

    we can make constructor private

    This is called SINGLETON PATERN ( preventing creating object )
     */

    private Driver(){ // SINGLETON PATERN

    }

    static WebDriver driver;
    public static WebDriver getDriver(){

        // to get the browser value from configuration.properties file
        String browser = ConfigReader.getProperty("browser"); // chrome


        // we added if statement because, when we ran test methods it may try to open another driver by using this method
        // in each step. if the driver is not noll, this method will not open another driver.
        if (driver==null) {

            switch (browser) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;

                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();


            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));




        }
        return driver;
    }

    // to close opened driver // if a driver is opened, this method will close it. If there is no drider, it wont do anything
    public static void closeDriver(){
        if (driver!=null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            driver.close();
            driver=null;
        }

    }

    public static void quitDriver(){

        if (driver!=null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            driver.quit();
            driver=null;
        }

    }



}

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by colin on 2016/12/8.
 */
public class ChromeTest {
    @Test
    public void chrome(){
        System.setProperty("webdriver.chrome.driver", "/usr/local/opt/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
    }
}

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {

  public static void main(String[] args) throws Exception {
    //
    WebDriver driver = new FirefoxDriver();

    driver
        .get("https://secure.philips.com.cn/myphilips/landing.jsp?country=CN&language=zh&catalogType=CONSUMER");

    Set<Cookie> cookies = driver.manage().getCookies();

    for (Cookie cookie : cookies) {
      System.out.println(cookie);
    }
  }

}

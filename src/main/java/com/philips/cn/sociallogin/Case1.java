package com.philips.cn.sociallogin;

//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Case1 {

  // @Test
  public static void main(String[] args) {
    // fail("Not yet implemented");

    // Create a new instance of the Firefox driver
    // Notice that the remainder of the code relies on the interface,
    // not the implementation.
    WebDriver driver = new FirefoxDriver();

    driver.manage().deleteAllCookies();

    // And now use this to visit Google
    driver
        .get("https://secure.philips.com.cn/myphilips/landing.jsp?country=CN&language=zh&catalogType=CONSUMER");
    // Alternatively the same thing can be done like this
    // driver.navigate().to("http://www.google.com");


    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        WebElement element = d.findElement(By.id("sinaweibo"));

        return element != null;
        // System.out.println(d.getCurrentUrl().toString());
        // return d.getWindowHandles().size() > 0;
        // // return d.getTitle().toLowerCase().startsWith("cheese!");
      }
    });
    // Find the text input element by its name
    WebElement element = driver.findElement(By.id("sinaweibo"));

    element.click();



    // driver.getWindowHandles();
    for (String handle : driver.getWindowHandles()) {
      WebDriver window = driver.switchTo().window(handle);
      String windowUrl = window.getCurrentUrl();
      System.out.println(windowUrl);
      if (windowUrl.contains("https://api.weibo.com/oauth2")) {
        // yes, this is the window;
        WebElement userIdInput = window.findElement(By.id("userId"));
        WebElement passwordInput = window.findElement(By.id("passwd"));

        //

        userIdInput.sendKeys("kktest1@126.com");
        passwordInput.sendKeys("kktest1");

        //
        WebElement loginButton = null;
//            window.findElement(By.className("WB_btn_login formbtn_01"));
        // window.findElement(By.xpath("//*[@id="outer"]/div/div[2]/form/div[1]/div[2]/div/p/a[1]"));
            // window.findElement(By.className("WB_btn_login"));
            // window
            // .findElement(By
            // .cssSelector("#outer > div > div.WB_panel.oauth_main > form > div:nth-child(1) > div.oauth_login_box01.clearfix > div > p > a.WB_btn_login.formbtn_01"));
            //
            // System.out.println(loginButton);

        loginButton = window.findElement(By.className("WB_btn_login"));

        System.out.println(loginButton);

        loginButton.submit();

      }
      // if (newWindowTitle.equalsIgnoreCase(windowTitle))
      // // if it was found break out of the wait
      // return true;
    }

    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver d) {
        System.out.println(d.getCurrentUrl().toString());
        return d.getTitle().toLowerCase().startsWith("cheese!");
      }
    });

    // // Enter something to search for
    // element.sendKeys("Cheese!");
    //
    // // Now submit the form. WebDriver will find the form for us from the
    // element
    // element.submit();

    // Check the title of the page
    // System.out.println("Page title is: " + driver.getTitle());
    //
    // // Google's search is rendered dynamically with JavaScript.
    // // Wait for the page to load, timeout after 10 seconds
    // (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
    // public Boolean apply(WebDriver d) {
    // return d.getTitle().toLowerCase().startsWith("cheese!");
    // }
    // });
    //
    // // Should see: "cheese! - Google Search"
    // System.out.println("Page title is: " + driver.getTitle());
    //
    // // Close the browser
    System.out.println("where to go next?");
    // driver.quit();

  }

}

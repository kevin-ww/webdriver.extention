package com.philips.cn.sociallogin;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhilipsSocicalLogin {

  public static final Logger logger = Logger
      .getLogger(PhilipsSocicalLogin.class);

  public static final String LANDING_URL =
      "https://secure.philips.com.cn/myphilips/landing.jsp?country=CN&language=zh&catalogType=CONSUMER";

  public static final long TIMEOUT_IN_SECONDS = 10;

  public static final String WEIBO_OAUTH_URL_PREFIX =
      "https://api.weibo.com/oauth2";

  private WebDriver driver;

  public PhilipsSocicalLogin() {
    driver = new FirefoxDriver();
    // delete all cookies;
    driver.manage().deleteAllCookies();
  }

  /**
   * blocking call;
   */
  public void openLandingPage() {

    driver.get(LANDING_URL);

  }

  /**
   * blocking call;
   * 
   * @return
   */
  public WebElement getLoadedWeiboButton() {

    (new WebDriverWait(this.driver, TIMEOUT_IN_SECONDS))
        .until(new ExpectedCondition<Boolean>() {

          public Boolean apply(WebDriver d) {

            WebElement element = d.findElement(By.id("sinaweibo"));

            return element != null;

          }
        });

    return this.driver.findElement(By.id("sinaweibo"));
  }

  public void clickWeiboLoginButton(WebElement weiboButton) {
    weiboButton.click();
  }

  /**
   * blocking call;
   */
  public void loadWeiboOAuthPage(String userName, String password) {

    for (String handle : this.driver.getWindowHandles()) {

      WebDriver window = driver.switchTo().window(handle);
      String windowUrl = window.getCurrentUrl();
      logger.info(windowUrl);
      if (windowUrl.contains(WEIBO_OAUTH_URL_PREFIX)) {

        // yes, this is the window;
        WebElement userIdInput = window.findElement(By.id("userId"));
        WebElement passwordInput = window.findElement(By.id("passwd"));

        //

        userIdInput.sendKeys(userName);
        passwordInput.sendKeys(password);

        //
        WebElement loginButton =
            window.findElement(By.className("WB_btn_login"));

        loginButton.submit();

      }
    }

  }

}

package com.znsio.sample.e2e.screen.web.jiomeet;

import com.applitools.eyes.selenium.fluent.Target;
import com.znsio.e2e.tools.Driver;
import com.znsio.e2e.tools.Visual;
import com.znsio.sample.e2e.screen.jiomeet.InAMeetingScreen;
import com.znsio.sample.e2e.screen.jiomeet.LandingScreen;
import com.znsio.sample.e2e.screen.jiomeet.SignInScreen;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class SignInScreenWeb
        extends SignInScreen {
    private final Driver driver;
    private final Visual visually;
    private static final String SCREEN_NAME = SignInScreenWeb.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(SCREEN_NAME);
    private static final String NOT_YET_IMPLEMENTED = " not yet implemented";
    private final By byEnterMeetingId = By.id("meetingId");
    private final By byJoinMeetingButtonId = By.id("headerJoinMeetingButton");
    private final By byEnterPasswordId = By.id("pin");
    private final By byNameId = By.id("name");
    private final By byJoinMeetingButtonXpath = By.xpath("//button[contains(text(), 'Join')]");
    private final By bySignInXpath = By.xpath("//a[text()='Sign In']");
    private final By byUsernameId = By.id("username");
    private final By byProceedButtonId = By.id("proceedButton");
    private final By byPasswordId = By.id("password");
    private final By bySigninButtonId = By.id("signinButton");

    public SignInScreenWeb(Driver driver, Visual visually) {
        this.driver = driver;
        this.visually = visually;
    }

    @Override
    public LandingScreen signIn(String username, String password) {
        driver.waitTillElementIsPresent(bySignInXpath)
              .click();

        visually.checkWindow(SCREEN_NAME, "Start signin");
        WebElement usernameElement = driver.waitTillElementIsPresent(byUsernameId);
        usernameElement.clear();
        usernameElement.sendKeys(username);

        driver.waitTillElementIsPresent(byProceedButtonId)
              .click();

        WebElement passwordElement = driver.waitTillElementIsPresent(byPasswordId);
        passwordElement.clear();
        passwordElement.sendKeys(password);

        visually.checkWindow(SCREEN_NAME, "Credentials entered");

        driver.waitTillElementIsPresent(bySigninButtonId)
              .click();

        return LandingScreen.get();
    }

    @Override
    public InAMeetingScreen joinAMeeting(String meetingId, String meetingPassword, String currentUserPersona) {
        WebElement joinMeetingElement = driver.waitTillElementIsPresent(byJoinMeetingButtonId);
        visually.checkWindow(SCREEN_NAME, "Landing screen");
        joinMeetingElement.click();

        WebElement enterMeetingIdElement = driver.waitTillElementIsPresent(byEnterMeetingId);
        enterMeetingIdElement.clear();
        enterMeetingIdElement.sendKeys(meetingId);

        WebElement enterPasswordElement = driver.waitTillElementIsPresent(byEnterPasswordId);
        enterPasswordElement.clear();
        enterPasswordElement.sendKeys(meetingPassword);

        WebElement enterNameElement = driver.waitTillElementIsPresent(byNameId);
        enterNameElement.clear();
        enterNameElement.sendKeys(currentUserPersona);

        visually.check(SCREEN_NAME, "After entering meeting details", Target.window()
                                                                            .strict()
                                                                            .layout(byEnterPasswordId)
                                                                            .layout(byNameId));

        visually.takeScreenshot(SCREEN_NAME, "Before clicking on Join button");
        ((JavascriptExecutor) driver.getInnerDriver()).executeScript("arguments[0].click()", driver.waitForClickabilityOf(byJoinMeetingButtonXpath));
        return this.waitForInAMeetingScreenToLoad();
    }

    private InAMeetingScreen waitForInAMeetingScreenToLoad() {
        InAMeetingScreen inAMeetingScreen = InAMeetingScreen.get();
        inAMeetingScreen.getMicLabelText();
        return inAMeetingScreen;
    }
}

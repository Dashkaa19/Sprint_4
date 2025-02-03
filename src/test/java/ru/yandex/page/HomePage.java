package ru.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class HomePage extends BasePage {
    private final By homeHeader = By.cssSelector("[class^=Home_Header]");
    private final By headerOrderButton = By.cssSelector("[class*=Header_Nav] [class*=Button_Button]");
    private final By homeOrderButton = By.cssSelector("[class*=Home_FinishButton] button");
    private final By cookieButton = By.cssSelector("[class*=App_CookieButton]");
    private final By questions = By.cssSelector("[id*=accordion__heading]");
    private final By notHiddenAnswer = By.cssSelector("[id*=accordion__panel]:not([hidden])");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage clickOnQuestionByTitle(String questionTitle) {
        List<WebElement> questions = driver.findElements(this.questions);
        for (int i = 0; i < questions.size(); i++) {
            String question = questions.get(i).getText();
            if (question.contains(questionTitle)) {
                questions.get(i).click();
                return this;
            }
        }
        return null;
    }

    public void checkAnswer(String expectedAnswer) {
        String answer = driver.findElement(this.notHiddenAnswer).getText();
        assertEquals(expectedAnswer, answer);
    }


    public HomePage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }


    public HomePage scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(homeOrderButton));
        return this;
    }

    public HomePage clickUpOrderButton() {
        driver.findElement(headerOrderButton).click();
        return this;
    }

    public HomePage clickCookieButton() {
        driver.findElement(cookieButton).click();
        return this;
    }

    public HomePage clickDownOrderButton() {
        driver.findElement(homeOrderButton).click();
        return this;
    }

    public HomePage scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questions));
        return this;
    }

    public void clickCreateOrderButton(String button) {
        if (button.equals("Header")) {
            clickUpOrderButton();
        } else {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }
}

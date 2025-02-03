package ru.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WhoScooterForPage extends BasePage {
    private final By orderHeader = By.cssSelector("[class*=Order_Header]");
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By station = By.cssSelector(".select-search input");
    private final By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.cssSelector("[class*=Order_NextButton] button");
    private final String nameStation = ".//button[@value='%s']";

    public WhoScooterForPage(WebDriver driver) {

        this.driver = driver;
    }


    public WhoScooterForPage waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    public WhoScooterForPage inputName(String newName) {
        driver.findElement(name).sendKeys(newName);
        return this;
    }

    public WhoScooterForPage inputSurname(String newSurname) {
        driver.findElement(surname).sendKeys(newSurname);
        return this;
    }

    public WhoScooterForPage inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }

    public WhoScooterForPage changeStation(int stateNumber) {
        driver.findElement(station).click();
        By newStation = By.xpath(String.format(nameStation, stateNumber));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newStation));
        driver.findElement(newStation).click();
        return this;
    }

    public WhoScooterForPage inputTelephone(String newTelephone) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(phoneNumber));
        driver.findElement(phoneNumber).sendKeys(newTelephone);
        return this;
    }

    public void clickNextButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(buttonNext));
        driver.findElement(buttonNext).click();
    }

}

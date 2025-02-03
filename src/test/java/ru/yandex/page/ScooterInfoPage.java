package ru.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ScooterInfoPage extends BasePage {
    private final By header = By.cssSelector("[class*=Order_Header]");
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By durationRent = By.xpath(".//span[@class='Dropdown-arrow']");
    private final By colourBlack = By.id("black");
    private final By colourGrey = By.id("grey");
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public ScooterInfoPage(WebDriver driver) {

        this.driver = driver;
    }


    public ScooterInfoPage waitAboutRentHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(header).getText() != null
                && !driver.findElement(header).getText().isEmpty()
        ));
        return this;
    }

    public ScooterInfoPage inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }

    public ScooterInfoPage inputDuration(String newDuration) {
        driver.findElement(durationRent).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        return this;
    }

    public ScooterInfoPage changeColour(String colour) {
        if (colour.equals("black")) {
            driver.findElement(colourBlack).click();
        } else {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    public ScooterInfoPage inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    public void clickButtonCreateOrder() {

        driver.findElement(createOrderButton).click();
    }

}


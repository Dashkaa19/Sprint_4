package ru.yandex.test;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.constant.Duration;
import ru.yandex.page.HomePage;
import ru.yandex.page.PopUpWindow;
import ru.yandex.page.ScooterInfoPage;
import ru.yandex.page.WhoScooterForPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Random;


@RunWith(Parameterized.class)
public class OrderCreateTest extends BaseTest {
    private String button;
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    public OrderCreateTest(String button) {
        this.button = button;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"Header"},
                {"Bottom"},
        });
    }


    @Test
    public void testCreateOrderWithUpButton() {
        Faker faker = new Faker(new Locale("ru"));

        String name = faker.name().firstName();
        String surname = faker.name().lastName();

        String city = faker.address().city();
        String streetName = faker.address().streetName();
        String buildingNumber = faker.address().buildingNumber();


        String address = String.format("г. %s, ул. %s, д. %s",
                city, streetName, buildingNumber);

        int stationId = faker.number().numberBetween(1, 238);

        String phoneNumber = "7" + faker.number().digits(10);

        LocalDate tomorrow = LocalDate.now().plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = tomorrow.format(formatter);

        String duration = Duration.getRandomDuration();

        String color = new Random().nextBoolean() ? "black" : "grey";

        String comment = faker.lorem().sentence();

        driver.get(url);
        new HomePage(driver)
                .waitForLoadHomePage()
                .clickCookieButton()
                .clickCreateOrderButton(button);

        new WhoScooterForPage(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputSurname(surname)
                .inputAddress(address)
                .changeStation(stationId)
                .inputTelephone(phoneNumber)
                .clickNextButton();

        new ScooterInfoPage(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(color)
                .inputComment(comment)
                .clickButtonCreateOrder();

        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes().checkOrderTitle("Заказ оформлен");

    }
}

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

@RunWith(Parameterized.class)
public class OrderCreateTest extends BaseTest {
    private final String button;
    private final String name;
    private final String surname;
    private final String address;
    private final int stationId;
    private final String phoneNumber;
    private final String date;
    private final String duration;
    private final String color;
    private final String comment;
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    public OrderCreateTest(String button, String name, String surname, String address, int stationId,
                           String phoneNumber, String date, String duration, String color, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationId = stationId;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        Faker faker = new Faker(new Locale("ru"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate tomorrow = LocalDate.now().plusDays(1);


        return Arrays.asList(new Object[][]{
                {"Header", faker.name().firstName(), faker.name().lastName(), String.format("г. %s, ул. %s, д. %s", faker.address().city(), faker.address().streetName(), faker.address().buildingNumber()),
                        faker.number().numberBetween(1, 238), "7" + faker.number().digits(10),
                        tomorrow.format(formatter), Duration.getRandomDuration(), "black", faker.lorem().sentence()},
                {"Header", faker.name().firstName(), faker.name().lastName(), String.format("г. %s, ул. %s, д. %s", faker.address().city(), faker.address().streetName(), faker.address().buildingNumber()),
                        faker.number().numberBetween(1, 238), "7" + faker.number().digits(10),
                        tomorrow.plusDays(2).format(formatter), Duration.getRandomDuration(), "grey", faker.lorem().sentence()},
                {"Bottom", faker.name().firstName(), faker.name().lastName(), String.format("г. %s, ул. %s, д. %s", faker.address().city(), faker.address().streetName(), faker.address().buildingNumber()),
                        faker.number().numberBetween(1, 238), "7" + faker.number().digits(10),
                        tomorrow.plusDays(3).format(formatter), Duration.getRandomDuration(), "black", faker.lorem().sentence()},
                {"Bottom", faker.name().firstName(), faker.name().lastName(), String.format("г. %s, ул. %s, д. %s", faker.address().city(), faker.address().streetName(), faker.address().buildingNumber()),
                        faker.number().numberBetween(1, 238), "7" + faker.number().digits(10),
                        tomorrow.plusDays(4).format(formatter), Duration.getRandomDuration(), "grey", faker.lorem().sentence()}
        });
    }

    @Test
    public void testCreateOrder() {
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

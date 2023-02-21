package demoqa.form;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import demoqa.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import demoqa.pages.RegistrationPage;
import java.util.Map;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = System.getProperty("browser", "chrome");;
        Configuration.browserVersion = System.getProperty("browserVersion", "100");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.remote = System.getProperty("remote", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    RegistrationPage steps = new RegistrationPage();
    Faker faker = new Faker();
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = getRandomValue("Male", "Female", "Other"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            day = String.format("%02d", faker.number().numberBetween(1, 28)),
            month = getRandomValue("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"),
            year = String.valueOf(faker.number().numberBetween(1900, 2022)),
            subjects = getRandomValue("Math", "Chemistry", "Physics", "Computer Science", "English", "History"),
            hobbies = getRandomValue("Sports", "Reading", "Music"),
            currentAddress = faker.address().fullAddress(),
            state = getRandomValue("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = getCity(),
            dayOfBirth = day + " " + month + "," + year,
            fileName = "avatar.jpg";

    private String getRandomValue(String... initialValues) {

        return faker.options().option(initialValues);
    }

    private String getCity() {
        String cityItem = null;

        if (state.equals("NCR")) {
            cityItem = getRandomValue("Delhi", "Gurgaon", "Noida");
        }

        if (state.equals("Uttar Pradesh")) {
            cityItem = getRandomValue("Agra", "Lucknow", "Merrut");
        }

        if (state.equals("Haryana")) {
            cityItem = getRandomValue("Karnal", "Panipat");
        }

        if (state.equals("Rajasthan")) {
            cityItem = getRandomValue("Jaipur", "Jaiselmer");
        }

        return cityItem;
    }
}

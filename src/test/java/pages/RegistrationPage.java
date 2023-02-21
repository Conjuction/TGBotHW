package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.RegistrationResultModal;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    RegistrationResultModal registrationResultModal = new RegistrationResultModal();
    private final String TITLETEXT = "Student Registration Form";
    private SelenideElement firstNameInput = $("#firstName");
    @Step("открыть главную страницу")
    public RegistrationPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLETEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }
    @Step("Ввести имя")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }
    @Step("Ввести фамилию")
    public RegistrationPage setLastName(String value) {
        $("#lastName").setValue(value);

        return this;
    }
    @Step("Ввести email")
    public RegistrationPage setEmail(String value) {
        $("#userEmail").setValue(value);

        return this;
    }
    @Step("Выбрать пол")
    public RegistrationPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }
    @Step("Ввести номер телефона")
    public RegistrationPage setPhone(String value) {
        $("#userNumber").setValue(value);

        return this;
    }
    @Step("Выбрать дату рождения")
    public RegistrationPage setBirthDate(String month, String year, String day) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(month, year, day);

        return this;
    }
    @Step("Проверить наличие 'Thanks for submitting the form'")
    public RegistrationPage verifyResultsModalAppears() {
        registrationResultModal.verifyModalAppears();

        return this;
    }
    @Step("Проверить результат")
    public RegistrationPage verifyResults(String key, String value) {
        registrationResultModal.verifyResult(key, value);

        return this;
    }
    @Step("Выбрать предмет")
    public RegistrationPage setSubjects(String value) {
        $("#subjectsInput").setValue(value).pressEnter();

        return this;
    }
    @Step("Выбрать хобби")
    public RegistrationPage setHobbies(String value) {
        $("#hobbiesWrapper").$(byText(value)).click();

        return this;
    }
    @Step("Выбрать файл")
    public RegistrationPage setFile(String fileName) {
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + fileName));

        return this;
    }
    @Step("Ввести адрес")
    public RegistrationPage setAddress(String value) {
        $("#currentAddress").setValue(value);

        return this;
    }
    @Step("Выбрать штат")
    public RegistrationPage setState(String value) {
        $("#state").click();
        $("#stateCity-wrapper").find(byText(value)).click();

        return this;
    }
    @Step("Выбрать город")
    public RegistrationPage setCity(String value) {
        $("#city").click();
        $("#stateCity-wrapper").find(byText(value)).click();

        return this;
    }
    @Step("Нажать submit")
    public RegistrationPage submit() {
        $("#submit").click();

        return this;
    }
}

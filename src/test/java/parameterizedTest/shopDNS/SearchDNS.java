package parameterizedTest.shopDNS;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parameterizedTest.helpers.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchDNS extends TestBase {
    @BeforeAll
    static void simpleTest() {
        open("https://www.dns-shop.kz/");
    }

    @ValueSource(
            strings = {"холодильник", "Ноутбук", "Телевизор"}
    )
    @Owner("Sukhinin Dmitrii")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ParameterizedTest")
    @ParameterizedTest(name = "Наличие выдачи по ключу:{0}")
    void searchEquipment(String equipment) {
        $(".presearch__input").setValue(equipment).pressEnter();
        $(".products-list__group-title").shouldHave(text(equipment));
        $(".presearch__input").clear();
    }
}

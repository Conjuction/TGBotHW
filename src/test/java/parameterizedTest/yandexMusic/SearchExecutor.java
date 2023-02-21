package parameterizedTest.yandexMusic;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import parameterizedTest.helpers.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchExecutor extends TestBase {
    @BeforeAll
    static void simpleTest() {
        open("https://music.yandex.kz/home");
        $(".pay-promo-close-btn").click();
        $(".d-search__button__container").click();
    }

    @CsvSource({
            "нойз, Noize MC",
            "улица, Улица Восток",
            "анаконда, Anacondaz"
    })
    @Owner("Sukhinin Dmitrii")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ParameterizedTest")
    @ParameterizedTest(name = "В выпадающем списке должен быть исполнитель {1} при вводе {0}")
    @Tags({@Tag("Critical"), @Tag("Search_TEST")})
    void findMusician(
            String str,
            String Musician
    ) {

        $(".d-input__field").setValue(str);
        $(".d-suggest__item-artist .d-suggest-item__title-main").shouldHave(text(Musician));
    }
}

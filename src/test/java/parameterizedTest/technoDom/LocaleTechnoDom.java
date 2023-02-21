package parameterizedTest.technoDom;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import parameterizedTest.helpers.TestBase;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
public class LocaleTechnoDom extends TestBase {
    @BeforeAll
    static void simpleTest() {
        open("https://www.technodom.kz/");
        $(".VerifyCityModal_block__actions__MtNhQ").$(byText("Нет")).click();
        $("#CitiesModalSearch").setValue("Акт");
        $(".CitiesModal_block__list__8D_Jr ").$("[href='/aktobe']").click();
    }


    static Stream<Arguments> technoDomButtons() {
        return Stream.of(
                Arguments.of(Locale.KZ, List.of("Таңдаулы", "Салыстыру", "Қоржын", "Кіру")),
                Arguments.of(Locale.RU, List.of("Избранное", "Сравнить", "Корзина", "Вход"))
        );
    }
    @Owner("Sukhinin Dmitrii")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("ParameterizedTest")
    @MethodSource
    @ParameterizedTest(name = "Для локализации {0} отображаются кнопки {1}")
     void technoDomButtons(
            Locale locale,
            List<String> buttons
    ) {

        $(".LocalizationSelector_block__QNS8p").click();
        $(".LocalizationSelector_block__QNS8p").$(byText(locale.getDesc())).click();
        $$(".Desktop_block__links-wrapper__NjrQv a").filter(visible)
                .shouldHave(texts(buttons));

    }
}

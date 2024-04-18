import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppOrderTest {

    @BeforeEach
        void SetUpAll() {
        open("http://localhost:9999");
    }
    @Test
        void shouldSendCorrectForm() {
        $("[data-test-id=name] input"). setValue("Иван Иванов-Сидоров");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        }

    @Test
    void shouldNotSendInCorrectNameLatin() {
        $("[data-test-id=name] input"). setValue("Ivan Ivanov");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendInCorrectNameSymbol() {
        $("[data-test-id=name] input"). setValue("Иван Иванов=Сидоров");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendInCorrectNameVoid() {
        $("[data-test-id=name] input"). setValue("");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendInCorrectNameSpace() {
        $("[data-test-id=name] input"). setValue(" ");
        $("[data-test-id=phone] input"). setValue("+79252330000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    //@Test
    //void shouldNotSendInCorrectNameHyphen() {
      //  $("[data-test-id=name] input"). setValue("-");
        //$("[data-test-id=phone] input"). setValue("+79252330000");
        //$("[data-test-id=agreement]").click();
        //$("[type=button]").click();
        //$("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    //}

    @Test
    void shouldNotSendInCorrectPhoneWithoutPlus() {
        $("[data-test-id=name] input"). setValue("Иван Иванов-Сидоров");
        $("[data-test-id=phone] input"). setValue("79252330000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendInCorrectPhoneWithSymbols() {
        $("[data-test-id=name] input"). setValue("Иван Иванов-Сидоров");
        $("[data-test-id=phone] input"). setValue("+7(925)233 00 00");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendInCorrectPhoneVoid() {
        $("[data-test-id=name] input"). setValue("Иван Иванов-Сидоров");
        $("[data-test-id=phone] input"). setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendWithoutAgreement() {
        $("[data-test-id=name] input").setValue("Иван Иванов-Сидоров");
        $("[data-test-id=phone] input").setValue("+79252330000");
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}

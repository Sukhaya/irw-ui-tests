package pages;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static pages.AuthPopup.ROOT;

public class LoginPromise {

    /** проверка, что авторизация произошла **/
    public MainPage andSuccessLogin() {
        $(ROOT).shouldBe(disappear.because("Не исчез попап авторизации после попытки логина"));
        return new MainPage();
    }

    /** проверка на сообщение об ошибке **/
    public AuthPopup andExpectError() {
        return new AuthPopup();
    }
}

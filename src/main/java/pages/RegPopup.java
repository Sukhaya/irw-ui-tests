package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class RegPopup {
    private static final By EMAIL_FIELD = By.xpath(".//*[contains(@id, 'emailLogin')]");
    private static final By PASSWORD_FIELLD = By.xpath(".//*[contains(@id, 'passwordLogin')]");
    private static final By REPEAT_PASSWORD = By.xpath(".//*[contains(@id, 'passwordCheck')]");
    private static final By ROOT = By.xpath(".//*[contains(@class, 'styles_auth_body')]");
    private static final By REG_BUTTON = By.xpath(".//*[contains(@class, 'styles_button')]");
    private static final By LOGIN = By.xpath(".//*[contains(@class, 'styles_link_desktop')]");

    public void fillEmail(String email) {
        $(EMAIL_FIELD).shouldBe(visible.because("Нет поля ввода емейла"));
    }

    public void fillPassword(String password) {
        $(PASSWORD_FIELLD).shouldBe(visible.because("Нет поля ввода пароля"));
    }

    public void fillSamePassword(String samePassword) {
        $(REPEAT_PASSWORD).should(appear.because("Не появилось поля подтверждения пароля"));
    }

    public void setRegistration() {
        $(REG_BUTTON).shouldBe(visible.because("Нет кнопки запрегестрироваться"));
    }

    public void setLogin() {
        $(LOGIN).shouldBe(visible.because("Нет кнопки Войти"));
    }




    public ConfirmEmailPopup doRegistration() {
        $(REG_BUTTON).click();
        $(ROOT).shouldBe(disappear.because("Не пропал попап регистрации"));
        return new ConfirmEmailPopup();
    }

    public AuthPopup doLogin() {
        $(LOGIN).click();
        $(ROOT).shouldBe(disappear.because("Не пропал попап регистрации"));
        return new AuthPopup();
    }


}

package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class AuthPopup extends BaseComponent {
    private static final By EMAIL_FIELD = By.xpath(".//*[contains(@id, 'email')]");
    private static final By PASSWORD_FIELD = By.xpath(".//*[contains(@id, 'passwordAuth')]");
    protected static final By ROOT = By.xpath("//*[contains(@class, 'styles_auth_body')]");
    private static final By LOGIN_BUTTON = By.xpath(".//*[contains(@class, 'styles_button__')]");
    private static final By RESET_PASSWORD = By.xpath(".//*[text()='Выслать пароль']");
    private static final By ERROR_MESSAGE = By.xpath(".//*[contains (@class, 'error_message') and not(contains(@class,'styles_none'))]");

    @Override
    public void checkPage() {
        $(ROOT).shouldBe(visible.because("Не открылся попап"));
        $(EMAIL_FIELD).shouldBe(visible.because("Нет поля ввода логина"));
        $(PASSWORD_FIELD).shouldBe(visible.because("Нет поля ввода пароля"));
    }

    public void fillEmail(String email) {
        $(EMAIL_FIELD).shouldBe(visible.because("Нет поля ввода емейла")).setValue(email);
    }

    public void fillPassword(String password) {
        $(PASSWORD_FIELD).shouldBe(visible.because("Нет поля ввода пароля")).setValue(password);
    }

    public void changePassword() {
        $(RESET_PASSWORD).shouldBe(visible.because("Не появилась кнопка восстановления пароля"));
    }


    public String getErrorText() {
        return $(ERROR_MESSAGE).shouldBe(visible.because("Нет сообщения об ошибке")).getText();
    }


    public LoginPromise doLogin() {
        $(ROOT).$(LOGIN_BUTTON).click();
        return new LoginPromise();
    }

    public ConfirmEmailPopup doRegistration() {
        $(LOGIN_BUTTON).click();
        $(ROOT).shouldBe(disappear.because("Не пропал попап регистрации"));
        return new ConfirmEmailPopup();
    }

}

package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BaseComponent {
    private static final By LOGIN_BUTTON = By.xpath(".//*[contains(@class, 'styles_auth_login_btn')]//span[text()='Войти']");
    private static final By LOGOUT_BUTTON = By.xpath(".//*[contains(@class, 'styles_auth_login_btn')]//span[text()='Выйти']");
    private static final String TITLE_ATTR = "title";
    private static final By VIDEO = By.xpath(".//*[contains(@class,'video-react')]");
    private static final By TELEGRAM_LINK = By.xpath(".//*[contains(@class,'tg_banner_main')]");
    private static final By COMPANY_CARDS = By.xpath(".//*[contains(@class, 'companies')]");

    @Override
    public void checkPage() {
        /** проверка,что мы дейтвительно на нужной странице засчет видимости основных элементов **/
        $(VIDEO).shouldBe(visible.because("Нет видеоролика"));
        $(TELEGRAM_LINK).shouldBe(visible.because("Нет окна с подпиской на Telegram"));
        $(COMPANY_CARDS).shouldBe(visible.because("Нет блоков ТОП компаний"));
    }

    public FastFiltersBlock getFastFilterBlock() {
        return new FastFiltersBlock();
    }

    public AuthPopup openAuth() {
        $(LOGIN_BUTTON).shouldBe(visible.because("Нет кнопки Войти")).click();
        return new AuthPopup();
    }

    public SearchPage getTopCompanyVacancies() {
        $(COMPANY_CARDS).click();
        return new SearchPage();
    }

    public boolean loginButtonIsVisible() {
        return $(LOGIN_BUTTON).is(visible);
    }

    public boolean logoutButtonIsVisible() {
        return $(LOGOUT_BUTTON).is(visible);
    }

    /** проверяем, что кнопка Выйти имеет атрибут почты, с которой залогинились,
     * т.е. что мы залогинены под нужным пользователем **/
    public String getUserEmail() {
        return $(LOGOUT_BUTTON).parent().shouldBe(and("Нет кнопки логаута", visible, attribute(TITLE_ATTR))).getAttribute(TITLE_ATTR);
    }

}

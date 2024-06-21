package login;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AuthPopup;
import pages.MainPage;


import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private static final String POSITIVE_EMAIL = "irw.qa.maria.suhinina+87@gmail.com";
    private static final String POSITIVE_PASSWORD = "123456";
    private static final String EMPTY_EMAIL = "";
    private static final String EMPTY_PASSWORD = "";
    private static final String NEGATIVE_PASSWORD = "4689ggjbg";
    private static final String SHORT_PASSWORD = "1234";
    private static final String EXPECTED_ERROR_MESSAGE_EMPTY_FIELDS = "Поле не может быть пустым";
    private static final String EXPECTED_ERROR_MESSAGE_PASSWORD = "Пароль указан не верно. Выслать вам новый пароль на почту?";
    private static final String EXPECTED_ERROR_MESSAGE_SHORT_PASSWORD = "В поле должно быть не меньше 6 символов";


    @BeforeEach
    public void openHomePage() {
        Selenide.open("https://irecommendwork.com/");
    }

    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void loginTest() {
        AuthPopup authPopup = new MainPage().openAuth();
        authPopup.fillEmail(POSITIVE_EMAIL);
        authPopup.fillPassword(POSITIVE_PASSWORD);

        MainPage mainPageAfterAuth = authPopup.doLogin()
                .andSuccessLogin();

//        sleep(3000);

        assertTrue(mainPageAfterAuth.logoutButtonIsVisible(), "Не залогинились");
        assertFalse(mainPageAfterAuth.loginButtonIsVisible(), "Кнопка авторизации не исчезла");
        assertEquals(POSITIVE_EMAIL, mainPageAfterAuth.getUserEmail(), "Авторизовавшийся юзер не соответствует ожидаемому");
    }


    @Test
    public void negativeLoginTestWrongPassword() {
        AuthPopup authPopup = new MainPage().openAuth();
        authPopup.fillEmail(POSITIVE_EMAIL);
        authPopup.fillPassword(NEGATIVE_PASSWORD);

        AuthPopup authPopupAfterWrongPassword = authPopup.doLogin()
                .andExpectError();

        assertEquals(EXPECTED_ERROR_MESSAGE_PASSWORD, authPopupAfterWrongPassword.getErrorText(), "Нет сообщения об ошибке");
    }

    @Test
    public void negativeLoginTestEmptyFields() {
        AuthPopup authPopup = new MainPage().openAuth();
        authPopup.fillEmail(EMPTY_EMAIL);
        authPopup.fillPassword(EMPTY_PASSWORD);

        AuthPopup authPopupAfterEmptyFields = authPopup.doLogin()
                .andExpectError();

        assertEquals(EXPECTED_ERROR_MESSAGE_EMPTY_FIELDS, authPopupAfterEmptyFields.getErrorText(), "Нет сообщения об ошибке");
    }

    @Test
    public void negativeLoginShortPassword() {
        AuthPopup authPopup = new MainPage().openAuth();
        authPopup.fillEmail(POSITIVE_EMAIL);
        authPopup.fillPassword(SHORT_PASSWORD);

        AuthPopup authPopupAfterShortPassword = authPopup.doLogin()
                .andExpectError();

        assertEquals(EXPECTED_ERROR_MESSAGE_SHORT_PASSWORD, authPopupAfterShortPassword.getErrorText(), "Нет сообщения об ошибке");
    }

    /*
    @Test
    public void negativeLoginTestWrongEmail() {
        AuthPopup authPopup = new MainPage().openAuth();
        authPopup.fillEmail(NEGATIVE_EMAIL);
        authPopup.fillPassword(POSITIVE_PASSWORD);


        AuthPopup authPopupAfterWrongLogin = authPopup.doLogin()
                .andExpectError();

        assertEquals(EXPECTED_ERROR_MESSAGE_EMAIL,  authPopupAfterWrongLogin.getErrorText(), "нет сообщения об ошибке");
    }*/

}

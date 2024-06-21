package login;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.SearchPage;

import static com.codeborne.selenide.Selenide.sleep;

public class TopCompaniesTest {

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
    public void checkTopCompaniesTest() {
        SearchPage searchPage = new MainPage().getTopCompanyVacancies();

        sleep(3000);

    }
}

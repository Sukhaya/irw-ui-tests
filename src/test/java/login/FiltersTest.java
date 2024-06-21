package login;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.SearchPage;
import pages.VacancyCardWrapper;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class FiltersTest {
    private static final String CATEGORY = "Работа на удаленке";
    private static final String SPHERE = "Работа в маркетинге";

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
    public void checkFiltersTest() {
        SearchPage searchPage = new MainPage().getFastFilterBlock()
                .choooseCategory(CATEGORY);

        sleep(3000);
        List<VacancyCardWrapper> searchResults = searchPage.getSearchResultsBlock()
                .getSearchResults();

        assertThat(searchResults).describedAs("Нет совпадений вакансий на удаленке")
                .allSatisfy(result -> assertThat(result.getLocation()).contains("Удаленно"));
    }

    @Test
    public void checkSphereFiltersTest() {
        SearchPage searchPage = new MainPage().getFastFilterBlock()
                .choooseCategory(SPHERE);

        sleep(3000);
        List<VacancyCardWrapper> searchResults = searchPage.getSearchResultsBlock()
                .getSearchResults();

        assertThat(searchResults).describedAs("Нет совпадений вакансий в маркетинге")
                .allSatisfy(result -> {
                    assertThat(result.getProfessionSubSphere())
                            .anySatisfy(subSphere -> assertThat(subSphere).containsIgnoringCase("маркетинг"));
                });

    }


}

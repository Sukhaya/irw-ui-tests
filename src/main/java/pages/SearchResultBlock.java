package pages;

import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultBlock {
    private static final By VACANCY_CARD = By.xpath(".//*[contains(@href, '/vacancy/')]");


    public List<VacancyCardWrapper> getSearchResults() {
        /** обертка над вебэлементом, через него обращаемся дальше **/
        $(VACANCY_CARD).shouldBe(exist.because("Нет хотя бы одной карточки вакансий"));
        return $$(VACANCY_CARD).stream()
                .map(item -> new VacancyCardWrapper(item))
                .collect(Collectors.toList());
    }

}


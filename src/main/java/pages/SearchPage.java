package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage extends BaseComponent {


    private static final By PAGE_HEADER = By.xpath(".//*[contains(@class,'greetings')]");
    private static final By SEARCH_FIELD = By.xpath(".//*[contains(@id,'searchForm')]");
    private static final By ROOT = By.xpath(".//*[contains(@class,'scroll-component ')]");
    private static final By VACANCY_CARD = By.xpath(".//*[contains(@class,'styles_card')]");
    private static final By PROFESSION_SUB_SPHERE = By.xpath(".//*[contains(@class, 'badge')]");
    private static final By COMPANY_CARDS = By.xpath(".//*[contains(@class, 'companies')]");
    //company_name


    @Override
    public void checkPage() {
        /** проверка,что мы дейтвительно на нужной странице засчет видимости основных элементов **/
        $(COMPANY_CARDS).shouldNotBe(visible.because("Виден блок компаний"));
        $(PAGE_HEADER).shouldBe(visible.because("Нет заголовка страницы c результатами поиска"));
    }

    public SearchPage getHeader() {
        $(PAGE_HEADER).shouldHave(text("Работа в компании: "));
        return null;
    }

    public SearchPage getSearchField() {
        $(SEARCH_FIELD).shouldHave(text("Работа в компании: "));
        return null;
    }

    public SearchResultBlock getSearchResultsBlock() {
        return new SearchResultBlock();
    }


    public SearchPage getProfessionSubSphere() {
        $(ROOT).$(VACANCY_CARD).$(PROFESSION_SUB_SPHERE).getText();
        return null;
    }

    //getCompanyName



}

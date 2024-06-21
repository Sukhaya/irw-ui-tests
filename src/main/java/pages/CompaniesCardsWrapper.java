package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class CompaniesCardsWrapper {
    private static final By ROOT = By.xpath(".//*[contains(@class, 'styles_companies')]");
    private static final By COMPANY_CARD = By.xpath(".//*[contains(@href, '/search/')]");


    private SelenideElement companyCard;

    CompaniesCardsWrapper(SelenideElement companyCard) {
        this.companyCard = companyCard;
    }


    public String getCompanyName() {
        return companyCard.parent().getAttribute("href").split("'")[1];
    }


}

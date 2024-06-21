package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;

public class VacancyCardWrapper {

    protected static final By LOCATION = By.xpath(".//*[contains(@class, 'location_text__')]");
    private static final By PROFESSION_SUB_SPHERE = By.xpath(".//*[contains(@class, 'badge')]");
    private static final By COMPANY_NAME = By.xpath(".//*[contains(@class, 'company_text')]");

    /**
     * обертка над вебэлементом, через него обращаемся дальше
     **/
    private SelenideElement vacancyCard;

    VacancyCardWrapper(SelenideElement vacancyCard) {
        this.vacancyCard = vacancyCard;
    }

    public String getText() {
        return vacancyCard.innerText();
    }

    public String getLocation() {
        return vacancyCard.$(LOCATION).shouldBe(visible.because("Не отображается город")).getText();
    }

    public String getCompanyName() {
        return vacancyCard.$(COMPANY_NAME).shouldBe(visible.because("Не отображается наименование компании")).getText();
    }

    public List<String> getProfessionSubSphere() {
        return vacancyCard.$$(PROFESSION_SUB_SPHERE).shouldBe(sizeGreaterThan(0).because("")).texts();
    }
}

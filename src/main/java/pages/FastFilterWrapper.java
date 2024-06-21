package pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;

public class FastFilterWrapper {
    private static final By LINK = By.xpath(".//a");

    /** обертка над вебэлементом, через него обращаемся дальше **/
    public SelenideElement fastFilter;

    FastFilterWrapper(SelenideElement fastFilter) {
        this.fastFilter = fastFilter;
    }

    public String getText() {
        return fastFilter.innerText();
    }

    public MainPage openCategory() {
        fastFilter.$(LINK).click(ClickOptions.usingJavaScript());
        return new MainPage();
    }

    public boolean isVisible() {
        return fastFilter.$(LINK).is(visible);
    }
}

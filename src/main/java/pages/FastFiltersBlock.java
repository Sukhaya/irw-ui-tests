package pages;

import org.openqa.selenium.By;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FastFiltersBlock extends BaseComponent {
    private static final By ROOT = By.xpath(".//*[@class='swiper']");
    private static final By FAST_FILTER_ITEM = By.xpath(".//*[contains(@class, 'swiper-slide')]");
    private static final By SLIDER_ARROW_NEXT = By.xpath(".//*[contains(@class, 'swiper-button-next')]");
    private static final By ROOT_FOR_SLIDER = By.xpath(".//*[contains(@class, 'styles_slider')]");
    private static final By REMOTE_JOB_FILTER = By.xpath(".//*[contains(text() = 'Работа на удаленке')]");
    private static final int MAX_SLIDES_COUNT = 4;

    @Override
    public void checkPage() {
        $(ROOT).shouldBe(visible.because("Нет корневого элемента блока фильтров"));
    }

    /** запомнить как есть метод wrapper **/

    public List<FastFilterWrapper> getFastFilters() {
        return $(ROOT).$$(FAST_FILTER_ITEM).stream()
                .map(item -> new FastFilterWrapper(item))
                .collect(Collectors.toList());
    }

    public FastFilterWrapper getFastFilter(String name) {
        Optional<FastFilterWrapper> optionalWrapper = getFastFilters().stream()
                .filter(fastFilter -> fastFilter.getText().contains(name))
                .findFirst();
        assertTrue(optionalWrapper.isPresent(), "Не нашли ожидаемого быстрого фильтра");
        return optionalWrapper.get();
    }

    /** выбираем категорию Работа на удаленке **/

    public SearchPage choooseCategory(String name) {
        sleep(1000);
        clickNextSlide();
        sleep(500);
        clickNextSlide();
        sleep(500);
        clickNextSlide();
        sleep(500);
        executeJavaScript("return arguments[0].click();", $(ROOT).$(byText(name)));
        return new SearchPage();
    }

    private void clickNextSlide() {
        $(SLIDER_ARROW_NEXT).shouldBe(visible.because("Нет промотки слайдера категорий")).click();
    }




    private void clickPrevSlide() {
        $(".swiper-button-prev").shouldBe(visible.because("")).click();
    }
}

/*    public MainPage choOoseCategory() {
        System.out.println($$(FAST_FILTER_ITEM).texts());
        clickNextSlide();
        System.out.println("КЛИКНУЛИ ПЕРВЫЙ РАЗ");
        sleep(1000);
        System.out.println($$(FAST_FILTER_ITEM).texts());
        clickNextSlide();
        System.out.println("КЛИКНУЛИ ВТОРОЙ РАЗ");
        sleep(1000);
        System.out.println($$(FAST_FILTER_ITEM).texts());
        clickNextSlide();
        System.out.println("КЛИКНУЛИ ТРЕТИЙ РАЗ");
        sleep(1000);
        System.out.println($$(FAST_FILTER_ITEM).texts());
        clickNextSlide();
        System.out.println("КЛИКНУЛИ ЧЕТВРЫЙ РАЗ");
        sleep(1000);
        System.out.println($$(FAST_FILTER_ITEM).texts());
        return new MainPage();
    }




    public MainPage chooseCategory() {
        if ($(ROOT_FOR_SLIDER).$(REMOTE_JOB_FILTER).is(not(visible))) {
            $(SLIDER_ARROW_NEXT).click();
            $(REMOTE_JOB_FILTER).click();
        } else {
            $(REMOTE_JOB_FILTER).click();
        }
        return new MainPage();
    }*/


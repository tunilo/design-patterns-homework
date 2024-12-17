package ge.tbc.testautomation.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.CheckBoxPage;
import ge.tbc.testautomation.pages.CommonPage;
import ge.tbc.testautomation.pages.PricingPage;
import ge.tbc.testautomation.steps.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTests2 {
    PricingSteps pricingSteps;
    PricingPage pricingPage;
    CommonPage commonPage;
    CommonSteps commonSteps;
    Faker faker;
    BooksSteps booksSteps;// Declare Faker instance

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        commonPage = new CommonPage();
        commonSteps = new CommonSteps();
        pricingPage = new PricingPage();
        booksSteps = new BooksSteps();

        pricingSteps = new PricingSteps();
        Faker faker;
    }

    @Test
    public void validateDemosDesign() {
        commonSteps.openPage(Constants.DEMOS_URL);
        pricingSteps.navigateToWebSection()
                .validateWebCardsHoverEffect("rgba(40, 46, 137, 0.75)")
                .validateKendoUiCardLink("UI for Vue demos")
                .validateXamarinLinks()
                .validateStickyNavAndActiveLinks();
    }

    @Test
    public void validateOrderMechanics() {
       CommonSteps.openPage(Constants.DEMOS_URL);
        commonSteps.click(commonPage.pricingLink);
        SelenideElement completePriceElement = $x(Constants.COMPLETE_PRICE_XPATH);
        double displayedPrice = pricingSteps.fetchPrice(completePriceElement);

        pricingSteps.clickBuyNow($x(Constants.BUY_NOW_LINK_XPATH))
                .dismissPopup(Constants.CLOSE_MODAL_XPATH);

        double unitPrice = pricingSteps.fetchPrice($x(Constants.UNIT_PRICE_XPATH));
        pricingSteps.validatePrices(displayedPrice, unitPrice);

        pricingSteps.selectDropdownOption(Constants.DROPDOWN_BUTTON_XPATH, Constants.DROPDOWN_POPUP_XPATH, Constants.NUM);

        ElementsCollection licenseEntries = $$x(Constants.LICENSE_ENTRIES_XPATH);
        int discount = pricingSteps.fetchDiscount(Constants.LICENSE_ENTRIES_XPATH, Constants.TARGET_NUMBER);

        double expectedPrice = (unitPrice * Constants.TARGET_NUMBER) * (100 - discount) / 100.0;
        pricingSteps.validateTotalPrice(pricingPage.totalPriceElement, expectedPrice);

        pricingSteps.selectDropdownOption(Constants.DROPDOWN_BUTTON_YERS, Constants.POPUP_XPATH, Constants.TARGET_OPTION);

        pricingSteps.fillUserForm(faker, "Georgia");

    }

    @Test
    public void chainedLocatorsTest() {
        open("https://demoqa.com/books");
        ElementsCollection filteredBooks = booksSteps.filterBooksBy("O'Reilly Media", "Javascript");
        booksSteps.validateBooksAreNotEmpty(filteredBooks)
                .validateBookImagesHaveValidSrc(filteredBooks);
    }

    @Test
    public void softAssertTest() {
        open("https://demoqa.com/books");
        SoftAssert softAssert = new SoftAssert();
        ElementsCollection filteredBooks = booksSteps.filterBooksBy("O'Reilly Media", "Javascript");
        booksSteps.validateFilteredBooksCount(filteredBooks, 10, softAssert)
                .validateFirstBookTitle(filteredBooks, "Git Pocket Guide", softAssert);

    }
}

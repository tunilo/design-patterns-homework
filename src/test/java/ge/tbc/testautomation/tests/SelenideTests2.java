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
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;
@Epic("UI Validation")
@Feature("End-to-End Functionality Testing")
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

    @Story("Validate design and hover effects for demos page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates design elements and hover effects on the demos page.")
    @Test(description = "Validate design and hover effects on demos page")
    public void validateDemosDesign() {
        commonSteps.openPage(Constants.DEMOS_URL);
        pricingSteps.navigateToWebSection()
                .validateWebCardsHoverEffect("rgba(40, 46, 137, 0.75)")
                .validateKendoUiCardLink("UI for Vue demos")
                .validateXamarinLinks()
                .validateStickyNavAndActiveLinks();
    }

    @Story("Validate order mechanics for pricing page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates order mechanics, including prices and discounts, for the pricing page.")
    @Test(description = "Validate order mechanics on pricing page")
    public void validateOrderMechanics() {
       CommonSteps.openPage(Constants.DEMOS_URL);
        commonSteps.click(commonPage.pricingLink);
        SelenideElement completePriceElement = $x(Constants.COMPLETE_PRICE_XPATH);
        double displayedPrice = pricingSteps.fetchPrice(completePriceElement);

        pricingSteps.clickBuyNow($x(Constants.BUY_NOW_LINK_XPATH))
                .dismissPopup(Constants.CLOSE_MODAL_XPATH);

        double unitPrice = pricingSteps.fetchPrice($x(Constants.UNIT_PRICE_XPATH));
        pricingSteps.validatePrices(displayedPrice, unitPrice)
        .selectDropdownOption(Constants.DROPDOWN_BUTTON_XPATH, Constants.DROPDOWN_POPUP_XPATH, Constants.NUM);

        ElementsCollection licenseEntries = $$x(Constants.LICENSE_ENTRIES_XPATH);
        int discount = pricingSteps.fetchDiscount(Constants.LICENSE_ENTRIES_XPATH, Constants.TARGET_NUMBER);

        double expectedPrice = (unitPrice * Constants.TARGET_NUMBER) * (100 - discount) / 100.0;
        pricingSteps.validateTotalPrice(pricingPage.totalPriceElement, expectedPrice)
                .selectDropdownOption(Constants.DROPDOWN_BUTTON_YERS, Constants.POPUP_XPATH, Constants.TARGET_OPTION)
        .fillUserForm(faker, "Georgia");

    }

    @Story("Validate chained locators on books page")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test uses chained locators to filter books and validates their attributes.")
    @Test(description = "Validate chained locators on books page")
    public void chainedLocatorsTest() {
        open("https://demoqa.com/books");
        ElementsCollection filteredBooks = booksSteps.filterBooksBy("O'Reilly Media", "Javascript");
        booksSteps.validateBooksAreNotEmpty(filteredBooks)
                .validateBookImagesHaveValidSrc(filteredBooks);
    }

    @Story("Validate data consistency using soft assertions")
    @Severity(SeverityLevel.MINOR)
    @Description("This test validates filtered book count and titles using soft assertions.")
    @Test(description = "Validate books data consistency with soft assertions")
    public void softAssertTest() {
        open("https://demoqa.com/books");
        SoftAssert softAssert = new SoftAssert();
        ElementsCollection filteredBooks = booksSteps.filterBooksBy("O'Reilly Media", "Javascript");
        booksSteps.validateFilteredBooksCount(filteredBooks, 10, softAssert)
                .validateFirstBookTitle(filteredBooks, "Git Pocket Guide", softAssert);

    }
}

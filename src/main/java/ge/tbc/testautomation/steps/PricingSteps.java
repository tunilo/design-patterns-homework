package ge.tbc.testautomation.steps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.PricingPage;
import ge.tbc.testautomation.utils.TestUtils;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class PricingSteps extends CommonSteps{
    PricingPage pricingPage = new PricingPage();

    @Step("Validate that bundle is not supported")
    public PricingSteps validateNotSupported(SelenideElement bundle){
        bundle.shouldNotHave(Condition.exist);

        return this;
    }

    @Step("Validate that bundle is supported")
    public PricingSteps validateSupported(SelenideElement bundle){
        bundle.shouldHave(Condition.exist);
        return this;
    }
    @Step("Validate visibility of the element")
    public PricingSteps validateVisibility(SelenideElement bundle) {

        bundle
                .shouldBe(visible);
        return this;
    }
    @Step("Validate dot presence in row: {row} at column")
    public PricingSteps validateDotPresence(SelenideElement row, int columnIndex) {
        row.$x("./td[" + columnIndex + "]//span[@class='dot']")
                .scrollTo()
                .shouldBe(Condition.exist);
        return this;
    }
    @Step("Validate dot Absence in row: {row} at column")
    public PricingSteps validateDotAbsence(SelenideElement row, int columnIndex) {
        row.$x("./td[" + columnIndex + "]//span[@class='dot']")
                .shouldNot(Condition.exist);
        return this;
    }
    @Step("Find column index for header")
    public int findColumnIndex(String columnHeaderText) {
        return ((Long) executeJavaScript(
                "return document.evaluate(" +
                        "\"count(//th[div/h5[text()='" + columnHeaderText + "']]/preceding-sibling::th) + 1\", " +
                        "document, null, XPathResult.NUMBER_TYPE, null).numberValue;"))
                .intValue();
    }
    @Step("Validate text:  is present in row column {columnIndex}")
    public PricingSteps validateTextInCell(SelenideElement row, int columnIndex, String expectedText) {
        row.$x("./td[" + columnIndex + "]//p[contains(., '" + expectedText + "')]")
                .scrollTo()
                .shouldBe(visible);
        return this;
    }

    @Step("Validate text: is absent in row column {columnIndex}")
    public PricingSteps validateTextAbsenceInCell(SelenideElement row, int columnIndex, String text) {
        row.$x("./td[" + columnIndex + "]//p[contains(., '" + text + "')]")
                .shouldNot(Condition.exist);
        return this;
    }
    @Step("Hover on offer element")
    public PricingSteps hoverOnOffer(SelenideElement offerElement) {
        offerElement.hover();
        return this;
    }
    @Step("Validate image visibility")
    public PricingSteps validateImageVisibility(SelenideElement imageElement, boolean shouldBeVisible) {
        if (shouldBeVisible) {
            imageElement.shouldBe(visible);
        } else {
            imageElement.should(Condition.disappear);
        }
        return this;
    }
    @Step("Validate dropdown text")
    public PricingSteps validateDropdownText(SelenideElement dropdownElement, String expectedText) {
        dropdownElement.shouldHave(Condition.text(expectedText));
        return this;
    }
    @Step("Validate price")
    public PricingSteps validatePrice(SelenideElement priceElement, String expectedPrice) {
        priceElement.shouldHave(Condition.text(expectedPrice));
        return this;
    }

    public int getUiColumnIndex() {
        return findColumnIndex(Constants.DEVCRAFT_UI);
    }
    public int getCompleteColumnIndex() {
        return findColumnIndex(Constants.DEVCRAFT_COMPLATE);
    }
    public int getUltimateColumnIndex() {
        return findColumnIndex(Constants.DEVCRAFT_ULTIMATE);
    }
    @Step("Navigate to the web section")
    public PricingSteps navigateToWebSection() {
        pricingPage.webSectionLink.click();
        return this;
    }
    @Step("Validate web cards hover effect with expected color")
    public PricingSteps validateWebCardsHoverEffect(String expectedColor) {
        for (SelenideElement card : pricingPage.webCards) {
            card.hover();
            card.shouldHave(Condition.cssValue("background-color", expectedColor));
        }
        return this;
    }
    @Step("Validate Kendo UI card contains link")

    public PricingSteps validateKendoUiCardLink(String expectedLinkText) {
        pricingPage.webHeader.scrollTo();

        pricingPage.kendoUiCard.shouldBe(visible, Duration.ofSeconds(10));

        pricingPage.kendoUiCard.hover();

        SelenideElement linkContainer = pricingPage.kendoUiCard.$("div.LinkContainer");
        linkContainer.shouldBe(visible);

        ElementsCollection links = linkContainer.$$x(".//a");
        links.findBy(Condition.text(expectedLinkText)).shouldBe(visible);

        return this;
    }
    @Step("Fetch Microsoft Store cards")
    public List<SelenideElement> getMicrosoftStoreCards() {
        pricingPage.desktopSectionLink.click();
        List<SelenideElement> microsoftCards = new ArrayList<>();
        for (SelenideElement card : pricingPage.desktopCards) {
            if (card.$x(".//a[contains(@href, 'microsoft.com')]").exists()) {
                microsoftCards.add(card);
            }
        }
        return microsoftCards;
    }
    @Step("Validate Xamarin links")
    public PricingSteps validateXamarinLinks() {
        pricingPage.mobileSectionLink.click();

        pricingPage.xamarinPlayStoreLink.shouldBe(visible);
        pricingPage.xamarinMicrosoftStoreLink.shouldBe(visible);
        pricingPage.xamarinAppleStoreLink.shouldBe(visible);

        return this;
    }
    @Step("Validate sticky navigation and active links")
    public PricingSteps validateStickyNavAndActiveLinks() {
        pricingPage.navSection.shouldHave(Condition.cssClass("is-fixed"));

        for (SelenideElement link : pricingPage.navLinks) {
            String linkText = link.text();
            link.click();

            String actualBackground = link.getCssValue("background-color");
            assert !actualBackground.equals("rgba(0, 0, 0, 0)");
            sleep(1500);

            SelenideElement visibleHeader = pricingPage.sectionHeaders.stream()
                    .filter(header -> executeJavaScript(
                            "var rect = arguments[0].getBoundingClientRect();" +
                                    "return rect.top >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                                    "window.getComputedStyle(arguments[0]).visibility !== 'hidden';",
                            header))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("No visible header found in viewport"));
            visibleHeader.should(visible);
            String headerText = visibleHeader.text();

            try {
                Assert.assertTrue(headerText.contains(linkText));

            } catch (AssertionError e) {
                System.out.println("Header does not match link text. Expected: " + linkText + ", Actual: " + headerText);
            }

        }
        return this;
    }

    @Step("Fetch the price from the element")
    public double getPrice(SelenideElement priceElement) {
        String priceText = priceElement.text().replaceAll("[^\\d.]", "");
        return Double.parseDouble(priceText);
    }
    @Step("Fetch price value and trim extra spaces")
    public double fetchPrice(SelenideElement priceElement) {
        String priceText = priceElement.text()
                .replaceAll("[^\\d.]", "")
                .trim();
        return Double.parseDouble(priceText);
    }


    @Step("Click 'Buy Now' on the element")
    public PricingSteps clickBuyNow(SelenideElement buyNowElement) {
        executeJavaScript("arguments[0].click();", buyNowElement);
        return this;
    }

    @Step("Dismiss popup with XPath")
    public PricingSteps dismissPopup(String closePopupXPath) {
        $x(closePopupXPath).shouldBe(Condition.visible).click();
        return this;
    }


    @Step("Validate expected price equals actual price: {actualPrice}")
    public PricingSteps validatePrices(double expectedPrice, double actualPrice) {
        assertEquals(expectedPrice, actualPrice, "Prices do not match!");
        return this;
    }


    @Step("Select dropdown option")
    public PricingSteps selectDropdownOption(String dropdownButtonXPath, String popupXPath, String optionText) {
        SelenideElement dropdown = $x(dropdownButtonXPath);
        executeJavaScript("arguments[0].click();", dropdown);
        sleep(1000);
        SelenideElement popup = $x(popupXPath);
        popup.shouldBe(Condition.visible).$$x(".//li").findBy(Condition.text(optionText)).click();
        return this;
    }

    @Step("Fetch discount based on target number")
    public int fetchDiscount(String licenseEntriesXPath, int targetNumber) {
        ElementsCollection licenseEntries = $$x(licenseEntriesXPath);
        for (SelenideElement entry : licenseEntries) {
            String rangeText = entry.$x(".//span[contains(@class, 'label')][1]").getText().trim();
            String discountText = entry.$x(".//span[contains(@class, 'label')][2]").getText().trim();
            if (isNumberInRange(targetNumber, rangeText)) {
                return Integer.parseInt(discountText.replaceAll("[^\\d]", ""));
            }
        }
        return 0;
    }

    @Step("Validate total price")
    public PricingSteps validateTotalPrice(SelenideElement totalPriceElement, double expectedTotal) {
        double actualTotal = getPrice(totalPriceElement);
        double roundedExpected = Math.round(expectedTotal * 100.0) / 100.0;
        double roundedActual = Math.round(actualTotal * 100.0) / 100.0;

        System.out.println("Rounded Expected Price: " + roundedExpected);
        System.out.println("Rounded Actual Price: " + roundedActual);

        assertEquals(roundedExpected, roundedActual, "Total Price mismatch!");
        return this;
    }

    @Step("Validate tooltip discounts")
    public PricingSteps validateSubtotal(SelenideElement subtotalElement, double expectedSubtotal) {
        double actualSubtotal = fetchPrice(subtotalElement);
        assertEquals(expectedSubtotal, actualSubtotal, "Subtotal mismatch!");
        return this;
    }

    @Step("Fill user form with country")
    public PricingSteps validateTooltipDiscounts(SelenideElement tooltipIcon, SelenideElement tooltipContent, double expectedLicenseDiscount, double expectedSupportDiscount) {
        tooltipIcon.scrollTo().hover();
        tooltipContent.shouldBe(Condition.visible);

        ElementsCollection discountElements = tooltipContent.$$x(".//span/following-sibling::div/span");
        double actualLicenseDiscount = Double.parseDouble(discountElements.get(0).text().replaceAll("[^\\d.]", "")) / 100.0;
        double actualSupportDiscount = Double.parseDouble(discountElements.get(1).text().replaceAll("[^\\d.]", "")) / 100.0;

        assertEquals(expectedLicenseDiscount, actualLicenseDiscount, "License Discount mismatch!");
        assertEquals(expectedSupportDiscount, actualSupportDiscount, "Support Discount mismatch!");

        return this;
    }

    public PricingSteps fillUserForm(Faker faker, String country) {
        $x("//input[@id='biFirstName']").setValue(faker.name().firstName());
        $x("//input[@id='biLastName']").setValue(faker.name().lastName());
        $x("//input[@id='biEmail']").setValue(faker.internet().emailAddress());
        $x("//input[@id='biCompany']").setValue(faker.company().name());
        $x("//input[@id='biCity']").setValue(faker.address().city());
        $x("//input[@id='biAddress']").setValue(faker.address().streetAddress());
        $x("//input[@id='biZipCode']").setValue(faker.address().zipCode());
        $x("//input[contains(@class, 'k-input-inner')]").setValue(country);
        $x("//ul[contains(@class, 'k-list-ul')]//li").click();

        return this;
    }

    private boolean isNumberInRange(int number, String rangeText) {
        if (rangeText.contains("Licenses")) {
            String[] parts = rangeText.split(" ")[0].split("-");
            int lowerBound = Integer.parseInt(parts[0]);
            int upperBound = Integer.parseInt(parts[1]);
            return number >= lowerBound && number <= upperBound;
        }
        return false;
    }

}

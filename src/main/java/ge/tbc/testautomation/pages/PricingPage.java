package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.PricingSteps;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;

public class PricingPage {
    public SelenideElement stickyContainer = $x("//div[@class='PricingTable-Sticky']");
    public SelenideElement DEVCRAFT_UI_PRICING_INFO =
            $x("//h5[text()='DevCraft UI']/ancestor::table//tr[contains(@class, 'Pricings-info')]//th" +
                    "[contains(@class, 'UI') and not(.//*[text()='Mocking solution for rapid unit testing'])]");

    public SelenideElement ULTIMATE_ISSUE_ESCALATION =
            $x("//th[@class='Ultimate u-pb2']//p[contains(normalize-space(), 'Issue escalation')]");

    public SelenideElement UI_ISSUE_ESCALATION =
            $x("//th[@class='UI u-pb2']//p[contains(normalize-space(), 'Issue escalation')]");

    public SelenideElement COMPLETE_ISSUE_ESCALATION =
            $x("//th[@class='Complete u-pb2']//p[contains(normalize-space(), 'Issue escalation')]");

    public SelenideElement ULTIMATE_REPORT_MANAGEMENT =
            $x("//th[@class='Ultimate']//li[contains(text(), 'End-to-end report management solution')]");

    public SelenideElement UI_REPORT_MANAGEMENT =
            $x("//th[@class='UI']//li[contains(text(), 'End-to-end report management solution')]");

    public SelenideElement COMPLETE_REPORT_MANAGEMENT =
            $x("//th[@class='Complete']//li[contains(text(), 'End-to-end report management solution')]");

    public SelenideElement testStudioRow = $x("//tr[td[contains(., 'Telerik Test Studio Dev Edition')]]");
    public SelenideElement kendoRow = $x("//tr[td[contains(., 'Kendo UI for jQuery')]]");
    public SelenideElement reportServerRow = $x("//tr[td[contains(., 'Telerik Report Server')]]");
    public SelenideElement reportingRow = $x("//tr[td[contains(., 'Telerik Reporting')]]");
    public SelenideElement videosRow = $x("//tr[td[contains(., 'Access to on-demand videos')]]");
    public SelenideElement targetElement = $x("//tr[td[contains(., 'Access to on-demand videos')]]");
    public SelenideElement DEVCRAFT_UI_ELEMENT = $x("//h5[text()='DevCraft UI']");

    public SelenideElement kendoUIOffer = $x("//div[@data-opti-expid='Kendo UI']");
    public SelenideElement kendoReactOffer = $x("//div[@data-opti-expid='KendoReact']");
    public SelenideElement INDIVIDUAL_PRODUCTS = $x("//a[@href='#individual-products' and contains(@class, 'Tabs-tab')]");
    public SelenideElement kendoUiNinjaImage = $x("//div[@data-opti-expid='Kendo UI']//div[contains(@class, 'Box-ninja')]//img[contains(@title, 'Kendo Ui Ninja')]");
    public SelenideElement kendoReactNinjaImage = $x("//div[@data-opti-expid='KendoReact']//div[contains(@class, 'Box-ninja')]//img[contains(@title, 'Kendo React Ninja')]");
    public SelenideElement kendoReactDropdown = $x("//div[@data-opti-expid='KendoReact']//a[contains(@class, 'Btn Dropdown-control')]");
    public SelenideElement kendoReactPrice = $x("//div[@data-opti-expid='KendoReact']//span[@class='js-price']");
    public SelenideElement kendoUIPrice = $x("//div[@data-opti-expid='Kendo UI']//span[@class='js-price']");
    public SelenideElement kendoUiDropdown = $x("//div[@data-opti-expid='Kendo UI']//a[contains(@class, 'Btn Dropdown-control')]");




    public SelenideElement webSectionLink = $x("//a[@href='#web']");
    public SelenideElement desktopSectionLink = $x("//a[@href='#desktop']");
    public SelenideElement mobileSectionLink = $x("//a[@href='#mobile']");

    // Sections
    public ElementsCollection webCards = $$x(Constants.webCards);
    public SelenideElement kendoUiCard = $x(Constants.KENDO_UI_CARD_XPATH);
    public SelenideElement webHeader = $x(Constants.WEB_HEADER_XPATH);

    public ElementsCollection desktopCards = $$x("//div[@class='desktop-card']");
    public ElementsCollection microsoftStoreLinks = $$x("//a[contains(@href, 'microsoft.com')]");

    public SelenideElement xamarinSection = $x(Constants.XAMARIN_SECTION_XPATH);
    public SelenideElement xamarinPlayStoreLink = xamarinSection.$x(Constants.XAMARIN_PLAY_STORE_LINK_XPATH);
    public SelenideElement xamarinMicrosoftStoreLink = xamarinSection.$x(Constants.XAMARIN_MICROSOFT_STORE_LINK_XPATH);
    public SelenideElement xamarinAppleStoreLink = xamarinSection.$x(Constants.XAMARIN_APPLE_STORE_LINK_XPATH);

    // Navigation Section
    public SelenideElement navSection = $x(Constants.NAV_SECTION_XPATH);
    public ElementsCollection navLinks = $$x(Constants.NAV_LINKS_XPATH);
    public ElementsCollection sectionHeaders = $$x(Constants.HEADERS_XPATH);
    public SelenideElement buyNowLink = $x(Constants.BUY_NOW_LINK_XPATH);
    public SelenideElement pricingMenuLink = $x(Constants.PRICING_MENU_LINK_XPATH);
    public SelenideElement completePriceElement = $x(Constants.COMPLETE_PRICE_XPATH);

    public SelenideElement buyButton = $x(Constants.BUY_NOW_LINK_XPATH);;
    public  SelenideElement closeButton = $x(Constants.CLOSE_MODAL_XPATH);
    public  SelenideElement unitPriceElement = $x(Constants.UNIT_PRICE_XPATH);;
    public SelenideElement totalPriceElement = $x("//h4[contains(@class, 'e2e-total-price')]");
    public SelenideElement dropdownButton = $x("//button[@class='k-input-button k-button k-icon-button k-button-md k-button-solid k-button-solid-base']");
    public SelenideElement dropdownPopup = $x("//kendo-popup[contains(@class, 'k-animation-container k-animation-container-shown')]");
    public SelenideElement subtotalElement = $x("//div[contains(@class, 'e2e-cart-item-subtotal')]");
    public SelenideElement savingsTooltipIcon = $x("//div[contains(@class, 'e2e-total-discounts-label')]//i");
    public SelenideElement tooltipContent = $x("//div[contains(@class, 'tooltip-info--font-l')]");
    public SelenideElement firstNameField = $x("//input[@id='biFirstName']");
    public SelenideElement lastNameField = $x("//input[@id='biLastName']");
    public  SelenideElement emailField = $x("//input[@id='biEmail']");
    public SelenideElement companyField = $x("//input[@id='biCompany']");
    public SelenideElement continueButton = $x("//button[contains(@class, 'btn-primary e2e-continue')]");
}

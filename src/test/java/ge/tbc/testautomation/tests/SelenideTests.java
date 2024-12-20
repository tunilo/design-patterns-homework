package ge.tbc.testautomation.tests;


import com.codeborne.selenide.WebDriverRunner;
import ge.tbc.testautomation.data.Constants;

import ge.tbc.testautomation.pages.CheckBoxPage;
import ge.tbc.testautomation.pages.CommonPage;
import ge.tbc.testautomation.pages.PricingPage;
import ge.tbc.testautomation.steps.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Epic("Form Functionality")
@Feature("Form Submission")
public class SelenideTests {
    PricingSteps pricingSteps;
    PricingPage pricingPage;
    CommonPage commonPage;
    CommonSteps commonSteps;
    CheckBoxSteps checkboxSteps;
    CheckBoxPage checkboxPage;
    dropDownSteps dropdownSteps;
    CollectionsSteps collectionsSteps;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        commonPage = new CommonPage();
        commonSteps = new CommonSteps();
        pricingPage = new PricingPage();
        pricingSteps = new PricingSteps();
        checkboxSteps = new CheckBoxSteps();
        checkboxPage = new CheckBoxPage();
        dropdownSteps = new dropDownSteps();
        collectionsSteps = new CollectionsSteps();

    }


    @Story("Validate pricing bundle offers visibility and supported features")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test validates the visibility and support of various features in pricing bundles.")
    @Test
    public void validateBundleOffers() {
        CommonSteps.openPage("https://www.telerik.com/support/demos");
        System.out.println("this test run");
        commonSteps.click(commonPage.pricingLink);
        pricingSteps.validateVisibility(pricingPage.DEVCRAFT_UI_ELEMENT)
                .validateSupported(pricingPage.DEVCRAFT_UI_PRICING_INFO)
                .validateSupported(pricingPage.ULTIMATE_ISSUE_ESCALATION)
                .validateNotSupported(pricingPage.UI_ISSUE_ESCALATION)
                .validateNotSupported(pricingPage.COMPLETE_ISSUE_ESCALATION)
                .validateSupported(pricingPage.ULTIMATE_REPORT_MANAGEMENT)
                .validateNotSupported(pricingPage.UI_REPORT_MANAGEMENT)
                .validateNotSupported(pricingPage.COMPLETE_REPORT_MANAGEMENT)
                .validateDotPresence(pricingPage.testStudioRow, pricingSteps.getUltimateColumnIndex())
                .validateDotAbsence(pricingPage.testStudioRow, pricingSteps.getUiColumnIndex())
                .validateDotAbsence(pricingPage.testStudioRow, pricingSteps.getCompleteColumnIndex())
                .validateDotPresence(pricingPage.kendoRow, pricingSteps.getUiColumnIndex())
                .validateDotPresence(pricingPage.kendoRow, pricingSteps.getCompleteColumnIndex())
                .validateDotPresence(pricingPage.kendoRow, pricingSteps.getUltimateColumnIndex())
                .validateTextInCell(pricingPage.reportServerRow, pricingSteps.getUltimateColumnIndex(), Constants.One_instance_With_fifteen)
                .validateTextAbsenceInCell(pricingPage.reportServerRow, pricingSteps.getUiColumnIndex(), Constants.One_instance_With_fifteen)
                .validateTextAbsenceInCell(pricingPage.reportServerRow, pricingSteps.getCompleteColumnIndex(), Constants.One_instance_With_fifteen)
                .validateDotPresence(pricingPage.reportingRow, pricingSteps.getCompleteColumnIndex())
                .validateDotPresence(pricingPage.reportingRow, pricingSteps.getUltimateColumnIndex())
                .validateDotAbsence(pricingPage.reportingRow, pricingSteps.getUiColumnIndex())
                .validateDotPresence(pricingPage.videosRow, pricingSteps.getUiColumnIndex())
                .validateDotPresence(pricingPage.videosRow, pricingSteps.getCompleteColumnIndex())
                .validateDotPresence(pricingPage.videosRow, pricingSteps.getUltimateColumnIndex())
                .validateVisibility(pricingPage.stickyContainer);

    }

    @Story("Validate form submission and output display")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates user form submission and correct output display on the demo page.")
    @Test
    public void validateIndividualOffers() {
        System.out.println("this test run");

        CommonSteps.openPage("https://www.telerik.com/support/demos");
        commonSteps.click(commonPage.pricingLink);
        commonSteps.click(pricingPage.INDIVIDUAL_PRODUCTS);
        pricingSteps.hoverOnOffer(pricingPage.kendoReactOffer)
                .validateImageVisibility(pricingPage.kendoReactNinjaImage, true)
                .validateImageVisibility(pricingPage.kendoReactNinjaImage, false)
                .hoverOnOffer(pricingPage.kendoUIOffer)
                .validateImageVisibility(pricingPage.kendoUiNinjaImage, true)
                .validateDropdownText(pricingPage.kendoUiDropdown, Constants.PRIORITY_SUPPORT)
                .validateDropdownText(pricingPage.kendoReactDropdown, Constants.PRIORITY_SUPPORT)
                .validatePrice(pricingPage.kendoReactPrice, Constants.REACT_PRICE)
                .validatePrice(pricingPage.kendoUIPrice, Constants.UI_PRICE);
    }


    @Story("Validate checkbox attributes")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Validate checkbox selection and attributes")
    public void checkBoxTest() {
        System.out.println("this test run");

        CommonSteps.openPage("http://the-internet.herokuapp.com/checkboxes");
        checkboxSteps.selectCheckboxIfNotSelected(checkboxPage.firstCheckbox)
                .selectCheckboxIfNotSelected(checkboxPage.secondCheckbox)
                .validateCheckboxType(checkboxPage.firstCheckbox)
                .validateCheckboxType(checkboxPage.secondCheckbox);
    }

    @Story("Validate dropdown functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates dropdown selection and validation of options.")
    @Test
    public void dropDownTest() {
        System.out.println("this test run");

        CommonSteps.openPage(Constants.HEROKUAPP_URL);
        dropdownSteps.validateDefaultText("Please select an option")
                .selectOption("Option 2")
                .validateSelectedValue("2")
                .validateSelectedOptionText("Option 2");
    }

    @Story("Validate user form submission and output")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Validate form submission and output display")
    public void collectionsTest() {
        System.out.println("this test run");

        CommonSteps.openPage("https://demoqa.com/text-box");
        collectionsSteps.fillForm(Constants.FULL_NAME, Constants.EMAIL, Constants.ADDRESS, Constants.ADDRESS)
                .submitForm()
                .validateOutput(Constants.FULL_NAME, Constants.EMAIL, Constants.ADDRESS, Constants.ADDRESS);
    }
}

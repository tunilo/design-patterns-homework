package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.dropdownPage;
import io.qameta.allure.Step;

public class dropDownSteps {
    dropdownPage dropdownPage = new dropdownPage();
    @Step("Validate that the dropdown default text ")
    public dropDownSteps validateDefaultText(String expectedText) {
        dropdownPage.dropdown
                .shouldHave(Condition.text(expectedText));
        return this;
    }
    @Step("Select the option from the dropdown")
    public dropDownSteps selectOption(String option) {
        dropdownPage.dropdown.selectOption(option);
        return this;
    }
    @Step("Validate that the selected value in the dropdown")
    public dropDownSteps  validateSelectedValue(String expectedValue) {
        dropdownPage.dropdown
                .shouldHave(Condition.value(expectedValue));
        return this;
    }
    @Step("Validate that the selected option text in the dropdown")
    public dropDownSteps validateSelectedOptionText(String expectedText) {
        dropdownPage.dropdown
                .getSelectedOption()
                .shouldHave(Condition.text(expectedText));
        return this;
    }
}

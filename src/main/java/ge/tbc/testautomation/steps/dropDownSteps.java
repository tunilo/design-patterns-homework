package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.dropdownPage;

public class dropDownSteps {
    dropdownPage dropdownPage = new dropdownPage();

    public dropDownSteps validateDefaultText(String expectedText) {
        dropdownPage.dropdown
                .shouldHave(Condition.text(expectedText));
        return this;
    }
    public dropDownSteps selectOption(String option) {
        dropdownPage.dropdown.selectOption(option);
        return this;
    }
    public dropDownSteps  validateSelectedValue(String expectedValue) {
        dropdownPage.dropdown
                .shouldHave(Condition.value(expectedValue));
        return this;
    }
    public dropDownSteps validateSelectedOptionText(String expectedText) {
        dropdownPage.dropdown
                .getSelectedOption()
                .shouldHave(Condition.text(expectedText));
        return this;
    }
}

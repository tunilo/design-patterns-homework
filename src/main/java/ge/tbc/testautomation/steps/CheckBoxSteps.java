package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.CheckBoxPage;
import io.qameta.allure.Step;

public class CheckBoxSteps {

    @Step("Select the checkbox if it is not already selected")
    public CheckBoxSteps selectCheckboxIfNotSelected(SelenideElement checkbox) {
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        checkbox.shouldBe(Condition.selected);
        return this;
    }
    @Step("Validate that the checkbox has the correct type: 'checkbox'")
    public CheckBoxSteps validateCheckboxType(SelenideElement checkbox) {
        checkbox.shouldHave(Condition.attribute("type", "checkbox"));
        return this;
    }
}

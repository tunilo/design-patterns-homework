package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.CheckBoxPage;

public class CheckBoxSteps {


    public CheckBoxSteps selectCheckboxIfNotSelected(SelenideElement checkbox) {
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        checkbox.shouldBe(Condition.selected);
        return this;
    }
    public CheckBoxSteps validateCheckboxType(SelenideElement checkbox) {
        checkbox.shouldHave(Condition.attribute("type", "checkbox"));
        return this;
    }
}

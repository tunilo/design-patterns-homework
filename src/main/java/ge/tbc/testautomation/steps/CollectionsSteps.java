package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.CollectionsPage;

public class CollectionsSteps {
    CollectionsPage collectionsPage = new CollectionsPage();

    public CollectionsSteps fillForm(String name, String email, String currentAddress, String permanentAddress) {
        collectionsPage.userNameField.setValue(name);
        collectionsPage.userEmailField.setValue(email);
        collectionsPage.currentAddressField.setValue(currentAddress);
        collectionsPage.permanentAddressField.setValue(permanentAddress);
        return this;
    }
    public CollectionsSteps submitForm() {
        collectionsPage.submitButton.scrollTo().click();
        return this;
    }
    public CollectionsSteps validateOutput(String name, String email, String currentAddress, String permanentAddress) {
        collectionsPage.outputName.shouldHave(Condition.text("Name:" + name));
        collectionsPage.outputEmail.shouldHave(Condition.text("Email:" + email));
        collectionsPage.outputCurrentAddress.shouldHave(Condition.text("Current Address :" + currentAddress));
        collectionsPage.outputPermanentAddress.shouldHave(Condition.text("Permananet Address :" + permanentAddress));
        return this;
    }
}

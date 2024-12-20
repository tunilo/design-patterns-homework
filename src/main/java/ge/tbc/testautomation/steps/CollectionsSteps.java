package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import ge.tbc.testautomation.pages.CollectionsPage;
import io.qameta.allure.Step;

public class CollectionsSteps {
    CollectionsPage collectionsPage = new CollectionsPage();
    @Step("Fill form with name: {name}, email: {email}, currentAddress: {currentAddress}, permanentAddress: {permanentAddress}")
    public CollectionsSteps fillForm(String name, String email, String currentAddress, String permanentAddress) {
        collectionsPage.userNameField.setValue(name);
        collectionsPage.userEmailField.setValue(email);
        collectionsPage.currentAddressField.setValue(currentAddress);
        collectionsPage.permanentAddressField.setValue(permanentAddress);
        return this;
    }
    @Step("Submit the form")
    public CollectionsSteps submitForm() {
        collectionsPage.submitButton.scrollTo().click();
        return this;
    }

    @Step("Validate form output: name={name}, email={email}, currentAddress={currentAddress}, permanentAddress={permanentAddress}")
    public CollectionsSteps validateOutput(String name, String email, String currentAddress, String permanentAddress) {
        collectionsPage.outputName.shouldHave(Condition.text("Name:" + name));
        collectionsPage.outputEmail.shouldHave(Condition.text("Email:" + email));
        collectionsPage.outputCurrentAddress.shouldHave(Condition.text("Current Address :" + currentAddress));
        collectionsPage.outputPermanentAddress.shouldHave(Condition.text("Permananet Address :" + permanentAddress));
        return this;
    }
}

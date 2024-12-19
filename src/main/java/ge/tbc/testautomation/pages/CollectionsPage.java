package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CollectionsPage {
    public SelenideElement userNameField = $("#userName");
    public SelenideElement userEmailField = $("[id='userEmail']");
    public SelenideElement currentAddressField = $x("//textarea[@id='currentAddress']");
    public SelenideElement permanentAddressField = $x("//textarea[contains(@id,'permanentAddress')]");
    public SelenideElement submitButton = $("#submit");
    public SelenideElement outputSection = $(".border");
    public SelenideElement outputName = outputSection.$("#name");
    public SelenideElement outputEmail = outputSection.$("#email");
    public SelenideElement outputCurrentAddress = outputSection.$("#currentAddress");
    public SelenideElement outputPermanentAddress = outputSection.$("#permanentAddress");

}

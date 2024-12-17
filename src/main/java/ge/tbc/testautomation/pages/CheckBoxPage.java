package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CheckBoxPage {
    public SelenideElement firstCheckbox = $x("//form[@id='checkboxes']/input[1]");
    public SelenideElement secondCheckbox = $x("//form[@id='checkboxes']/input[2]");

}

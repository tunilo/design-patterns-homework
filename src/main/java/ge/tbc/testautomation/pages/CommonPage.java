package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.steps.CommonSteps;

import static com.codeborne.selenide.Selenide.$x;

public class CommonPage {
public SelenideElement pricingLink =  $x("//a[@class='TK-Menu-Item-Link' and text()='Pricing']");
}

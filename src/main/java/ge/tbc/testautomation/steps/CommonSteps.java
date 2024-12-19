package ge.tbc.testautomation.steps;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CommonSteps {
    public CommonSteps click(SelenideElement element) {
        element.click();
        return this;
    }
    public static void openPage(String url) {
        open(url);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}

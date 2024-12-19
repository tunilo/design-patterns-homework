package ge.tbc.testautomation.steps;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CommonSteps {

    @Step("Click on the element: ")
    public CommonSteps click(SelenideElement element) {
        element.click();
        return this;
    }

    @Step("Open the page with URL and maximize the browser window")
    public static void openPage(String url) {
        open(url);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}

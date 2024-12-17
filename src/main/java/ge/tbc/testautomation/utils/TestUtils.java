package ge.tbc.testautomation.utils;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import dev.failsafe.internal.util.Assert;
import org.apache.hc.core5.util.Asserts;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TestUtils {
    public static boolean isNumberInRange(int number, String rangeText) {
        if (rangeText.contains("Licenses")) {
            String[] parts = rangeText.split(" ")[0].split("-");
            int lowerBound = Integer.parseInt(parts[0]);
            int upperBound = Integer.parseInt(parts[1]);
            return number >= lowerBound && number <= upperBound;
        }
        return false;
    }

    public static void fillUserForm(Faker faker, String country) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        SelenideElement firstNameField = $x("//input[@id='biFirstName']");

        firstNameField.shouldBe(visible).setValue(firstName);
        $x("//input[@id='biLastName']").shouldBe(visible).setValue(lastName);
        $x("//input[@id='biEmail']").shouldBe(visible).setValue(faker.internet().emailAddress());
        $x("//input[@id='biCompany']").shouldBe(visible).setValue(faker.company().name());
        $x("//input[@id='biPhone']").shouldBe(visible).setValue(faker.phoneNumber().cellPhone());
        $x("//input[@id='biCity']").shouldBe(visible).setValue(faker.address().city());
        $x("//input[@id='biAddress']").shouldBe(visible).setValue(faker.address().streetAddress());
        $x("//input[@id='biZipCode']").shouldBe(visible).setValue(faker.address().zipCode());

        SelenideElement countryDropdown = $x("//input[contains(@class, 'k-input-inner') and @aria-expanded='false']");
        countryDropdown.shouldBe(visible).setValue(country);

        SelenideElement firstOption = $x("//ul[contains(@class, 'k-list-ul')]//li");
        firstOption.click();

    }
}

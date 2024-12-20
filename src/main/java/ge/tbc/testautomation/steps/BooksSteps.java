package ge.tbc.testautomation.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.BooksPage;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Step;


import static org.testng.Assert.assertFalse;

public class BooksSteps {
    BooksPage booksPage = new BooksPage();

    @Step("Filter books by publisher: {publisher} and keyword: {keyword}")
    public ElementsCollection filterBooksBy(String publisher, String keyword) {
        return booksPage.bookRows
                .filterBy(Condition.text(publisher))
                .filterBy(Condition.text(keyword));
    }
    @Step("Validate that books are not empty")

    public BooksSteps validateBooksAreNotEmpty(ElementsCollection books) {
        assertFalse(books.isEmpty(), "No books found with the given criteria.");
        return this;
    }
    @Step("Validate book images have non-empty 'src' attributes")

    public BooksSteps validateBookImagesHaveValidSrc(ElementsCollection books) {
        for (SelenideElement book : books) {
            SelenideElement image = book.$("img");
            image.shouldHave(Condition.attributeMatching("src", ".*\\S+.*")); // Non-empty 'src' attribute
        }
        return this;
    }
    @Step("Validate filtered books count: expected {expectedCount} books")
    public BooksSteps validateFilteredBooksCount(ElementsCollection books, int expectedCount, SoftAssert softAssert) {
        softAssert.assertEquals(books.size(), expectedCount, "Number of filtered books does not match the expected count.");
        return this;
    }
    @Step("Validate the title of the first book: expected title '{expectedTitle}'")
    public BooksSteps validateFirstBookTitle(ElementsCollection books, String expectedTitle, SoftAssert softAssert) {
        if (!books.isEmpty()) {
            SelenideElement firstBook = books.get(0);
            String firstBookTitle = firstBook.$x(booksPage.bookTitleCellXpath).getText();
            softAssert.assertEquals(firstBookTitle, expectedTitle, "First book title does not match the expected value.");
        } else {
            softAssert.fail("No books found to verify the first book's title.");
        }
        return this;
    }
}

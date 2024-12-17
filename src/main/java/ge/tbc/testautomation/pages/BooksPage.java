package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class BooksPage {
        public ElementsCollection bookRows = $$x("//div[@class='rt-tbody']//div[@class='rt-tr-group']");
    public String bookTitleCellXpath = ".//div[@role='gridcell'][2]";


}

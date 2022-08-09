package uk.gov.cslearning.acceptanceTests.pages.CslManagement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ContentManagementPage extends CslManagementBasePage {

    @FindBy(how = How.LINK_TEXT, using = "Add a new course")
    protected WebElement addNewCourseLink;

    @FindBy(how = How.LINK_TEXT, using = "Manage organisations")
    protected WebElement manageOrganisationsLink;

    @FindBy(how = How.LINK_TEXT, using = "Manage Quiz")
    protected WebElement manageQuizLink;

    @FindBy(how = How.CSS, using = ".govuk-input-search")
    protected WebElement searchTxt;

    @FindBy(how = How.CSS, using = ".search-button")
    protected WebElement searchBtn;

    public void executeSearch(String query) {
        searchTxt.sendKeys(query);
        searchBtn.click();
    }

    public void navigateTo() {
        this.navigate(getPageUrl());
    }

    public String getPageUrl() {
        return String.format("%s/content-management", this.baseUrl);
    }
}

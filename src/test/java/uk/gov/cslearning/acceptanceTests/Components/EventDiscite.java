package uk.gov.cslearning.acceptanceTests.Components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;

public class EventDiscite extends BaseDiscite{

    public WebElement cancelBookingLink;

    public EventDiscite(String status, WebElement cancelBookingLink) {
        super(status);
        this.cancelBookingLink = cancelBookingLink;
    }

    public boolean isRequested() {
        return status.equals("requested");
    }

    public boolean isConfirmed() {
        return status.equals("confirmed");
    }
    public boolean isCancelled() {
        return status.equals("cancelled");
    }

    public void assertRequested() {
        Assert.isTrue(isRequested(), String.format("Expected %s status to be requested, but was %s", getDisciteType(), status));
    }

    public void assertConfirmed() {
        Assert.isTrue(isConfirmed(), String.format("Expected %s status to be confirmed, but was %s", getDisciteType(), status));
    }

    public void assertCancelled() {
        Assert.isTrue(isCancelled(), String.format("Expected %s status to be cancelled, but was %s", getDisciteType(), status));
    }

    public static EventDiscite fromWebElement(WebElement disciteItem) {
        String status = disciteItem.findElement(By.cssSelector(".badge")).getText();
        WebElement cancelBookingLink = null;
        if (status.equals("requested") || status.equals("confirmed")) {
            cancelBookingLink = disciteItem.findElement(By.linkText("Cancel booking"));
        }
        return new EventDiscite(status, cancelBookingLink);
    }

    @Override
    String getDisciteType() {
        return "event";
    }
}

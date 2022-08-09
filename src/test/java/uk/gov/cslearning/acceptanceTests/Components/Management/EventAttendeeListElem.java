package uk.gov.cslearning.acceptanceTests.Components.Management;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EventAttendeeListElem {

    public String email;
    public String paymentStatus;
    public String bookingReference;
    public WebElement viewLink;

    public EventAttendeeListElem(String email, String paymentStatus, String bookingReference, WebElement viewLink) {
        this.email = email;
        this.paymentStatus = paymentStatus;
        this.bookingReference = bookingReference;
        this.viewLink = viewLink;
    }

    public static EventAttendeeListElem fromWebElement(WebElement listElem) {
        List<WebElement> nestedParagraphs = listElem.findElements(By.tagName("p"));
        String email = nestedParagraphs.get(0).getText();
        String paymentStatus = nestedParagraphs.get(1).getText();
        String bookingReference = nestedParagraphs.get(2).getText();
        WebElement viewLink = listElem.findElement(By.linkText("View"));
        return new EventAttendeeListElem(email, paymentStatus, bookingReference, viewLink);
    }
}

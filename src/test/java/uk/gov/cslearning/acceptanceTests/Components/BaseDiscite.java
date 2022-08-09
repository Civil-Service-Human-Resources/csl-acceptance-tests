package uk.gov.cslearning.acceptanceTests.Components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.util.Assert;

public abstract class BaseDiscite {

    public String status;

    public BaseDiscite(String status) {
        this.status = status;
    }

    public boolean isCompleted() {
        return status.equals("completed");
    }

    public boolean isInProgress() {
        return status.equals("in progress");
    }

    public boolean isJustAdded() { return status.equals("just added"); }

    public void assertCompleted() {
        Assert.isTrue(isCompleted(), String.format("Expected %s status to be completed, but was %s", getDisciteType(), status));
    }

    public void assertInProgress() {
        Assert.isTrue(isInProgress(), String.format("Expected %s status to be in progress, but was %s", getDisciteType(), status));
    }

    public void assertJustAdded() {
        Assert.isTrue(isJustAdded(), String.format("Expected %s status to be just added, but was %s", getDisciteType(), status));
    }

    abstract String getDisciteType();

}

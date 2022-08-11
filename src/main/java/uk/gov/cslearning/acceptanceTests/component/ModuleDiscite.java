package uk.gov.cslearning.acceptanceTests.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ModuleDiscite extends BaseDiscite {

    public String heading;
    public String description;
    public String type;
    public String duration;
    public String cost;
    public WebElement startLink;

    public ModuleDiscite(String heading, String description, String type, String duration, String cost, String status,
                         WebElement startLink) {
        super(status);
        this.heading = heading;
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.duration = duration;
        this.startLink = startLink;
    }

    public String getDisciteType() {
        return "module";
    }

    public boolean isDownloadableContent() {
        return isFile() || isELearning();
    }

    public boolean isVideo() {
        return type.equals("video");
    }

    public boolean isFile() {
        return type.equals("file");
    }

    public boolean isELearning() {
        return type.equals("online course");
    }

    public boolean isLink() {
        return type.equals("link");
    }

    public boolean isFaceToFace() { return type.equals("face to face"); }

    public static ModuleDiscite fromWebElement(WebElement disciteItem) {
        String heading = disciteItem.findElement(By.cssSelector(".heading")).getText();
        String description = disciteItem.findElement(By.cssSelector(".discite__description")).getText();
        String type = disciteItem.findElement(By.cssSelector(".lpg-course-type")).getText().toLowerCase();
        String duration = disciteItem.findElement(By.cssSelector(".lpg-course-duration")).getText();
        String cost = "";
        if (!type.equals("file")) {
            cost =  disciteItem.findElement(By.cssSelector(".lpg-course-cost")).getText();
        }
        String status = disciteItem.findElement(By.cssSelector(".badge--info")).getText();

        String startLinkText = "Start";
        if (type.equals("file")) {
            startLinkText = "Download document";
        } else if (type.equals("face to face")) {
            startLinkText = "Book";
        }

        WebElement startLink = disciteItem.findElement(By.partialLinkText(startLinkText));

        return new ModuleDiscite(heading, description, type, duration, cost, status, startLink);
    }
}

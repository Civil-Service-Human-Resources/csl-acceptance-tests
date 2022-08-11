package uk.gov.cslearning.acceptanceTests.page.CslIdentity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.BasePage;

@Component
public abstract class CslIdentityBasePage extends BasePage {

    @Value("${pages.identity.baseUrl}")
    protected
    String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

}

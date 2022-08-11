package uk.gov.cslearning.acceptanceTests.page.CslIdentity;

import org.springframework.stereotype.Component;

@Component
public class ReactivationPage extends CslIdentityBasePage {

    public void navigateTo(String code) {
        navigate(String.format("%s/account/reactivate/%s", baseUrl, code));
    }
}

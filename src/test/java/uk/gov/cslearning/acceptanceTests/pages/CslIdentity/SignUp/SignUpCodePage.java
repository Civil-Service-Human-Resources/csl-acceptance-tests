package uk.gov.cslearning.acceptanceTests.pages.CslIdentity.SignUp;

import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.pages.CslIdentity.CslIdentityBasePage;

@Component
public class SignUpCodePage extends CslIdentityBasePage {

    public void navigateTo(String code) {
        navigate(String.format("%s/signup/%s", this.baseUrl, code));
    }
}

package uk.gov.cslearning.acceptanceTests.page.CslIdentity.SignUp;

import org.springframework.stereotype.Component;
import uk.gov.cslearning.acceptanceTests.page.CslIdentity.CslIdentityBasePage;

@Component
public class SignUpCodePage extends CslIdentityBasePage {

    public void navigateTo(String code) {
        navigate(String.format("%s/signup/%s", this.baseUrl, code));
    }
}

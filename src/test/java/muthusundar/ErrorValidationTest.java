package muthusundar;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import muthusundar.TestComponents.BaseTests;
import muthusundar.TestComponents.Retry;

public class ErrorValidationTest extends BaseTests {

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginwithInvalidCredentials() throws IOException, InterruptedException {
		 landingPage.loginApplication("muthusundar044@gmail.com", "qw123455");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

}

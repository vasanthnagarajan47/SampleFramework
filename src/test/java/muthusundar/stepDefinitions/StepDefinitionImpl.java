package muthusundar.stepDefinitions;

import java.io.IOException;

import io.cucumber.java.en.Given;
import muthusundar.TestComponents.BaseTests;
import muthusundar.pageobjects.LandingPage;

public class StepDefinitionImpl extends BaseTests{
	
	public LandingPage landingPage;

	@Given("I landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
		landingPage=  launchApplication();
	}
}

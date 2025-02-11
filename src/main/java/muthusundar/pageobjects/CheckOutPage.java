package muthusundar.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import muthusundar.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//*[contains(text(),\"Place Order \")]")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCounty(String countryName) throws InterruptedException {
		waitForElementToAppear(By.cssSelector("input[placeholder='Select Country']"));
		moveToElement(country);
		country.sendKeys(countryName);
		Thread.sleep(3000);
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() throws InterruptedException {
		moveToElement(submit);
		//submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}

package muthusundar.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import muthusundar.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent {

	WebDriver driver;

	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;
	
	@FindBy(css = ".cart h3")
	List<WebElement> cartProducts;

	public boolean verifyProductDisplay(String productName) {

		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;

	}
	
	public CheckOutPage clickCheckoutButton() {
		moveToElement(checkOutButton);
//		checkOutButton.click();
		CheckOutPage checkOutPage= new CheckOutPage(driver);
		 return checkOutPage;
	}
	

}

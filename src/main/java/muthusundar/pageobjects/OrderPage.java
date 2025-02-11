package muthusundar.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import muthusundar.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public boolean verifyOrderDisplay(String productName) {

		boolean match = productNames.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}
	

}

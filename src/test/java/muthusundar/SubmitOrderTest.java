package muthusundar;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import muthusundar.TestComponents.BaseTests;
import muthusundar.pageobjects.CheckOutPage;
import muthusundar.pageobjects.ConfirmationPage;
import muthusundar.pageobjects.LandingPage;
import muthusundar.pageobjects.MyCartPage;
import muthusundar.pageobjects.OrderPage;
import muthusundar.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTests {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"} )
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

		MyCartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);

		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.clickCheckoutButton();
		
		Thread.sleep(3000);
		checkOutPage.selectCounty("ind");		
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// to verify ZARA COAT 3 part is displaying in orders page
		ProductCatalogue productCatalogue = landingPage.loginApplication("muthusundar044@gmail.com", "Sundar@04");
		OrderPage orderPage = productCatalogue.goToOrdersPage();

		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+ "\\\\src\\\\test\\\\java\\\\muthusundar\\\\data\\\\PurchaseOrder.json");
	
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { {"muthusundar044@gmail.com", "Sundar@04"}, {"shetty@gmail.com", "Iamking@000"} };
//	}
	
	
//	HashMap<String, String> map = new HashMap<>();
//	map.put("email", "muthusundar044@gmail.com");
//	map.put("password", "Sundar@04");
	
	
	
	//Extent reports
}

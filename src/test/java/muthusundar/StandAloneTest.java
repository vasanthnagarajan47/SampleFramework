package muthusundar;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import muthusundar.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		String productName ="ADIDAS ORIGINAL";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("muthusundar044@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sundar@#27");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> productsList =driver.findElements(By.cssSelector(".mb-3"));
//		List<WebElement> element = driver.findElements(By.xpath("//div[@class=\"card-body\"]/h5/b"));
//		String expected = "QWERTY";
//		for(int i=0; i<productsList.size(); i++) {
//			
//			System.out.println(element.get(i).getText());
//			if(element.get(i).getText().contains(expected)) {
//				System.out.println(element.get(i).getText());
//				break;
//			}
//		}
		
		WebElement prod =productsList.stream().filter(product->product.findElement(By.cssSelector("b")).getText()
				.equals(productName)).findFirst().orElse(null);
		
				prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		 List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cart h3"));
		 boolean match =cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		 
		 Assert.assertTrue(match);
		 
		 driver.findElement(By.cssSelector(".totalRow button")).click();
		 Thread.sleep(3000);
		 Actions a = new Actions(driver);
		 a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		 
		
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		 WebElement element = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		 a.moveToElement(element).click().perform();

	//	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("scroll(0, 200)");
		 Thread.sleep(3000);
		driver.findElement(By.cssSelector(".action__submit")).click();
		 
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
	}

}

package ajd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class bas {
	public static WebDriver driver;
	
	// Registration Validation
	@Test(dataProvider = "data")
	public void test(Hashtable<String, String> table) {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.get("https://meralda.scalenext.io/user/register");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[1]/div[1]/input")).sendKeys(table.get("fname"));
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[1]/div[2]/input")).sendKeys(table.get("lname"));
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[2]/input")).sendKeys(table.get("gmail"));
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[3]/input")).sendKeys(table.get("phone"));
		//driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[4]/div/div/div/input")).click();
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[4]/div/div/div/input")).sendKeys(table.get("dob"));
		System.out.println(table.get("dob"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String daet = table.get("dob");
		calenderselect(daet);
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[5]/input")).sendKeys(table.get("password"));
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[6]/input")).sendKeys(table.get("cpassword"));
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[2]/button")).click();
		
		if(driver.getCurrentUrl().equals("https://meralda.scalenext.io/user/register")){
			String message = driver.findElement(By.xpath(table.get("xpath"))).getAttribute("validationMessage");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println(message);
			
			Assert.assertEquals(message, "Please fill out this field.","Successfully displayed error message");
			
		}
		
	}
	// select the date in application
			public static void calenderselect(String date){
				
				Date currentDate = new Date();
				System.out.println(currentDate);
				
				SimpleDateFormat formateDate = new SimpleDateFormat("d,MM,yyyy");
				try {
					Date expectedDate = formateDate.parse(date);
					String day = new SimpleDateFormat("d").format(expectedDate);
					String month = new SimpleDateFormat("MMM").format(expectedDate);
					String year = new SimpleDateFormat("yyyy").format(expectedDate);

					String expectedmonthyear = month+ " " +year;
					
					while(true){
						String eyear = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/span/button[2]")).getText();
						String emonth = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/span/button[1]")).getText();
						
						if(year.equals(eyear)){
							if(month.equals(emonth)){
								driver.findElement(By.xpath("//table/tbody/tr/td[@class='cell']/div[text()='"+day+"']")).click();
								break;
							}else if(month.compareTo(emonth)<0){
								driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/button[4]")).click();
								
							}else{
								driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/button[2]")).click();
							}
							
						}
						else if(eyear.compareTo(year)<0){
							driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/button[3]")).click();
						}else{
							driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/button[1]")).click();
						}
					}
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
	@DataProvider
	public Object[][] data(){
		return hashTable.driven("kemper.xlsx","Reg");
	}
	
	// Login Validation
	@Test(dataProvider = "Logindata",priority = 1)
	public void Login(Hashtable<String, String> table) {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.get("https://meralda.scalenext.io/user/login");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[1]/input")).sendKeys(table.get("gmail"));
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div[2]/input")).sendKeys(table.get("password"));
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[3]/button")).click();
		
		if(driver.getCurrentUrl().equals("https://meralda.scalenext.io/user/login")){
			String message = driver.findElement(By.xpath(table.get("xpath"))).getAttribute("validationMessage");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			System.out.println(message);
			
			Assert.assertEquals(message, "Please fill out this field.","Successfully displayed error message");
			String text = driver.findElement(By.xpath("//*[@id='app']/div[1]")).getText();
			System.out.println(text);
		}
	}
	
	@DataProvider
	public Object[][] Logindata(){
		return hashTable.driven("kemper.xlsx","Login");
	}
	
	
	// Forgot password validation
	@Test(priority = 2)
	public void forget() {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.get("https://meralda.scalenext.io/user/login");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[2]")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div/input")).sendKeys("test@test.com");
		driver.findElement(By.xpath("//*[@id='shippingForm']/div[2]/button")).click();
		System.out.println(driver.findElement(By.xpath("//*[@id='shippingForm']/div[1]/div/div/span")).getText());
	}


}

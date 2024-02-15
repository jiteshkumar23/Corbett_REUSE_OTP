package NightStay;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OTPReaderProfile1 extends DataProfile1 {

	static WebElement searchForChatEnterSearch = null;
	static WebDriverWait waitMessageNew = null;

	public static String getMeOTP(int hourOfClickingOTPButton, int minuteOfClickingOTPButton)
			throws InterruptedException, IOException {

//	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Starting to read OTP from Profile 1");
        ChromeOptions options = new ChromeOptions();
		//options.setExperimentalOption("debuggerAddress", "localhost:8000");
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:8000");
		ChromeDriver driver2 = new ChromeDriver(options);
		System.out.println(driver2.getCurrentUrl());
		driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		JavascriptExecutor js = (JavascriptExecutor) driver2;

		WebDriverWait wait2 = new WebDriverWait(driver2, Duration.ofSeconds(20));
		
		switchToWhatsappSite(driver2);
		
		try {
			wait2.until(ExpectedConditions
					.visibilityOf(driver2.findElement(By.xpath("//div[@title=\"Search input textbox\"]"))));
		} catch (Exception ab) {
			// Capture a screenshot
			TakesScreenshot screenshot = (TakesScreenshot) driver2;
			File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
			try {
				// Save the screenshot to a file
				FileUtils.copyFile(srcFile, new File("whatsappException1.png"));
				System.out.println("Screenshot captured in headless mode and saved as screenshot.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		int hourOfClickingOTPButton = 18;
//		int minuteOfClickingOTPButton = 11;
		String otp = readOTP(driver2, js, hourOfClickingOTPButton, minuteOfClickingOTPButton);
		System.out.println("Got the MESSAGE: "+otp);
		return otp;
	}

	public static String readOTP(ChromeDriver driver2, JavascriptExecutor js, int hourOfClickingOTPButton,
			int minuteOfClickingOTPButton) throws InterruptedException {

		int newmessagelistSize = 0;
		String pin = "Not Found";
		String message = "NoMessage";
		
		System.out.println("curent messagelistSize is " + newmessagelistSize);

	    waitMessageNew = new WebDriverWait(driver2, Duration.ofSeconds(120));
		waitMessageNew.until(ExpectedConditions
				.numberOfElementsToBeMoreThan((By.xpath("//div[@class='_1BOF7 _2AOIt']")), newmessagelistSize));

		List<WebElement> newmessagelistCheckAgain = driver2.findElements(By.xpath("//div[@class='_1BOF7 _2AOIt']"));
		int newmessagelistSize2 = newmessagelistCheckAgain.size();
		System.out.println("curent messagelistSize is " + newmessagelistSize2);
		message = newmessagelistCheckAgain.get(newmessagelistSize2 - 1).getText();
        
		
		try {
			System.out.println(message);
			if (message.toLowerCase().contains("check your phone")) {

				// Hard Refresh
				((JavascriptExecutor) driver2).executeScript("location.reload(true);");

				searchForChatAndClick(driver2);
				
				List<WebElement> newmessagelistNew2 = driver2.findElements(By.xpath("//div[@class='_1BOF7 _2AOIt']"));
				int newmessagelistSizeNew2 = newmessagelistNew2.size();
				message = newmessagelistNew2.get(newmessagelistSizeNew2 - 1).getText();
			}
		
		String hourAndMin = null;
		
		
		try {
			hourAndMin = message.split("reply!")[1].trim();
		} catch (Exception e) {
			System.out.println("Handling this exception in trimming");
			
		}

		try {
			int length = message.length();
			String trimmedString = message.trim();
			hourAndMin = length <= 5 ? trimmedString : trimmedString.substring(length - 5);
			System.out.println(hourAndMin);
		} catch (Exception e) {
			System.out.println("Handling this exception in trimming again");
			
		}
		
		int hourOfReadMessage = Integer.parseInt(hourAndMin.split(":")[0].trim());
		int minOfReadMessage = Integer.parseInt(hourAndMin.split(":")[1].substring(0, 2).trim());
		LocalTime timeOfClickingOTPSend = LocalTime.of(hourOfClickingOTPButton, minuteOfClickingOTPButton);
		System.out.println("Time of clicking send OTP was " + timeOfClickingOTPSend);
		LocalTime timeOfWhatsAppMessageReceived = LocalTime.of(hourOfReadMessage, minOfReadMessage);
		System.out.println("Time of Receiving OTP was " + timeOfWhatsAppMessageReceived);

		if (timeOfWhatsAppMessageReceived.isBefore(timeOfClickingOTPSend)) {
			System.out.println(
					"timeOfWhatsAppMessageReceived is before timeOfClickingOTPSend - special scenario - stale otp ");
			System.out.println("Due to old OTP waiting for new OTP to appear");


			waitMessageNew = new WebDriverWait(driver2, Duration.ofSeconds(120));
			waitMessageNew.until(ExpectedConditions
					.numberOfElementsToBeMoreThan((By.xpath("//div[@class='_1BOF7 _2AOIt']")), newmessagelistSize2));

			System.out.println("Wait over , got a new message");

			// Get the size of new message list
			List<WebElement> newmessagelistNewH = driver2.findElements(By.xpath("//div[@class='_1BOF7 _2AOIt']"));
			int newmessagelistSizeNewH = newmessagelistNewH.size();
			System.out.println("newmessagelistSizeNew is " + newmessagelistSizeNewH);

			message = newmessagelistNewH.get(newmessagelistSizeNewH - 1).getText();

		} else if (timeOfWhatsAppMessageReceived.isAfter(timeOfClickingOTPSend)) {
			System.out.println("Regular scenario : Fine Go Ahead");
		} else {
			System.out.println("Regular scenario Same time , Fine Go Ahead");
		}
	}catch(Exception e23)
	{
		e23.printStackTrace();
		System.out.println("Some exception in the whatsapp logic of checking time");
	}

	System.out.println("Message is "+message);
	return message;
	}

	private static void searchForChatAndClick(ChromeDriver driver2) {
		searchForChatEnterSearch = driver2.findElement(By.xpath("//div[@title=\"Search input textbox\"]"));
		searchForChatEnterSearch.clear();
		String toSearch = "75095";
		String[] arrayMobile = toSearch.split("");
		for (String e : arrayMobile) {
		
			searchForChatEnterSearch.sendKeys(e);
		}
		driver2.findElement(By.xpath("//span[contains(@title ,\"85643\") or contains(@title ,\"Zipr\")]")).click();
		
	}

	private static void smallSleep() {
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private static void switchToWhatsappSite(ChromeDriver driver2) {
		Set<String> windowHandles = driver2.getWindowHandles();

		for (String winHandle : windowHandles) {
			driver2.switchTo().window(winHandle);
			System.out.println(driver2.getCurrentUrl());
			if (driver2.getCurrentUrl().toLowerCase().contains("whatsapp")) {
				break;
			} else {
				continue;
			}
		}

		System.out.println("Current window title is " + driver2.getTitle());

	}

}

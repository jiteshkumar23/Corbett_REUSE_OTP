package NightStay;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FullFlowWithWaits_Profile1 extends DataProfile1 {
	static ChromeDriver driver;
	static JavascriptExecutor js;
	static WebDriverWait wait;
	static WebDriverWait waitnew;
	static boolean closed = true;
	static int intNumberOfAdultsFromExcel;

	@BeforeTest()
	public static void prerequisite() throws InterruptedException, IOException {
		// Create ChromeOptions instance
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		// Set the remote debugging address
		options.setExperimentalOption("debuggerAddress", "localhost:7777");

		driver = new ChromeDriver(options);
		js = (JavascriptExecutor) driver;
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	@Test
	private static void FillFormPart1AndClickFindPart2() throws Exception {
		System.out.println("###################   Starting   ###################");
		System.out.println("");
		printDateTime("Profile 1 Starting Time -->");

		System.out.println(checkInDateFromExcel);
		System.out.println(checkOutDateFromExcel);

		// check that time is after this time
		// checkForTime(WaitsProfile1.hourForStarting, WaitsProfile1.minuteForStarting,
		// WaitsProfile1.secondForStarting);

		// Hard refresh
		js.executeScript("location.reload(true);");

		// Launch website
		driver.get(URLString);
		Thread.sleep(1000);

		// check for portal close
		checkForPortalClose(driver);

		System.out.println("Profile 1 - Chrome Browser - Current page URL is :" + driver.getCurrentUrl());

		// check Geolocation popup
		Thread.sleep(500);
		try {
			clickOkonGeoLocationPopup(driver);
		} catch (Exception e) {
			clickOkonGeoLocationPopup(driver);
		}

		// check for second popup
		try {
			clickOkonPopup(driver, wait);
		} catch (Exception e) {
			clickOkonPopup(driver, wait);
		}
		smallSleep();

		// click on input
		try {
			waitForVisibilityAndClick(driver, wait, "//input[@id=\"checkInDate\"]");
		} catch (Exception e) {
			System.out.println("I am caught in exception in profile 1, trying to get a solution");
			Thread.sleep(1000);
			clickOkonPopup(driver, wait);
			waitForVisibilityAndClick(driver, wait, "//input[@id=\"checkInDate\"]");
		}

		// Checking and clicking check-in date
		boolean disabledFlag = checkINDateBeforeClicking(driver, js, wait, checkInDateFromExcel);
		while (disabledFlag) {
			try {
				if (disabledFlag == true) {
					// Hard Page Refresh
					((JavascriptExecutor) driver).executeScript("location.reload(true);");
					try {
						clickOkonGeoLocationPopup(driver);
					} catch (Exception e) {
						clickOkonGeoLocationPopup(driver);
					}
					try {
						clickOkonPopup(driver, wait);
					} catch (Exception e) {
						clickOkonPopup(driver, wait);
					}
					waitForVisibilityAndClick(driver, wait, "//input[@id=\"checkInDate\"]");
					disabledFlag = checkINDateBeforeClicking(driver, js, wait, checkInDateFromExcel);
				} else {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception here" + e);
				if (disabledFlag == true) {
					// Hard Page Refresh
					((JavascriptExecutor) driver).executeScript("location.reload(true);");
					try {
						clickOkonGeoLocationPopup(driver);
					} catch (Exception e1) {
						clickOkonGeoLocationPopup(driver);
					}
					try {
						clickOkonPopup(driver, wait);
					} catch (Exception e1) {
						clickOkonPopup(driver, wait);
					}
					waitForVisibilityAndClick(driver, wait, "//input[@id=\"checkInDate\"]");
					disabledFlag = checkINDateBeforeClicking(driver, js, wait, checkInDateFromExcel);
				} else {
					break;
				}

			}
		}

		// click on input
		printDateTime("Execution Start Time - Enhanced");
		waitForVisibilityAndClick(driver, wait, "//input[@id=\"checkOutDate\"]");

		// Checking and clicking check-out date
		boolean WasThereAnIssueWithcheckoutdateClick = checkOUTDateBeforeClicking(driver, js, wait,
				checkOutDateFromExcel);
		System.out.println("WasThereAnIssueWithcheckoutdateClick is :" + WasThereAnIssueWithcheckoutdateClick);

		// wait and select number of rooms
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='requiredRooms']")));
		WebElement numberOfRoomsDropDown = driver.findElement(By.xpath("//select[@id='requiredRooms']"));
		Select selectDropDown = new Select(numberOfRoomsDropDown);
		System.out.println("Number of rooms to select is : " + numberOfRooms);
		selectDropDown.selectByValue(numberOfRooms);

		// wait and select number of Adults
		WebElement numberOfAdultsDropDown = driver.findElement(By.xpath("//select[@id='totalAdults']"));
		wait.until(ExpectedConditions.elementToBeClickable(numberOfAdultsDropDown));
		Select selectDropDownForAdults = new Select(numberOfAdultsDropDown);
		System.out.println("Number of Adults to select is : " + NumberOfAdultsFromExcel);
		selectDropDownForAdults.selectByValue(NumberOfAdultsFromExcel);

		// wait and select number of Children
		WebElement numberOfChildrenDropDown = driver.findElement(By.xpath("//select[@id='totalChildren']"));
		wait.until(ExpectedConditions.elementToBeClickable(numberOfChildrenDropDown));
		Select selectDropDownForChildren = new Select(numberOfChildrenDropDown);
		System.out.println("Number of Children to select is : " + numberOfChildren);
		selectDropDownForChildren.selectByValue(numberOfChildren);

		// wait and select PaxType
		int paxCount = Integer.parseInt(NumberOfAdultsFromExcel);

		for (int i = 1; i <= paxCount; i++) {
			String nationality = null;
			switch (i) {
			case 1:
				nationality = nationalityOfFirstPersonFromExcel;
				break;
			case 2:
				nationality = NationalityOfSecondPerson;
				break;
			case 3:
				nationality = NationalityOfThirdPerson;
				break;
			case 4:
				nationality = NationalityOfFourthPerson;
				break;
			case 5:
				nationality = NationalityOfFifthPerson;
				break;
			case 6:
				nationality = NationalityOfSixthPerson;
				break;
			}
			WebElement paxDropDown = driver.findElement(By.xpath("//select[@id='pax" + i + "']"));
			wait.until(ExpectedConditions.elementToBeClickable(paxDropDown));
			Select selectDropDownForPax = new Select(paxDropDown);
			System.out.println("Value of Nationality to select is : " + nationality);
			selectDropDownForPax.selectByValue(nationality);
		}

		// click on Find Button
		sleepForSeconds();
		driver.findElement(By.xpath("//button[@id='go-button']")).click();
		Thread.sleep(500);

		printDateTime("FillFormPart1AndClickFindPart2 Ended At ");
	}

	@Test
	private static void SelectRoomPart3() throws Exception {
		String[] arrayRooms = { roomPrioirty1, roomPrioirty2, roomPrioirty3, roomPrioirty4, roomPrioirty5,
				roomPrioirty6, roomPrioirty7, roomPrioirty8 };

		printDateTime("SelectRoomPart3 Started At ");
		waitnew = new WebDriverWait(driver, Duration.ofSeconds(360));
		try {
			waitnew.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("(//div[contains(@class,'resort-img-holder')])[1]")));
		} catch (Exception e) {
			System.out.println("Exception waiting for restort image 1");
		}

		// Thread.sleep(2000);

		List<WebElement> cardTitlesH5 = driver.findElements(By.xpath("//h5[contains(@class,'card-title')]"));

		int roomsAvailableCount = cardTitlesH5.size();
		System.out.println("Number of rooms available is : " + roomsAvailableCount);

		// Resort to be selected in order of priority

		boolean found = false;
		String roomType = "";
		for (int j = 0; j < arrayRooms.length; j++) {

			System.out.println("Originally searching for " + arrayRooms[j]);

			try {
				if (arrayRooms[j].toLowerCase().contains("bijrani")) {
					roomType = arrayRooms[j].split(":")[1];
					arrayRooms[j] = arrayRooms[j].split(":")[0];
				}
			} catch (Exception e) {
				System.out.println(arrayRooms[j]);
			}
			System.out.println("Updated Searching for " + arrayRooms[j]);
			for (int i = 0; i < roomsAvailableCount; i++) {
				System.out.println(cardTitlesH5.get(i).getText());
				if (cardTitlesH5.get(i).getText().contains(arrayRooms[j])) {
					System.out.println(arrayRooms[j] + " was found");

					if (arrayRooms[j].toLowerCase().contains("bijrani")) {

						WebElement roomTypeBijrani = driver.findElements(By
								.xpath("//h5[contains(@class,'card-title')]/../following-sibling::table//tr[3]//td[2]"))
								.get(i);
						System.out.println("Seen on screen :" + roomTypeBijrani.getText());
						System.out.println("what we want to book: " + roomType);
						if (!roomTypeBijrani.getText().toLowerCase().contains(roomType)) {
							System.out.println("Expected Room Type of Bijrani was not found: " + roomType);
							continue;
						}
					}

					WebElement bookNowtobeClicked = driver.findElements(By.xpath("//button[contains(text(),'Book')]"))
							.get(i);
					try {
						
						js.executeScript("arguments[0].click();", bookNowtobeClicked);
						
					//	driver.findElements(By.xpath("//button[contains(text(),'Book')]")).get(i).click();
					} catch (Exception ee) {
						try {
							Thread.sleep(1000);
							System.out.println("I am in first exception of Book Now button click for profile 1 " + ee);
						//	driver.findElements(By.xpath("//button[contains(text(),'Book')]")).get(i).click();
							js.executeScript("arguments[0].click();", bookNowtobeClicked);
						} catch (Exception ee1) {
							System.out.println("I am in second exception of Book Now button click for profile 1 " + ee);
							System.out.println(ee1);
							js.executeScript("arguments[0].scrollIntoView(true);",
									driver.findElements(By.xpath("//button[contains(text(),'Book')]")).get(i));
							try {
								scrollToElementUsingMouse(driver,
										driver.findElements(By.xpath("//button[contains(text(),'Book')]")).get(i));
							} catch (Exception e) {
								System.out.println(e);
							}
							Thread.sleep(1500);
							wait.until(ExpectedConditions.elementToBeClickable(
									driver.findElements(By.xpath("//button[contains(text(),'Book')]")).get(i)));
						//	driver.findElements(By.xpath("//button[contains(text(),'Book')]")).get(i).click();
							js.executeScript("arguments[0].click();", bookNowtobeClicked);
						}
					}
					found = true;
					break;
				} else {
					System.out.println("Expected room " + arrayRooms[j] + " was not found");

				}
			}
			if (found == true) {
				break;
			}
		}
		sleepForSeconds();
		printDateTime("SelectRoomPart3 Ended At ");
	}

	@Test
	private static void FillMemberDetailsPart4() throws Exception {
		printDateTime("FillMemberDetailsPart4 Started At ");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='pax-info-modal']")));

		// First Person Data Input
		intNumberOfAdultsFromExcel = Integer.parseInt(NumberOfAdultsFromExcel);
		if (intNumberOfAdultsFromExcel >= 1) {

			sendTextHumanLikeName(driver.findElement(By.id("name-0")), nameOfFirstPersonFromExcel);
			specificWait();
			WebElement dropdownSex1 = driver.findElement(By.id("gender-0"));
			dropdownSex1.click();
			Thread.sleep(WaitsProfile1.delayInGenderDropdown);
			Select select3 = new Select(dropdownSex1);
			select3.selectByVisibleText(genderOfFirstPersonFromExcel);
			specificWait();
			selectForeignCountry(driver, wait, nationalityOfFirstPersonFromExcel, "country-pax-0", countryFirstPerson);
			specificWait();
			WebElement dropdownID1 = driver.findElement(By.id("id-type-0"));
			smallSleep();
			dropdownID1.click();
			Thread.sleep(WaitsProfile1.delayInProofDropdown);
			Select select5 = new Select(dropdownID1);
			smallSleep();
			// Iterate through the options and select the one containing the desired partial text
	        for (WebElement option : select5.getOptions()) {
	            if (option.getText().contains(IdTypeOfFirstPerson)) {
	            	select5.selectByVisibleText(option.getText());
	                break; // Break the loop once the desired option is found
	            }
	        }
	        specificWait();
			sendTextHumanLikeID(driver.findElement(By.id("id-proof-0")), IdNumberOfFirstPerson);
			specificWait();
			sendTextHumanLikeAge(driver.findElement(By.id("age-0")), ageOfFirstPersonFromExcel);
			if (intNumberOfAdultsFromExcel == 1) {
				Thread.sleep(WaitsProfile1.person1wait);
			}
		}

		// Second Person Data Input
		if (intNumberOfAdultsFromExcel >= 2) {

			sendTextHumanLikeName(driver.findElement(By.id("name-2")), NameOfSecondPerson);
			specificWait();
			WebElement dropdownSex2 = driver.findElement(By.id("gender-2"));
			dropdownSex2.click();
			Thread.sleep(WaitsProfile1.delayInGenderDropdown);
			Select select6 = new Select(dropdownSex2);
			select6.selectByVisibleText(GenderOfSecondPerson);
			specificWait();
			selectForeignCountry(driver, wait, NationalityOfSecondPerson, "country-pax-2", countrySecondPerson);
			specificWait();
			WebElement dropdownID2 = driver.findElement(By.id("id-type-2"));
			smallSleep();
			dropdownID2.click();
			Thread.sleep(WaitsProfile1.delayInProofDropdown);
			Select select7 = new Select(dropdownID2);
			smallSleep();
			// Iterate through the options and select the one containing the desired partial text
	        for (WebElement option : select7.getOptions()) {
	            if (option.getText().contains(IdTypeOfSecondPerson)) {
	            	select7.selectByVisibleText(option.getText());
	                break; // Break the loop once the desired option is found
	            }
	        }
			specificWait();
			sendTextHumanLikeID(driver.findElement(By.id("id-proof-2")), IdNumberOfSecondPerson);
			specificWait();
			sendTextHumanLikeAge(driver.findElement(By.id("age-2")), AgeOfSecondPerson);
			if (intNumberOfAdultsFromExcel == 2) {
				Thread.sleep(WaitsProfile1.person2wait);
			}

		}

		// Third Person Data Input
		if (intNumberOfAdultsFromExcel >= 3) {
			sendTextHumanLikeName(driver.findElement(By.id("name-3")), NameOfThirdPerson);
			specificWait();
			WebElement dropdownSex3 = driver.findElement(By.id("gender-3"));
			dropdownSex3.click();
			Thread.sleep(WaitsProfile1.delayInGenderDropdown);
			Select select8 = new Select(dropdownSex3);
			select8.selectByVisibleText(GenderOfThirdPerson);
			specificWait();
			selectForeignCountry(driver, wait, NationalityOfThirdPerson, "country-pax-3", countryThirdPerson);
			specificWait();
			WebElement dropdownID3 = driver.findElement(By.id("id-type-3"));
			smallSleep();
			dropdownID3.click();
			Thread.sleep(WaitsProfile1.delayInProofDropdown);
			Select select9 = new Select(dropdownID3);
			smallSleep();
			// Iterate through the options and select the one containing the desired partial text
	        for (WebElement option : select9.getOptions()) {
	            if (option.getText().contains(IdTypeOfThirdPerson)) {
	            	select9.selectByVisibleText(option.getText());
	                break; // Break the loop once the desired option is found
	            }
	        }
			smallSleep();
			sendTextHumanLikeID(driver.findElement(By.id("id-proof-3")), IdNumberOfThirdPerson);
			specificWait();
			sendTextHumanLikeAge(driver.findElement(By.id("age-3")), AgeOfThirdPerson);
			if (intNumberOfAdultsFromExcel == 3) {
				Thread.sleep(WaitsProfile1.person3wait);
			}
			
		}

		// Fourth Person Data Input
		if (intNumberOfAdultsFromExcel >= 4) {
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("name-4")));
			Thread.sleep(500);
			scrollToTop(driver, 2);
			Thread.sleep(500);
			sendTextHumanLikeName(driver.findElement(By.id("name-4")), NameOfFourthPerson);
			specificWait();
			WebElement dropdownSex4 = driver.findElement(By.id("gender-4"));
			dropdownSex4.click();
			Thread.sleep(WaitsProfile1.delayInGenderDropdown);
			Select select10 = new Select(dropdownSex4);
			select10.selectByVisibleText(GenderOfFourthPerson);
			specificWait();
			selectForeignCountry(driver, wait, NationalityOfFourthPerson, "country-pax-4", countryFourthPerson);
			specificWait();
			WebElement dropdownID4 = driver.findElement(By.id("id-type-4"));
			smallSleep();
			dropdownID4.click();
			Thread.sleep(WaitsProfile1.delayInProofDropdown);
			Select select11 = new Select(dropdownID4);
			smallSleep();
			// Iterate through the options and select the one containing the desired partial text
	        for (WebElement option : select11.getOptions()) {
	            if (option.getText().contains(IdTypeOfFourthPerson)) {
	            	select11.selectByVisibleText(option.getText());
	                break; // Break the loop once the desired option is found
	            }
	        }
			smallSleep();
			sendTextHumanLikeID(driver.findElement(By.id("id-proof-4")), IdNumberOfFourthPerson);
			specificWait();
			sendTextHumanLikeAge(driver.findElement(By.id("age-4")), AgeOfFourthPerson);
			if (intNumberOfAdultsFromExcel == 4) {
				Thread.sleep(WaitsProfile1.person4wait);
			}
			
		}

		// Fifth Person Data Input
		if (intNumberOfAdultsFromExcel >= 5) {
			sendTextHumanLikeName(driver.findElement(By.id("name-5")), NameOfFifthPerson);
			specificWait();
			WebElement dropdownSex5 = driver.findElement(By.id("gender-5"));
			dropdownSex5.click();
			Thread.sleep(WaitsProfile1.delayInGenderDropdown);
			Select select12 = new Select(dropdownSex5);
			select12.selectByVisibleText(GenderOfFifthPerson);
			specificWait();
			selectForeignCountry(driver, wait, NationalityOfFifthPerson, "country-pax-5", countryFifthPerson);
			specificWait();
			WebElement dropdownID5 = driver.findElement(By.id("id-type-5"));
			smallSleep();
			dropdownID5.click();
			Thread.sleep(WaitsProfile1.delayInProofDropdown);
			Select select13 = new Select(dropdownID5);
			smallSleep();
			// Iterate through the options and select the one containing the desired partial text
	        for (WebElement option : select13.getOptions()) {
	            if (option.getText().contains(IdTypeOfFifthPerson)) {
	            	select13.selectByVisibleText(option.getText());
	                break; // Break the loop once the desired option is found
	            }
	        }
			smallSleep();
			sendTextHumanLikeID(driver.findElement(By.id("id-proof-5")), IdNumberOfFifthPerson);
			specificWait();
			sendTextHumanLikeAge(driver.findElement(By.id("age-5")), AgeOfFifthPerson);
			if (intNumberOfAdultsFromExcel == 5) {
				Thread.sleep(WaitsProfile1.person5wait);
			}
		}

		// Sixth Person Data Input
		if (intNumberOfAdultsFromExcel >= 6) {
			sendTextHumanLikeName(driver.findElement(By.id("name-6")), NameOfSixthPerson);
			specificWait();
			WebElement dropdownSex6 = driver.findElement(By.id("gender-6"));
			dropdownSex6.click();
			Thread.sleep(WaitsProfile1.delayInGenderDropdown);
			Select select14 = new Select(dropdownSex6);
			select14.selectByVisibleText(GenderOfFifthPerson);
			specificWait();
			selectForeignCountry(driver, wait, NationalityOfSixthPerson, "country-pax-6", countrySixthPerson);
			specificWait();
			WebElement dropdownID6 = driver.findElement(By.id("id-type-6"));
			smallSleep();
			dropdownID6.click();
			Thread.sleep(WaitsProfile1.delayInProofDropdown);
			Select select15 = new Select(dropdownID6);
			smallSleep();
			// Iterate through the options and select the one containing the desired partial text
	        for (WebElement option : select15.getOptions()) {
	            if (option.getText().contains(IdTypeOfSixthPerson)) {
	            	select15.selectByVisibleText(option.getText());
	                break; // Break the loop once the desired option is found
	            }
	        }
	        sendTextHumanLikeID(driver.findElement(By.id("id-proof-6")), IdNumberOfSixthPerson);
			specificWait();
			sendTextHumanLikeAge(driver.findElement(By.id("age-6")), AgeOfSixthPerson);
			if (intNumberOfAdultsFromExcel == 6) {
				Thread.sleep(WaitsProfile1.person6wait);
			}
		}
	
		printDateTime("FillMemberDetailsPart4 Ended At ");

	}

	private static void selectForeignCountry(ChromeDriver driver2, WebDriverWait wait2,
			String nationalityOfPersonFromExcel, String locator, String countryPerson) {
		try {
			if (nationalityOfPersonFromExcel.equalsIgnoreCase("foreigner")) {

				if (countryPerson.equalsIgnoreCase("USA")) {
					driver.findElement(By.id(locator)).click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li/a[text()='A']"))))
							.click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//li/a[@data-country=\"america\"]")))).click();
				}

				else if (countryPerson.equalsIgnoreCase("United Kingdom")) {
					driver.findElement(By.id(locator)).click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li/a[text()='U']"))))
							.click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//li/a[@data-country=\"united-kingdom\"]"))))
							.click();
				} else if (countryPerson.equalsIgnoreCase("Canada")) {
					driver.findElement(By.id(locator)).click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li/a[text()='C']"))))
							.click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//li/a[@data-country=\"canada\"]")))).click();
				} else if (countryPerson.equalsIgnoreCase("Australia")) {
					driver.findElement(By.id(locator)).click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li/a[text()='A']"))))
							.click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//li/a[@data-country=\"australia\"]")))).click();
				} else if (countryPerson.equalsIgnoreCase("Italy")) {
					driver.findElement(By.id(locator)).click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li/a[text()='I']"))))
							.click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//li/a[@data-country=\"italy\"]")))).click();
				} else {
					driver.findElement(By.id(locator)).click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);
					wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li/a[text()='A']"))))
							.click();
					Thread.sleep(WaitsProfile1.delayInCountryDropdown);	 	
					wait.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//li/a[@data-country=\"america\"]")))).click();
				}

			}
		} catch (Exception e) {
			System.out.println("Expection in selecting foreign country :" + e);
		}
	}

	@Test
	private static void EnterMobileAndOtpPart5() throws Exception {
		// System.out.println(AgeOfSixthPerson);
		printDateTime("EnterMobileAndOtpPart5 Started At ");
		switchToCorbetteSite(driver);
		scrollDownUsingMouse(driver);

		WebElement mobileNumberBox = driver.findElement(By.id("mobile-0"));
		scrollToElementUsingMouse(driver, mobileNumberBox);
		mobileNumberBox.click();
		mobileNumberBox.clear();

		sendTextHumanLikeMobileNumber(mobileNumberBox, mobileNumber);

		WebElement whatapplogo = driver.findElement(By.xpath("//img[@alt=\"WhatsApp Logo\"]"));
		scrollToElementUsingMouse(driver, whatapplogo);
		whatapplogo.click();
		// Thread.sleep(2235);
		WebElement sendOTPButton = driver.findElement(By.id("sendOTP"));
		scrollToElementUsingMouse(driver, sendOTPButton);
		sendOTPButton.click();

		// capture hour and min here

		// Get the current time
		LocalTime currentTime = LocalTime.now();

		// Get hour and minute components
		int hourOfClickingOTPButton = currentTime.getHour();
		int minuteOfClickingOTPButton = currentTime.getMinute();

		System.out.println(
				"OTP Button Clicked on hour " + hourOfClickingOTPButton + " and minute " + minuteOfClickingOTPButton);

		waitForOnlyVisibility(driver, wait, "//button[text()='Ok']");
		Thread.sleep(1133);
		WebElement okButton = driver.findElement(By.xpath("//button[text()='Ok']"));
		scrollToElementUsingMouse(driver, okButton);
		okButton.click();
		scrollToElementUsingMouse(driver, whatapplogo);

		waitForOnlyVisibility(driver, wait, "//div[@class='overlay d-flex text-center flex-column']");
		
		printDateTime("Black box started displaying at this time -->");
		
		switch(intNumberOfAdultsFromExcel) {
		case 1:
			Thread.sleep(WaitsProfile1.person1BoxRemove);
			break;
		case 2:
			Thread.sleep(WaitsProfile1.person2BoxRemove);
			break;
		case 3:
			Thread.sleep(WaitsProfile1.person3BoxRemove);
			break;
		case 4:
			Thread.sleep(WaitsProfile1.person4BoxRemove);
			break;
		case 5:
			Thread.sleep(WaitsProfile1.person5BoxRemove);
			break;
		case 6:
			Thread.sleep(WaitsProfile1.person6BoxRemove);
			break;
		}
		
		printDateTime("Wait ended at this time -->");
		
		// Find the WebElement you want to delete
		WebElement elementToDelete = driver.findElement(By.xpath("//div[@id='mobile-otp-overlay']"));

		// Use JavascriptExecutor to delete the element
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].remove();", elementToDelete);
		
		printDateTime("Black box removed at this time -->");
		
		WebElement otpbox = driver.findElement(By.id("otp"));
		
		//this will enable the otp box
		String scriptToDeleteAttribute = "arguments[0].removeAttribute(arguments[1]);";
        jsExecutor.executeScript(scriptToDeleteAttribute, otpbox, "disabled");
		
		scrollToElementUsingMouse(driver, otpbox);
		wait.until(ExpectedConditions.elementToBeClickable(otpbox));
		otpbox.clear();
		otpbox.click();
		String otpReceivedFromWhatsapp = otp;


		System.out.println("otp received from whatsapp is " + otpReceivedFromWhatsapp);
		otpbox.click();
		Thread.sleep(250);
		sendTextHumanLikeOtp(otpbox, otpReceivedFromWhatsapp);
		Thread.sleep(250);
		WebElement validateOTPButton = driver.findElement(By.id("validateOTP"));
		validateOTPButton.click();

		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait2.until(ExpectedConditions.alertIsPresent());

		Alert alert = driver.switchTo().alert();

		String messagefromPopup = alert.getText();
		System.out.println("ALERT MESSAGE  Profile 1 -->" + messagefromPopup);

		if (messagefromPopup.toLowerCase().contains("successful"))

		{
			alert.accept();
			scrollDownUsingMouse(driver);
			Thread.sleep(500);
			WebElement payNowButton = driver.findElement(By.xpath("//button[contains(text(),'Pay Now')]"));
			wait.until(ExpectedConditions.elementToBeClickable(payNowButton));
			//scrollToElementUsingMouse(driver, payNowButton);
			payNowButton.click();
			System.out.println("");
			System.out.println("Flow successfully completed");
			System.out.println("###################   Completed   ###################");
			printDateTime("Profile 1 Ending Time -->");
		} else {

//			alert.accept();
//			driver.findElement(By.id("otp")).clear();
//			// wait for edit button to be enabled
//			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(50));
//			WebElement b = driver.findElement(By.id("editNumber"));
//			wait3.until(ExpectedConditions.elementToBeClickable(b));
//			b.click();
//			smallSleep();
//			driver.findElement(By.id("mobile-0")).clear();
//			driver.findElement(By.id("mobile-0")).sendKeys(mobileNumber);
//			driver.findElement(By.id("sendOTP")).click();
//
//			String pinreceived2 = readOTP(driver, js);
//			System.out.println("Pin received is " + pinreceived2);
//
//			switchToCorbetteSite(driver);
//			driver.findElement(By.xpath("//button[text()='Ok']")).click();
//			driver.findElement(By.id("otp")).sendKeys(pinreceived2);
//			
//			driver.findElement(By.id("validateOTP")).click();
//
//			Alert alert2 = driver.switchTo().alert();
//
//			String messagefromPopup2 = alert2.getText();
//			System.out.println(messagefromPopup2);
//
//			System.out.println("Message2 displayed on popup is: " + messagefromPopup2);
//			if (messagefromPopup2.toLowerCase().contains("successful"))
//
//			{
//				// click ok
//
//				alert2.accept();
//				scrollDownUsingMouse(driver);
//				// click on pay now
//				driver.findElement(By.xpath("//button[normalize-space()='Pay Now']")).click();
//			}
//
//			else {
//				System.out.println("Else 2 - Message displayed on popup is: " + messagefromPopup2);
//				throw new Exception();
//			}

//		}

//		
//		printDateTime("sendOTP clicked At ");

//		String pinreceived = readOTP(driver, js);
//		System.out.println("Pin received is " + pinreceived);
//
//		// switch to official site
//
//		switchToCorbetteSite(driver);
//
//		// enter pin
////		driver.findElement(By.xpath("//button[text()='Ok']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.id("otp")).sendKeys(pinreceived);
//		Thread.sleep(3000);
//	
//		driver.findElement(By.id("validateOTP")).click();
//		  printDateTime("validateOTP clicked At ");
//		Thread.sleep(1000);

		}

		waitnew.until(ExpectedConditions.urlContains("success"));
		Thread.sleep(200);
		String grabURLFromScreen = driver.getCurrentUrl();
		System.out.println("After payment success URL is -->" + grabURLFromScreen);

		try {
			String osname = System.getProperty("os.name").toLowerCase();
			String command = "";
			if (osname.contains("win")) {
				command = "cmd /c start firefox \"" + grabURLFromScreen + "\" --start-maximized --new-window";
				System.out.println(command);
			}
			// this will be used for mac for opening safari browser
			else {
				command = "open -a Safari --args " + grabURLFromScreen;
			}
			// Using the Runtime class to execute the command
			Runtime.getRuntime().exec(command);

			System.out.println("Browser is launched");

		} catch (Exception e) {
			System.out.println("Error in opening new browser with the specified URL" + grabURLFromScreen);
			e.printStackTrace();
			// Capture a screenshot
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
			try {
				// Save the screenshot to a file
				FileUtils.copyFile(srcFile, new File("ErrorOpeningPaymentURL1.png"));
				System.out.println("Screenshot captured in headless mode and saved as screenshot.png");
			} catch (IOException ev) {
				ev.printStackTrace();
			}
		}

	}

	private static void waitForVisibilityAndClick(ChromeDriver driver, WebDriverWait wait, String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();

	}

	private static void waitForOnlyVisibility(ChromeDriver driver, WebDriverWait wait, String xpath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	}

	private static void waitForClickabilityAndClick(ChromeDriver driver, WebDriverWait wait, String xpath) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

	}

	private static void clickOkonPopup(ChromeDriver driver, WebDriverWait wait) {
		String popupStatus = driver.findElement(By.id("toast-alert")).getAttribute("class");
		System.out.println("Profile 1 -->" + popupStatus);
		try {
			if (driver.findElement(By.id("toast-alert")).getAttribute("class").toLowerCase().contains("show")) {

				while (driver.findElement(By.id("toast-alert")).getAttribute("class").toLowerCase().contains("show")) {
					System.out.println("I am in while loop Profile 1");
					System.out.println(driver.findElement(By.id("toast-alert")).getAttribute("class"));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Ok\"]")));
					// driver.findElement(By.xpath("//button[text()=\"Ok\"]")).click();
					js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()=\"Ok\"]")));
				}

			} else {
				System.out.println("Profile 1 seen in else block is -->"
						+ driver.findElement(By.id("toast-alert")).getAttribute("class"));
			}
			System.out.println("Final value of popup after exiting while loop for Profile 1 is : "
					+ driver.findElement(By.id("toast-alert")).getAttribute("class"));
		} catch (Exception e) {
			System.out.println("I am catch block profile 1" + e);
			try {
				if (driver.findElement(By.id("toast-alert")).getAttribute("class").toLowerCase().contains("show")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Ok\"]")));
					// driver.findElement(By.xpath("//button[text()=\"Ok\"]")).click();
					js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()=\"Ok\"]")));
				}
			} catch (Exception e1) {
				System.out.println(e1);

			}
		}
	}

	private static void clickOkonGeoLocationPopup(ChromeDriver driver) {

		String classOfgeolocationModalDisplayed = driver.findElement(By.id("geo-location-modal")).getAttribute("class");
		System.out.println(classOfgeolocationModalDisplayed);
		try {
			Thread.sleep(500);
			if (driver.findElement(By.id("geo-location-modal")).getAttribute("class").toLowerCase().contains("none")) {

				System.out.println("Geo location modal is not displayed");
			} else {
				driver.findElement(By.id("retry-location")).click();
			}
		} catch (Exception e) {
			clickOkonGeoLocationPopup(driver);
			System.out.println("Exception in trying to click on Geo location modal and it is : " + e);
		}
	}

	private static void scrollDownUsingMouse(ChromeDriver driver) {
		try {
			Actions actions = new Actions(driver);
			WebElement focusElement = driver.findElement(By.tagName("body"));
			// Click the element to give it focus
			actions.moveToElement(focusElement).click().build().perform();

			// Send keyboard keys for scrolling (e.g., Arrow Down)
			for (int i = 0; i < 1; i++) {
				focusElement.sendKeys(Keys.PAGE_DOWN);

			}

		} catch (Exception e) {
			System.out.println("Error on scrolling down");
		}
	}

	private static void scrollToElementUsingMouse(ChromeDriver driver, WebElement element) {
		try {
			Actions actions = new Actions(driver);

			actions.moveToElement(element).build().perform();

		}

		catch (Exception e) {
			System.out.println("Error on scrolling down");
		}
	}

	private static void scrollToTop(ChromeDriver driver, int upArrowClickCount) {
		try {
			Actions actions = new Actions(driver);
			WebElement focusElement = driver.findElement(By.tagName("body"));
			// Click the element to give it focus
			actions.moveToElement(focusElement).click().build().perform();
			for (int i = 0; i < upArrowClickCount; i++) {
				focusElement.sendKeys(Keys.ARROW_UP);

			}

		} catch (Exception e) {
			System.out.println("Error on scrolling up");
		}
	}

	private static void smallSleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static boolean checkINDateBeforeClicking(ChromeDriver driver, JavascriptExecutor js, WebDriverWait wait,
			String dateFromExcel) {
		// Check In Date
		System.out.println(dateFromExcel);
		String[] parts = dateFromExcel.split("-");

		String year = parts[2];

		String month = parts[1];

		String date = parts[0];

		System.out.println(year);
		System.out.println(month);
		System.out.println(date);

		// getting to year
		try {
			waitForOnlyVisibility(driver, wait, "(//thead//th[@class='datepicker-switch'])[1]");
		} catch (Exception e) {
			System.out.println("Checkin date box didnot open in profile 1");
			waitForVisibilityAndClick(driver, wait, "//input[@id=\"checkInDate\"]");
		}
		driver.findElement(By.xpath("(//thead//th[@class='datepicker-switch'])[1]")).click();
		driver.findElement(By.xpath("(//thead//th[@class='datepicker-switch'])[2]")).click();

		// clicking year
		String checkYearStatus = driver.findElement(By.xpath("//tbody//span[text()='" + year + "']"))
				.getAttribute("class");
		if (checkYearStatus.contains("disabled")) {
			return true;
		} else {
			driver.findElement(By.xpath("//tbody//span[text()='" + year + "']")).click();
		}

		// clicking month
		String checkMonthStatus = driver
				.findElement(By.xpath("//div[@class='datepicker-months']//span[text()='" + month + "']"))
				.getAttribute("class");
		if (checkMonthStatus.contains("disabled")) {
			return true;
		} else {
			driver.findElement(By.xpath("//div[@class='datepicker-months']//span[text()='" + month + "']")).click();
		}

		// getting to date
		WebElement dateElement = driver.findElement(By.xpath(
				"(//tr/td[@class='today active day' or @class='day' or @class='highlighted day' or @class='new disabled day' or @class='disabled day' or @class='disabled highlighted day'][text()="
						+ date + "])[1]"));

		wait.until(ExpectedConditions.elementToBeClickable(dateElement));

		String status = dateElement.getAttribute("class");

		boolean disabledFlag = false;
		if (status.contains("disabled")) {
			disabledFlag = true;

			System.out.println("Previous Attribute Value: " + status);
//
//			js.executeScript("arguments[0].setAttribute('class', 'day');", dateElement);
//			smallSleep();
//			System.out.println("New Attribute Value: " + dateElement.getAttribute("class"));
//			dateElement.click();

		} else {

			dateElement.click();
		}
		return disabledFlag;
	}

	private static boolean checkOUTDateBeforeClicking(ChromeDriver driver, JavascriptExecutor js, WebDriverWait wait,
			String dateFromExcel) {
		// Check In Date

		System.out.println(dateFromExcel);
		String[] parts = dateFromExcel.split("-");

		String year = parts[2];

		String month = parts[1];

		String date = parts[0];

		System.out.println(year);
		System.out.println(month);
		System.out.println(date);

		// getting to year
		driver.findElement(By.xpath("(//thead//th[@class='datepicker-switch'])[1]")).click();
		driver.findElement(By.xpath("(//thead//th[@class='datepicker-switch'])[2]")).click();

		// clicking year
		driver.findElement(By.xpath("//tbody//span[text()='" + year + "']")).click();

		// getting to month
		waitForClickabilityAndClick(driver, wait, "//div[@class='datepicker-months']//span[text()='" + month + "']");

		// getting to date
		WebElement dateElement = null;
		try {
			dateElement = driver.findElement(By.xpath(
					"(//tr/td[@class='highlighted day' or @class='active day' or @class='day' or @class='active highlighted day'][text()="
							+ date + "])[1]"));
			wait.until(ExpectedConditions.elementToBeClickable(dateElement));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in clicking checkout date, it may not be active");
		}

		String status = dateElement.getAttribute("class");

		boolean disabledFlag = false;
		if (status.contains("disabled")) {
			disabledFlag = true;

		} else {
			disabledFlag = false;
			dateElement.click();
		}
		return disabledFlag;

	}

	private static void switchToCorbetteSite(ChromeDriver driver) {
		Set<String> windowHandles = driver.getWindowHandles();

		for (String winHandle : windowHandles) {
			driver.switchTo().window(winHandle);
			System.out.println(driver.getCurrentUrl());
			if (driver.getCurrentUrl().toLowerCase().contains("corbett")) {
				break;
			} else {
				continue;
			}
		}

		System.out.println("Current window title is " + driver.getTitle());

	}

	public static String readOTP(ChromeDriver driver, JavascriptExecutor js) throws InterruptedException {
		switchToWhatsappSite(driver);
		int newmessagelistSize = 0;
		String pin = "Not Found ";

		for (int i = 1; i < 240; i++) {
			Thread.sleep(1000);

			// random mouse movement

			int mod = i % 2;
			if (mod == 0) {
				switchToCorbetteSite(driver);
				performRandomMouseMovementOnPage(driver, 20);
				switchToWhatsappSite(driver);
			}

			// Get the size of new message list
			List<WebElement> newmessagelist = driver.findElements(By.xpath("//div[@class='_1BOF7 _2AOIt']"));
			newmessagelistSize = newmessagelist.size();
			System.out.println("newmessagelistSize is " + newmessagelistSize);
			if (newmessagelistSize > 0) {
				String message = newmessagelist.get(newmessagelistSize - 1).getText();
				System.out.println(message);

				String[] parts = message.split(":|\\s+");

				// Iterate through the parts and find the one that contains digits
				for (String part : parts) {
					if (part.matches("\\d+")) {
						System.out.println("PIN: " + part);
						pin = part;
						System.out.println("And the pin is " + pin);
						break; // Exit the loop once the PIN is found

					}
				}
				break;
			} else {
				System.out.println("Pin in attempt number " + i + " is " + pin);
				continue;
			}
		}

		System.out.println("Final Pin is " + pin);
		return pin;
	}

	private static String deleteWhatAppChat(ChromeDriver driver) {

		switchToWhatsappSite(driver);

		// Locate the chat you want to read
		WebElement searchForChatEnterSearch = null;
		try {
			searchForChatEnterSearch = driver.findElement(By.xpath("//div[@title=\"Search input textbox\"]"));
			searchForChatEnterSearch.sendKeys("check");
		} catch (Exception e) {
			System.out.println("Search Input box was not found");
			return "Search Input box was not found";
		}
		smallSleep();
		WebElement searchForChat = driver.findElement(By.xpath("//div[@title=\"Search input textbox\"]//span"));
		searchForChat.sendKeys(Keys.CONTROL + "a");
		searchForChat.sendKeys(Keys.DELETE);
		// searchForChat.clear();
		searchForChatEnterSearch.sendKeys("+91 75095 85643");
		smallSleep();
		WebElement chat = driver.findElement(By.xpath("//span[@title=\"+91 75095 85643\"]"));
		// Click on the chat to open it
		chat.click();

		deleteChat(driver);
		return "Delete Whatapp chat is complete";

	}

	private static void switchToWhatsappSite(ChromeDriver driver) {
		Set<String> windowHandles = driver.getWindowHandles();

		for (String winHandle : windowHandles) {
			driver.switchTo().window(winHandle);
			System.out.println(driver.getCurrentUrl());
			if (driver.getCurrentUrl().toLowerCase().contains("whatsapp")) {
				break;
			} else {
				continue;
			}
		}

		System.out.println("Current window title is " + driver.getTitle());

	}

	private static void deleteChat(ChromeDriver driver) {
		driver.findElement(By.xpath("(//div[@role='button' and @title='Menu'])[2]")).click();
		smallSleep();
		smallSleep();
		driver.findElement(By.xpath("//div[text()='Clear chat']")).click();
		smallSleep();
		smallSleep();
		driver.findElement(By.xpath("//button//div[text()='Clear chat']")).click();
		smallSleep();

		System.out.println("chat cleared");

	}

	public static void performRandomClicksOnPage(ChromeDriver driver) {

		Random random = new Random();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int numberOfClicks = 10; // Change this to the desired number of random clicks

		for (int i = 0; i < numberOfClicks; i++) {
			int randomX, randomY;
			do {
				randomX = random.nextInt(driver.manage().window().getSize().getWidth());
				randomY = random.nextInt(driver.manage().window().getSize().getHeight());
			} while (randomX <= 0 || randomY <= 0);

			// Scroll to the random coordinates to ensure they are within the viewport
			js.executeScript("window.scrollTo(" + randomX + "," + randomY + ")");

			// Perform a click at the random coordinates
			driver.findElement(By.tagName("body")).click();

			// Optional: Print the coordinates of the click
			System.out.println("Clicked at X: " + randomX + ", Y: " + randomY);
		}

	}

	public static void performRandomMouseMovementOnPage(ChromeDriver driver, int count) {

		Random random = new Random();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int numberOfMovements = count; // Change this to control the number of random movements

		for (int i = 0; i < numberOfMovements; i++) {
			int randomX, randomY;

			// Generate random coordinates that are within the visible area
			do {
				randomX = random.nextInt(driver.manage().window().getSize().getWidth());
				randomY = random.nextInt(driver.manage().window().getSize().getHeight());
			} while (randomX <= 0 || randomY <= 0);

			// Scroll to the random coordinates to ensure they are within the viewport
			js.executeScript("window.scrollTo(" + randomX + "," + randomY + ")");

			// Perform a click at the random coordinates
			driver.findElement(By.tagName("body")).click();
		}
	}

	private static void printDateTime(String msg) {
		// Create a SimpleDateFormat instance with the desired date and time format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		// Format and print the current date and time
		System.out.println(msg + dateFormat.format(new Date()));
	}

	private static void sleepForSeconds() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void refreshbuttoninwhatsappfix() {
		// Fix for refresh displayed in whatsapp
		List<WebElement> refreshicon = driver.findElements(By.xpath("//span[@data-icon='refresh']"));
		System.out.println("refresh icon displayed in whatsapp ==>" + refreshicon.size());
		if (refreshicon.size() > 0) {
			try {
				refreshicon.get(0).click();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void sendTextHumanLike(WebElement element, String text) throws InterruptedException {
		String[] arrayMobile = text.split("");
		element.clear();
		for (String a : arrayMobile) {
			element.sendKeys(a);
		}
//		element.clear();
//		element.sendKeys(text);
	}
	
	public static void sendTextHumanLikeName(WebElement element, String text) throws InterruptedException {
		int timePerCharacter = WaitsProfile1.totalTimeForTypingName / text.length();
		String[] arrayMobile = text.split("");
		element.clear();
		//printDateTime("Start time of printing :");
		for (String a : arrayMobile) {
			element.sendKeys(a);
			try {
                Thread.sleep(timePerCharacter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		//printDateTime("End time of printing :");
	}
	
	public static void sendTextHumanLikeID(WebElement element, String text) throws InterruptedException {
		int timePerCharacter = WaitsProfile1.totalTimeForTypingID / text.length();
		String[] arrayMobile = text.split("");
		element.clear();
		//printDateTime("Start time of printing :");
		for (String a : arrayMobile) {
			element.sendKeys(a);
			try {
                Thread.sleep(timePerCharacter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		//printDateTime("End time of printing :");
	}
	
	public static void sendTextHumanLikeAge(WebElement element, String text) throws InterruptedException {
		int timePerCharacter = WaitsProfile1.totalTimeForTypingAge / text.length();
		String[] arrayMobile = text.split("");
		element.clear();
		//printDateTime("Start time of printing :");
		for (String a : arrayMobile) {
			element.sendKeys(a);
			try {
                Thread.sleep(timePerCharacter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		//printDateTime("End time of printing :");
	}
	
	public static void sendTextHumanLikeMobileNumber(WebElement element, String text) throws InterruptedException {
		int timePerCharacter = WaitsProfile1.totalTimeForTypingMobileNumber / text.length();
		String[] arrayMobile = text.split("");
		element.clear();
		//printDateTime("Start time of printing :");
		for (String a : arrayMobile) {
			element.sendKeys(a);
			try {
                Thread.sleep(timePerCharacter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		//printDateTime("End time of printing :");
	}
	
	public static void sendTextHumanLikeOtp(WebElement element, String text) throws InterruptedException {
		int timePerCharacter = WaitsProfile1.totalTimeForTypingOtp / text.length();
		String[] arrayMobile = text.split("");
		element.clear();
		//printDateTime("Start time of printing :");
		for (String a : arrayMobile) {
			element.sendKeys(a);
			try {
                Thread.sleep(timePerCharacter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
		//printDateTime("End time of printing :");
	}
	
	
	
	

	private static void specificWait() throws InterruptedException {
		Thread.sleep(20);

	}

	private static void checkForPortalClose(ChromeDriver driver) throws InterruptedException {
		while (closed) {
			if (driver.getCurrentUrl().contains("closed") || driver.getCurrentUrl().contains("maintenance") ) {
				driver.get(URLString);
				System.out.println("Profile 1 - Chrome Browser - Current page URL is :" + driver.getCurrentUrl());
				Thread.sleep(1000);
			} else {
				closed = false;
				driver.get(URLString);
				break;
			}
		System.out.println("Inside portal closed maintenance check ");
		}
		System.out.println("Out of portal closed maintenance check ");
	}

	private static void checkForTime(int hour1, int min1, int sec1) throws InterruptedException {

		LocalTime targetTime = LocalTime.of(hour1, min1, sec1);

		boolean refresh = true;

		while (refresh) {

			if (LocalTime.now().isAfter(targetTime)) {
				System.out.println("Current time " + LocalTime.now() + " is after target time" + targetTime + "");
				refresh = false;
			} else if (LocalTime.now().equals(targetTime)) {
				System.out.println("Current time is exactly 10:00:00 AM.");
				Thread.sleep(1000);
				refresh = false;
			} else {
				System.out.println("Current time " + LocalTime.now() + " is less than target time " + targetTime + "");
				Thread.sleep(1000);
			}

		}

	}

}

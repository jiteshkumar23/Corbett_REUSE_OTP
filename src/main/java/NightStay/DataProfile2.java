package NightStay;

import java.util.HashMap;

public class DataProfile2 {
	public static String URLString;
	public static String checkInDateFromExcel;
	public static String checkOutDateFromExcel;
	public static String zoneFromExcel;
	public static String NumberOfAdultsFromExcel;
	public static String numberOfRooms;
	public static String numberOfChildren;
	public static String nameOfFirstPersonFromExcel;
	public static String ageOfFirstPersonFromExcel;
	public static String genderOfFirstPersonFromExcel;
	public static String nationalityOfFirstPersonFromExcel;
	public static String countryFirstPerson;
	public static String IdTypeOfFirstPerson;
	public static String IdNumberOfFirstPerson;
	public static String NameOfSecondPerson;
	public static String AgeOfSecondPerson;
	public static String GenderOfSecondPerson;
	public static String NationalityOfSecondPerson;
	public static String countrySecondPerson;
	public static String IdTypeOfSecondPerson;
	public static String IdNumberOfSecondPerson;
	public static String NameOfThirdPerson;
	public static String AgeOfThirdPerson;
	public static String GenderOfThirdPerson;
	public static String NationalityOfThirdPerson;
	public static String countryThirdPerson;
	public static String IdTypeOfThirdPerson;
	public static String IdNumberOfThirdPerson;
	public static String NameOfFourthPerson;
	public static String AgeOfFourthPerson;
	public static String GenderOfFourthPerson;
	public static String NationalityOfFourthPerson;
	public static String countryFourthPerson;
	public static String IdTypeOfFourthPerson;
	public static String IdNumberOfFourthPerson;
	public static String NameOfFifthPerson;
	public static String AgeOfFifthPerson;
	public static String GenderOfFifthPerson;
	public static String NationalityOfFifthPerson;
	public static String countryFifthPerson;
	public static String IdTypeOfFifthPerson;
	public static String IdNumberOfFifthPerson;
	public static String NameOfSixthPerson;
	public static String AgeOfSixthPerson;
	public static String GenderOfSixthPerson;
	public static String NationalityOfSixthPerson;
	public static String countrySixthPerson;
	public static String IdTypeOfSixthPerson;
	public static String IdNumberOfSixthPerson;
	public static String mobileNumber;
	public static String chromeProfilePathString;
	public static String firefoxProfileNameString;
	public static String roomPrioirty1;
	public static String roomPrioirty2;
	public static String roomPrioirty3;
	public static String roomPrioirty4;
	public static String roomPrioirty5;
	public static String roomPrioirty6;
	public static String roomPrioirty7;
	public static String roomPrioirty8;
	public static String UPI_ID;
	public static String Enable_UPI_ID;
	public static String otp;

	public DataProfile2() {

		HashMap<String, String> test = returnData();
		URLString = test.get("URLString");
		checkInDateFromExcel = test.get("checkInDateFromExcel");
		checkOutDateFromExcel = test.get("checkOutDateFromExcel");
		NumberOfAdultsFromExcel = test.get("NumberOfAdultsFromExcel");
		numberOfRooms = test.get("numberOfRooms");
		numberOfChildren = test.get("numberOfChildren");

		nameOfFirstPersonFromExcel = test.get("nameOfFirstPersonFromExcel");
		ageOfFirstPersonFromExcel = test.get("ageOfFirstPersonFromExcel");
		genderOfFirstPersonFromExcel = test.get("genderOfFirstPersonFromExcel");
		nationalityOfFirstPersonFromExcel = test.get("nationalityOfFirstPersonFromExcel");
		IdTypeOfFirstPerson = test.get("IdTypeOfFirstPerson");
		IdNumberOfFirstPerson = test.get("IdNumberOfFirstPerson");
		countryFirstPerson = test.get("countryFirstPerson");

		NameOfSecondPerson = test.get("NameOfSecondPerson");
		AgeOfSecondPerson = test.get("AgeOfSecondPerson");
		GenderOfSecondPerson = test.get("GenderOfSecondPerson");
		NationalityOfSecondPerson = test.get("NationalityOfSecondPerson");
		IdTypeOfSecondPerson = test.get("IdTypeOfSecondPerson");
		IdNumberOfSecondPerson = test.get("IdNumberOfSecondPerson");
		countrySecondPerson = test.get("countrySecondPerson");

		NameOfThirdPerson = test.get("NameOfThirdPerson");
		AgeOfThirdPerson = test.get("AgeOfThirdPerson");
		GenderOfThirdPerson = test.get("GenderOfThirdPerson");
		NationalityOfThirdPerson = test.get("NationalityOfThirdPerson");
		IdTypeOfThirdPerson = test.get("IdTypeOfThirdPerson");
		IdNumberOfThirdPerson = test.get("IdNumberOfThirdPerson");
		countryThirdPerson = test.get("countryThirdPerson");

		NameOfFourthPerson = test.get("NameOfFourthPerson");
		AgeOfFourthPerson = test.get("AgeOfFourthPerson");
		GenderOfFourthPerson = test.get("GenderOfFourthPerson");
		NationalityOfFourthPerson = test.get("NationalityOfFourthPerson");
		IdTypeOfFourthPerson = test.get("IdTypeOfFourthPerson");
		IdNumberOfFourthPerson = test.get("IdNumberOfFourthPerson");
		countryFourthPerson = test.get("countryFourthPerson");

		NameOfFifthPerson = test.get("NameOfFifthPerson");
		AgeOfFifthPerson = test.get("AgeOfFifthPerson");
		GenderOfFifthPerson = test.get("GenderOfFifthPerson");
		NationalityOfFifthPerson = test.get("NationalityOfFifthPerson");
		IdTypeOfFifthPerson = test.get("IdTypeOfFifthPerson");
		IdNumberOfFifthPerson = test.get("IdNumberOfFifthPerson");
		countryFifthPerson = test.get("countryFifthPerson");

		NameOfSixthPerson = test.get("NameOfSixthPerson");
		AgeOfSixthPerson = test.get("AgeOfSixthPerson");
		GenderOfSixthPerson = test.get("GenderOfSixthPerson");
		NationalityOfSixthPerson = test.get("NationalityOfSixthPerson");
		IdTypeOfSixthPerson = test.get("IdTypeOfSixthPerson");
		IdNumberOfSixthPerson = test.get("IdNumberOfSixthPerson");
		countrySixthPerson = test.get("countrySixthPerson");
		mobileNumber = test.get("mobileNumber");
		UPI_ID = test.get("UPI_ID");
		Enable_UPI_ID = test.get("Enable_UPI_ID");
		

		// Set profile of Chrome and Firefox here
		chromeProfilePathString = test.get("chromeProfilePathString");
		firefoxProfileNameString = test.get("firefoxProfileNameString");
		// Set Room Priority here
		roomPrioirty1 = test.get("roomPrioirty1");
		roomPrioirty2 = test.get("roomPrioirty2");
		roomPrioirty3 = test.get("roomPrioirty3");
		roomPrioirty4 = test.get("roomPrioirty4");
		roomPrioirty5 = test.get("roomPrioirty5");
		roomPrioirty6 = test.get("roomPrioirty6");
		roomPrioirty7 = test.get("roomPrioirty7");
		roomPrioirty8 = test.get("roomPrioirty8");
		
		//OTP here
		otp = test.get("otp");


	}

	public HashMap<String, String> returnData() {

		HashMap<String, String> map1 = new HashMap<String, String>();
		// set profile path of chrome and firefox here
		map1.put("chromeProfilePathString",
				"user-data-dir=C:\\Users\\jites\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 7");
		map1.put("firefoxProfileNameString", "default");

		// set room priority here   //Special case --> Bijrani:single , Bijrani:double , Bijrani:four
		map1.put("roomPrioirty1", "Bijrani:double");
		map1.put("roomPrioirty2", "Bijrani:double");
		map1.put("roomPrioirty3", "Bijrani:single");
		map1.put("roomPrioirty4", "Bijrani:four");
		map1.put("roomPrioirty5", "Bijrani");
		map1.put("roomPrioirty6", "Jhirna");
		map1.put("roomPrioirty7", "Gairal");
		map1.put("roomPrioirty8", "Halduparao");

		// set URL of application here
		map1.put("URLString", "https://booking.corbettgov.org/resort/#");

		// set dates , number of adults and number of rooms here
		map1.put("checkInDateFromExcel", "02-Apr-2024");
		map1.put("checkOutDateFromExcel", "03-Apr-2024");
		map1.put("NumberOfAdultsFromExcel", "1");
		map1.put("numberOfRooms", "1");
		map1.put("numberOfChildren", "0");// valid values are "0", "1","2"

		// Set First Person Details
		map1.put("nameOfFirstPersonFromExcel", "Profile Two Name");
		map1.put("ageOfFirstPersonFromExcel", "66");
		map1.put("genderOfFirstPersonFromExcel", "Male"); // valid values are "Male", "Female","Transgender"
		map1.put("nationalityOfFirstPersonFromExcel", "Indian"); // valid values are
																	// "Indian","Foreigner","Student","Senior Citizen"
		map1.put("countryFirstPerson", "USA"); // valid values are "USA", "United Kingdom","Canada","Australia","Italy"
		map1.put("IdTypeOfFirstPerson", "Aadhar Card"); // valid values "Aadhar Card","Pan Card","Driving
														// License","Passport","Student ID Card"
		map1.put("IdNumberOfFirstPerson", "745852356125");

		// Second Person Details
		map1.put("NameOfSecondPerson", "Testing Name");
		map1.put("AgeOfSecondPerson", "44");
		map1.put("GenderOfSecondPerson", "Female");
		map1.put("NationalityOfSecondPerson", "Foreigner");// valid values are "Indian","Foreigner","Student","Senior
															// Citizen"
		map1.put("countrySecondPerson", "United Kingdom");
		map1.put("IdTypeOfSecondPerson", "Pan Card");
		map1.put("IdNumberOfSecondPerson", "ARHPK1892B");

		// Third Person Details
		map1.put("NameOfThirdPerson", "ThirdPerson kumar");
		map1.put("AgeOfThirdPerson", "33");
		map1.put("GenderOfThirdPerson", "Female");
		map1.put("NationalityOfThirdPerson", "Foreigner");// valid values are "Indian","Foreigner","Student","Senior
														// Citizen"
		map1.put("countryThirdPerson", "Canada");
		map1.put("IdTypeOfThirdPerson", "Pan Card");
		map1.put("IdNumberOfThirdPerson", "ARHPK1893B");

		// Fourth Person Details
		map1.put("NameOfFourthPerson", "FourthPerson kumar");
		map1.put("AgeOfFourthPerson", "34");
		map1.put("GenderOfFourthPerson", "Female");
		map1.put("NationalityOfFourthPerson", "Foreigner");// valid values are "Indian","Foreigner","Student","Senior
														// Citizen"
		map1.put("countryFourthPerson", "Australia");
		map1.put("IdTypeOfFourthPerson", "Pan Card");
		map1.put("IdNumberOfFourthPerson", "ARHPK1894B");

		// Fifth Person Details
		map1.put("NameOfFifthPerson", "FifthPerson kumar");
		map1.put("AgeOfFifthPerson", "35");
		map1.put("GenderOfFifthPerson", "Female");
		map1.put("NationalityOfFifthPerson", "Foreigner");// valid values are "Indian","Foreigner","Student","Senior
														// Citizen"
		map1.put("countryFifthPerson", "Italy");
		map1.put("IdTypeOfFifthPerson", "Pan Card");
		map1.put("IdNumberOfFifthPerson", "ARHPK1895B");

		// Sixth Person Details
		map1.put("NameOfSixthPerson", "SixthPerson kumar");
		map1.put("AgeOfSixthPerson", "36");
		map1.put("GenderOfSixthPerson", "Female");
		map1.put("NationalityOfSixthPerson", "Foreigner");// valid values are "Indian","Foreigner","Student","Senior
														// Citizen"
		map1.put("countrySixthPerson", "USA");
		map1.put("IdTypeOfSixthPerson", "Pan Card");
		map1.put("IdNumberOfSixthPerson", "ARHPK1896B");

		// set mobile number here
		map1.put("mobileNumber", "9999999999");
		map1.put("UPI_ID", "9268565533@paytm");
		map1.put("Enable_UPI_ID", "NO"); // Valid Values "YES", "NO"
		map1.put("otp", "5687");
		return map1;
	}

}

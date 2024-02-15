package NightStay;

import org.testng.annotations.Test;

public class KillChromeAndEdge {

	@Test
	private static void KillChromeAndEdgeMethod() throws Exception {

		try {
			System.out.println("Kill Start");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Thread.sleep(1000);
			System.out.println("Killed chromedriver");

		} catch (Exception e) {
			System.out.println("Error in killing chromedriver");
			e.printStackTrace();
		}
		
		try {
			
			Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
			Thread.sleep(1000);
			System.out.println("Killed msedgedriver");
		} catch (Exception e) {
			System.out.println("Error in killing msedgedriver");
			e.printStackTrace();
		}
		
		try {
		
			Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
			Thread.sleep(1000);
			System.out.println("Killed edge browser");
		} catch (Exception e) {
			System.out.println("Error in killing edge browser");
			e.printStackTrace();
		}
		
		try {
			
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Thread.sleep(1000);
			System.out.println("Killed chrome browser");
		} catch (Exception e) {
			System.out.println("Error in killing chrome browser");
			e.printStackTrace();
		}

	}

}

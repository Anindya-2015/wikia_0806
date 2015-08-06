package exercise;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class KeyWordLibFunctions {

	// To verify presence of desired element in web page
	public WebElement klIsElementPresent(String strXpath) {
		WebElement el = null;
		try {
			Thread.sleep(2000);
			el= MethodRepository.wd.findElement(By.xpath(strXpath));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occured.");
		}
		return el;
	}

	// To perform click operation on desired element
	public void klWebElementClick(String strXpath) {
		try {
			MethodRepository.wd.findElement(By.xpath(strXpath)).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occured.");
		}
	}

	// To extract text from a given element
	public String klGetTextFromWebElement(String strXpath) {
		String strText="";
		try {
			strText = MethodRepository.wd.findElement(By.xpath(strXpath)).getText().toString();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occured.");
		}
		return strText;
	}

	// To wait for page to load properly and elements interactable 
	public boolean klWait() {
		boolean wait = false;
		try {
			MethodRepository.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occured.");
		}
		return wait;
	}

}

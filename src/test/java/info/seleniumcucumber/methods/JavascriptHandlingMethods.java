package info.seleniumcucumber.methods;

import org.openqa.selenium.WebDriver;

import com.bookit.utilities.Driver;

public class JavascriptHandlingMethods implements BaseTest {
	protected WebDriver driver = Driver.getDriver();
	/**Method to handle alert
	 * @param decision : String : Accept or dismiss alert
	 */
	public void handleAlert(String decision)
	{
		if(decision.equals("accept"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
}

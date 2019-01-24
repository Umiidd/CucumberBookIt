package com.bookit.step_definition;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import com.bookit.pages.MapPage;
import com.bookit.pages.SignInPage;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import info.seleniumcucumber.methods.AssertionMethods;
import info.seleniumcucumber.methods.TestCaseFailed;

public class LoginStepDefintions {

	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		// open browser and go to url
		String url = ConfigurationReader.getProperty("url");
		Driver.getDriver().get(url);
	}

	@When("the user logs in as a teacher")
	public void the_user_logs_in_as_a_teacher() {

		String email = ConfigurationReader.getProperty("teacher_email");
		String password = ConfigurationReader.getProperty("teacher_password");

		SignInPage signInPage = new SignInPage();
		signInPage.email.sendKeys(email);
		signInPage.password.sendKeys(password + Keys.ENTER);

	}

	@Then("the user should be logged in")
	public void the_user_should_be_logged_in() throws TestCaseFailed {
		MapPage mapPage = new MapPage();
		ChromeDriver driver=new ChromeDriver();
		AssertionMethods AssertM= new AssertionMethods();
		Assert.assertTrue(mapPage.map.isDisplayed());
		String title=driver.getTitle();		
		AssertM.checkTitle(title, true);
}

	@When("the user logs in as a team lead")
	public void the_user_logs_in_as_a_team_lead() {

		String email = ConfigurationReader.getProperty("team_leader_email");
		String password = ConfigurationReader.getProperty("team_leader_password");

		SignInPage signInPage = new SignInPage();
		signInPage.email.sendKeys(email);
		signInPage.password.sendKeys(password + Keys.ENTER);
	}

	@When("the user logs using {string} and {string}")
	public void the_user_logs_using_and(String email, String password) {
		SignInPage signInPage = new SignInPage();
		signInPage.email.sendKeys(email);
		signInPage.password.sendKeys(password + Keys.ENTER);
	}

	@Then("there should be {int} rooms")
	public void there_should_be_rooms(Integer int1) {
		System.out.println(int1);
	}

}
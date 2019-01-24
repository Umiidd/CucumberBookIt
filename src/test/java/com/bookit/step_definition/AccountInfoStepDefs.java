package com.bookit.step_definition;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import com.bookit.pages.MapPage;
import com.bookit.pages.MySelfPage;
import com.bookit.pages.SignInPage;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import info.seleniumcucumber.methods.AssertionMethods;
import info.seleniumcucumber.methods.TestCaseFailed;

public class AccountInfoStepDefs {

	@When("the user goes to myself page")
	public void the_user_goes_to_myself_page() {
		// open the my self page
		new MapPage().goToSelf();
		
	}
	
	@Then("team name {string} should be displayed")
	public void team_name_should_be_displayed(String expectedTeamName) {
		MySelfPage mySelfPage = new MySelfPage();
		String actualTeamName = mySelfPage.teamName.getText();
		
		Assert.assertEquals(expectedTeamName, actualTeamName);
		
	}

@Given("the user is on the logIn page")
public void the_user_is_on_the_logIn_page() {
	String url = ConfigurationReader.getProperty("url");
	Driver.getDriver().get(url);
}

@When("the user logs in as a Team Lead")
public void the_user_logs_in_as_a_Team_Lead() {
	String email = ConfigurationReader.getProperty("team_leader_email");
	String password = ConfigurationReader.getProperty("team_leader_password");

	SignInPage signInPage = new SignInPage();
	signInPage.email.sendKeys(email);
	signInPage.password.sendKeys(password + Keys.ENTER);
}

@Then("the user should logged in")
public void the_user_should_logged_in() throws TestCaseFailed {
	MapPage mapPage = new MapPage();
	ChromeDriver driver=new ChromeDriver();
	AssertionMethods AssertM= new AssertionMethods();
	Assert.assertTrue(mapPage.map.isDisplayed());
	String title=driver.getTitle();		
	AssertM.checkTitle(title, true);
}

@Then("there should be {int} room\\(s)")
public void there_should_be_room_s(Integer int1) {
    
}


}
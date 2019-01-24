package com.bookit.step_definition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.bookit.pages.SelfPage;
import com.bookit.pages.SignInPage;
import com.bookit.pages.TeamPage;

import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.DbUtilities;
import com.bookit.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyInfoStepDefs {

	@Given("user logs in using {string} {string}")
	public void user_logs_in_using(String email, String password) {
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		Driver.getDriver().manage().window().maximize();
		SignInPage signInPage = new SignInPage();
		signInPage.email.sendKeys(email);
		signInPage.password.sendKeys(password);
		signInPage.signInBTN.click();
		BrowserUtils.waitFor(2);

	}

	@When("user in on the my self page")
	public void user_in_on_the_my_self_page() {
		BrowserUtils.waitFor(2);
		SelfPage selfPage = new SelfPage();
		selfPage.goToSelf();

	}

	@Then("user info should match with the database records for {string}")
	public void user_info_should_match_with_the_database_records_for(String email) {
		String query = "SELECT firstname, lastname, role FROM users\r\n" + "WHERE  email= '" + email + "'";
		Map<String, Object> result = DbUtilities.getRowMap(query);

		String expectedFirstName = (String) result.get("firstname");
		String expectedLastName = (String) result.get("lastname");
		String expectedRole = (String) result.get("role");

		SelfPage selfPage = new SelfPage();
		BrowserUtils.waitFor(2);

		String[] name = selfPage.name.getText().split(" ");
		String firstName = name[0];
		String lastName = name[1];
		String role = selfPage.role.getText();
		BrowserUtils.waitFor(2);

		Assert.assertEquals(firstName, expectedFirstName);
		Assert.assertEquals(lastName, expectedLastName);
		Assert.assertEquals(role, expectedRole);
	}

	@When("user in on the my team page")
	public void user_in_on_the_my_team_page() {
		BrowserUtils.waitFor(2);
		SelfPage selfPage = new SelfPage();
		selfPage.goToTeam();

	}

	@Then("team info should match with the database records for {string}")
	public void team_info_should_match_with_the_database_records_for(String email) {
		String query = "SELECT firstname, role FROM users\r\n"
				+ "WHERE  team_id= (select team_id From users where email='" + email + "')";
		List<Map<String, Object>> result = DbUtilities.getQueryResultMap(query);
		BrowserUtils.waitFor(2);
		TeamPage teamPage = new TeamPage();
		List<String> actualNames = new ArrayList<>();
		List<String> actualRoles = new ArrayList<>();

		for (WebElement el : teamPage.teamMemberNames) {
			actualNames.add(el.getText());
		}

		for (WebElement el : teamPage.teamMemberRoles) {
			actualRoles.add(el.getText());
		}

		Assert.assertEquals(result.size(), actualNames.size());
		Assert.assertEquals(result.size(), actualRoles.size());

		for (Map<String, Object> row : result) {
			String firstname = (String) row.get("firstname");
			Assert.assertTrue(actualNames.contains(firstname));
		}

		for (Map<String, Object> row : result) {
			String role = (String) row.get("role");
			Assert.assertTrue(actualRoles.contains(role));
		}
	}

}

package com.bookit.step_definition;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Schedule {

@When("go to my schedule")
public void go_to_my_schedule() {
System.out.println("Going to my Schedule");
}

@Then("I should be able do see the reservation on the page")
public void i_should_be_able_do_see_the_reservation_on_the_page() {
    System.out.println("I see my reservation for my team");
}

}

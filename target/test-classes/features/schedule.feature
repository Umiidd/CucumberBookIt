Feature: View schedule 

		
Scenario: My team schedule
	Given the user is on the logIn page 
	And the user logs in as a teacher
	When go to my schedule
	Then I should be able do see the reservation on the page
	
	

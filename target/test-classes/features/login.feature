Feature: Login 


Background: 
	Given the user is on the logIn page 


@teacher
Scenario: logIn as a teacher 
	Given the user is on the logIn page 	
	When the user logs in as a teacher 
	Then the user should be logged in 

	@teamLead
Scenario: logIn as a Team Lead 
	#Given the user is on the logIn page
	When the user logs in as a Team Lead 
	Then the user should be logged in 

	@login1
 Scenario: login as anyone
    Given the user is on the logIn page
    When the user logs using "kliversageu@cbslocal.com" and "kerrieliversage"
    Then the user should be logged in 
    
    @login2
Scenario: login as anyone
    Given the user is on the logIn page
    When the user logs using "rbarstowk@cyberchimps.com" and "reneebarstow"
    Then the user should logged in
    And there should be 1 room(s)
	

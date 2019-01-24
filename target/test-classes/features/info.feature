Feature: Information About me 

@myselftemp @db 
Scenario: my self 
	Given user logs in using "efewtrell8c@craigslist.org" "jamesmay" 
	When user in on the my self page 
	Then user info should match with the database records for "efewtrell8c@craigslist.org" 
@teamtemp @db 
Scenario: my self 
	Given user logs in using "efewtrell8c@craigslist.org" "jamesmay" 
	When user in on the my team page 
	Then team info should match with the database records for "efewtrell8c@craigslist.org" 
@temp @db 
Scenario Outline: my self 
	Given user logs in using "<email>" "<password>" 
	When user in on the my self page 
	Then user info should match with the database records for "<email>" 
	Examples: 
		|email            |password        |
		|efewtrell8c@craigslist.org|jamesmay        |
		|jrowesby8h@google.co.uk   |aldridgegrimsdith  |
		|bmurkus8q@psu.edu         |alicasanbroke       |
		|admin@mail.com|nedstark       |
		|sdarben7g@alibaba.com   |angiecoatham  |
		|aneagle7h@miibeian.gov.cn         |bennetttomanek       |
		|ewrist7i@livejournal.com|marianndewi        |
		|gmalkin7j@mail.ru   |christopheflory |
		|vtugman7k@joomla.org       |daryledikles       |
		|daldie7l@seattletimes.com|ruthannjohnes        |
		|rkernocke7m@cnet.com   |merrileeambler  |
		|gmcalister7n@google.nl      |arluenereolfo       |
		|uvalerio7o@google.com |sharaipencot        |
		|bczadla7p@uol.com.br   |humphreyalsop |
		|ehegel7q@usatoday.com         |nefenyong      |
		|apobred@hotmailmail.com|bredpenhalurick        |
		|asolon7s@pinterest.com   |rozamondhellard |
		|kodonnelly7t@bigcartel.com         |robertamurrison      |
		|sutting7v@liveinternet.ru  |leonardwarfield  |
		|dcranstoun7x@sina.com.cn|durantdominey        |
		|ucharlot7y@nbcnews.com  |archibaldmelloy |
		|fklampt80@seesaa.net|timotheameade        |
		|bcircuit81@whitehouse.gov |heinriksummersett |
		|marblaster82@patch.com    |rosanneloadman      |
		|csummergill83@blinklist.com|edycaton        |
		|strayford84@e-recht24.de  |carlosmichie |
		|filieve85@amazon.de       |francescagowing     |
		
		@temp @db 
		Scenario Outline: my team 
			Given user logs in using "<email>" "<password>" 
			When user in on the my team page 
			Then team info should match with the database records for "<email>" 
			Examples: 
				|email             |password        |
				|efewtrell8c@craigslist.org|jamesmay        |
				|jrowesby8h@google.co.uk   |aldridgegrimsdith  |
				|bmurkus8q@psu.edu         |alicasanbroke       |
				|admin@mail.com|nedstark       |
				|sdarben7g@alibaba.com   |angiecoatham  |
				|aneagle7h@miibeian.gov.cn         |bennetttomanek       |
				|ewrist7i@livejournal.com|marianndewi        |
				|gmalkin7j@mail.ru   |christopheflory |
				|vtugman7k@joomla.org       |daryledikles       |
				|daldie7l@seattletimes.com|ruthannjohnes        |
				|rkernocke7m@cnet.com   |merrileeambler  |
				|gmcalister7n@google.nl      |arluenereolfo       |
				|uvalerio7o@google.com |sharaipencot        |
				|bczadla7p@uol.com.br   |humphreyalsop |
				|ehegel7q@usatoday.com         |nefenyong      |
				|apobred@hotmailmail.com|bredpenhalurick        |
				|asolon7s@pinterest.com   |rozamondhellard |
				|kodonnelly7t@bigcartel.com         |robertamurrison      |
				|sutting7v@liveinternet.ru  |leonardwarfield  |
				|dcranstoun7x@sina.com.cn|durantdominey        |
				|ucharlot7y@nbcnews.com  |archibaldmelloy |
				|fklampt80@seesaa.net|timotheameade        |
				|bcircuit81@whitehouse.gov |heinriksummersett |
				|marblaster82@patch.com    |rosanneloadman      |
				|csummergill83@blinklist.com|edycaton        |
				|strayford84@e-recht24.de  |carlosmichie |
				|filieve85@amazon.de       |francescagowing     |
		
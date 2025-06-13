@All
Feature: Selenium Base Demo Page

    @abc 
  Scenario Outline: Automate the Selenium Base Demo Page
    Given Navigate to Selenium Base Demo Page
    Then Requested functionalities for "<TextInputField>", "<SelectDropdown>" and other fields should be working as Expected

    Examples: 
      | TextInputField | SelectDropdown	| 
      | MAPS is boring | Set to 50%     |
      
   

  
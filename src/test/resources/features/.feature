@All
Feature: PL Registration

  @UKIBM-3943
  Scenario Outline: Verify the Vehicle Details under New case screen
    Given Logged into vehicle registration application in "<Environment>" and navigate to New Case page
    
    Examples:
      | Environment |
      | QA          |
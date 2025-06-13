@All
Feature: Electronic Parts Delivery Note EPDN with Admin Role

    @abc 
  Scenario Outline: Electronic Parts Delivery Note Display EPDN in Parts Despatch Note screen when logged in with Admin Role
    Given Login to the Electronic Parts Delivery Note EPDN application in "<Environment>" Environment with Admin Role
    #Then Export functionality in Parts Despatch Note screen for "<DealerName>" , "<DespatchNoteDateTime>" should be working as Expected when logged in with Admin Role in EPDN application

    Examples: 
      | Environment | DealerName 							| DespatchNoteDateTime   |
      | INT         | 02417 - N CONLAN & SONS | 03537 - 18/09/24 13:18 |
      
   

  
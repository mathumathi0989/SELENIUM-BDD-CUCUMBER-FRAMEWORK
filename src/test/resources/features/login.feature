Feature: Test the Login feature

  Scenario Outline: Test login with "<scenario>" data
    Given Enter the username and password with "<scenario>" details
    When click the Login button
    Then verify the appropriate result displayed for "<scenario">

    Examples: 
      | scenario        |
      #| invalidUsername |
      #| invalidPwd      |
      #| withoutUsername |
      #| withoutPwd      |
      #| withoutBoth     |
      | valid           |

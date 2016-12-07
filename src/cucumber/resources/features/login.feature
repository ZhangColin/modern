Feature: Login

  Scenario: Successful Login
    Given there is a user named "Colin" and password is "123456"
    When login with user name "Colin" and password "123456"
    Then login successfully


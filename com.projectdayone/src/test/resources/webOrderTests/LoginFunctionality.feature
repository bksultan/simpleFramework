@regressiontest @TEC-1002
Feature: Testing Log in functionality

  @TEC-2003 @ui @api
  Scenario: Testing log in functionality with valid/invalid credentials
    Given User navigates to WebOrders application
    When User provides username "username" and password "password"
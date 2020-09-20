@smokeTest @regressiontest @TEC-1001
Feature: Order creation Functionality

  Background: Common Steps for all scenarios
    Given User navigates to WebOrders application

  @TEC-2001
  Scenario: Creating order with valid data
    When User provides username "username" and password "password"
    When User click on Order part
    And User adds new order with data
      | Quantity | Customer name | Street     | City       | Zip   | State | Card number | Expire Date |
      | 2        | John Doe      | 123 Doe st | Des Plains | 60006 | IL    | 123456789   | 12/21       |
    Then User click on Process button and validates "New order has been successfully added." message
    When User click View all orders part
    Then User created order is added to list
      | Customer name | Quantity | Street     | City       | State | Zip   | Card number | Expire Date |
      | John Doe      | 2        | 123 Doe st | Des Plains | IL    | 60006 | 123456789   | 12/21       |

  @TEC-2002
  Scenario: Validate calculate functionality
    When User provides username "username" and password "password"
    Then User click on Order part
    When User provides quantity 3
    Then Total price equals to price multiple to 3

  @TEC-2020 @ui
  Scenario: Creating multiple order
    When User provides username "Tester" and password "test" without credentials
    Then User click on Order part
    And User creates all orders from "book" excel file
    Then User click on Process button and validates "New order has been successfully added." message
    When User click View all orders part
    Then User validates that orders from "book" excel file is created

  






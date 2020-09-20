@regressiontest
Feature: Testing Edit functionality of orders

  Scenario: Testing order information update functionality
    Given User navigates to WebOrders application and provides username "Tester" and password "test"
    When  User click Edit Button and update customer Name "Beksultan Ismatov"
    Then User validates information is updated to "Beksultan Ismatov"

  Scenario: Testing order information update functionality with invalid name
    Given User navigates to WebOrders application and provides username "Tester" and password "test"
    When User click Edit Button and update customer Name "123456789"
    Then Validate if customer name contains numbers

  Scenario: Validate if order is being edited then mandatory fields cannot be empty.
    Given User navigates to WebOrders application and provides username "Tester" and password "test"
    When User click Edit Button and clear Customer name field and update
    Then User is able to view "Field 'Customer name' cannot be empty."



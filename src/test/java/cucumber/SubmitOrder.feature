@tag
Feature: Purchase an item from an E-commerce website
  I want to use this template for my feature file

  Background:
    Given I land on the Ecommercec page

@tag2
Scenario Outline: Positive test for submitting the order
  Given Logged in with username <username> and password <password>
  When I add productname <productName> from cart
  And I checkout the product <productName> and submit the order
  Then "THANKYOU FOR THE ORDER." is displayed on ConfirmationPage

Examples:
| username            | password | productName|
| testk@gmail.com     | Test@1234| I PHONE |
| testk@gmail.com     | Test@1234| ZARA COAT 3 |
| testk@gmail.com     | Test@1234| ADIDAS ORIGINAL |
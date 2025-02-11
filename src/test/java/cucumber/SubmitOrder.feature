#Author: muthusundar044@gmail.com

@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file as my file
  
  Background:
   Given I landed on Ecommerce page

   Scenario Outline: Positive Test of Submitting the order
     Given Logged in with username <name> and password <password>
     When I add product <productname> to Cart
     And Checkout <productname> and submit the order
     Then "Thankyou for the order." message is displayed on ConfirmationPage
     
     Examples:
     | name                     | password    | productname       |
     |muthusundar044@gmail.com  | Sundar@04   | ZARA COAT 3       |
 

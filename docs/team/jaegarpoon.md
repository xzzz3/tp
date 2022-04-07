---
layout: page
title: Jaegar's Project Portfolio Page
---

## Project: FoodOnWheels

FoodOnWheels (FOW) is a **desktop app for managing delivery orders,
optimized for use via a Command Line Interface** (CLI) while still having the benefits
of a Graphical User Interface (GUI).
If you can type fast, FOW can get your order management tasks done faster
than traditional GUI apps.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

### Summary of Contributions
**Code contributed**: [RepoSense Link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=jaegarpoon&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Features implemented

**Model**:
* **Dish Class**: Added `Dish` class
  * Overview: `Dish` class provides a blueprint for the creation of a dish in the restaurant to be
  used in FOW
  * Justification: It is a required and fundamental building block for FOW, a delivery management
  app. Four fundamental classes: `Order`, `Dish`, `Customer` and `Driver`
  * Attribute 1: `NameDish` - Stores the name of the dish (adapted from AB3 `Name` class)
  * Attribute 2: `PriceDish` - Stores the price of the dish (created) 
    * Accepts only entries with 2 d.p.
    * Limits entry from '0.00' to '99999.99'
  * Credits: `Dish` class was adapted and worked from AB3's `Person` class, with the same idea
  but different conditions for equality checks
* **UniqueDishList Class**: Added `UniqueDishList` class
  * Overview: `UniqueDishList` class provides a list which stores all unique dishes added into FOW,
  with its uniqueness quantified by `Dish#isSameDish(Dish)`
  * Credits: `UniqueDishList` class was adapted and worked from AB3's `UniquePersonList` class, for the
  purpose of storing dishes instead of persons


**Logic**:
* **Add Dish Command and Parser**: Command and Parser to add a dish into FOW
  * Overview: Adds a dish into the `UniqueDishList` stored in FOW
  * Credits: `AddDishCommand` and `AddDishCommandParser` was adapted from AB3 `AddPersonCommand`
  and `AddPersonCommandParser`, with edits of the fields required for the `Dish` class as opposed
  to the `Person` class
* **Delete Dish Command and Parser**: Command and Parser to delete a dish from FOW
  * Overview: Deletes a dish from the `UniqueDishList` stored in FOW
  * Credits: `DeleteDishCommand` and `DeleteDishCommandParser` was adapted from AB3 `DeletePersonCommand`
    and `DeletePersonCommandParser`
* **Edit Dish Command and Parser**: Command and Parser to edit a dish in FOW
  * Overview: Edits a dish from the `UniqueDishList` stored in FOW
  * Credits: `EditDishCommand` and `EditDishCommandParser` was adapted from AB3 `EditPersonCommand`
    and `EditPersonCommandParser`, with edits of the fields required for the `Dish` class as opposed
    to the `Person` class
* **List Dish Command**: Command to list all dishes in FOW
  * Overview: Lists all the dishes in FOW, obtained from `UniqueDishList` as an unmodifiable view.
  * Credits: `ListDishCommand` was adapted from AB3 `ListPersonCommand`
* **List Order Command and Parser**: Command and Parser to list orders in FOW based on their current status
  * Overview: Updates the filtered order list stored in `ModelManager` based on the input status,
  and displays the list of orders on the UI
  * Statuses allowed: Based on the status present in `Order` class, namely, `all`, 
  `in_progress` or `in progress`, `delivered` and `cancelled`
  * Predicate for `ListOrder`: Implemented a predicate `OrderStatusContainsKeywordsPredicate` 
  to update `ModelManager#filteredOrderList`
  * Highlights: 
    * Parser checks for arguments `in progress` and `in_progress` to ensure that they produce the
    same outcome
    * Parser rejects any commands with more than 1 status 
* **Revenue Command**: Command to show the revenue of the restaurant using FOW for the day
  * Overview: Displays the revenue (sum of all sales) as a result, and lists out all the orders
  in the restaurant
  * Justification: Required to manage the sales and report the daily earnings for the restaurant
  * Predicate for getting orders from current date AND status is delivered: Implemented a predicate
  `OrderDeliveredAndFromDatePredicate`
    * Predicate updates `ModelManager#filteredOrderList` to orders from the current date AND status 
    delivered
    * Streams the orders and sums up the revenue
    * Then updates `ModelManager#filteredOrderList` to show all orders again


**UI**:
* **Tab Display Feature**: Displays the relevant classes: `Dishes`, `Customers`, `Orders` and `Drivers`
  * Highlights: Relevant class that was in the latest command will be bolded, with the relevant list being
    displayed
  * Implemented the logic to show the relevant `ListPanel`, eg. when a command relating to `Dish` is 
  entered, the `DishListPanel` will be displayed
    * Logic implemented using additional boolean conditions `isDish`, `isDriver`, `isOrder` in the 
    constructor of `CommandResult` (see Pull Request #73)

#### Enhancements implemented
* **Improvements to UI**: Rearranged and displayed neatly the results in `DishCard` (see Pull Request #103)


#### Contributions to the UG
* **Documenting usage of Commands**: Documented usage of commands based on the commands implemented and mentioned
above
* **Documenting usage of Tabs**: Documented how a user can switch between tabs in FOW
* **Introduction to FOW**: Added on to the introduction to the UG, from 'The main idea... ' onwards
* **Java `11` installation guide**: Added instructions for Java `11` installation
* **Instructions to start FOW**: Wrote instructions on usage of `java -jar foodonwheels.jar`

#### Contributions to the DG
* **Model Component**: Updated relevant changes and diagrams made to the `Model` component after adaptation from AB3
* **Implementations**: Updated implementations based on implementations and enhancements mentioned above
* **UI Component**: Updated relevant changes and diagrams made to the `Ui` component after adaptation from AB3

#### Review / Mentoring contributions
* **Pull Request Reviews**: Reviewed PR #70, #83, #85, #97, #98, #100, #106, #111, #114, #115, #116, #163
* **Mentoring Contributions**: 
  * Provided comments on how to improve code in the PRs reviewed
  * Started a bug list (not found on GitHub, on Google Docs) to track bugs discovered while exploring
  features implemented by team members and myself
  * Taking initiative to establish UI features to enhance visuals of FOW for easier use, where team members
  adapted the UI features implemented

#### Contributions beyond the project team
* **Sharing information on forums**: 
  * Forum post on [JavaFX SDK](https://github.com/nus-cs2103-AY2122S2/forum/issues/66)
  * Forum post on [Varying builds from CI and local machine](https://github.com/nus-cs2103-AY2122S2/forum/issues/122)



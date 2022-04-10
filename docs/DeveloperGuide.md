---
layout: page
title: Developer Guide
---
## Table of Contents
1. [Acknowledgements](#acknowledgements)    
2. [Setting up, getting started](#setting-up-getting-started)   
3. [Design](#design)    
   3.1 [Architecture](#architecture)    
   3.2 [UI component](#ui-component)    
   3.3 [Logic component](#logic-component)  
   3.4 [Model component](#model-component)  
   3.5 [Storage component](#storage-component)  
4. [Implementation](#feature-implementation)    
   3.1 [Add/Delete Driver](#adddelete-driver-feature)   
   3.2 [List Driver](#list-driver-feature)  
   3.3 [Add Customer](#add-customer-feature)    
   3.4 [Delete Customer](#delete-customer-feature)  
   3.5 [Add/Delete/List Dish](#adddeletelist-dish-feature)  
   3.6 [Tab Display](#tab-display-feature)  
   3.7 [Add Order](#add-order-feature)  
   3.8 [Edit Order Status](#edit-order-status-feature)  
   3.9 [List Order](#list-order-feature)  
   3.10 [Revenue](#revenue-feature)  
   3.11 [[Proposed] Undo/redo](#proposed-undoredo-feature)   
5. [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)  
6. [Appendix: Requirements](#appendix-requirements) 
7. [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)   

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S2-CS2103-F10-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S2-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2122S2-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S2-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S2-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S2-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="800" />

The `Model` component,

* stores the FoodOnWheels data i.e., all `Dish/Customer/Driver/Order` objects (`Dish` objects are contained in a `UniqueDishList` object, `Customer` objects are contained in a `UniqueCustomerList` object, etc.).
* stores the currently 'selected' `Dish/Customer/Driver/Order` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S2-CS2103-F10-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

(todo: update storage class diagram)
<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------


## **Feature Implementation**

This section describes some noteworthy details on how certain features are implemented.


### Add/Delete Driver feature

#### Implementation

The add/delete/list driver feature are commands which inherits from `Command`. These are the basic commands
to add, remove or view `Driver` objects.

#### Example run-through

(todo: sequence diagram for add/delete/list driver)
A sample run-through is shown below in the sequence diagram:

### List Driver feature

#### Implementation

The list driver feature is a command which inherits from `FindCommand`. It is the basic command 
to find `Driver` objects which have the status match with the argument in the command.

#### Example run-through

(todo: sequence diagram for add/delete/list driver)
A sample run-through is shown below in the sequence diagram:

### Add Customer feature

#### Implementation

The add customer mechanism is facilitated by `Customer`. It is a model in the application and some of its important
attributes include:

* `NameCustomer`  — Contains the name of the given customer
* `PhoneCustomer`  — Contains the phone number of the given customer
* `AddressCustomer`  — Contains the address of the given customer

These attributes are used in the `customer#Customer()` instantiation, which is called to create a new Customer when the
add customer  
command is called.

Given below is an example usage scenario and how the add customer mechanism behaves at each step.

Step 1. The user executes the `addcustomer n/john p/12345678 a/nus` command in the application to add a given
customer, which is handled by `Logic#execute`.
The `addcustomer` command is parsed by the `AddressBookParser#parseCommand` and `AddCustomerCommandParser#parse` to
create a new
`AddCustomerCommand` with the given phone number and dishes.

Step 2. The `Logic` then executes the `AddCustomerCommand#execute()` with a `Customer` object

Step 3. The `Customer` object, which contains the following attributes `NameCustomer` `PhoneCustomer`
`AddressCustomer` is added to the `Model` with
`Model#addCustomer()`.

Step 5. A new `CommandResult` with the success message is returned to `Logic` and returned as the output.

#### Design considerations:
* The `Customer` ensures that the value of the customer name, phone number and address is enclosed by the
  `NameCustomer` `PhoneCustomer` `AddressCustomer` classes instead of `Name` `Phone` `Address` respectively in order to
  ensure
  greater level of encapsulation of data being handled.

![AddCustomerSequenceDiagram](images/AddCustomerSequenceDiagram.png)

### Delete Customer feature

#### Implementation

The delete customer mechanism is faciliated by `Customer`.

Given below is an example usage scenario and how the delete customer mechanism behaves at each step.

Step 1. The user executes the `deletecustomer 3` command in the application to delete the customer represented with
the index 3.
The `deletecustomer` keyword is parsed by the `AddressBookParser#parseCommand` and
`DeleteCustomerCommandParser#parse` to
create a new `DeleteCustomerCommand` with the given index.

Step 2. The `Logic` then executes the `DeleteCustomerCommand#execute()`.

Step 3. The `DeleteCustomerCommand` finds the `Customer` using the given index from the `Model#filteredCustomers`and
calls
its
`Model#deleteCustomer()`
method to delete the customer.

Step 4. A new `CommandResult` with the success message is returned to `Logic` and returned as the output.

![DeleteCustomerSequenceDiagram](images/DeleteCustomerSequenceDiagram.png)

### Add/Delete/List Dish feature

#### Implementation

The add/delete/edit/list dish feature are commands which inherits from `Command`. These are the basic commands
to add, remove, edit or view `Dish` objects respectively.

Given below is a successful usage scenario of the commands.

Step 1. The user executes the `adddish`, `deletedish`, `editdish`, `listdish` command in the application to add, delete or list the dishes, 
which is handled by `Logic#execute`.

Step 2. The command entered is parsed by the `AddressBookParser#parseCommand`.

Step 3. The `adddish`, `deletedish` and `editdish` command will then be parsed by `AddDishCommandParser#parse`, `EditDishCommandParser#parse` 
and `DeleteDishCommandParser#parse` to create a new `AddDishCommand`, `DeleteDishCommand` and `EditDishCommand` respectively.

The list dish command will be created directly from `AddressBookParser#parseCommand`, since no further parsing is required.

Step 4. The `Logic` component then performs the `Command#execute()` method in the respective commands

Step 5. A new `CommandResult` with the success message is returned to `Logic` and returned as the output.


### Tab Display feature

`TabDisplay` was implemented as an additional feature to the UI component. 
It extends from `UiPart` and exists within `MainWindow`. The Tab Display feature was implemented with the
intention to show which component in FoodOnWheels the user is interacting with, and has four different tabs
representing the four main models:

* `Customer`
* `Driver`
* `Order`
* `Dish`

It implements the following operations:
* TabDisplay#setFocus() - Bolds the focusItem (`Driver`/`Customer`/`Dish`/`Order`) in the Tab Display, 
identifying it as the current focus of the `ListPanel`

The operation `TabDisplay#setFocus()` is exposed in the `MainWindow` class 
in `MainWindow#handleDish()`, `MainWindow#handleDriver()`, `MainWindow#handleOrder()`, `MainWindow#handleCustomer()`
`MainWindow#fillInnerParts()` and `MainWindow#executeCommand()`.

#### Example run-through
A sample run-through is shown below in the sequence diagram:

<img src="images/TabDisplaySequenceDiagram.png" width="550" />

Explanation:

1. Command is called -> `TabDisplay` set to default, which is `Customer` tab
2. When a `CommandResult` is returned, it `CommandResult#isDish()` will be evaluated to true,
since the command is related to a dish
3. `TabDisplay` is then set to `Dish`, as seen from the second time `TabDisplay#setFocus()` is called

### Add Order feature

#### Implementation

The add order mechanism is facilitated by `Order`. It is a model in the application and some of its important attributes include:

* `customer`  — Holds the Customer object who ordered the given Order.
* `driver`  — Holds the Driver object who will be delivering the given Order.
* `dishes`  — Holds the Dish objects that are included in the given Order.

![OrderClassDiagram](images/OrderClassDiagram.png)

These attributes are used in the `Order#Order()` instantiation, which is called to create a new Order when the add order command is called.

Given below is an example usage scenario and how the add order mechanism behaves at each step.

Step 1. The user executes the `addorder p/ 123 d/ sushi` command in the application to add an order of sushi for a registered Customer with phone number 123, which is handled by `Logic#execute`. 
The `addorder` command is parsed by the `AddressBookParser#parseCommand` and `AddOrderCommandParser#parse` to create a new `AddOrderCommand` with the given phone number and dishes.

Step 2. The `Logic` then executes the `AddOrderCommand#execute()` with the stored lists using `Logic#getFilteredCustomerList()`, `Logic#getFilteredDriverList` and `Logic#getFilteredDishList`.

Step 3. `AddOrderCommand#Execute` uses the lists to find the `Customer` object with the given phone number (i.e. 123) , a free `Driver` object, and all the `Dish` objects with the given names (i.e. sushi). It then creates an `Order` with them.

Step 4. The `Order` is added to the `Model` with `Model#addOrder()`.

Step 5. A new `CommandResult` with the success message is returned to `Logic` and returned as the output.

#### Design considerations:
* The `Order` stores its component objects directly, in order to make future implementation easier. Future updates that targets the different parts of an `Order` can easily be implemented in this way.

### Edit Order Status feature

#### Implementation

The edit order status mechanism is faciliated by `Order` and `OrderStatus`. The latter is an enumeration class to include the possible statuses.
This mechanism also relies on the method `Order#updateStatus()` which updates the status of the given Order.

Given below is an example usage scenario and how the edit order status mechanism behaves at each step.

Step 1. The user executes the `mark 1 s/ delivered` command in the application to mark the first order in the list as delivered.
The `mark` keyword is parsed by the `AddressBookParser#parseCommand` and `EditOrderStatusCommandParser#parse` to create a new `EditOrderStatusCommand` with the given index and status.

Step 2. The `Logic` then executes the `EditOrderStatusCommand#execute()`. 

Step 3. The `EditOrderStatusCommand` finds the `Order` using the given index and calls its `Order#updateStatus()` method to update the status to the given new status.

Step 4. A new `CommandResult` with the success message is returned to `Logic` and returned as the output.

![EditOrderStatusSequenceDiagram](images/EditOrderStatusSequenceDiagram.png)

### List Order feature

#### Implementation

The list order mechanism is faciliated by `Order` and `OrderStatus`. The latter is an enumeration class to include the possible statuses.

Given below is an example usage scenario and how the list order mechanism behaves at each step.

Step 1. The user executes the `listorder delivered` command in the application to list the delivered orders.
The `listorder` keyword is parsed by the `AddressBookParser#parseCommand` and `ListOrderCommandParser#parse` to create a new `ListOrderCommand` with the provided status.

Step 2. The `Logic` then executes the `ListOrderCommand#execute()`.

Step 3. A new `CommandResult` with the success message is returned to `Logic` and returned as the output, with the orders of `OrderStatus#DELIVERED` being displayed.

### Revenue feature

#### Implementation

The revenue mechanism is faciliated by `Order` and `OrderDeliveredAndFromDatePredicate`. The latter is predicate to include filter the order list to contain only orders
from the current date (determined by date on OS) and with `OrderStatus#DELIVERED`.

Given below is an example usage scenario and how the list order mechanism behaves at each step.

Step 1. The user executes the `revenue` command in the application to obtain the revenue of the current day.
The `revenue` keyword is parsed by the `AddressBookParser#parseCommand` to create a new `RevenueCommand`.

Step 2. The `Logic` then executes the `RevenueCommand#execute()`.

Step 3. A new `CommandResult` with the success message and the revenue of the day is returned to `Logic` and returned as the output.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of delivery orders
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage delivery orders faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                                                                                       | So that I can…​                                                     |
|-------| ------------------------------------------ |----------------------------------------------------------------------------------------------------| ---------------------------------------------------------------------- |
| `* * *` | new user                            | view all the possible commands                                                                     | easily refer to the commands without needing to remember them          |
| `* * *` | new user                                   | see usage instructions                                                                             | refer to instructions when I forget how to use the App                 |
| `* * *` | user                                       | add a new person                                                                                   |                                                                        |
| `* * *` | user                                       | delete a person                                                                                    | remove entries that I no longer need                                   |
| `* * *` | user                                       | find a person by name                                                                              | locate details of persons without having to go through the entire list |
| `* * *` | experienced user                           | view a list of previous delivery orders                                                            | review the previous transactions' details in case they're needed (e.g. if the driver accidentally clicks delivered and the task disappears) |
| `* * *` | experienced user                        | view a list of current delivery orders                                                             | know the details of the delivery orders and respond to them. |
| `* * *` | basic user                                       | view a list of free drivers                                                                        | assign them to delivery orders
| `* * *` | basic user                                       | add information of a driver                                                                        |
| `* * *` | basic user                                       | delete information of a driver                                                                     |
| `* * *` | basic user                                       | add information of a repeat customer                                                               |
| `* * *` | basic user                                       | delete information of a repeat customer                                                            |
| `* * *` | basic user                                       | add a new delivery order                                                                           | I can store the information of food, customer and driver.
| `* * *` | basic user                                       | edit the status of the order (done/ not done, customer not respond, ...)                           | I can keep track of the order status.
| `* *` | experienced user                                       | view my revenue for the day                                                                        | report to the accountant of the company or show my managers the earnings of the delivery orders.               |
| `* *` | experienced user                                       | undo a previous action                                                                             | deal with mistakes when keying in   
| `* *` | experienced user                                       | view the delivery orders of a single restaurant (of the chain)                                     | have a better idea of which ones are more popular at the current time              |
| `* *` | user                                       | hide private contact details                                                                       | minimize chance of someone else seeing them by accident                |
| `* *` | basic user                                       | edit information of a driver                                                                       | I can have a new driver in the database
| `* *` | basic user                                       | edit information of a repeat customer                                                              |I can make the new order faster and update the customer information
| `* *` | basic user                                       | automatically detect when a repeat customer registers                                              |I can reduce duplicates in the system
| `* *` | potential user                             | view the app with dummy data filled in                                                             | assess the suitability of the app for my needs           |
| `* *` | user ready to use the app                  | purge dummy data from the app                                                                      | begin to use the app                                                   |
| `* *` | user who uses hardcopy tracking            | annotate delivery orders                                                                           | follow the conventions previously set in the restaurant                |
| `* *` | first time user                            | view a step-by-step tutorial                                                                       | pick up the knowledge necessary for me to operate the application      |
| `* *` | returning user                             | rewatch the tutorial                                                                               | refresh my memory on how to use the application                        |
| `*`   | first time user                            | use the application through a clean and easy interface                                             | not be overwhelmed by the application           |
| `*`   | experienced user                                       | use shortcuts instead of the normal commands to add order or assign driver                         | save time      |
| `*`   | experienced user                                       | view a summary of the past month's order (e.g. most popular delivery region, most popular dishes)  | better plan the future deliveries and report the findings to my managers      |
| `*`   | experienced user                                       | remove (at least visually) the functions that are cluttering the interface but not frequently used | easily navigate the app interface      |
| `*`   | forgetful user                             | retrieve password                                                                                  | recover my account when I forget my password                           |
| `*`   | impatient user                             | navigate functionalities from a single page                                                        | easily access the different functionalities directly     |
| `*`   | user who uses other management tools       | migrate into the platform without major restructuring                                              | transition from one app to another              |
| `*`   | user with many persons in the address book | sort persons by name                                                                               | locate a person easily                                                 |
| `*`   | basic user                                       | View the detail of a driver (e.g. number, car plate number, current order)                         | I can contact the drivers in case of emergency

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `FoodOnWheels` application and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a customer**

**MSS**

1.  User requests to add customer by providing the required info (name, address, phone number)
2.  FoodOnWheels adds the customer

    Use case ends.


**Use case: Delete a customer**

**MSS**

1. User requests to delete customer by providing the required info (name, address, phone number)
2. FoodOnWheels deletes the customer

    Use case ends.

**Use case: Add a dish**

**MSS**

1.  User requests to add a dish by providing the required info (name, price)
2.  FoodOnWheels adds the dish

    Use case ends

**Extensions**

* 1a. Some fields are missing
    * 1a1. FoodOnWheels shows an error message

      Use case resumes at step 1.
* 1b. Price is out of given bound
    * 1b1. FoodOnWheels shows an error message

      Use case resumes at step 1.
* 1c. Add results in a duplicate dish
    * 1c1. FoodOnWheels shows an error message

      Use case resumes at step 1.

**Use case: Delete a dish**

**MSS**

1.  User requests to delete a dish by providing the required index
2.  FoodOnWheels deletes the dish

    Use case ends.

**Extensions**

* 1a. Index is unrecognized or out of bounds
    * 1a1. FoodOnWheels shows an error message

      Use case resumes at step 1.

**Use case: Edit a dish**

**MSS**

1.  User requests to edit a dish by providing the index of the dish and fields to change 
2.  FoodOnWheels edits the dish based on the fields provided

    Use case ends

**Extensions**

* 1a. Edit results in a duplicate dish
    * 1a1. FoodOnWheels shows an error message

      Use case resumes at step 1.
* 1b. No fields indicated for edit
    * 1b1. FoodOnWheels shows an error message

      Use case resumes at step 1.
* 1c. Index is unrecognized or out of bounds
    * 1c1. FoodOnWheels shows an error message

      Use case resumes at step 1.

**Use case: List orders**

**MSS**

1. User requests to view a list of current orders with a given status
2. FoodOnWheels displays all the orders based on the given status

    Use case ends.

**Extensions**

* 1a. Status is unrecognized
  * 1a1. FoodOnWheels shows an error message

    Use case resumes at step 1.

**Use case: Revenue**

**MSS**

1. User requests to view the revenue for the day
2. FoodOnWheels displays all the orders and the revenue for the day

   Use case ends.

**Extensions**

* 2a. No delivered orders for the day. 
  * 2a1. Revenue shows as $0.00

    Use case ends.

**Use case: View all commands**

**MSS**

1.  User requests to view the list of all commands
2.  FoodOnWheels shows a list of commands

    Use case ends.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 orders, dishes, customers and drivers without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.
      1. If double-clicking the file does not work, use the command `java -jar foodonwheels.jar` from the
      folder containing `foodonwheels.jar`.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.
   

### Deleting a customer 

1. Deleting a customer while all customers are being shown

   1. Prerequisites: List all customers using the `listcustomer` command. Multiple customers in the list.

   2. Test case: `deletecustomer 1`<br>
      Expected: First customer is deleted from the list. Details of the deleted customer shown in the status message. 

   3. Test case: `deletecustomer 0`<br>
      Expected: No customer is deleted. Error details shown in the status message. 

   4. Other incorrect delete commands to try: `deletecustomer`, `deletecustomer x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Deleting a dish

1. Deleting a dish while all dishes are being shown

    1. Prerequisites: List all dishes using the `listdish` command. Multiple dishes in the list.

    2. Test case: `deletedish 1`<br>
       Expected: First dish is deleted from the list. Details of the deleted dish shown in the status message.

    3. Test case: `deletedish 0`<br>
       Expected: No dish is deleted. Error details shown in the status message. 

    4. Other incorrect delete commands to try: `deletedish`, `deletedish x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Adding a dish

1. Adding a dish while all dishes are being shown

    1. Prerequisites: List all dishes using the `listdish` command. Multiple dishes in the list.

    2. Test case: `adddish n/Dish One $/1.00`<br>
       Expected: Dish with name 'Dish One' and price $1.00 added to the list. Ensure that 'Dish One' does not exist
       in the list of dishes. If it exists, use another name that does not exist.

    3. Test case: `adddish n/Dish One $/1.00`<br>
       Expected: No dish is added since it is a duplicate dish (if a different name is used in step 2, use that name instead) 
       Error details shown in the status message. 

    4. Other incorrect add commands to try: `adddish`, `adddish n/random`, `adddish n/random $/1.0` (where x is larger than the list size)<br>
       Expected: Similar to previous.


### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

---
layout: page
title: User Guide
---

FoodOnWheels (FOW) is a **desktop app for managing delivery orders, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FOW can get your order management tasks done faster than traditional GUI apps.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `foodonwheels.jar` from [here](https://github.com/AY2122S2-CS2103-F10-2/tp) (to be updated).

3. Copy the file to the folder you want to use as the _home folder_ for your FoodOnWheels.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   <img src="images/FoodOnWheels.png" width=65% height=65%>

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`add dish`**`n/Crab Pasta` : Adds a dish named `Crab Pasta` to the restaurant's menu.

   * **`delete dish`**`n/Crab Pasta` : Deletes a dish named `Crab Pasta` to the restaurant's menu.

   * **`listorders`** : Lists all the current orders in the system. 

   * **`listordersprev`** : Lists all the previous orders in the system.

   (to be updated)
   * **`delete`** : Deletes all contacts.

   * **`exit`** : Exits the app.

   * **`add`** : Lists all contacts.

   * **`delete`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.


1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features 

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `{curly brackets}` are the parameters to be supplied by the user.<br>
  e.g. in `add /n {name}`, `name` is a parameter which can be used as `add /n John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## Customer features

### Adding a customer: `add`

Adds a customer to the addressbook.

Format: `add n/{name} a/{address} p/{phone}`

Examples:
* `add n/John Doe a/John street, block 123, #01-01 p/98765432`
* `add n/Betsy Crowe a/Newgate Prison p/1234567`

### Deleting a customer: `delete`

Deletes a customer from the addressbook.

Format: `delete n/{name} a/{address} p/{phone}`

Examples:
* `delete n/John Doe a/John street, block 123, #01-01 p/98765432`
* `delete n/Betsy Crowe a/Newgate Prison p/1234567`

## Driver features

### Adding a driver: `add driver`

Adds a driver to the database.

Format: ` add driver n/{name} p/{phone}`

Examples:
* `add driver n/John Doe p/98765432 `
* `add driver n/Betsy Crowe p/1234567 `

### Deleting a driver: `delete driver`

Deletes a driver from the database, together with his/her information.

Format: `delete driver n/{name} p/{phone}`

Examples:
* `delete driver n/John Doe p/98765432`
* `delete driver n/Betsy Crowe p/1234567`

### List free drivers: `list driver free`

Lists free drivers who are not delivering any order and can receive new orders.

Format: `list driver free`

## Dish features

### Adding a dish: `add dish`

Adds a dish to the restaurant’s menu.

Format: `add dish n/{name}`

Examples:
* `add dish n/Crab Pasta`
* `add dish n/Kimchi Fried Rice`

### Deleting a dish: `delete dish`

Deletes a dish from the restaurant’s menu.

Format: `delete dish n/{name}`

Examples:
* `delete dish n/Crab Pasta`
* `delete dish n/Kimchi Fried Rice`





### List previous orders: `listordersprev`

Lists all the previous orders in the system

Format: `listordersprev`


(to be updated)
### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

## Order Features

### Adding a new Delivery Order: `add order`

Adds a new delivery order to the list of orders.

Format: `add order /p {phone} /d {dishes separated by comma}`

Examples:
* `add order /p 81234567 /d {Fried Rice, Pasta}`
* `add order /p 81234567 /d {Pasta}`

### Edit the status of a Delivery Order: `mark`

Edit the status of a delivery order in the list using its index.

Format: `mark {index} {string for the status}`

Examples:
* `mark 1 done`
* `mark 3 cancelled`

### Search for an Order by Phone Number: `find order`

Search for an order with the phone number provided in the list of orders.

Format: `find /p {phone}`

Examples:
* `find /p 81234567`

### List current orders: `list`

Lists all the current orders in the system

Format: `listorders`

### List previous orders: `listordersprev`

Lists all the previous orders in the system

Format: `listordersprev`
=======

### Adding a new Delivery Order: `add order`

Adds a new delivery order to the list of orders.

Format: `add order /p {phone} /d {dishes separated by comma}`

Examples:
* `add order /p 81234567 /d {Fried Rice, Pasta}`
* `add order /p 81234567 /d {Pasta}`

### Edit the status of a Delivery Order: `mark`

Edit the status of a delivery order in the list using its index.

Format: `mark {index} {string for the status}`

Examples:
* `mark 1 done`
* `mark 3 cancelled`

### Search for an Order by Phone Number: `find order`

Search for an order with the phone number provided in the list of orders.

Format: `find /p {phone}`

Examples:
* `find /p 81234567`


### Exiting the program : `exit`

Exits the program.

Format: `exit`





### Saving the data

FoodOnWheels data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FoodOnWheels data are saved as a JSON file `[JAR file location]/data/addressbook.json` (to be updated). Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FoodOnWheels home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary
(to be updated)

Action | Feature type | Format, Examples
--------|--------------|------------------
**Add** | **Customer** |`add n/{name} a/{address} p/{phone}` <br> e.g,`add n/James Ho a/123, Clementi Rd, 1234665 p/22224444`
**Delete** | **Customer** |`delete n/{name} a/{address} p/{phone}` <br> e.g,`delete n/James Ho a/123, Clementi Rd, 1234665 p/22224444`
**Add** | **Driver**   |`add driver n/{name} p/{phone}` <br> e.g,`add driver n/John Doe p/98765432`
**Delete** | **Driver**   |`delete driver n/{name} p/{phone}` <br> e.g,`delete driver n/John Doe p/98765432`
**List** | **Driver**   |`list driver free` 
**Add** | **Dish**     | `add dish n/NAME` <br> e.g., `add dish n/Crab Pasta`
**Delete** | **Dish**     | `delete dish n/NAME` <br> e.g., `delete dish n/Crab Pasta`
**List (current orders)** | **Order**    | `listorders`
**List (previous orders)** | **Order**    | `listordersprev`

[//]: # (**Add** |              | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`)

[//]: # (**Clear** |              | `clear`                                                                                                                     )

[//]: # (**Delete** |              | `delete INDEX`<br> e.g., `delete 3`                                                                                         )

[//]: # (**Edit** |              | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com` )

[//]: # (**Find** |              | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                  )

[//]: # (**List** |              | `list`                                                                                                                      )

[//]: # (**Help** |              | `help`                                                                                                                      )

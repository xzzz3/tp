package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.customer.Customer;
import seedu.address.model.driver.Driver;
import seedu.address.model.item.Dish;
import seedu.address.model.item.Person;
import seedu.address.model.order.Order;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the customers list.
     * This list will not contain any duplicate customers.
     */
    ObservableList<Person> getPersonList();
    ObservableList<Driver> getDriverList();
    ObservableList<Dish> getDishList();

    /**
     * Returns an unmodifiable view of the orders list.
     * This list will not contain any duplicate orders.
     */
    ObservableList<Order> getOrderList();
    ObservableList<Customer> getCustomerList();

}

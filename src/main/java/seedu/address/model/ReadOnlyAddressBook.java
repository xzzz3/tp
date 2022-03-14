package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.driver.Driver;
import seedu.address.model.item.Dish;
import seedu.address.model.item.Person;
import seedu.address.model.order.Order;


/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<Driver> getDriverList();
    ObservableList<Dish> getDishList();

    /**
     * Returns an unmodifiable view of the orders list.
     * This list will not contain any duplicate orders.
     */
    ObservableList<Order> getOrderList();

}

package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

import seedu.address.model.order.Order;
import seedu.address.model.order.UniqueOrderList;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.UniqueDriverList;
import seedu.address.model.item.Dish;
import seedu.address.model.item.Person;
import seedu.address.model.item.UniqueDishList;
import seedu.address.model.item.UniquePersonList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueOrderList orders;
    private final UniqueDishList dishes;
    private final UniqueDriverList drivers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        orders = new UniqueOrderList();
        dishes = new UniqueDishList();
        drivers = new UniqueDriverList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the dish list with {@code dishes}.
     * {@code dishes} must not contain duplicate dishes.
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes.setDishes(dishes);
    }

    /**
     * Replaces the contents of the driver list with {@code drivers}.
     * {@code drivers} must not contain duplicate dishes.
     */
    public void setDrivers(List<Driver> drivers) {
        this.drivers.setDrivers(drivers);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setDishes(newData.getDishList());
        setDrivers(newData.getDriverList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a driver with the same identity as {@code driver} exists in the database
     */
    public boolean hasDriver(Driver driver) {
        requireNonNull(driver);
        return drivers.contains(driver);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    public void addDriver(Driver d) {
        drivers.add(d);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    public void removeDriver(Driver key) {
        drivers.remove(key);
    }

    /**
     * Returns true if a dish with the same identity as {@code dish} exists in the address book.
     */
    public boolean hasDish(Dish dish) {
        requireNonNull(dish);
        return dishes.contains(dish);
    }

    /**
     * Adds a dish to FoodOnWheels.
     * The dish must not already exist in FoodOnWheels.
     */
    public void addDish(Dish d) {
        dishes.add(d);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeDish(Dish key) {
        dishes.remove(key);
    }


    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons "
                + dishes.asUnmodifiableObservableList().size() + " dishes "
                + drivers.asUnmodifiableObservableList().size() + " drivers";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Driver> getDriverList() {
        return drivers.asUnmodifiableObservableList();
    }
    @Override
    public ObservableList<Dish> getDishList() {
        return dishes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    /**
     * Adds an order.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    /**
     * Returns true if an order with the same identity as {@code order} exists in the address book.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }
}

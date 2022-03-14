package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.order.Order;
import seedu.address.model.driver.Driver;
import seedu.address.model.item.Dish;
import seedu.address.model.item.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Driver> PREDICATE_SHOW_ALL_DRIVERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Dish> PREDICATE_SHOW_ALL_DISHES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);
    boolean hasDriver(Driver driver);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);
    void deleteDriver(Driver driverToDelete);
    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);
    void addDriver(Driver driver);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns true if a dish with the same identity as {@code dish} exists in FoodOnWheels.
     */
    boolean hasDish(Dish dish);

    /**
     * Deletes the given dish.
     * The dish must exist in the address book.
     */
    void deleteDish(Dish target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addDish(Dish dish);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();
    ObservableList<Driver> getFilteredDriverList();

    /** Returns an unmodifiable view of the filtered dish list */
    ObservableList<Dish> getFilteredDishList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Adds the given order.
     */
    void addOrder(Order order);
  
    void updateFilteredDriverList(Predicate<Driver> driver);

    /**
     * Updates the filter of the filtered dish list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDishList(Predicate<Dish> predicate);
}

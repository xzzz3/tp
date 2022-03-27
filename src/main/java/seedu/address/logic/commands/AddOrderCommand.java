package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.exceptions.CustomerNotFoundException;
import seedu.address.model.driver.Driver;
import seedu.address.model.item.Dish;
import seedu.address.model.item.exceptions.DishNotFoundException;
import seedu.address.model.order.Order;
import seedu.address.model.order.exception.NoFreeDriverException;

public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an order.\n"
            + "Parameters: "
            + "p/ [PHONE] d/ [DISHES]\n"
            + "Example: " + COMMAND_WORD
            + " p/ 87654321 d/ Chicken Pasta, Fries"; // todo change to static variable

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists!";


    private final String phoneInput;
    private final String[] dishesInput;

    /**
     * Creates an AddCommand to add the specified {@code Order}
     */
    public AddOrderCommand(String phone, String ... dishes) {
        requireAllNonNull(phone, dishes);
        this.phoneInput = phone;
        this.dishesInput = dishes;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS),
                false, false, false, false, true);
    }

    /**
     * Executes the AddOrderCommand and returns the result message.
     */
    public CommandResult execute(Model model,
                                 ObservableList<Customer> customers, ObservableList<Driver> drivers,
                                 ObservableList<Dish> dishes) {
        requireAllNonNull(model, customers, drivers, dishes);

        // todo add duplicate checker

        // checking for free driver to assign for the newly created order
        Driver freeDriver = null;
        for (Driver driver : drivers) {
            if (driver.isFree()) {
                freeDriver = driver;
                break;
            }
        }

        if (freeDriver == null) {
            throw new NoFreeDriverException();
        }

        // matching customer phone number in input to the actual customer in stored data
        Customer customer = null;
        for (Customer storedCustomer : customers) {
            if (storedCustomer.getPhone().toString().equals(phoneInput)) {
                customer = storedCustomer;
                break;
            }
        }

        if (customer == null) {
            throw new CustomerNotFoundException();
        }

        // matching dishes in string in the input to the actual dishes in menu
        ArrayList<Dish> addedDishes = new ArrayList<Dish> ();

        List<String> dishesInputList = Arrays.asList(dishesInput);
        for (Dish dish : dishes) {
            if (dishesInputList.contains(dish.toString())) {
                addedDishes.add(dish); // todo use comma as delimiter
            }
        }

        if (dishesInputList.size() != addedDishes.size()) {
            throw new DishNotFoundException();
        }

        Order toAdd = new Order(customer, freeDriver, addedDishes.toArray(new Dish[0]));
        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                false, false, false, false, true);
    }


}

package seedu.address.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customer.Customer;
import seedu.address.model.driver.Driver;
import seedu.address.model.order.Order;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final Customer customer;
    private final Driver driver;
    private final ArrayList<String> dishes;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given dish details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("customer") Customer customer,
                            @JsonProperty("driver") Driver driver,
                            @JsonProperty("dishes") ArrayList<String> dishes) {
        this.customer = customer;
        this.dishes = dishes;
        this.driver = driver;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        customer = source.getCustomer();
        dishes = source.getDishes();
        driver = source.getDriver();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (customer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        final Customer modelCustomer = customer;

        if (dishes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        final ArrayList<String> modelDishes = dishes;

        return new Order(customer, driver, dishes.toArray(new String[0]));
    }

}


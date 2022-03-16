package seedu.address.storage;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.driver.Driver;
import seedu.address.model.order.Order;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String customerPhone;
    private final Driver driver;
    private final ArrayList<String> dishes;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given dish details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("customerPhone") String customerPhone,
                            @JsonProperty("driver") Driver driver,
                            @JsonProperty("dishes") ArrayList<String> dishes) {
        this.customerPhone = customerPhone;
        this.dishes = dishes;
        this.driver = driver;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        customerPhone = source.getCustomerPhone();
        dishes = source.getDishes();
        driver = source.getDriver();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (customerPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        final String modelPhone = customerPhone;

        if (dishes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        final ArrayList<String> modelDishes = dishes;

        return new Order("Dummy Customer", modelPhone, driver, dishes.toArray(new String[0]));
    }

}


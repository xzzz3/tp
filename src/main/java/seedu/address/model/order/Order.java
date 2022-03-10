package seedu.address.model.order;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Order {
    // Data fields
    private final String customer;  // todo change to Customer class in v1.3++
    private final String phone; // temporary variable before customer class with phone is implemented
    private final String driver; // todo change this to Driver class in v1.3++
    private final int orderNumber;
    private final ArrayList<String> dishes;  // todo change to Dish calss in v1.3++
    private OrderStatus status;

    // class-level attribute
    private static int nextOrderNumber = 1;

    /**
     * Every field must be present and not null.
     */
    public Order(String customer, String phone, String driverName, String ... orderedDishes) {
        requireAllNonNull(customer, orderedDishes);
        this.customer = customer;
        this.driver = driverName;
        this.dishes = new ArrayList<String>();
        for (String dish : orderedDishes) {
            this.dishes.add(dish);
        }
        this.phone = phone;
        this.orderNumber = Order.nextOrderNumber;
        nextOrderNumber++;
        this.status = OrderStatus.CREATED;
    }

    public String getCustomerName() {
        return customer;
    }

    public String getCustomerPhone() {
        return phone;
    }

    /*
    public Address getCustomerAddress() {
        return customer.getAddress();
    }
    */

    public String getDriverName() {
        return driver;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<String> getDishes() {
        return dishes;
    }

    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Returns true if both orders share the same unique order number.
     * @param other the other order to be compared with
     * @return a boolean value indicating the equality
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return this.orderNumber == otherOrder.orderNumber;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getOrderNumber())
                .append("; Customer: ")
                .append(getCustomerName())
                .append("; Phone: ")
                .append(getCustomerPhone())
                /*
                .append("; Address: ")
                .append(getCustomerAddress())
                 */
                .append("; Driver: ")
                .append(getDriverName())
                .append("; Status: ")
                .append(getStatus());

        ArrayList<String> dishes = getDishes();
        builder.append("; Dishes: ");
        dishes.forEach(builder::append);

        return builder.toString();
    }
}

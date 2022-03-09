package seedu.address.model.order;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Order {
    // Data fields
    private final Customer customer;
    private final Driver driver;
    private final int orderNumber;
    private final ArrayList<Dish> dishes;
    private OrderStatus status;

    // class-level attribute
    private static int nextOrderNumber = 1;

    /**
     * Every field must be present and not null.
     */
    public Order(Customer customer, Dish ... orderedDishes) {
        requireAllNonNull(customer, orderedDishes);
        this.customer = customer;
        this.driver = DriverList.getNextFreeDriver();
        this.dishes = new ArrayList<Dish>();
        for (Dish dish : orderedDishes) {
            this.dishes.add(dish);
        }
        this.orderNumber = Order.nextOrderNumber;
        nextOrderNumber++;
        this.status = OrderStatus.CREATED;
    }

    public Name getCustomerName() {
        return customer.getName();
    }

    public Phone getCustomerPhone() {
        return customer.getPhone();
    }

    public Address getCustomerAddress() {
        return customer.getAddress();
    }

    public Name getDriverName() {
        return driver.getName();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<Dish> getDishes() {
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
                .append("; Address: ")
                .append(getCustomerAddress())
                .append("; Driver: ")
                .append(getDriverName())
                .append("; Status: ")
                .append(getStatus());

        ArrayList<Dish> dishes = getDishes();
        builder.append("; Dishes: ");
        dishes.forEach(builder::append);

        return builder.toString();
    }
}

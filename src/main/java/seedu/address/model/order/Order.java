package seedu.address.model.order;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.driver.Driver;
import seedu.address.model.dish.Dish;

/**
 * Represents an Order in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order {
    // class-level attribute
    private static int nextOrderNumber = 1;

    // Data fields
    private final Customer customer;
    private final Driver driver;
    private final int orderNumber;
    private final ArrayList<Dish> dishes;
    private final LocalDateTime time;
    private OrderStatus status;

    /**
     * Every field must be present and not null.
     */
    public Order(Customer customer, Driver driver, LocalDateTime time, Dish ... orderedDishes) {
        requireAllNonNull(customer, orderedDishes);
        this.customer = customer;
        this.driver = driver;
        driver.setStatus("occupied");
        this.dishes = new ArrayList<Dish>();
        for (Dish dish : orderedDishes) {
            this.dishes.add(dish);
        }
        this.time = time;
        this.orderNumber = Order.nextOrderNumber;
        nextOrderNumber++;
        this.status = OrderStatus.CREATED;
    }

    public Order(Customer customer, Driver driver, Dish ... orderedDishes) {
        this(customer, driver, LocalDateTime.now(), orderedDishes);
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getCustomerName() {
        return customer.getName().fullName;
    }

    public String getCustomerPhone() {
        return customer.getPhone().value;
    }

    public AddressCustomer getCustomerAddress() {
        return customer.getAddress();
    }

    public Driver getDriver() {
        return driver;
    }

    public String getDriverName() {
        return driver.getName().fullName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    public LocalDateTime getTime() {
        return time;
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
                .append(getStatus())
                .append("; Time: ")
                .append(getTime());

        ArrayList<Dish> dishes = getDishes();
        builder.append("; Dishes: ");
        dishes.forEach(builder::append);

        return builder.toString();
    }

    /**
     * Updates the status of the {@code Order}.
     * @param status the status in lower-case String
     * @return an {@code Order} with the updated order status.
     */
    public Order updateStatus(String status) {
        if (status.equals("created")) {
            this.status = OrderStatus.CREATED;
        } else if (status.equals("in progress")) {
            this.status = OrderStatus.IN_PROGRESS;
        } else if (status.equals("delivered")) {
            this.status = OrderStatus.DELIVERED;
            this.driver.setStatus("free");
        } else if (status.equals("cancelled")) {
            this.status = OrderStatus.CANCELLED;
        }
        // todo implement exception for invalid status
        return this;
    }
}

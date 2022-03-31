package seedu.address.testutil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;
import seedu.address.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {

    public static final Order AMY_ORDER = new OrderBuilder().withCustomer(TypicalCustomers.AMY)
            .withDriver(new Driver(new NameDriver("Zac"), new PhoneDriver("83725646")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Sushi"), new PriceDish("4.99")))))
            .build();

    public static final Order BENSON_ORDER = new OrderBuilder().withCustomer(TypicalCustomers.BENSON)
            .withDriver(new Driver(new NameDriver("Yong Jie"), new PhoneDriver("94615254")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Kimchi"), new PriceDish("5.50")),
                    new Dish(new NameDish("Truffle Fries"), new PriceDish("6.70")))))
            .build();

    public static final Order CARL_ORDER = new OrderBuilder().withCustomer(TypicalCustomers.CARL)
            .withDriver(new Driver(new NameDriver("Xavier"), new PhoneDriver("67776888")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Fried Rice"), new PriceDish("6.50")))))
            .build();

    public static final Order DANIEL_ORDER = new OrderBuilder().withCustomer(TypicalCustomers.DANIEL)
            .withDriver(new Driver(new NameDriver("Wilson"), new PhoneDriver("85736285")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Milktea"), new PriceDish("3.20") ))))
            .build();

    public static final Order ELLE_ORDER = new OrderBuilder().withCustomer(TypicalCustomers.ELLE)
            .withDriver(new Driver(new NameDriver("Vivi"), new PhoneDriver("72364513")))
            .withDish(new ArrayList<Dish>(Arrays.asList(new Dish(new NameDish("Chicken chop"), new PriceDish("4.50") ))))
            .build();

    private TypicalOrders() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical orders.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(AMY_ORDER, BENSON_ORDER, CARL_ORDER, DANIEL_ORDER, ELLE_ORDER));
    }
}

package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.customer.Address;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private NameCustomer nameCustomer;
    private PhoneCustomer phoneCustomer;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        nameCustomer = new NameCustomer(DEFAULT_NAME);
        phoneCustomer = new PhoneCustomer(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        nameCustomer = customerToCopy.getName();
        phoneCustomer = customerToCopy.getPhone();
        address = customerToCopy.getAddress();
    }

    /**
     * Sets the {@code NameCustomer} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.nameCustomer = new NameCustomer(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code PhoneCustomer} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phoneCustomer = new PhoneCustomer(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Customer build() {
        return new Customer(nameCustomer, phoneCustomer, address);
    }

}

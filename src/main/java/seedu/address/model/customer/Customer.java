package seedu.address.model.customer;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Customer in the addressCustomer book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Customer {

    // Identity fields
    private final NameCustomer nameCustomer;
    private final PhoneCustomer phoneCustomer;

    // Data fields
    private final AddressCustomer addressCustomer;

    /**
     * Every field must be present and not null.
     */
    public Customer(NameCustomer nameCustomer, PhoneCustomer phoneCustomer, AddressCustomer addressCustomer) {
        requireAllNonNull(nameCustomer, phoneCustomer, addressCustomer);
        this.nameCustomer = nameCustomer;
        this.phoneCustomer = phoneCustomer;
        this.addressCustomer = addressCustomer;
    }

    public NameCustomer getName() {
        return nameCustomer;
    }

    public PhoneCustomer getPhone() {
        return phoneCustomer;
    }

    public AddressCustomer getAddress() {
        return addressCustomer;
    }

    /**
     * Returns true if both customers have the same nameCustomer.
     * This defines a weaker notion of equality between two customers.
     */
    public boolean isSameCustomer(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
                && (otherCustomer.getName().equals(getName())
                || otherCustomer.getPhone().equals(getPhone()));
    }

    /**
     * Returns true if both customers have the same identity and data fields.
     * This defines a stronger notion of equality between two customers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getName().equals(getName())
                && otherCustomer.getPhone().equals(getPhone())
                && otherCustomer.getAddress().equals(getAddress());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nameCustomer, phoneCustomer, addressCustomer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; PhoneCustomer: ")
                .append(getPhone())
                .append("; AddressCustomer: ")
                .append(getAddress());

        return builder.toString();
    }

}

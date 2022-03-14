package seedu.address.model.driver;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Driver in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Driver {
    private final NameDriver name;
    private final PhoneDriver phone;

    private final String status;

    /**
     * Every field must be present and not null.
     */
    public Driver(NameDriver name, PhoneDriver phone) {
        requireAllNonNull(name, phone);
        this.name = name;
        this.phone = phone;
        this.status = "free";
    }

    public NameDriver getName() {
        return name;
    }

    public PhoneDriver getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public boolean isFree() {
        return status.equals("free");
    }

    /**
     * Returns true if both driver have the same name.
     * This defines a weaker notion of equality between two drivers.
     */
    public boolean isSameDriver(Driver otherDriver) {
        if (otherDriver == this) {
            return true;
        }

        return otherDriver != null
                && otherDriver.getName().equals((getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Driver)) {
            return false;
        }

        Driver otherDriver = (Driver) other;
        return otherDriver.getName().equals(getName())
                && otherDriver.getPhone().equals(getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone());

        return builder.toString();
    }

}
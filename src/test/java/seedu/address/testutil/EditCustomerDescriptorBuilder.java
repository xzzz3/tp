package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.EmailCustomer;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditCustomerDescriptorBuilder {

    private final EditPersonDescriptor descriptor;

    public EditCustomerDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditCustomerDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code customer}'s details
     */
    public EditCustomerDescriptorBuilder(Customer customer) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(customer.getName());
        descriptor.setPhone(customer.getPhone());
        descriptor.setAddress(customer.getAddress());
    }

    /**
     * Sets the {@code NameCustomer} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withName(String name) {
        descriptor.setName(new NameCustomer(name));
        return this;
    }

    /**
     * Sets the {@code PhoneCustomer} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new PhoneCustomer(phone));
        return this;
    }

    /**
     * Sets the {@code EmailCustomer} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new EmailCustomer(email));
        return this;
    }

    /**
     * Sets the {@code AddressCustomer} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new AddressCustomer(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditCustomerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditPersonDescriptor build() {
        return descriptor;
    }
}

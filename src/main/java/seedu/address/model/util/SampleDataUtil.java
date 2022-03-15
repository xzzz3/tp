package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(new NameCustomer("Alex Yeoh"), new PhoneCustomer("87438807"),
                new AddressCustomer("Blk 30 Geylang Street 29, #06-40")),
            new Customer(new NameCustomer("Bernice Yu"), new PhoneCustomer("99272758"),
                new AddressCustomer("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            new Customer(new NameCustomer("Charlotte Oliveiro"), new PhoneCustomer("93210283"),
                new AddressCustomer("Blk 11 Ang Mo Kio Street 74, #11-04")),
            new Customer(new NameCustomer("David Li"), new PhoneCustomer("91031282"),
                new AddressCustomer("Blk 436 Serangoon Gardens Street 26, #16-43")),
            new Customer(new NameCustomer("Irfan Ibrahim"), new PhoneCustomer("92492021"),
                new AddressCustomer("Blk 47 Tampines Street 20, #17-35")),
            new Customer(new NameCustomer("Roy Balakrishnan"), new PhoneCustomer("92624417"),
                new AddressCustomer("Blk 45 Aljunied Street 85, #11-31"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers()) {
            sampleAb.addCustomer(sampleCustomer);
        }
        return sampleAb;
    }


}

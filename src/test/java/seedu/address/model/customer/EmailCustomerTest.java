package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailCustomerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmailCustomer(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new EmailCustomer(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> EmailCustomer.isValidEmail(null));

        // blank email
        assertFalse(EmailCustomer.isValidEmail("")); // empty string
        assertFalse(EmailCustomer.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(EmailCustomer.isValidEmail("@example.com")); // missing local part
        assertFalse(EmailCustomer.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(EmailCustomer.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(EmailCustomer.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(EmailCustomer.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(EmailCustomer.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(EmailCustomer.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(EmailCustomer.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(EmailCustomer.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(EmailCustomer.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(EmailCustomer.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(EmailCustomer.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(EmailCustomer.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(EmailCustomer.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(EmailCustomer.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(EmailCustomer.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(EmailCustomer.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(EmailCustomer.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(EmailCustomer.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(EmailCustomer.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(EmailCustomer.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(EmailCustomer.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(EmailCustomer.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(EmailCustomer.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(EmailCustomer.isValidEmail("a@bc")); // minimal
        assertTrue(EmailCustomer.isValidEmail("test@localhost")); // alphabets only
        assertTrue(EmailCustomer.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(EmailCustomer.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special
        // characters
        assertTrue(EmailCustomer.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(EmailCustomer.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(EmailCustomer.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }
}

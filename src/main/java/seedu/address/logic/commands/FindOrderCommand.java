package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.OrderContainsKeywordsPredicate;

/**
 * Finds and lists all orders in address book who contains the phone number.
 */
public class FindOrderCommand extends Command {

    public static final String COMMAND_WORD = "findorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all orders whose names contain any of "
            + "the specified phone number and displays them as a list with index numbers.\n"
            + "Parameters: PHONENUMBER \n"
            + "Example: " + COMMAND_WORD + " 81234567";

    public final OrderContainsKeywordsPredicate predicate;

    public FindOrderCommand(OrderContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindCommand)
                && predicate.equals(((FindOrderCommand) other).predicate);
    }
}

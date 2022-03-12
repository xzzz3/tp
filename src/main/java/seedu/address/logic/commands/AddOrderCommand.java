package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.order.Order;

public class AddOrderCommand extends Command {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an order.\n"
            + "Parameters: "
            + "p/ [PHONE] d/ [DISHES]\n"
            + "Example: " + COMMAND_WORD
            + "p/ 87654321 d/ Chicken Pasta";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists!";


    private final Order toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Order}
     */
    public AddOrderCommand(String phone, String ... dishes) {
        requireAllNonNull(phone, dishes);
        toAdd = new Order("Dummy customer", phone, "Dummy driver", "Dummy dishes");
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // todo add duplicate checker
        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

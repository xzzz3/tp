package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.driver.Driver;

/**
 * Adds a driver to the database.
 */
public class AddDriverCommand extends Command {
    public static final String COMMAND_WORD = "adddriver";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a driver to the database. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 ";

    public static final String MESSAGE_SUCCESS = "New driver added: %1$s";
    public static final String MESSAGE_DUPLICATE_DRIVER = "This driver already exists in the database";

    private final Driver toAdd;

    /**
     * Creates an AddDriverCommand to add the specified {@code Driver}
     */
    public AddDriverCommand(Driver driver) {
        requireNonNull(driver);
        toAdd = driver;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDriver(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DRIVER);
        }

        model.addDriver(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDriverCommand // instanceof handles nulls
                && toAdd.equals(((AddDriverCommand) other).toAdd));
    }
}

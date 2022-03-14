package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.EditOrderStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
<<<<<<< HEAD
 * Parses input arguments and creates a new EditOrderCommand object
=======
 * Parses input arguments and creates a new EditOrderStatusCommand object
>>>>>>> Feature-FindOrderByPhone
 */
public class EditOrderStatusCommandParser {

    /**
<<<<<<< HEAD
     * Parses the given {@code String} of arguments in the context of the EditOrderCommand
     * and returns an EditOrderCommand object for execution.
=======
     * Parses the given {@code String} of arguments in the context of the EditOrderStatusCommand
     * and returns an EditOrderStatusCommand object for execution.
>>>>>>> Feature-FindOrderByPhone
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditOrderStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_STATUS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditOrderStatusCommand.MESSAGE_USAGE), ive);
        }

        String newStatus = argMultimap.getValue(PREFIX_STATUS).orElse("");

        return new EditOrderStatusCommand(index, newStatus);
    }
}

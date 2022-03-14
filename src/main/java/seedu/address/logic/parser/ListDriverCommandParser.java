package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ListDriverCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.driver.StatusContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListDriverCommandParser object
 */
public class ListDriverCommandParser implements Parser<ListDriverCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListDriverCommand
     * and returns a ListDriverCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListDriverCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListDriverCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ListDriverCommand(new StatusContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

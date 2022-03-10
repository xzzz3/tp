package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ListDriverCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.driver.StatusContainsKeywordsPredicate;

public class ListDriverCommandParser implements Parser<ListDriverCommand> {

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

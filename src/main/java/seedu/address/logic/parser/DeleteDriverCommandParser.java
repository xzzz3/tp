package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDriverCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteDriverCommandParser implements Parser<DeleteDriverCommand> {
    public DeleteDriverCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDriverCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDriverCommand.MESSAGE_USAGE), pe);
        }
    }
}

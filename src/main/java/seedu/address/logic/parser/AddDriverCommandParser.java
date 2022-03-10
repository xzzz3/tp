package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddDriverCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;

public class AddDriverCommandParser implements Parser<AddDriverCommand> {
    public AddDriverCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDriverCommand.MESSAGE_USAGE));
        }

        NameDriver name = ParserUtil.parseNameDriver(argMultimap.getValue(PREFIX_NAME).get());
        PhoneDriver phone = ParserUtil.parsePhoneDriver(argMultimap.getValue(PREFIX_PHONE).get());

        Driver driver = new Driver(name, phone);

        return new AddDriverCommand(driver);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
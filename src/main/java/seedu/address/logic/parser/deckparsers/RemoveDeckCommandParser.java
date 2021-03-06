package seedu.address.logic.parser.deckparsers;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deckcommands.RemoveDeckCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new RemoveDeckCommand object
 */
public class RemoveDeckCommandParser implements Parser<RemoveDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveDeckCommand
     * and returns an RemoveDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveDeckCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new RemoveDeckCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveDeckCommand.MESSAGE_USAGE), pe);
        }
    }
}

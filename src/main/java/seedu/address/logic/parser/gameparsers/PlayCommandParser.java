package seedu.address.logic.parser.gameparsers;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.gamecommands.PlayCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new PlayCommand object
 */
public class PlayCommandParser implements Parser<PlayCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PlayCommand
     * and returns an PlayCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PlayCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new PlayCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlayCommand.MESSAGE_USAGE), pe);
        }
    }
}

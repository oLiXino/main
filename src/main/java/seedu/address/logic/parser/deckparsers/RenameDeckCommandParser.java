package seedu.address.logic.parser.deckparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RenameDeckCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.dump.Name;

/**
 * Parses input arguments and creates a new RenameDeckCommand object
 */
public class RenameDeckCommandParser implements Parser<RenameDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RenameDeckCommand
     * and returns an RenameDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RenameDeckCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String[] values = args.split(" ");
        if (values.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameDeckCommand.MESSAGE_USAGE));
        }

        // to handle exception and change parsing method to regex
        Index index = Index.fromOneBased(Integer.parseInt(values[0].trim()));
        Name newName = new Name(values[1].trim());

        return new RenameDeckCommand(index, newName);
    }
}

package seedu.address.logic.parser.deckparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.dump.CreateDeckCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;

/**
 * Parses input arguments and creates a new CreateDeckCommand object
 */
public class CreateDeckCommandParser implements Parser<CreateDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateDeckCommand
     * and returns an CreateDeckCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateDeckCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateDeckCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(trimmedArgs);

        Deck deck = new Deck(name);
        return new CreateDeckCommand(deck);
    }
}

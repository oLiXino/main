package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.cardcommands.AddCardCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCardCommandParser implements Parser<AddCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCardCommand parse(String args) throws ParseException {
        // naive check -> must have 1 and only 1 ":" in input
        String[] values = args.split(":");
        if (values.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));
        }

        FrontFace front = new FrontFace(values[0].trim());
        BackFace back = new BackFace(values[1].trim());
        Card card = new Card(front, back);

        return new AddCardCommand(card);
    }
}

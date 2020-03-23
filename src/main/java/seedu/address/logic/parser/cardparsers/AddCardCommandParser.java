package seedu.address.logic.parser.cardparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.cardcommands.AddCardCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

/**
 * Parses input arguments and creates a new AddCardCommand object.
 */
public class AddCardCommandParser implements Parser<AddCardCommand> {

    /**
     * Gets pattern FRONT:BACK, spaces before and after ":" are handled.
     */
    private final Pattern COMMAND_FORMAT = Pattern.compile("(?<front>.*)(\\s*:\\s*)(?<back>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCardCommand
     * and returns an AddCardCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddCardCommand parse(String args) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT.matcher(args.strip());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));
        }

        final String frontValue = matcher.group("front");
        final String backValue = matcher.group("back");

        if (frontValue.isBlank() || backValue.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));
        }

        FrontFace front = new FrontFace(frontValue);
        BackFace back  = new BackFace(backValue);
        
        Card card = new Card(front, back);
        return new AddCardCommand(card);
    }
}

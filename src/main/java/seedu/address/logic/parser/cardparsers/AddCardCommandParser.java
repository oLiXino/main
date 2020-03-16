package seedu.address.logic.parser.cardparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.cardcommands.AddCardCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses input arguments and creates a new AddCardCommand object.
 */
public class AddCardCommandParser implements Parser<AddCardCommand> {

    /**
     * Used to get pattern FRONT:BACK, spaces before and after ":" is handled.
     */
    private final Pattern COMMAND_FORMAT = Pattern.compile("(?<front>.*)(\\s*:\\s*)(?<back>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCardCommand
     * and returns an AddCardCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddCardCommand parse(String args) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));
        }

        final String frontValue = matcher.group("front");
        final String backValue = matcher.group("back");

//        // first occurrence of ":" will be used as the delineation between the front and back values
//        int delinIdx = args.indexOf(":");
//        if (delinIdx == -1) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));
//        }
//
//        String frontValue = args.substring(0, delinIdx).strip();
//        String backValue  = args.substring(delinIdx + 1).strip();

        if (frontValue.isBlank() || backValue.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE));
        }
        
        FrontFace front = new FrontFace(frontValue);
        BackFace  back  = new BackFace(backValue);
        
        Card card = new Card(front, back);
        return new AddCardCommand(card);
    }
}

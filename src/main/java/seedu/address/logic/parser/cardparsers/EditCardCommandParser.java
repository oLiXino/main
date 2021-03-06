package seedu.address.logic.parser.cardparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.cardcommands.EditCardCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses input arguments and creates a new EditCardCommand object.
 */
public class EditCardCommandParser implements Parser<EditCardCommand> {

    /**
     * Gets pattern INDEX FRONT:BACK, spaces before and after ":" is handled.
     * Also, can choose whether to allow no space between INDEX and FRONT:BACK.
     * Allows for fast edit (INDEX :BACK) or (INDEX FRONT:).
     */
    private final Pattern COMMAND_FORMAT = Pattern.compile("(?<index>\\d+)(\\s+)(?<front>.*)(\\s*:\\s*)(?<back>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCardCommand
     * and returns a DeleteCardCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditCardCommand parse(String args) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT.matcher(args.strip());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE));
        }

        final String indexStr = matcher.group("index");
        final String frontValue = matcher.group("front");
        final String backValue = matcher.group("back");

        if (frontValue.isBlank() && backValue.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE));
        }

        FrontFace front = new FrontFace(frontValue);
        BackFace back  = new BackFace(backValue);
        Card card = new Card(front, back);

        try {
            Index index = ParserUtil.parseIndex(indexStr);
            return new EditCardCommand(index, card);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE), pe);
        }
    }
}

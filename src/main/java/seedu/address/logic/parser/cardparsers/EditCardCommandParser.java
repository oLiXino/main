package seedu.address.logic.parser.cardparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.cardcommands.AddCardCommand;
import seedu.address.logic.commands.cardcommands.EditCardCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

/**
 * Parses input arguments and creates a new EditCardCommand object.
 */
public class EditCardCommandParser implements Parser<EditCardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCardCommand
     * and returns a DeleteCardCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditCardCommand parse(String args) throws ParseException {
        int firstSpaceIdx = args.indexOf(" ");
        if (firstSpaceIdx == -1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE));
        }
        String deckIdxStr = args.substring(0, firstSpaceIdx);
        
        String cardValues = args.substring(firstSpaceIdx + 1);
        int delinIdx = cardValues.indexOf(":");
        if (delinIdx == -1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE));
        }
        
        String frontValue = args.substring(0, delinIdx).strip();
        String backValue  = args.substring(delinIdx + 1).strip();

        if (frontValue.isBlank() || backValue.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE));
        }

        FrontFace front = new FrontFace(frontValue);
        BackFace back  = new BackFace(backValue);
        Card card = new Card(front, back);
        
        try {
            Index index = ParserUtil.parseIndex(deckIdxStr);
            return new EditCardCommand(index, card);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE), pe);
        }
    }
}

package seedu.address.logic.parser.cardparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.cardcommands.AddCardCommand;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.deck.card.FrontFace;

class AddCardCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCardCommand.MESSAGE_USAGE);

    private AddCardCommandParser parser = new AddCardCommandParser();

    @Test
    void parse_noColon_failure() {
        assertParseFailure(parser, "front, back", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_valid_success() {
        Card toAdd = new Card(new FrontFace("front value 1"), new BackFace("back value 1"));
        AddCardCommand expectedCommand = new AddCardCommand(toAdd);
        assertParseSuccess(parser, "front value 1:back value 1", expectedCommand);
    }
}

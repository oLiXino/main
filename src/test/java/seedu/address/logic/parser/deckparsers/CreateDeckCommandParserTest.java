package seedu.address.logic.parser.deckparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.deckcommands.CreateDeckCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

class CreateDeckCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateDeckCommand.MESSAGE_USAGE);

    private CreateDeckCommandParser parser = new CreateDeckCommandParser();

    @Test
    void parse_blankName_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "        ", MESSAGE_INVALID_FORMAT);
    }
}

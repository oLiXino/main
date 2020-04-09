package seedu.address.logic.parser.deckparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.deckcommands.SelectDeckCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

class SelectDeckCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectDeckCommand.MESSAGE_USAGE);

    private SelectDeckCommandParser parser = new SelectDeckCommandParser();

    @Test
    void parse_invalidIndex_failure() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
    }
}

package seedu.address.logic.parser.deckparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.deckcommands.RenameDeckCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

class RenameDeckCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameDeckCommand.MESSAGE_USAGE);

    private RenameDeckCommandParser parser = new RenameDeckCommandParser();

    @Test
    void parse_blankName_failure() {
        assertParseFailure(parser, "1  ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidIndex_failure() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
    }
}

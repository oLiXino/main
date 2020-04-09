package seedu.address.logic.parser.gameparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.gamecommands.PlayCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

class PlayCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PlayCommand.MESSAGE_USAGE);

    private PlayCommandParser parser = new PlayCommandParser();

    @Test
    void parse_invalidIndex_failure() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
    }
}

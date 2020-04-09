package seedu.address.logic.parser.cardparsers;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.cardcommands.DeleteCardCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class DeleteCardCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCardCommand.MESSAGE_USAGE);

    private DeleteCardCommandParser parser = new DeleteCardCommandParser();

    @Test
    void parse_invalidIndex_failure() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_valid_success() {
        DeleteCardCommand expectedCommand = new DeleteCardCommand(Index.fromOneBased(1));
        assertParseSuccess(parser, "1", expectedCommand);
    }
}

package seedu.address.logic.parser.deckparsers;

        import org.junit.jupiter.api.Test;
        import seedu.address.logic.commands.deckcommands.RemoveDeckCommand;

        import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
        import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

class RemoveDeckCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveDeckCommand.MESSAGE_USAGE);

    private RemoveDeckCommandParser parser = new RemoveDeckCommandParser();

    @Test
    void parse_invalidIndex_failure() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
    }
}

package seedu.address.logic.parser.cardparsers;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.cardcommands.EditCardCommand;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.FrontFace;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class EditCardCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCardCommand.MESSAGE_USAGE);

    private Index idxOneFromOneBased = Index.fromOneBased(1);
    private FrontFace ff = new FrontFace("front");
    private BackFace bf = new BackFace("back");
    private FrontFace emptyFront = new FrontFace("");
    private BackFace emptyBack = new BackFace("");

    private EditCardCommandParser parser = new EditCardCommandParser();

    @Test
    void parse_noIndex_failure() {
        assertParseFailure(parser, "front:back", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_noFrontAndBack_failure() {
        assertParseFailure(parser, "1 :", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_noColon_failure() {
        assertParseFailure(parser, "1 front, back", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_invalidIndex_failure() {
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_withFrontOnly_success() {
        EditCardCommand expectedCommand = new EditCardCommand(idxOneFromOneBased, ff, emptyBack);
        assertParseSuccess(parser, "1 front:", expectedCommand);
    }

    @Test
    void parse_withBackOnly_success() {
        EditCardCommand expectedCommand = new EditCardCommand(idxOneFromOneBased, emptyFront, bf);
        assertParseSuccess(parser, "1 :back", expectedCommand);
    }

    @Test
    void parse_withFrontAndBack_success() {
        EditCardCommand expectedCommand = new EditCardCommand(idxOneFromOneBased, ff, bf);
        assertParseSuccess(parser, "1 front:back", expectedCommand);
    }
}

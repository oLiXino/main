package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.DeckUtils;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

public class ReturnToLibraryCommandTest {

    @Test
    public void execute_return_success() {
        Model model = new ModelManager(DeckUtils.getTypicalLibrary(), new UserPrefs());
        model.selectDeck(Index.fromZeroBased(0));

        Model expectedModel = new ModelManager(DeckUtils.getTypicalLibrary(), new UserPrefs());
        assertCommandSuccess(new ResetLibraryCommand(), model, ReturnToLibraryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_inPlayMode_throwsCommandException() {
        Model model = new ModelManager(DeckUtils.getTypicalLibrary(), new UserPrefs());
        model.play(Index.fromZeroBased(0));

        assertCommandFailure(new ReturnToLibraryCommand(), model, ReturnToLibraryCommand.MESSAGE_NOT_IN_VIEW_MODE);
    }

    @Test
    public void execute_alreadyInLibraryView_throwsCommandException() {
        Model model = new ModelManager();

        assertCommandFailure(new ReturnToLibraryCommand(), model, ReturnToLibraryCommand.MESSAGE_ALREADY_IN_LIBRARY);
    }
}

package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.DeckUtils;

public class ResetLibraryCommandTest {

    @Test
    public void execute_emptyLibrary_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ResetLibraryCommand(), model, ResetLibraryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyLibrary_success() {
        Model model = new ModelManager(DeckUtils.getTypicalLibrary(), new UserPrefs());
        Model expectedModel = new ModelManager(DeckUtils.getTypicalLibrary(), new UserPrefs());
        expectedModel.setLibrary(new Library());

        assertCommandSuccess(new ResetLibraryCommand(), model, ResetLibraryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_inPlayMode_throwsCommandException() {
        Model model = new ModelManager(DeckUtils.getTypicalLibrary(), new UserPrefs());
        model.play(Index.fromZeroBased(0));

        assertCommandFailure(new ResetLibraryCommand(), model, ResetLibraryCommand.MESSAGE_NOT_IN_VIEW_MODE);
    }
}

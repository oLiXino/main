package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Library;
import seedu.address.model.Model;

/**
 * Adds a person to the address book.
 */
public class ClearLibraryCommand extends Command {

    public static final String COMMAND_WORD = "reset";
    public static final String MESSAGE_SUCCESS = "Library has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLibrary(new Library());
        return new CommandResult(MESSAGE_SUCCESS);
    }

}

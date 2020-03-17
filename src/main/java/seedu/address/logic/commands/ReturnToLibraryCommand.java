package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.util.View;


/**
 * Creates a deck in the library.
 */
public class ReturnToLibraryCommand extends Command {

    public static final String COMMAND_WORD = "return";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns to Library Mode.";

    public static final String MESSAGE_SUCCESS = "Returned to Library Mode";
    public static final String MESSAGE_DECK_NOT_FOUND = "Already in Library Mode!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.getView().equals(View.LIBRARY)) {
            throw new CommandException(MESSAGE_DECK_NOT_FOUND);
        }

        model.returnToLibrary();

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReturnToLibraryCommand); // instanceof handles nulls
    }
}

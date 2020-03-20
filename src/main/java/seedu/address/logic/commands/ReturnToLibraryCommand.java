package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.util.View;

/**
 * Returns to the library view.
 */
public class ReturnToLibraryCommand extends Command {

    public static final String COMMAND_WORD = "return";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns to library view.";

    public static final String MESSAGE_SUCCESS = "Returned to library view.";
    public static final String MESSAGE_ALREADY_IN_LIBRARY = "Already in library view!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.getView().equals(View.LIBRARY)) {
            throw new CommandException(MESSAGE_ALREADY_IN_LIBRARY);
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

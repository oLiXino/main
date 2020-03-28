package seedu.address.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;
import seedu.address.model.util.Mode;
import seedu.address.model.util.View;

/**
 * Removes a deck from the library.
 */
public class RemoveDeckCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a deck from the library.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example:" + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DECK_SUCCESS = "Deleted Deck: %1$s";
    public static final String MESSAGE_NOT_IN_VIEW_MODE = "Not in View Mode";

    private final Index targetIndex;

    public RemoveDeckCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getMode() == Mode.PLAY) {
            throw new CommandException(MESSAGE_NOT_IN_VIEW_MODE);
        }

        List<Deck> lastShownList = model.getFilteredDeckList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
        }

        Deck deckToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteDeck(deckToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_DECK_SUCCESS, deckToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveDeckCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveDeckCommand) other).targetIndex)); // state check
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;


/**
 * Creates a deck in the library.
 */
public class SelectDeckCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Select a deck. "
            + "Parameters: "
            + " <Index of the deck>";

    public static final String MESSAGE_SUCCESS = "Deck selected: %1$s";
    public static final String MESSAGE_DECK_NOT_FOUND = "Deck not found in the library!";

    private final Index targetIdx;

    /**
     * Creates an AddCommand to add the specified {@code Deck}
     */
    public SelectDeckCommand(Index targetIdx) {
        requireNonNull(targetIdx);
        this.targetIdx = targetIdx;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        /*
        if (!model.hasDeck(toSelect)) {
            throw new CommandException(MESSAGE_DECK_NOT_FOUND);
        }
        */

        model.selectDeck(targetIdx);
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIdx));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectDeckCommand // instanceof handles nulls
                && targetIdx.equals(((SelectDeckCommand) other).targetIdx));
    }
}

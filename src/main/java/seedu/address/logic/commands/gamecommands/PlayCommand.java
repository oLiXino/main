package seedu.address.logic.commands.gamecommands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PlayCommand extends Command {
    public static final String COMMAND_WORD = "play";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Play a session with the deck identified by the index number used in the displayed deck list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example:" + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deck selected: %1$s";
    public static final String MESSAGE_DECK_NOT_FOUND = "Deck not found in the library!";

    private final Index targetIdx;

    /**
     * Creates an PlayCommand with a specific {@code Deck}
     */
    public PlayCommand(Index targetIdx) {
        requireNonNull(targetIdx);
        this.targetIdx = targetIdx;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //List<Deck> filteredDeckList = model.getFilteredDeckList();

        Deck deck = model.play(targetIdx);
        if (deck == null) {
            throw new CommandException(MESSAGE_DECK_NOT_FOUND);
        }
        //model.setSelectedDeck(filteredDeckList.get(targetIdx.getZeroBased()));
        return new CommandResult(String.format(MESSAGE_SUCCESS, deck));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlayCommand // instanceof handles nulls
                && targetIdx.equals(((PlayCommand) other).targetIdx));
    }
}

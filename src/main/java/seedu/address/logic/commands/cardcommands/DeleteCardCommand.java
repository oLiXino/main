package seedu.address.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.card.Card;
import seedu.address.model.util.Mode;
import seedu.address.model.util.View;

/**
 * Deletes a card from the current deck.
 */
public class DeleteCardCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a card from the current deck.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Card: %1$s";
    public static final String MESSAGE_NOT_IN_VIEW_MODE = "Cannot delete card in the play view";

    private final Index targetIndex;

    public DeleteCardCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getMode() == Mode.PLAY) {
            throw new CommandException(MESSAGE_NOT_IN_VIEW_MODE);
        }

        if (model.getView() != View.DECK) {
            throw new CommandException(Messages.MESSAGE_NOT_IN_DECK_VIEW);
        }

        if (targetIndex.getZeroBased() >= model.getCurrentDeck().getSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        Card cardToDelete = model.getCard(targetIndex);
        model.deleteCard(cardToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, cardToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCardCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCardCommand) other).targetIndex)); // state check
    }
}

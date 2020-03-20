package seedu.address.logic.commands.cardcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.card.Card;

/**
 * Edits a card in the current deck.
 */
public class EditCardCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits a card in the current deck.\n"
            + "Either FRONT or BACK can be omitted.\n"
            + "Parameters: \n"
            + "INDEX (must be a positive integer) FRONT:BACK\n"
            + "or "
            + "INDEX (must be a positive integer) :BACK\n"
            + "or "
            + "INDEX (must be a positive integer) FRONT:\n"
            + "Example 1: " + COMMAND_WORD + " "
            + "1 "
            + "ありがとう:thanks\n"
            + "Example 2: " + COMMAND_WORD + " "
            + "1 "
            + "ありがとう:\n"
            + "Example 3: " + COMMAND_WORD + " "
            + "1 "
            + ":thanks";

    public static final String MESSAGE_SUCCESS = "Card edited: %1$s";

    private final Index targetIndex;
    private final Card editedCard;

    public EditCardCommand(Index targetIndex, Card editedCard) {
        this.targetIndex = targetIndex;
        this.editedCard = editedCard;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        /*
        List<Card> lastShownList = model.getFilteredCardList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }
        */

        // Card oldCard = lastShownList.get(targetIndex.getZeroBased());
        Card oldCard = model.getCard(targetIndex);

        model.replaceCard(oldCard, editedCard);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedCard));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditCardCommand // instanceof handles nulls
                && targetIndex.equals(((EditCardCommand) other).targetIndex)); // state check
    }
}

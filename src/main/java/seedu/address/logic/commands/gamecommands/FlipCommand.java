package seedu.address.logic.commands.gamecommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.util.Mode;

import static java.util.Objects.requireNonNull;

public class FlipCommand extends Command {
    public static final String COMMAND_WORD = "flip";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Flips the card to see the back face.\n";
    public static final String MESSAGE_SUCCESS = "Back face: %1$s";
    public static final String MESSAGE_NOT_PLAY_MODE = "Not in play mode!";
    public static final String MESSAGE_ALREADY_FLIPPED = "Card already flipped!";

    private BackFace backFace;
    
    /**
     * Creates an FlipCommand with a specific {@code Deck}
     */
    public FlipCommand() {
        this.backFace = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        
        if (model.getMode() == Mode.VIEW) {
            throw new CommandException(MESSAGE_NOT_PLAY_MODE);
        }
        BackFace backFace= model.flip();
        if (backFace == null) {
            throw new CommandException(MESSAGE_ALREADY_FLIPPED);
        }
        
        return new CommandResult(String.format(MESSAGE_SUCCESS, backFace));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlipCommand // instanceof handles nulls
                && backFace.equals(((FlipCommand) other).backFace));
    }
}

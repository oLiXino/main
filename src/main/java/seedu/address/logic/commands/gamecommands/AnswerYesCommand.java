package seedu.address.logic.commands.gamecommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.deck.card.Card;
import seedu.address.model.util.Mode;

import static java.util.Objects.requireNonNull;

public class AnswerYesCommand extends Command {
    public static final String COMMAND_WORD = "yes";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Answer yes if you have the correct answer.\n";
    public static final String MESSAGE_SUCCESS = "Nice, you got it right!";
    public static final String MESSAGE_NOT_PLAY_MODE = "Not in play mode!";
    public static final String MESSAGE_NOT_FLIPPED = "Card has not flipped yet!";
    public static final String MESSAGE_END_GAME = "Session ends";

    /**
     * Creates an YesCommand.
     */
    public AnswerYesCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getMode() == Mode.VIEW) {
            throw new CommandException(MESSAGE_NOT_PLAY_MODE);
        }
        Card nextCard = model.answerYes();
        if (nextCard == null) {
            throw new CommandException(MESSAGE_NOT_FLIPPED);
        }
        if (nextCard.getFrontFace() == null && nextCard.getBackFace() == null) {
            return new CommandResult(String.format(MESSAGE_END_GAME));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlipCommand); // instanceof handles nulls

    }
}

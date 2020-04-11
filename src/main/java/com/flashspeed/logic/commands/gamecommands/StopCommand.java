package com.flashspeed.logic.commands.gamecommands;

import static java.util.Objects.requireNonNull;

import com.flashspeed.model.Model;
import com.flashspeed.model.Statistics;
import com.flashspeed.logic.commands.Command;
import com.flashspeed.logic.commands.CommandResult;
import com.flashspeed.logic.commands.exceptions.CommandException;
import com.flashspeed.model.util.Mode;

public class StopCommand extends Command {
    public static final String COMMAND_WORD = "stop";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Stop the current game session.\n";
    public static final String MESSAGE_SUCCESS = "Session stopped!";
    public static final String MESSAGE_NOT_PLAY_MODE = "You have not started a new game session";

    Statistics statistics;
    /**
     * Creates an StopCommand
     */
    public StopCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getMode() == Mode.VIEW) {
            throw new CommandException(MESSAGE_NOT_PLAY_MODE);
        }
        statistics = model.stop();
        //return new CommandResult(String.format(MESSAGE_SUCCESS, statistics));
        return new CommandResult(String.format(MESSAGE_SUCCESS), false, false, true, statistics);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StopCommand // instanceof handles nulls
                && statistics.equals(((StopCommand) other).statistics));
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.*;
import seedu.address.logic.commands.deckcommands.RenameDeckCommand;
import seedu.address.logic.commands.cardcommands.AddCardCommand;
import seedu.address.logic.commands.cardcommands.DeleteCardCommand;
import seedu.address.logic.commands.cardcommands.EditCardCommand;
import seedu.address.logic.commands.deckcommands.CreateDeckCommand;
import seedu.address.logic.commands.deckcommands.RemoveDeckCommand;
import seedu.address.logic.commands.deckcommands.SelectDeckCommand;
import seedu.address.logic.commands.gamecommands.AnswerNoCommand;
import seedu.address.logic.commands.gamecommands.AnswerYesCommand;
import seedu.address.logic.commands.gamecommands.FlipCommand;
import seedu.address.logic.commands.gamecommands.PlayCommand;
import seedu.address.logic.parser.cardparsers.AddCardCommandParser;
import seedu.address.logic.parser.cardparsers.DeleteCardCommandParser;
import seedu.address.logic.parser.cardparsers.EditCardCommandParser;
import seedu.address.logic.parser.deckparsers.CreateDeckCommandParser;
import seedu.address.logic.parser.deckparsers.RemoveDeckCommandParser;
import seedu.address.logic.parser.deckparsers.RenameDeckCommandParser;
import seedu.address.logic.parser.deckparsers.SelectDeckCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.gameparsers.PlayCommandParser;

/**
 * Parses input for FlashSpeed.
 */
public class MasterParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        // to handle get of commandWord and args
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        /* List of commands:
        Deck:
          - CreateDeck
          - RemoveDeck
          - RenameDeck
          - SelectDeck
        Card:
          - AddCard
          - DeleteCard
          - EditCard
        Game:
          - Play
          - Flip
          - AnswerYes
          - AnswerNO
        General:
          - Help
          - Exit
          - ReturnToLibrary

         */
        switch (commandWord) {

        // Deck functions
        case CreateDeckCommand.COMMAND_WORD:
            return new CreateDeckCommandParser().parse(arguments);

        case RemoveDeckCommand.COMMAND_WORD:
            return new RemoveDeckCommandParser().parse(arguments);

        case RenameDeckCommand.COMMAND_WORD:
            return new RenameDeckCommandParser().parse(arguments);

        case SelectDeckCommand.COMMAND_WORD:
            return new SelectDeckCommandParser().parse(arguments);

        // Card functions
        case AddCardCommand.COMMAND_WORD:
        return new AddCardCommandParser().parse(arguments);

        case DeleteCardCommand.COMMAND_WORD:
            return new DeleteCardCommandParser().parse(arguments);

        case EditCardCommand.COMMAND_WORD:
            return new EditCardCommandParser().parse(arguments);

        // Game functions
        case PlayCommand.COMMAND_WORD:
            return new PlayCommandParser().parse(arguments);

        case FlipCommand.COMMAND_WORD:
            return new FlipCommand();

        case AnswerYesCommand.COMMAND_WORD:
            return new AnswerYesCommand();

        case AnswerNoCommand.COMMAND_WORD:
            return new AnswerNoCommand();

        // General
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ResetLibraryCommand.COMMAND_WORD:
            return new ResetLibraryCommand();

        case ReturnToLibraryCommand.COMMAND_WORD:
            return new ReturnToLibraryCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

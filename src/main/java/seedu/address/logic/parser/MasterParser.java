package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.RenameDeckCommand;
import seedu.address.logic.commands.ReturnToLibraryCommand;
import seedu.address.logic.commands.cardcommands.AddCardCommand;
import seedu.address.logic.commands.cardcommands.DeleteCardCommand;

import seedu.address.logic.commands.cardcommands.EditCardCommand;
import seedu.address.logic.commands.deckcommands.CreateDeckCommand;
import seedu.address.logic.commands.deckcommands.RemoveDeckCommand;
import seedu.address.logic.commands.deckcommands.SelectDeckCommand;
import seedu.address.logic.parser.cardparsers.AddCardCommandParser;
import seedu.address.logic.parser.cardparsers.DeleteCardCommandParser;
import seedu.address.logic.parser.cardparsers.EditCardCommandParser;
import seedu.address.logic.parser.deckparsers.CreateDeckCommandParser;
import seedu.address.logic.parser.deckparsers.RemoveDeckCommandParser;
import seedu.address.logic.parser.deckparsers.RenameDeckCommandParser;
import seedu.address.logic.parser.deckparsers.SelectDeckCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 Parses input for FlashSpeed.
 */
public class MasterParser {


    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {

        // to handle invalid input
        if (false) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        // to handle get of commandWord and args
        final String commandWord = userInput.split(" ")[0];
        final String arguments = userInput.split(" ")[1];

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

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        // other
        case ReturnToLibraryCommand.COMMAND_WORD:
            return new ReturnToLibraryCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

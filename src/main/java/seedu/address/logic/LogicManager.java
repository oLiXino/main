package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DeckParser;
import seedu.address.logic.parser.LibraryParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.deck.Deck;
import seedu.address.model.util.View;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    //private final View view;
    private final LibraryParser libParser;
    private final DeckParser deckParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        //this.view = View.LIBRARY;  // 1st view will always be in library
        this.libParser = new LibraryParser();
        this.deckParser = new DeckParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command;
        
        if (model.getView().equals(View.LIBRARY)) {
            command = libParser.parseCommand(commandText);
        } else {
            command = deckParser.parseCommand(commandText);
        }
        
        // somehow change this.view if command makes us move in/out of library/deck
        
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getLibrary());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyLibrary getAddressBook() {
        return model.getLibrary();
    }

    @Override
    public ObservableList<Deck> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}

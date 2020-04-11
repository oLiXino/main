package com.flashspeed.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import com.flashspeed.commons.core.GuiSettings;
import com.flashspeed.commons.core.LogsCenter;
import com.flashspeed.logic.commands.Command;
import com.flashspeed.logic.commands.CommandResult;
import com.flashspeed.logic.commands.exceptions.CommandException;
import com.flashspeed.logic.parser.MasterParser;
import com.flashspeed.logic.parser.exceptions.ParseException;
import com.flashspeed.model.Model;
import com.flashspeed.model.ReadOnlyLibrary;
import com.flashspeed.model.deck.Deck;
import com.flashspeed.model.deck.card.Card;
import com.flashspeed.model.util.View;
import com.flashspeed.storage.Storage;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;


/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MasterParser masterParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        this.masterParser = new MasterParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command;
        command = masterParser.parseCommand(commandText);
        commandResult = command.execute(model);
        try {
            storage.saveLibrary(model.getLibrary());
        } catch (IOException ioe) {
            //throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    public ReadOnlyLibrary getLibrary() {
        return model.getLibrary();
    }

    @Override
    public ObservableList<Deck> getFilteredDeckList() {
        return model.getFilteredDeckList();
    }

    @Override
    public Path getLibraryFilePath() {
        return model.getLibraryFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public Deck getCurrentDeck() {
        return model.getCurrentDeck();
    }

    @Override
    public ReadOnlyProperty<Deck> selectedDeckProperty() {
        return model.selectedDeckProperty();
    }

    @Override
    public ReadOnlyProperty<Card> playingCardProperty() {
        return model.playingCardProperty();
    }

    @Override
    public ReadOnlyProperty<View> currentViewProperty() {
        return model.currentViewProperty();
    }

    @Override
    public ReadOnlyProperty<Boolean> flippedProperty() {
        return model.flippedProperty();
    }

    @Override
    public ReadOnlyProperty<Integer> cardAttemptedProperty() {
        return model.cardAttemptedProperty();
    }

    @Override
    public ReadOnlyProperty<Integer> cardRemainingProperty() {
        return model.cardRemainingProperty();
    }

    @Override
    public void setSelectedDeck(Deck deck) {
        model.setSelectedDeck(deck);
    }

    @Override
    public View getView() {
        return model.getView();
    }
}

package com.flashspeed.logic;

import java.nio.file.Path;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;

import com.flashspeed.commons.core.GuiSettings;
import com.flashspeed.logic.commands.CommandResult;
import com.flashspeed.logic.commands.exceptions.CommandException;
import com.flashspeed.logic.parser.exceptions.ParseException;
import com.flashspeed.model.deck.Deck;
import com.flashspeed.model.deck.card.Card;
import com.flashspeed.model.util.Mode;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /** Returns an unmodifiable view of the filtered list of decks. */
    ObservableList<Deck> getFilteredDeckList();

    /**
     * Returns the user prefs' library file path.
     */
    Path getLibraryFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Get the selected deck
     */
    Deck getCurrentDeck();

    ReadOnlyProperty<Deck> selectedDeckProperty();

    ReadOnlyProperty<Card> playingCardProperty();

    ReadOnlyProperty<Mode> currentModeProperty();

    ReadOnlyProperty<Boolean> flippedProperty();

    ReadOnlyProperty<Integer> cardAttemptedProperty();

    ReadOnlyProperty<Integer> cardRemainingProperty();

    /**
     * Returns the mode of the model manager.
     */
    Mode getMode();

    void setSelectedDeck(Deck deck);
}

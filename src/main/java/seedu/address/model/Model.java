package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.util.Mode;
import seedu.address.model.util.View;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Deck> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setLibrary(ReadOnlyLibrary library);

    /** Returns the AddressBook */
    ReadOnlyLibrary getLibrary();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Deck deck);

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in the library.
     */
    boolean hasDeck(Deck deck);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Deck target);

    /**
     * Deletes the given deck.
     * The deck must exist in the library.
     */
    void deleteDeck(Deck target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Deck deck);

    /**
     * Adds the given deck.
     * {@code deck} must not already exist in the library.
     */
    void createDeck(Deck deck);

    /**
     * Selects a deck and enter Deck Mode.
     */
    void selectDeck(Index targetIdx);

    /**
     * Returns the current deck;
     */
    Deck getCurrentDeck();

    /**
     * Returns the deck at index of library;
     */
    Deck getDeck(Index index);

     /* Renames the a given deck.
     *
     * Returns true if there is no deck with the same name, false otherwise.
     */
    boolean renameDeck(Index targetIndex, Name name);

    /**
     * Returns the readonly property
     */
    ReadOnlyProperty<Deck> selectedDeckProperty();

    /**
     * Returns the readonly property
     */
    ReadOnlyProperty<Mode> currentModeProperty();

    /**
     * Returns the user from Deck Mode to Library Mode.
     */
    void returnToLibrary();

    /**
     * Returns true if a card with the same identity as {@code card} exists in the deck.
     */
    boolean hasCard(Card card);

    /**
     * Deletes the given card.
     * {@code target} must exist in the deck.
     */
    void deleteCard(Card target);
    
    /**
     * Adds the given card.
     * {@code card} must not already exist in the deck.
     */
    void addCard(Card card);

    /**
     * Replaces the given old card with the new card.
     * {@code target} must exist in the deck.
     * {@code card} must not already exist in the deck.
     */
    void replaceCard(Card target, Card card);

    /**
     * Gets the current view of the model.
     * @return The current view of the model.
     */
    public View getView();

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Deck target, Deck editedDeck);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Deck> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered deck list */
    ObservableList<Deck> getFilteredDeckList();

    /** Returns an unmodifiable view of the filtered card list */
    ObservableList<Card> getFilteredCardList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Deck> predicate);

    /**
     * Sets the selected person in the filtered person list.
     */
    void setSelectedDeck(Deck deck);

    void setCurrentMode(Mode mode);

    /**
     *
     * @return
     */
    Card getCard(Index index);

    /**
     * Changes the mode to play mode.
     */
    Card play(Index index);

    /**
     * Flips the card to the back face.
     * @return true if the card has not been flipped, false otherwise.
     */
    BackFace flip();

    /**
     * Returns the next card after user answer yes.
     * @return the next card or null if card list is empty.
     */
    Card answerYes();

    /**
     * Returns the next card after user answer no.
     * @return the next card or null if card list is empty.
     */
    Card answerNo();

    /**
     * Returns the mode of the model manager.
     */
    Mode getMode();

}

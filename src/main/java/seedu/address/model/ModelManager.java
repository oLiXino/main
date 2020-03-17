package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javax.swing.text.html.Option;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.card.Card;
import seedu.address.model.util.View;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Library library;
    private final UserPrefs userPrefs;
    private final FilteredList<Deck> filteredDecks;

    private View view;

    private Optional<Index> deckIndex;
    private final SimpleObjectProperty<Deck> selectedDeck = new SimpleObjectProperty<>();

    /**
     * Initializes a ModelManager with the given library and userPrefs.
     */
    public ModelManager(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(library, userPrefs);

        logger.fine("Initializing with address book: " + library + " and user prefs " + userPrefs);

        this.library = new Library(library);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDecks = new FilteredList<>(this.library.getDeckList());
        this.deckIndex = Optional.empty();

        this.view = View.LIBRARY;  // 1st view will always be in library
    }

    public ModelManager() {
        this(new Library(), new UserPrefs());
    }

    //=========== Getters ==================================================================================

    /**
     * Gets the current view of the model.
     * @return The current view of the model.
     */
    public View getView() {
        return view;
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public ReadOnlyProperty<Deck> selectedDeckProperty() {
        return selectedDeck;
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setLibrary(ReadOnlyLibrary library) {
        this.library.resetData(library);
    }

    @Override
    public ReadOnlyLibrary getLibrary() {
        return library;
    }

    @Override
    public boolean hasPerson(Deck deck) {
        requireNonNull(deck);
        return library.hasDeck(deck);
    }

    @Override
    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return library.hasDeck(deck);
    }

    @Override
    public void deletePerson(Deck target) {
        library.deleteDeck(target);
    }

    @Override
    public void deleteDeck(Deck target) {
        library.deleteDeck(target);
    }

    @Override
    public void addPerson(Deck deck) {
        library.createDeck(deck);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void createDeck(Deck deck) {
        library.createDeck(deck);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void selectDeck(Index targetIdx) {
        deckIndex = Optional.of(targetIdx);
        this.view = View.DECK;
    }

    @Override
    public Deck getCurrentDeck() {
        if (deckIndex.equals(Optional.empty())) {
            return null;
        }
        Deck deck = library.getDeck(deckIndex.get());
        setSelectedDeck(deck);
        return deck;
    }

    @Override
    public void setSelectedDeck(Deck deck) {
        selectedDeck.setValue(deck);
    }




    @Override
    public void returnToLibrary() {
        view = View.LIBRARY;
        deckIndex = Optional.empty();
    }

    @Override
    public boolean hasCard(Card card) {
        Deck deck = library.getDeck(deckIndex.get());
        if (deck == null) return false;
        requireNonNull(card);
        return deck.contains(card);
    }

    @Override
    public void deleteCard(Card target) {
        Deck deck = library.getDeck(deckIndex.get());
        if (deck == null) return;
        deck.remove(target);
    }

    @Override
    public void addCard(Card card) {
        Deck deck = library.getDeck(deckIndex.get());
        if (deck == null) return;
        deck.add(card);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void replaceCard(Card target, Card card) {
        Deck deck = library.getDeck(deckIndex.get());
        if (deck == null) return;
        deck.replace(target, card);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Deck target, Deck editedDeck) {
        requireAllNonNull(target, editedDeck);

        library.setPerson(target, editedDeck);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Deck> getFilteredPersonList() {
        return filteredDecks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Deck} backed by the internal list of
     */
    @Override
    public ObservableList<Deck> getFilteredDeckList() {
        return filteredDecks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Card} backed by the internal list of
     */
    @Override
    public ObservableList<Card> getFilteredCardList() {
        return filteredDecks.get(Integer.parseInt(deckIndex.get().toString())).asUnmodifiableObservableList();
    }

    @Override
    public void updateFilteredPersonList(Predicate<Deck> predicate) {
        requireNonNull(predicate);
        filteredDecks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return library.equals(other.library)
                && userPrefs.equals(other.userPrefs)
                && filteredDecks.equals(other.filteredDecks);
    }
}

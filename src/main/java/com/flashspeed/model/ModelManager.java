package com.flashspeed.model;

import static com.flashspeed.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.flashspeed.commons.core.GuiSettings;
import com.flashspeed.commons.core.LogsCenter;
import com.flashspeed.commons.core.index.Index;
import com.flashspeed.model.deck.Deck;
import com.flashspeed.model.deck.Name;
import com.flashspeed.model.deck.card.BackFace;
import com.flashspeed.model.deck.card.Card;
import com.flashspeed.model.util.View;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the library data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Library library;
    private final UserPrefs userPrefs;
    private final FilteredList<Deck> filteredDecks;

    private View view;

    private Optional<Index> deckIndex;
    private final SimpleObjectProperty<Deck> selectedDeck = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Card> playingCard = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Boolean> flipped = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<View> currentView = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Integer> cardAttempted = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Integer> cardRemaining = new SimpleObjectProperty<>();
    private GameManager game;

    /**
     * Initializes a ModelManager with the given library and userPrefs.
     */
    public ModelManager(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(library, userPrefs);

        logger.fine("Initializing with library: " + library + " and user prefs " + userPrefs);

        this.library = new Library(library);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDecks = new FilteredList<>(this.library.getDeckList());
        this.deckIndex = Optional.empty();
        this.view = View.LIBRARY; // 1st view will always be in library
        setCurrentView(View.LIBRARY);
        this.game = null;
    }

    /**
     * Creates a model manager.
     */
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

    /**
     * Returns the index of the selected deck.
     */
    public Optional<Index> getDeckIndex() {
        return this.deckIndex;
    }

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
    public ReadOnlyProperty<Card> playingCardProperty() {
        return playingCard;
    }

    @Override
    public ReadOnlyProperty<Boolean> flippedProperty() {
        return flipped;
    }

    @Override
    public ReadOnlyProperty<View> currentViewProperty() {
        return currentView;
    }

    @Override
    public ReadOnlyProperty<Integer> cardAttemptedProperty() {
        return cardAttempted;
    }

    @Override
    public ReadOnlyProperty<Integer> cardRemainingProperty() {
        return cardRemaining;
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getLibraryFilePath() {
        return userPrefs.getLibraryFilePath();
    }

    @Override
    public void setLibraryFilePath(Path libraryFilePath) {
        requireNonNull(libraryFilePath);
        userPrefs.setLibraryFilePath(libraryFilePath);
    }

    //=========== Library ================================================================================

    @Override
    public void setLibrary(ReadOnlyLibrary library) {
        this.library.resetData(library);
    }

    @Override
    public ReadOnlyLibrary getLibrary() {
        return library;
    }

    @Override
    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return library.hasDeck(deck);
    }

    @Override
    public void deleteDeck(Deck target) {
        library.deleteDeck(target);
        if (library.getSize() == 0) {
            returnToLibrary();
        }
    }

    @Override
    public void createDeck(Deck deck) {
        library.createDeck(deck);
        Index currIndex = Index.fromZeroBased(library.getDeckList().indexOf(deck));
        selectDeck(currIndex);
        setSelectedDeck(deck);
    }

    @Override
    public void selectDeck(Index targetIdx) {
        deckIndex = Optional.of(targetIdx);
        this.view = View.DECK;
        setCurrentView(View.DECK);
        setSelectedDeck(library.getDeck(targetIdx));
    }

    @Override
    public boolean renameDeck(Index targetIndex, Name name) {
        Deck deck = library.getDeck(targetIndex);
        Deck temp = new Deck(name);
        if (library.hasDeck(temp)) {
            return false;
        } else {
            deck.setName(name);

            selectDeck(targetIndex);
            returnToLibrary();
            setSelectedDeck(deck);
            return true;
        }
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
        if (deck != null) {
            selectedDeck.setValue(deck);
            Index currIndex = Index.fromZeroBased(library.getDeckList().indexOf(deck));
            deckIndex = Optional.of(currIndex);
            this.view = View.DECK;
            setCurrentView(View.DECK);
        }

    }

    @Override
    public void setFlipped(Boolean value) {
        flipped.setValue(value);
    }

    @Override
    public void setCurrentView(View view) {
        currentView.setValue(view);
    }

    @Override
    public void setCardAttempted(int value) {
        cardAttempted.setValue(value);
    }


    @Override
    public void setCardRemaining(int value) {
        cardRemaining.setValue(value);
    }

    @Override
    public void setPlayingCard(Card card) {
        playingCard.setValue(card);
    }

    @Override
    public Deck getDeck(Index targetIdx) {
        return library.getDeck(targetIdx);
    }

    @Override
    public void returnToLibrary() {
        selectedDeck.setValue(null);
        deckIndex = Optional.empty();
        this.view = View.LIBRARY;
        setCurrentView(View.LIBRARY);
    }

    @Override
    public boolean hasCard(Card card) {
        Deck deck = library.getDeck(deckIndex.get());
        if (deck == null) {
            return false;
        }
        requireNonNull(card);
        return deck.contains(card);
    }

    @Override
    public Card getCard(Index index) {
        return library.getDeck(deckIndex.get()).getCard(index);
    }

    @Override
    public void deleteCard(Card cardToDelete) {
        Deck deck = library.getDeck(deckIndex.get());
        if (deck == null) {
            return;
        }
        deck.remove(cardToDelete);
        returnToLibrary();
        setSelectedDeck(deck);
    }

    @Override
    public void addCard(Card card) {
        Deck deck = library.getDeck(deckIndex.get());
        if (deck == null) {
            return;
        }
        deck.add(card);
        returnToLibrary();
        setSelectedDeck(deck);
    }

    @Override
    public void replaceCard(Card target, Card card) {
        Deck deck = library.getDeck(deckIndex.get());
        if (deck == null) {
            return;
        }
        deck.replace(target, card);
        returnToLibrary();
        setSelectedDeck(deck);
    }

    @Override
    public void setDeck(Deck target, Deck editedDeck) {
        requireAllNonNull(target, editedDeck);
        library.setDeck(target, editedDeck);
    }

    //=========== Filtered Deck List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Deck} backed by the internal list.
     */
    @Override
    public ObservableList<Deck> getFilteredDeckList() {
        return filteredDecks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Card} backed by the internal list.
     */
    @Override
    public ObservableList<Card> getFilteredCardList() {
        return filteredDecks.get(deckIndex.get().getZeroBased()).asUnmodifiableObservableList();
    }

    @Override
    public void updateFilteredDeckList(Predicate<Deck> predicate) {
        requireNonNull(predicate);
        filteredDecks.setPredicate(predicate);
    }

    /**
     * Starts a game session with a given deck index.
     * @param index index of the deck to play with.
     * @return a randomly selected card from the deck
     */
    @Override
    public Card play(Index index) {
        Deck deck = library.getDeck(index);
        if (deck == null) {
            return null;
        }
        if (deck.asUnmodifiableObservableList().size() == 0) {
            return new Card(null, null);
        }
        this.game = new GameManager(deck);
        this.view = View.PLAY;
        setCurrentView(View.PLAY);
        Card card = deck.asUnmodifiableObservableList().get(game.getCurrCardIdx());
        setPlayingCard(card);
        setCardAttempted(0);
        setCardRemaining(game.getDeckSize());
        setFlipped(false);
        return card;
    }

    /**
     * Flips the card to the back face.
     * @return true if the card has not been flipped, false otherwise
     */
    @Override
    public BackFace flip() {
        setFlipped(true);
        return this.game.flip();
    }

    /**
     * Returns the next card after user answers Yes.
     * @return the next card or null if card list is empty
     */
    @Override
    public Card answerYes() {
        Card card = this.game.answerYes();
        if (card == null) {
            //Statistics statistics = stop();
        }
        setPlayingCard(card);
        setFlipped(false);
        setCardAttempted(game.getCardAttempted());
        setCardRemaining(game.getDeckSize());
        return card;
    }

    /**
     * Returns the game manager object.
     */
    public GameManager getGame() {
        return this.game;
    }

    /**
     * Returns the next card after user answers No.
     * @return the next card or null if card list is empty
     */
    @Override
    public Card answerNo() {
        Card card = this.game.answerNo();
        if (card == null) {
            //Statistics statistics = stop();
        }
        setPlayingCard(card);
        setFlipped(false);
        setCardAttempted(game.getCardAttempted());
        setCardRemaining(game.getDeckSize());
        return card;
    }

    /**
     * Stops the game session.
     * @return the statistics report.
     */
    @Override
    public Statistics stop() {
        Statistics statistics = this.game.stop();
        this.game = null;
        setPlayingCard(null);
        returnToLibrary();
        this.view = View.LIBRARY;
        setCurrentView(View.LIBRARY);
        return statistics;
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

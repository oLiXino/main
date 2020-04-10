package seedu.address.logic.commands.gamecommands;

import java.nio.file.Path;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GameManager;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Statistics;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.model.deck.card.BackFace;
import seedu.address.model.deck.card.Card;
import seedu.address.model.util.Mode;
import seedu.address.model.util.View;

public class FlipCommandTest {

    @Test
    public void execute_FlipSuccessful() throws Exception {
        ModelStubAcceptingCardFlipped modelStub = new ModelStubAcceptingCardFlipped();
        CommandResult commandResult = new FlipCommand().execute(modelStub);

        assertEquals(String.format(FlipCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_notInPlayMode_throwsCommandException() {
        ModelStubAcceptingCardFlipped modelStub = new ModelStubAcceptingCardFlipped();
        FlipCommand flipCommand = new FlipCommand();

        assertThrows(CommandException.class, FlipCommand.MESSAGE_NOT_PLAY_MODE,
                () -> flipCommand.execute(modelStub));
    }

    @Test
    public void execute_alreadyFlipped_throwsCommandException() {
        ModelStubCardAlreadyFlipped modelStub = new ModelStubCardAlreadyFlipped();
        FlipCommand flipCommand = new FlipCommand();

        assertThrows(CommandException.class, FlipCommand.MESSAGE_ALREADY_FLIPPED,
                () -> flipCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        FlipCommand flipCommand = new FlipCommand();

        // same object -> returns true
        assertTrue(flipCommand.equals(flipCommand));

        // same values -> returns true
        FlipCommand flipCommandCopy = new FlipCommand();
        assertTrue(flipCommand.equals(flipCommandCopy));

        // different types -> returns false
        assertFalse(flipCommand.equals(1));

        // null -> returns false
        assertFalse(flipCommand.equals(null));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getLibraryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLibraryFilePath(Path libraryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLibrary(ReadOnlyLibrary newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyLibrary getLibrary() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void returnToLibrary() {
            throw new AssertionError("This method should not be called");
        }

        //=========== Deck Functions =============================================================
        @Override
        public boolean hasDeck(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeck(Deck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeck(Deck target, Deck editedDeck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void createDeck(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void selectDeck(Index targetIdx) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Deck getCurrentDeck() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Deck getDeck(Index index) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean renameDeck(Index targetIndex, Name name) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Deck> selectedDeckProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Mode> currentModeProperty() {
            throw new AssertionError("This method should not be called");
        }

        //=========== Card Functions  =============================================================
        @Override
        public boolean hasCard(Card card) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteCard(Card target) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addCard(Card card) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void replaceCard(Card target, Card card) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Card> playingCardProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Boolean> flippedProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Integer> cardAttemptedProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Integer> cardRemainingProperty() {
            throw new AssertionError("This method should not be called");
        }


        @Override
        public View getView() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Mode getMode() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setCurrentMode(Mode mode) {
            throw new AssertionError("This method should not be called");
        }

        //=========== Filtered Deck List Accessors =============================================================

        @Override
        public ObservableList<Deck> getFilteredDeckList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Card> getFilteredCardList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredDeckList(Predicate<Deck> predicate) {
            throw new AssertionError("This method should not be called");
        }

        //=========== Game Mode Functions =============================================================
        @Override
        public void setSelectedDeck(Deck deck) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setFlipped(Boolean value) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setCardAttempted(int value) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setCardRemaining(int value) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setPlayingCard(Card card) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Card getCard(Index index) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Card play(Index index) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public BackFace flip() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Card answerYes() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Statistics stop() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public GameManager getGame() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Card answerNo() {
            throw new AssertionError("This method should not be called");
        }

    }

    /**
     * A Model stub that always accepts a card being flipped.
     */
    private class ModelStubAcceptingCardFlipped extends ModelStub {

        @Override
        public Mode getMode() {
            return Mode.PLAY;
        }

        @Override
        public BackFace flip() {
            return new BackFace("testString");
        }
    }

    /**
     * A Model stub that cannot flip due to not in play mode.
     */
    private class ModelStubNotPlayMode extends ModelStub {

        @Override
        public Mode getMode() {
            return Mode.VIEW;
        }

        @Override
        public BackFace flip() {
            return new BackFace("testString");
        }
    }

    /**
     * A Model stub that cannot be flipped since card is already flipped.
     */
    private class ModelStubCardAlreadyFlipped extends ModelStub {

        @Override
        public Mode getMode() {
            return Mode.PLAY;
        }

        @Override
        public BackFace flip() {
            return null;
        }
    }
}

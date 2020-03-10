package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.dump.CreateDeckCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.deck.Deck;
import seedu.address.testutil.PersonBuilder;

public class CreateDeckCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateDeckCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Deck validDeck = new PersonBuilder().build();

        CommandResult commandResult = new CreateDeckCommand(validDeck).execute(modelStub);

        assertEquals(String.format(CreateDeckCommand.MESSAGE_SUCCESS, validDeck), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDeck), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Deck validDeck = new PersonBuilder().build();
        CreateDeckCommand createDeckCommand = new CreateDeckCommand(validDeck);
        ModelStub modelStub = new ModelStubWithPerson(validDeck);

        assertThrows(CommandException.class, CreateDeckCommand.MESSAGE_DUPLICATE_PERSON, () -> createDeckCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Deck alice = new PersonBuilder().withName("Alice").build();
        Deck bob = new PersonBuilder().withName("Bob").build();
        CreateDeckCommand addAliceCommand = new CreateDeckCommand(alice);
        CreateDeckCommand addBobCommand = new CreateDeckCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        CreateDeckCommand addAliceCommandCopy = new CreateDeckCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Deck deck) {
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
        public boolean hasPerson(Deck deck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Deck target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Deck target, Deck editedDeck) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Deck> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Deck> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Deck deck;

        ModelStubWithPerson(Deck deck) {
            requireNonNull(deck);
            this.deck = deck;
        }

        @Override
        public boolean hasPerson(Deck deck) {
            requireNonNull(deck);
            return this.deck.isSameDeck(deck);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Deck> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Deck deck) {
            requireNonNull(deck);
            return personsAdded.stream().anyMatch(deck::isSameDeck);
        }

        @Override
        public void addPerson(Deck deck) {
            requireNonNull(deck);
            personsAdded.add(deck);
        }

        @Override
        public ReadOnlyLibrary getLibrary() {
            return new Library();
        }
    }

}

package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.DeckUtils.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.dump.CreateDeckCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.Deck;
import seedu.address.testutil.DeckBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class CreateDeckCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Deck validDeck = new DeckBuilder().build();

        Model expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        expectedModel.addPerson(validDeck);

        assertCommandSuccess(new CreateDeckCommand(validDeck), model,
                String.format(CreateDeckCommand.MESSAGE_SUCCESS, validDeck), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Deck deckInList = model.getLibrary().getPersonList().get(0);
        assertCommandFailure(new CreateDeckCommand(deckInList), model, CreateDeckCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

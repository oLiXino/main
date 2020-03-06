package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.Deck;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Deck validDeck = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validDeck);

        assertCommandSuccess(new AddCommand(validDeck), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validDeck), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Deck deckInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(deckInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

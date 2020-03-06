package seedu.address.model.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.deck.exceptions.DuplicatePersonException;
import seedu.address.model.deck.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueDeckListTest {

    private final UniqueDeckList uniqueDeckList = new UniqueDeckList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueDeckList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueDeckList.add(ALICE);
        assertTrue(uniqueDeckList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueDeckList.add(ALICE);
        Deck editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueDeckList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueDeckList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDeckList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDeckList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueDeckList.add(ALICE);
        uniqueDeckList.setPerson(ALICE, ALICE);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(ALICE);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueDeckList.add(ALICE);
        Deck editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueDeckList.setPerson(ALICE, editedAlice);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(editedAlice);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueDeckList.add(ALICE);
        uniqueDeckList.setPerson(ALICE, BOB);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(BOB);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueDeckList.add(ALICE);
        uniqueDeckList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> uniqueDeckList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueDeckList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueDeckList.add(ALICE);
        uniqueDeckList.remove(ALICE);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setPersons((UniqueDeckList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueDeckList.add(ALICE);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(BOB);
        uniqueDeckList.setPersons(expectedUniqueDeckList);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setPersons((List<Deck>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueDeckList.add(ALICE);
        List<Deck> deckList = Collections.singletonList(BOB);
        uniqueDeckList.setPersons(deckList);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(BOB);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Deck> listWithDuplicateDecks = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniqueDeckList.setPersons(listWithDuplicateDecks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueDeckList.asUnmodifiableObservableList().remove(0));
    }
}

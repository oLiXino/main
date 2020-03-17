package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.UniqueDeckList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Library implements ReadOnlyLibrary {


    private final UniqueDeckList decks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        decks = new UniqueDeckList();
    }

    public Library() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public Library(ReadOnlyLibrary toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code decks}.
     * {@code decks} must not contain duplicate decks.
     */
    //public void setDecks(List<Deck> decks) {
    //this.decks.setPersons(decks);
    //}
    public void setDecks(List<Deck> decks) {
        this.decks.setDecks(decks);
    }


    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
//    public void resetData(ReadOnlyLibrary newData) {
//        requireNonNull(newData);
//
//        setDecks(newData.getPersonList());
//    }
    public void resetData(ReadOnlyLibrary newData) {
        requireNonNull(newData);

        setDecks(newData.getDeckList());
    }

    //// person-level operations

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in the Library.
     */
    public boolean hasPerson(Deck deck) {   // remove the code at the enc
        requireNonNull(deck);
        return decks.contains(deck);
    }

    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    public Deck getDeck(int index) {
        return decks.get(index);
    }

    public Deck getDeck(Index index) {
        int idxInInt = index.getZeroBased();
        return decks.get(idxInInt);

    }

    /**
     * Adds a deck to the library.
     * The deck must not already exist in the library.
     */
    public void addPerson(Deck p) {
        decks.add(p);
    }   // remove the code at the end

    public void createDeck(Deck p) {
        decks.add(p);
    }

    /**
     * Replaces the given deck {@code target} in the list with {@code editedDeck}.
     * {@code target} must exist in the library.
     * The deck identity of {@code editedDeck} must not be the same as another existing deck in the library.
     */
    public void setPerson(Deck target, Deck editedDeck) { // remove the code at the end
        requireNonNull(editedDeck);

        decks.setPerson(target, editedDeck);
    }


    /**
     * Removes {@code key} from this {@code Library}.
     * {@code key} must exist in the library .
     */

    public void deleteDeck(Deck key) {
        decks.remove(key);
    }



    @Override
    public String toString() {
        return decks.asUnmodifiableObservableList().size() + " decks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Deck> getPersonList() {   // remove the code at the end
        return decks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Deck> getDeckList() {
        return decks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Library // instanceof handles nulls
                && decks.equals(((Library) other).decks));
    }

    @Override
    public int hashCode() {
        return decks.hashCode();
    }
}

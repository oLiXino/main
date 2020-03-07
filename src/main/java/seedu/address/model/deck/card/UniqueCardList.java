package seedu.address.model.deck.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.deck.exceptions.DuplicatePersonException;
import seedu.address.model.deck.exceptions.PersonNotFoundException;

/**
 * A list of cards that enforces uniqueness between its elements and does not allow nulls.
 * A card is considered unique by comparing using {@code Card#equals(Object)}. As such, adding and updating of
 * cards uses Card#equals(Object) for equality so as to ensure that the person being added or updated is
 * unique in terms of content in the UniqueCardList. The removal of a card also uses Card#equals(Object)
 *
 * Supports a minimal set of list operations.
 *
 * @see Card#equals(Object)
 */
public class UniqueCardList implements Iterable<Card> {

    private final ObservableList<Card> internalList = FXCollections.observableArrayList();
    private final ObservableList<Card> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent card as the given argument.
     */
    public boolean contains(Card toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Card toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedCard} must not be the same as another existing person in the list.
     */
    public void setPerson(Card target, Card editedCard) {
        requireAllNonNull(target, editedCard);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.equals(editedCard) && contains(editedCard)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedCard);
    }

    /**
     * Removes the equivalent card from the list.
     * The card must exist in the list.
     */
    public void remove(Card toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniqueCardList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Card> cards) {
        requireAllNonNull(cards);
        if (!personsAreUnique(cards)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(cards);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Card> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Card> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCardList // instanceof handles nulls
                && internalList.equals(((UniqueCardList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Card> decks) {
        for (int i = 0; i < decks.size() - 1; i++) {
            for (int j = i + 1; j < decks.size(); j++) {
                if (decks.get(i).equals(decks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
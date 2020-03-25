package seedu.address.model.deck.card;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.deck.exceptions.CardNotFoundException;
import seedu.address.model.deck.exceptions.DuplicateCardException;

/**
 * A list of cards that enforces uniqueness between its elements and does not allow nulls.
 * A card is considered unique by comparing using {@code Card#equals(Object)}. As such, adding and updating of
 * cards uses Card#equals(Object) for equality so as to ensure that the card being added or updated is
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
    
    public int getSize() {
        return internalList.size();
    }

    /**
     * Adds a card to the list.
     * The card must not already exist in the list.
     */
    public void add(Card toAdd) throws DuplicateCardException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCardException();
        }
        internalList.add(toAdd);
    }

    public Card getCard(Index index) {
        return internalList.get(index.getZeroBased());
    }

    /**
     * Replaces the card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the list.
     * The card identity of {@code editedCard} must not be the same as another existing card in the list.
     */
    public void setCard(Card target, Card editedCard) {
        requireAllNonNull(target, editedCard);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CardNotFoundException();
        }

        if (!target.equals(editedCard) && contains(editedCard)) {
            throw new DuplicateCardException();
        }

        internalList.set(index, editedCard);
    }

    /**
     * Removes the equivalent card from the list.
     * The card must exist in the list.
     */
    public void remove(Card toRemove) throws DuplicateCardException {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CardNotFoundException();
        }
    }

    /**
     * Replaces the equivalent card from the list with the new card.
     * The old card must exist in the list and the new card must not already exist in the list.
     */
    public void replace(Card toRemove, Card toAdd) throws CardNotFoundException, DuplicateCardException {
        requireAllNonNull(toRemove, toAdd);

        int idx = internalList.indexOf(toRemove);
        if (idx == -1) {
            throw new CardNotFoundException();
        }
        
        // leave the unedited face value intact
//        if (toAdd.getBackFace().getValue().isBlank()) {  // only change the front
//            FrontFace newFrontFace = toAdd.getFrontFace();
//            BackFace oldFrontFace = toRemove.getBackFace();
//            toAdd = new Card(newFrontFace, oldFrontFace);
////            internalList.set(idx, new Card(newFrontFace, oldFrontFace));
//        } else if (toAdd.getFrontFace().getValue().isBlank()) {  // only change the back
//            FrontFace oldFrontFace = toRemove.getFrontFace();
//            BackFace newFrontFace = toAdd.getBackFace();
//            toAdd = new Card(oldFrontFace, newFrontFace);
////            internalList.set(idx, new Card(oldFrontFace, newFrontFace));
//        }
//        else {
//            internalList.set(idx, toAdd);
//        }
        
        if (contains(toAdd)) {
            throw new DuplicateCardException();
        }

        internalList.set(idx, toAdd);
    }

    public void setCards(UniqueCardList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Card> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public ObservableList<Card> asObservableList() {
        return FXCollections.observableArrayList(internalList);
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
     * Returns true if {@code cards} contains only unique cards.
     */
    private boolean cardsAreUnique(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).equals(cards.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

package seedu.address.model.deck.card;

import seedu.address.model.deck.dump.Address;
import seedu.address.model.deck.dump.Email;
import seedu.address.model.deck.dump.Name;
import seedu.address.model.deck.dump.Phone;
import seedu.address.model.deck.dump.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Card {

    //New fields for FlashSpeed
    private final Face frontFace = new Face("Dummy Front");
    private final Face backFace = new Face("Dummy Back");

    public boolean isSamePerson(Card otherDeck) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashCode() {
        return 1;
    };

    @Override
    public String toString() {
        return "";
    };

}

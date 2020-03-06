package seedu.address.testutil;

import seedu.address.model.Library;
import seedu.address.model.deck.Deck;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Library library;

    public AddressBookBuilder() {
        library = new Library();
    }

    public AddressBookBuilder(Library library) {
        this.library = library;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Deck deck) {
        library.addPerson(deck);
        return this;
    }

    public Library build() {
        return library;
    }
}

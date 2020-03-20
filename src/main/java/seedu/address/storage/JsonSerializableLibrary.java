package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Library;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.deck.Deck;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "decks")
class JsonSerializableLibrary {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedDeck> decks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given decks.
     */
    @JsonCreator
    public JsonSerializableLibrary(@JsonProperty("decks") List<JsonAdaptedDeck> decks) {
        this.decks.addAll(decks);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableLibrary(ReadOnlyLibrary source) {
        decks.addAll(source.getDeckList().stream().map(JsonAdaptedDeck::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Library toModelType() throws IllegalValueException {
        Library library = new Library();
        for (JsonAdaptedDeck jsonAdaptedPerson : decks) {
            Deck deck = jsonAdaptedPerson.toModelType();
            if (library.hasPerson(deck)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            library.addPerson(deck);
        }
        return library;
    }

}

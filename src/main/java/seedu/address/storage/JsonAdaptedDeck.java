package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;

/**
 * Jackson-friendly version of {@link Deck}.
 */
class JsonAdaptedDeck {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deck's %s field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedDeck} with the given deck details.
     */
    @JsonCreator
    public JsonAdaptedDeck(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
    }

    /**
     * Converts a given {@code Deck} into this class for Jackson use.
     */
    public JsonAdaptedDeck(Deck source) {
        name = source.getName().name;
    }

    /**
     * Converts this Jackson-friendly adapted deck object into the model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted deck.
     */
    public Deck toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        
        return new Deck(modelName);
    }
}

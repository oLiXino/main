package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Library;
import seedu.address.testutil.DeckUtils;

public class JsonSerializableLibraryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLibraryTest");
    private static final Path TYPICAL_DECKS_FILE = TEST_DATA_FOLDER.resolve("typicalDecksLibrary.json");
    private static final Path INVALID_DECK_FILE = TEST_DATA_FOLDER.resolve("invalidDeckLibrary.json");
    private static final Path DUPLICATE_DECKS_FILE = TEST_DATA_FOLDER.resolve("duplicateDeckLibrary.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(TYPICAL_DECKS_FILE,
                JsonSerializableLibrary.class).get();
        Library libraryFromFile = dataFromFile.toModelType();
        Library typicalPersonsLibrary = DeckUtils.getTypicalLibrary();
        assertEquals(libraryFromFile, typicalPersonsLibrary);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(INVALID_DECK_FILE,
                JsonSerializableLibrary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DECKS_FILE,
                JsonSerializableLibrary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLibrary.MESSAGE_DUPLICATE_DECK,
                dataFromFile::toModelType);
    }

}

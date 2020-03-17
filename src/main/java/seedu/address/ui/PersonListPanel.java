package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deck.Deck;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Deck> personListView;

    public PersonListPanel(ObservableList<Deck> deckList, ObservableValue<Deck> selectedPerson,
                           Consumer<Deck> onSelectedPersonChange) {
        super(FXML);
        personListView.setItems(deckList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        //personListView.getSelectionModel().clearAndSelect(2);

        personListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in person list panel changed to : '" + newValue + "'");
            onSelectedPersonChange.accept(newValue);
        });

        selectedPerson.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected person changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected person,
            // otherwise we would have an infinite loop.
            if (Objects.equals(personListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                personListView.getSelectionModel().clearSelection();
            } else {
                int index = personListView.getItems().indexOf(newValue);
                personListView.scrollTo(index);
                personListView.getSelectionModel().clearAndSelect(index);
            }
        });


    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Deck> {
        @Override
        protected void updateItem(Deck deck, boolean empty) {
            super.updateItem(deck, empty);

            if (empty || deck == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(deck, getIndex() + 1).getRoot());
            }
        }
    }

}

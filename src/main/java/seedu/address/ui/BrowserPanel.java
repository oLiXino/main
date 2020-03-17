package seedu.address.ui;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.card.Card;


import java.net.URL;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;


/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    TableView itemTbl;

    @FXML
    TableColumn id;
    @FXML
    TableColumn front;
    @FXML
    TableColumn back;

    public BrowserPanel(ObservableValue<Deck> selectedPerson) {
        super(FXML);


        TableColumn<Deck, Number> indexColumn = new TableColumn<Deck, Number>("ID");
       

        indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(itemTbl.getItems().indexOf(column.getValue())+1));

        TableColumn frontColumn = new TableColumn("Front");
        frontColumn.setCellValueFactory(new PropertyValueFactory<>("frontFace"));

        TableColumn backColumn = new TableColumn("Back");
        backColumn.setCellValueFactory(new PropertyValueFactory<>("backFace"));

        itemTbl.getColumns().addAll(indexColumn, frontColumn, backColumn);

        indexColumn.prefWidthProperty().bind(itemTbl.widthProperty().multiply(0.2));
        frontColumn.prefWidthProperty().bind(itemTbl.widthProperty().multiply(0.4));
        backColumn.prefWidthProperty().bind(itemTbl.widthProperty().multiply(0.4));

        indexColumn.setSortable(false);
        frontColumn.setSortable(false);
        backColumn.setSortable(false);
        
        indexColumn.setResizable(false);
        frontColumn.setResizable(false);
        backColumn.setResizable(false);

        // Load person page when selected person changes.
        selectedPerson.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {

                return;
            }
            getCardList(newValue);


        });
    }

    private void getCardList(Deck deck) {
        itemTbl.getItems().clear();
        ObservableList<Card> cardList = deck.asUnmodifiableObservableList();
        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            itemTbl.getItems().add(card);

        }
    }

    private void printCard(Card card) {
        //loadPage(SEARCH_PAGE_URL + person.getName().fullName);

        itemTbl.getItems().add(card);


    }





}

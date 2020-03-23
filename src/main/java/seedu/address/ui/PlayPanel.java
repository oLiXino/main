package seedu.address.ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.card.Card;

import java.util.logging.Logger;

/**
 * The Browser Panel of the App.
 */
public class PlayPanel extends UiPart<Region> {

    private static final String FXML = "PlayPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    Label front;

    @FXML
    Label back;

    @FXML
    StackPane pane;

    public PlayPanel(ObservableValue<Card> playingCard, ObservableValue<Boolean> flipped) {
        super(FXML);
        
        back.setVisible(false);
        
        //Load playing card
        playingCard.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showPlayingCard(newValue);
            }            
        });

        //Show back face when flipped
        flipped.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                back.setVisible(true);
            } else {
                back.setVisible(false);
            }
        });
    }

    public void showPlayingCard(Card card) {
        front.setText(card.getFrontFace().toString());
        front.setWrapText(true);
        back.setText(card.getBackFace().toString());
        back.setWrapText(true);
    }
}

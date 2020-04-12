package com.flashspeed.ui;

import java.util.logging.Logger;

import com.flashspeed.commons.core.LogsCenter;
import com.flashspeed.model.deck.card.Card;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * The Browser Panel of the App.
 */
public class PlayPanel extends UiPart<Region> {

    private static final String FXML = "PlayPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private int attempted;

    private int remaining;

    @FXML
    private Label front;

    @FXML
    private Label back;

    @FXML
    private Label noAttempted;

    @FXML
    private Label noRemaining;

    @FXML
    private ProgressBar progress;

    @FXML
    private Label progressPercent;

    @FXML
    private VBox progressRoot;

    @FXML
    private Label instruction;

    public PlayPanel(ObservableValue<Card> playingCard, ObservableValue<Boolean> flipped,
                     ObservableValue<Integer> cardAttempted, ObservableValue<Integer> cardRemaining) {
        super(FXML);

        back.setVisible(false);
        progress.setProgress(0);
        progressPercent.setText("0.0%");
        progress.prefWidthProperty().bind(progressRoot.widthProperty().subtract(40));

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
                instruction.setText("Did you get your answer right? Answer 'yes' or 'no'");
            } else {
                back.setVisible(false);
                instruction.setText("Type 'flip' to reveal back face");
            }
        });

        cardAttempted.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                attempted = newValue;
                setProgress();
                noAttempted.setText(String.valueOf(newValue));
            }
        });

        cardRemaining.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                remaining = newValue;
                setProgress();
                noRemaining.setText(String.valueOf(newValue));
            }
        });
    }

    /**
     * Shows current playing card in right panel.
     *
     * @param card card to be shown
     */
    private void showPlayingCard(Card card) {
        front.setText(card.getFrontFace().toString());
        front.setWrapText(true);
        back.setText(card.getBackFace().toString());
        back.setWrapText(true);
        instruction.setText("Type 'flip' to reveal back face");
    }

    private void setProgress() {
        double currentProgress = Double.valueOf(attempted) / (attempted + remaining);
        double prog = currentProgress * 100;
        progressPercent.setText(String.format("%.1f", prog) + "%");
        progress.setProgress(currentProgress);
    }
}

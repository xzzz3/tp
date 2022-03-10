package seedu.address.ui;

import javafx.scene.layout.Region;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.driver.Driver;

public class DriverCard extends UiPart<Region> {
    private static final String FXML = "DriverListCard.fxml";

    public final Driver driver;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label status;

    public DriverCard(Driver driver, int displayedIndex) {
        super(FXML);
        this.driver = driver;
        id.setText(displayedIndex + ". ");
        name.setText(driver.getName().fullName);
        phone.setText(driver.getPhone().value);
        status.setText(driver.getStatus());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DriverCard)) {
            return false;
        }

        // state check
        DriverCard card = (DriverCard) other;
        return id.getText().equals(card.id.getText())
                && driver.equals(card.driver);
    }
}

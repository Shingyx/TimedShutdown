import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Controller for the interface.
 */
public class MainController {

    @FXML
    private TextField timeField;
    @FXML
    private Label label;

    /**
     * Attempt to perform a timed shutdown based on input in timeField.
     */
    @FXML
    private void shutdown() {
        String input = timeField.getText();
        int seconds = parseSeconds(input);
        if (seconds < 0) {
            label.setTextFill(Color.RED);
            label.setText("Time cannot be empty");
        } else {
            try {
                String command = "shutdown -s -t " + seconds;
                Runtime.getRuntime().exec(command);
                label.setTextFill(Color.valueOf("0x333333ff"));
                label.setText(String.format("Shutting down in %s. You may close this window.", input));
            } catch (Exception e) {
                label.setTextFill(Color.RED);
                label.setText("Shutdown failed. Perhaps this application lacks permissions?");
            }
        }
    }

    /**
     * Cancel an existing timed shutdown.
     */
    @FXML
    private void cancelShutdown() {
        try {
            String command = "shutdown -a";
            Runtime.getRuntime().exec(command);
            label.setTextFill(Color.valueOf("0x333333ff"));
            label.setText("Shutdown has been cancelled");
        } catch (Exception e) {
            label.setTextFill(Color.RED);
            label.setText("Failed to cancel. Perhaps this application lacks permissions?");
        }
    }

    /**
     * Get the number of seconds from a string that follows the XhYm time format. Ensures
     * that string follows the specified format and converts any hours and minutes into seconds.
     *
     * @param inputString The time formatted string to parse.
     * @return The time in seconds, -1 if input is empty or invalid.
     */
    private int parseSeconds(String inputString) {
        String timeRegex = "([0-9]+h)?([0-9]+m)?";
        inputString = inputString.trim();
        int hours = 0;
        int minutes = 0;
        int result = -1;
        if (!inputString.isEmpty() && inputString.matches(timeRegex)) {
            char[] charArray = inputString.toCharArray();
            String accumulator = "";
            for (char c : charArray) {
                if (c == 'h') {
                    hours = Integer.parseInt(accumulator);
                    accumulator = "";
                } else if (c == 'm') {
                    minutes = Integer.parseInt(accumulator);
                } else {
                    accumulator += c;
                }
            }
            result = 60 * (minutes + (60 * hours));
        }
        return result;
    }
}

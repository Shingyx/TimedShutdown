import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main entry point into the application.
 */
public class Main extends Application {

    /**
     * Start up the application interface.
     *
     * @param primaryStage Primary stage of the interface.
     * @throws Exception Failed to load resources or open interface.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Timed Shutdown");
        primaryStage.setScene(new Scene(root, 360, 170));
        primaryStage.show();
    }

    /**
     * Launch the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

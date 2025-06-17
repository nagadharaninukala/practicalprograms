import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class ButtonExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a button
        Button button = new Button("Click Me");
        // Set an action when the button is clicked
        button.setOnAction(e -> System.out.println("Button was clicked!"));
        // Create a layout pane and add the button
        StackPane root = new StackPane();
        root.getChildren().add(button);
        // Create a scene with the layout pane
        Scene scene = new Scene(root, 300, 200);
        // Set the stage (window) title and scene, then show it
        primaryStage.setTitle("JavaFX Button Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // The main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
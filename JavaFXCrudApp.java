import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class JavaFXCrudApp extends Application {

    private final ObservableList<String> names = FXCollections.observableArrayList();
    private final ListView<String> listView = new ListView<>(names);
    private final TextField inputField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        inputField.setPromptText("Enter name");

        Button insertBtn = new Button("Insert");
        Button updateBtn = new Button("Update");
        Button deleteBtn = new Button("Delete");

        insertBtn.setOnAction(e -> insertName());
        updateBtn.setOnAction(e -> updateName());
        deleteBtn.setOnAction(e -> deleteName());

        HBox buttons = new HBox(10, insertBtn, updateBtn, deleteBtn);
        VBox layout = new VBox(10, inputField, listView, buttons);
        layout.setStyle("-fx-padding: 10;");

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setTitle("JavaFX CRUD (Insert, Delete, Update)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void insertName() {
        String name = inputField.getText().trim();
        if (!name.isEmpty()) {
            names.add(name);
            inputField.clear();
        }
    }

    private void updateName() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            String newName = inputField.getText().trim();
            if (!newName.isEmpty()) {
                names.set(selectedIndex, newName);
                inputField.clear();
            }
        }
    }

    private void deleteName() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            names.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

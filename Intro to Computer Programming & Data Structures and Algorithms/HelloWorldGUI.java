import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class HelloWorldGUI extends Application {
  public void start(Stage stage) {
    // Create a new Label with the text "Hello World"
    Label label = new Label("Hello World");
    
    // Create a new Button with the text "Close"
    Button button = new Button("Close");
    
    // Set the button's action to close the window
    button.setOnAction(e -> stage.close());
    
    // Add the label and button to a new VBox
    VBox root = new VBox(label, button);
    
    // Set the VBox as the root of a new Scene
    Scene scene = new Scene(root);
    
    // Set the Scene on the Stage and show it
    stage.setScene(scene);
    stage.show();
  }
  
  public static void main(String[] args) {
    // Launch the GUI
    launch(args);
  }
}

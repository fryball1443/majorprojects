import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.swing.JOptionPane;

public class GridPaneDemo extends Application {
   public static void main(String[] args) {
      launch(args);//launch the application
   }
   
   @Override
   public void start (Stage primaryStage) {
      Button btnHello    = new Button("Hello");
      Button btnOla      = new Button("Ola");
      Button btnWelcome  = new Button("Welcome");
      Button btnThankYou = new Button("Thank You");
      
      GridPane gridpane  = new GridPane();
      gridpane.add(btnHello,0,0);
      gridpane.add(btnOla,1,0);
      gridpane.add(btnWelcome,0,1);
      gridpane.add(btnThankYou,1,1);
      
      Scene scene=new Scene (gridpane, 300,100);//add container to the scene hbox. setAlignment (Pos .CENTER);
      primaryStage.setScene(scene);//add scene to stage
      primaryStage.setTitle("BUTTONSSSSS");
      primaryStage.show();
   }
   
   class ButtonClickHandler implements EventHandler<actionevent> {
      @Override
      public void handle(ActionEvent event) {
         JOptionPane.showMessageDialog(null, "heloooooooooo");
      }
   }
}
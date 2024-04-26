import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javax.swing.JOptionPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class TextFeildDemo extends Application//inheriting app class to create GUI
{
   private TextField numberSquare;
   private Label lblEnter;
   public static void main(String[] args)
   {
       launch(args);//launch the application
   }
   @Override
   public void start(Stage primaryStage)
   {
     lblEnter=new Label("Enter a number ");
     numberSquare=new TextField();
     Button getSquare=new Button("FindSquare");
     getSquare.setOnAction(event ->
     {
       int n=Integer.parseInt(numberSquare.getText());
         JOptionPane.showMessageDialog
         (null,"Square of "+n+" is "+(n*n));  
     });
     
     HBox hbox=new HBox(30,lblEnter,numberSquare,getSquare);
     hbox.setPadding(new Insets(20));
     hbox.setAlignment(Pos.CENTER);
     Scene scene=new Scene(hbox);
     scene.getStylesheets().add("labelStyle.css");
     
     primaryStage.setScene(scene);
     primaryStage.setTitle("Finding a square");
     primaryStage.show();
     
   }
}
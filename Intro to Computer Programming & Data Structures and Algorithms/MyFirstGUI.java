import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.geometry.


public class MyFirstGUI extends Application { //inheriting app class to create
   public static void main(String[] args) {
      launch(args);//launch the application
   }
   @Override
   public void start (Stage primaryStage) {
      Label lblMessage=new Label("Hello World");//control a leaf node 
      
      /*/
      String imagePath = "~/Downloads/Unknown.png";
      imagePath        = "file:" + System.getProperty("user.home") + imagePath.substring(1);
      Image image = new Image(imagePath);
      //*/
      Image image = new Image("file:/Users/fryball/Downloads/Unknown.png");

      ImageView imageView = new ImageView(image);
      HBox hbox= new HBox (lblMessage, imageView); //container a root/branch node
      
      
      //Scene scene=new Scene (hbox);//add container to the scene 
      //primaryStage.setScene (scene);//add scene to stage
      
      //HBox hbox=new HBox (lblMessage, imageView); //container a root/branch node
      
      
      Scene scene=new Scene (hbox, 300,300);//add container to the scene hbox. setAlignment (Pos .CENTER);
      primaryStage.setScene(scene);//add scene to stage
      primaryStage.setTitle("My first GUI app");//setting stage title primaryStage. show();/ /Show the window
      primaryStage.show();
   }
}
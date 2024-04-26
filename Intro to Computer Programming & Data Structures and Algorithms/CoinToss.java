import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class CoinToss extends Application {
//declaring UI components
private ImageView imgView;
private Image headsImage, tailsImage;
private Button btn;

@Override

public void start(Stage primaryStage) {
//initializing ImageView,setting height and width
imgView = new ImageView();
imgView.setFitHeight(300);
imgView.setFitWidth(300);

//loading heads2png and tails2png and tails2png.make sure you have those files
//in root directory of your project or else,the images wont be displayed
headsImage= new Image("file:heads2.png");
tailsImage = new Image("file:tails2.png");

//initilizing the button,adding event listener using lambda expression
btn=new Button("toss");
btn.setOnAction(e->{
  //generating a number between 0 and 1
   int num=(int) (Math.random()*2);
   
  //if number is 0, setting heads image, else tails image
   if(num==0){
      //imgView.setImage(headsImage);
   }else{
      //imgView.setImage(tailsImage);
   }
});

//adding the image view and button to a VBox to arrange verticity
VBox root=new VBox(imgView,btn);

//aligning vbox to center and setting some spacing between components
root.setAlignment(Pos.CENTER);

//creating and displaying the scene
Scene scene = new Scene(root);
primaryStage.setScene(scene);
primaryStage.setTitle("Coin Toss");
primaryStage.show();
}

public static void main(String[] args) {
   launch(args);
}
}
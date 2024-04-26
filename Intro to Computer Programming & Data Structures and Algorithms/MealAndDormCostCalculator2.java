//*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class MealAndDormCostCalculator2 extends Application {

double totalCost =0;
RadioButton chkAllenHall,chkPikeHall,chkFarthingHall,chkUniversitySuites;
RadioButton chk7Meals,chk14Meals,chkUnlimitedMeals;
CheckBox chkBowling, chkMovie;
Label lblResult,lblDormitories,lblMeals, lblOthers;
ToggleGroup group1;
ToggleGroup group2;

@Override
public void start(Stage primaryStage) throws Exception {
lblDormitories = new Label("Dormitories ");
chkAllenHall = new RadioButton("DormA: $1000 per semester");
chkPikeHall = new RadioButton("DormB: $1200 per semester");
chkFarthingHall = new RadioButton("DormC: $1500 per semester");
chkUniversitySuites = new RadioButton("University Suites: $3,000 per semester");
lblMeals = new Label("Meals");
chk7Meals = new RadioButton("7 meals per week: $600 per semester");
chk14Meals= new RadioButton("14 meals per week: $1,200 per semester");
chkUnlimitedMeals = new RadioButton("Unlimited meals: $1,500 per semester");
lblOthers = new Label("Others");
chkBowling = new CheckBox("Bowling night: $50 per semester");
chkMovie= new CheckBox("Movie night: $40 per semester");

group1 = new ToggleGroup();
chkAllenHall.setToggleGroup(group1);
chkPikeHall.setToggleGroup(group1);
chkFarthingHall.setToggleGroup(group1);
chkUniversitySuites.setToggleGroup(group1);

group2 = new ToggleGroup();
chk7Meals.setToggleGroup(group2);
chk14Meals.setToggleGroup(group2);
cUnlimitedMeals.setToggleGroup(group2);



//================================
lblResult = new Label("Total Cost: "+String.format("$%.2f",totalCost));

VBox box1 = new VBox();
box1.setSpacing(10);
box1.setPadding(new Insets(5,5,5,5));
box1.getChildren().addAll(lblDormitories,chkAllenHall,chkPikeHall,chkFarthingHall,chkUniversitySuites);

//========================
VBox box2 = new VBox();
box2.setSpacing(10);
box2.setPadding(new Insets(5,5,5,5));
box2.getChildren().addAll(lblMeals,chk7Meals,chk14Meals,chkUnlimitedMeals);
  

VBox box22 = new VBox();
box2.setSpacing(10);
box2.setPadding(new Insets(5,5,5,5));
box2.getChildren().addAll(lblOthers,chkBowling,chkMovie);

//===========================
HBox box3 = new HBox();
box3.setSpacing(10);
box3.setAlignment(Pos.CENTER);
box3.setPadding(new Insets(5,5,5,5));
box3.getChildren().add(lblResult);
  
//===========================
HBox box4 = new HBox();
box4.setPadding(new Insets(5,5,5,5));
box4.setSpacing(10);
box4.getChildren().addAll(box1,box2,box22);

//===============================
VBox finalBox = new VBox();
finalBox.setSpacing(10);
finalBox.setPadding(new Insets(5,5,5,5));
finalBox.getChildren().addAll(box4,box3);
//=================================
primaryStage.setTitle("Meal and Dorm Cost Calculator");
Scene scene = new Scene(finalBox,550,200);
primaryStage.setScene(scene);
primaryStage.show();

/*/add listener
chkUniversitySuites.setOnAction(handler);
chkFarthingHall.setOnAction(handler);
chkPikeHall.setOnAction(handler);
chkAllenHall.setOnAction(handler);
chk7Meals.setOnAction(handler);
chk14Meals.setOnAction(handler);
chkUnlimitedMeals.setOnAction(handler);
chkMovie.setOnAction(handler);
chkBowling.setOnAction(handler);
}
*/
chkUniversitySuites.setUserData(3000);
chkFarthingHall.setUserData(2500);
chkPikeHall.setUserData(200);
chkAllenHall.setUserData(1500);
chk7Meals.setUserData(600);
chk14Meals.setUserData(1200);
chkUnlimitedMeals.setUserData(1500);
chkMovie.setUserData(40);
chkBowling.setUserData(50);

lblResult += (Double) group1.getSelectedToggle().getUserData();
lblResult += (Double) group2.getSelectedToggle().getUserData();


calculate = new Button("Calculate Cost");
calculate.setOnAction(e -> update());
/*
final EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
   @Override
   public void handle(ActionEvent event) {
      int tC = 0;
      if(event.getSource().equals(chkAllenHall)) {
         if(chkAllenHall.isSelected())
            tC=1500;
         else
            tC=1500;
      }
      if(event.getSource().equals(chkPikeHall)) {
         if(chkPikeHall.isSelected())
            tC=200;
      }
      if(event.getSource().equals(chkFarthingHall)) {
         if(chkFarthingHall.isSelected())
            tC=2500;
         }
      if(event.getSource().equals(chkUniversitySuites)) {
         if(chkUniversitySuites.isSelected())
            tC=3000;
      }
      //totalCost += tC;
      
      if(event.getSource().equals(chk7Meals)) {
         if(chk7Meals.isSelected())
         totalCost+=600;
         else
         totalCost-=600;
      }
      if(event.getSource().equals(chk14Meals)) {
         if(chk14Meals.isSelected())
         totalCost+=1200;
         else
         totalCost-=1200;
      }
      if(event.getSource().equals(chkUnlimitedMeals)) {
         if(chkUnlimitedMeals.isSelected())
         totalCost+=1500;
         else
         totalCost-=1500;
      }
      
      

      //*
      if(event.getSource().equals(chkBowling)) {
         if(chkBowling.isSelected())
         totalCost+=50;
         else
         totalCost-=40;
      }
      if(event.getSource().equals(chkMovie)) {
         if(chkMovie.isSelected())
         totalCost+=40;
         else
         totalCost-=40;
      }//*

         lblResult.setText("Total Cost: "+ DecimalFormat.getCurrencyInstance().format(totalCost));
   }*/
};
public static void main(String[] args) {
   launch(args);
}
}

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class mealanddormscalculator extends Application {
//defining all needed UI elements
RadioButton dorm1,dorm2,dorm3,dorm4;
RadioButton chk7Meals,chk14Meals,chkUnlimitedMeals;
CheckBox chkBowling, chkMovie;
Label lblResult,lblDormitories,lblMeals, lblOthers;
ToggleGroup group1;
ToggleGroup group2;
Text totalText;

Button calculate;

@Override
public void start(Stage primaryStage) {
//creating a VBox and adjusting attributes
VBox root = new VBox();

//setting padding and spacing
root.setSpacing(10);
//root.setAlignment(Pos.CENTER);
root.setPadding(new Insets(20));

//defining radio buttons and check boxes, assigning the cost of each
//item as their user data
lblDormitories = new Label("Dormitories ");
dorm1 = new RadioButton("DormA: $1000 per semester");
dorm2 = new RadioButton("DormB: $1200 per semester");
dorm3 = new RadioButton("DormC: $1500 per semester");
dorm4 = new RadioButton("University Suites: $3,000 per semester");
lblMeals = new Label("Meals");
chk7Meals = new RadioButton("7 meals per week: $600 per semester");
chk14Meals= new RadioButton("14 meals per week: $1,200 per semester");
chkUnlimitedMeals = new RadioButton("Unlimited meals: $1,500 per semester");
lblOthers = new Label("Others");
chkBowling = new CheckBox("Bowling night: $50 per semester");
chkMovie= new CheckBox("Movie night: $40 per semester");

group1 = new ToggleGroup();
dorm1.setToggleGroup(group1);
dorm2.setToggleGroup(group1);
dorm3.setToggleGroup(group1);
dorm4.setToggleGroup(group1);
dorm1.setSelected(true);

group2 = new ToggleGroup();
chk7Meals.setToggleGroup(group2);
chk14Meals.setToggleGroup(group2);
chkUnlimitedMeals.setToggleGroup(group2);
chk7Meals.setSelected(true);

//adding the two radio buttons inside a toggle group, so that only
//one can be selected at a time
dorm4.setUserData(3000.0);
dorm3.setUserData(2500.0);
dorm2.setUserData(200.0);
dorm1.setUserData(1500.0);
chk7Meals.setUserData(600.0);
chk14Meals.setUserData(1200.0);
chkUnlimitedMeals.setUserData(1500.0);
chkMovie.setUserData(40.0);
chkBowling.setUserData(50.0);



//enabling selection of multiple items
totalText = new Text("Cost: $0.00");

//creating calculate button and adding event listener
calculate = new Button("Calculate Cost");
calculate.setOnAction(e -> update());

//adding each component to the root
root.getChildren().add(dorm1);
root.getChildren().add(dorm2);
root.getChildren().add(dorm3);
root.getChildren().add(dorm4);
root.getChildren().add(chk7Meals);
root.getChildren().add(chk14Meals);
root.getChildren().add(chkBowling);
root.getChildren().add(chkMovie);
root.getChildren().add(totalText);
root.getChildren().add(calculate);

//setting up scene and displaying
Scene scene = new Scene(root, 300, 400);
primaryStage.setScene(scene);
primaryStage.setTitle("Conference Registration");
primaryStage.show();
}

//event handler method to compute total cost whenever calculate button is clicked
private void update() {

//total cost
double total = 0.0;

//adding the user data of selected radio button in the group to total



total += (Double) group2.getSelectedToggle().getUserData();
//adding user data of other optional components if they are selected
if (chkMovie.isSelected()) {
total += (Double) chkMovie.getUserData();
}
if (chkBowling.isSelected()) {
total += (Double) chkBowling.getUserData();
}

//updating final cost
totalText.setText(String.format("Cost: $%.2f", total));
}
public static void main(String[] args) {
launch(args);
}
}
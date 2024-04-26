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
public class ConferenceFeeCalculator extends Application {
  //defining all needed UI elements
  RadioButton generalConf, studentConf;
  ToggleGroup group;
  CheckBox nightDinner;
  Text totalText;
  ListView<String> precon;
  Button calculate;

  //names of precon workshops
  String[] precon_names = {"Introduction to E-commerce ($295)", "The Future of the Web ($295)",
  "Advanced Java Programming ($395)", "Network Security ($395)"};

  //costs of precon workshops
  double[] precon_costs = {295, 295, 395, 395};
  @Override
  public void start(Stage primaryStage) {

  //creating a VBox and adjusting attributes
  VBox root = new VBox();

  //setting padding and spacing
  root.setSpacing(20);
  root.setAlignment(Pos.CENTER);
  root.setPadding(new Insets(20));

  //defining radio buttons and check boxes, assigning the cost of each
  //item as their user data
  generalConf = new RadioButton("General Registration ($895)");
  generalConf.setUserData(895.0);
  studentConf = new RadioButton("Student Registration ($495)");
  studentConf.setUserData(495.0);
  //adding the two radio buttons inside a toggle group, so that only
  //one can be selected at a time
  group = new ToggleGroup();
  generalConf.setToggleGroup(group);
  studentConf.setToggleGroup(group);
  generalConf.setSelected(true);
  nightDinner = new CheckBox("Opening Night Dinner ($30)");
  nightDinner.setUserData(30.0);

  //creating a ListView for optional pre con workshop selection
  precon = new ListView<>(FXCollections.observableArrayList(precon_names));

  //enabling selection of multiple items
  precon.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
  totalText = new Text("Cost: $0.00");

  //creating calculate button and adding event listener
  calculate = new Button("Calculate Cost");
  calculate.setOnAction(e -> update());

  //adding each component to the root
  root.getChildren().add(generalConf);
  root.getChildren().add(studentConf);
  root.getChildren().add(nightDinner);
  root.getChildren().add(new Label("Select an Option Workshop: "));
  root.getChildren().add(precon);
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
  double total = 0;

  //adding the user data of selected radio button in the group to total
  total += (Double) group.getSelectedToggle().getUserData();

  //adding user data of other optional components if they are selected
  if (nightDinner.isSelected()) {
  total += (Double) nightDinner.getUserData();
  }

  //getting selected indices from precon choice box
  ObservableList<Integer> indices = precon.getSelectionModel().getSelectedIndices();
  if (indices != null) {

  //looping through each index
  for (int index : indices) {

  //adding cost of the selected precon workshop to total
  total += precon_costs[index];
  }
  }

  //updating final cost
  totalText.setText(String.format("Cost: $%.2f", total));
  }
  public static void main(String[] args) {
  launch(args);
  }
}
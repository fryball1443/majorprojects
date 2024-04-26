import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class UniversityApplication extends Application {
  // Dorm costs
  private final double DORM_A_COST = 1000;
  private final double DORM_B_COST = 1200;
  private final double DORM_C_COST = 1500;
  
  // Meal plan costs
  private final double MEAL_PLAN_7_COST = 200;
  private final double MEAL_PLAN_14_COST = 400;
  private final double MEAL_PLAN_UNLIMITED_COST = 500;
  
  // Miscellaneous costs
  private final double BOWLING_NIGHT_COST = 50;
  private final double MOVIE_NIGHT_COST = 40;
  
  // GUI elements
  private Label dormLabel;
  private ComboBox<String> dormComboBox;
  private Label mealPlanLabel;
  private ComboBox<String> mealPlanComboBox;
  private CheckBox bowlingNightCheckBox;
  private CheckBox movieNightCheckBox;
  private Label totalCostLabel;
  private Label totalCostValueLabel;
  
  public void start(Stage stage) {
    // Create the dorm label and combo box
    dormLabel = new Label("Dorm:");
    dormComboBox = new ComboBox<>();
    dormComboBox.getItems().addAll("Dorm A", "Dorm B", "Dorm C");
    
    // Create the meal plan label and combo box
    mealPlanLabel = new Label("Meal Plan:");
    mealPlanComboBox = new ComboBox<>();
    mealPlanComboBox.getItems().addAll("7 meals per week", "14 meals per week", "Unlimited meals");
    
    // Create the bowling night and movie night check boxes
    bowlingNightCheckBox = new CheckBox("Bowling night");
    movieNightCheckBox = new CheckBox("Movie night");
    
    // Create the total cost label and value label
    totalCostLabel = new Label("Total Cost:");
    totalCostValueLabel = new Label();
    
    // Create a new HBox for the dorm label and combo box
    HBox dormBox = new HBox(dormLabel, dormComboBox);
    dormBox.setSpacing(10);
    
    // Create a new HBox for the meal plan label and combo box
    HBox mealPlanBox = new HBox(mealPlanLabel, mealPlanComboBox);
    mealPlanBox.setSpacing(10);
    
    // Create a new HBox for the bowling night and movie night check boxes
    HBox miscBox = new HBox(bowlingNightCheckBox, movieNightCheckBox);
    miscBox.setSpacing(10);
    
    // Create a new HBox for the total cost label and value label
    HBox totalCostBox = new HBox(totalCostLabel, totalCostValueLabel);
    totalCostBox.setSpacing

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Calculator");

        GridPane rootNode = new GridPane();
        rootNode.setPadding(new Insets(15));
        rootNode.setHgap(5);
        rootNode.setVgap(5);
        rootNode.setAlignment(Pos.CENTER);

        Scene myScene = new Scene(rootNode, 300, 200);

        rootNode.add(new Label("Amount:"), 0, 0);
        TextField firstValue = new TextField();
        rootNode.add(firstValue, 1, 0);
        rootNode.add(new Label("Total is:"), 0, 5);
        Button aButton = new Button("Calculate");
        rootNode.add(aButton, 1, 2);
        GridPane.setHalignment(aButton, HPos.LEFT);
        TextField result = new TextField();
        result.setEditable(false);
        rootNode.add(result, 1, 5);
        TextField tax = new TextField();
        rootNode.add(new Label("Tax:"), 0, 3);
        tax.setEditable(false);
        rootNode.add(tax,1,3);
        TextField tip = new TextField();
        rootNode.add(new Label("Tip:"), 0, 4);
        tip.setEditable(false);
        rootNode.add(tip,1,4);

        aButton.setOnAction(e -> {
            Float value1 = Float.valueOf(firstValue.getText());

            Float value2 =(value1*18)/100;
            Float value3 = (value1*7)/100;
            Float r = value1+value2+value3 ;
            tax.setText(value3.toString());
            tip.setText(value2.toString());
            result.setText(r.toString());
        });

        primaryStage.setScene(myScene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
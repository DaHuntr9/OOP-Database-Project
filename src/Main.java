import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Programmer Name: Hunter Danielson
 * Date Created:11/19/2018
 * Description of file: This file is to start the GUI application and Start the DBTable.fxml
 * file which on activation will DataBaseTableController.java on initialization.
 */

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }


  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("DBTable.fxml"));
    primaryStage.setTitle("Data Base Application");
    //sets scene size
    primaryStage.setScene(new Scene(root, 600, 400));
    //displays stage
    primaryStage.show();
  }
}
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Programmer Name: Hunter Danielson
 * Date Created:12/9/2018
 * Description of file: This File is the Controller for removeFromTableSubmission.fxml file.
 * This controller establishes connection and queries the SQL database in the lib folder called
 * books. This file contains the functions to add a data point to the database, and also to
 * return to the homepage.
 */

public class AddToTableSubmissionController {

  @FXML
  Button submitButton;
  @FXML
  JFXTextField firstNameTextField;
  @FXML
  JFXTextField lastNameTextField;
  @FXML
  Label errorLabel;
  //Url and driver
  static final String databaseUrl = "jdbc:derby:lib\\books";
  // database credentials
  static final String USER = "deitel";
  static final String PASS = "deitel";
  Statement stmt = null;
  Connection conn = null;

  /**
   * This is for when the submit button is pressed and it will attempt to add the text fields
   * to the database. If it fails it will display a error label prompting the user to try again.
   * @param actionEvent occurs when the button is pressed, triggering the function to operate.
   */
  @FXML
  public void submitButtonPressed(javafx.event.ActionEvent actionEvent) {
    if (firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()) {
      errorLabel.setText("Please fill out all Fields.");
    } else {
      try {
        System.out.println("Connecting to Database...");
        //As of right now there is no login system implemented so hardcoding of the password is
        //necessary to connect tot the database.
        conn = DriverManager.getConnection(databaseUrl,USER,PASS);
        String addSalStatement = "INSERT INTO authors (FIRSTNAME,LASTNAME) " + "VALUES ('"
            + firstNameTextField.getText() + "', '"
            + lastNameTextField.getText() + "')";
        System.out.println(addSalStatement);
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        System.out.println("Executing Statement");
        //FindBugs states that this is a potential problem for sql but it runs fine.
        //This is probably not the best methodology to have a dynamic SQL statement.
        stmt.executeUpdate(addSalStatement);
        System.out.println("Data added to Database");
        errorLabel.setText("Successfully added to Database");
      } catch (SQLException e) {
        System.out.println(e);
        errorLabel.setText("Please check if database is connected.");
      } finally {
        //closes all resources
        try {
          if (stmt != null) {
            stmt.close();
          }
        } catch (SQLException se2) {
          errorLabel.setText("Please check if database is connected.");
        }
        try {
          if (conn != null) {
            conn.close();
          }
        } catch (SQLException se) {
          se.printStackTrace();
        }
      }
    }
  }

  /**
   * This function redirects the user to the table page after clicking on the hyperlink.
   * @param actionEvent This action occurs when the user clicks on the hyperlink.
   * @throws IOException Catches IOExceptions when moving between pages.
   */
  public void goToTable(ActionEvent actionEvent) throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("DBTable.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }
}

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;


public class addToTableSubmissionController {

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
  static final String USER="deitel";
  static final String PASS="deitel";
  Statement stmt = null;
  Connection conn = null;

  @FXML
  public void submitButtonPressed(javafx.event.ActionEvent actionEvent) {
    if(firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()){
      errorLabel.setText("Please fill out all Fields.");
    }else{
      try{
        System.out.println("Connecting to Database...");
        conn = DriverManager.getConnection(databaseUrl,USER,PASS);
        String SQLadd = "INSERT INTO authors (FIRSTNAME,LASTNAME) " + "VALUES ('"
            +firstNameTextField.getText()+"', '"
            +lastNameTextField.getText()+"')";
        System.out.println(SQLadd);
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        System.out.println("Executing Statement");
        stmt.executeUpdate(SQLadd);
        System.out.println("Data added to Database");
        errorLabel.setText("Successfully added to Database");
      }catch(SQLException e){
        System.out.println(e);
      }
      finally {
        //closes all resources
        try{
          if(stmt!=null)
            stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
          if(conn!=null)
            conn.close();
        }catch(SQLException se){
          se.printStackTrace();
        }//end finally try
      }
    }
  }

  public void goToTable(ActionEvent actionEvent) throws IOException{
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("DBTable.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }
}

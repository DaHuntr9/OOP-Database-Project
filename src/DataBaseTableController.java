// Import Statements
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Programmer Name: Hunter Danielson
 * Date Created:11/19/2018
 * Description of file: This File is the Controller for DBTable.fxml file. This controller
 * establishes connection and queries the SQL database in the lib folder called books.
 * It then creates tables in the DBTable.fxml file and sends the information to the cells of
 * these columns.
 */

public class DataBaseTableController {

  @FXML
  private TableView<?> tableView;
  private TableColumn[] tableColumns;
  private ObservableList observableList = FXCollections.observableArrayList();

  @FXML
  private void initialize() {
    String databaseUrl = "jdbc:derby:lib\\books";
    String selectQuery = "SELECT authorID, firstName, lastName FROM authors";
    /**
     * This code will establish connection to the database.
     * The code is provided by Oracle in their Database tutorial in Chapter 24.
     */
    try (
        //As of right now there is no login system implemented so hardcoding of the password is
        //necessary to connect tot the database.
        Connection connection = DriverManager.getConnection(databaseUrl, "deitel", "deitel");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery)) {
      // get ResultSet's meta data
      ResultSetMetaData metaData = resultSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();

      /**
       * I put a JavaDoc Comment so that its easier to recognize where the different
       * sections of code are in the initialization.
       *
       * This code creates the number of columns to be equal to the
       * number of columns in this ResultSet.
       */
      tableColumns = new TableColumn[numberOfColumns];

      //now setting the Columns names to be equal to their meta data in the ResultSet
      for (int i = 1; i <= numberOfColumns; i++) {
        tableColumns[i - 1] = new TableColumn(metaData.getColumnName(i));
      }
      //displays the columns onto the tableView
      tableView.getColumns().addAll(tableColumns);

      /**
       * This section of code is to populate the data into the columns.
       */
      // display query results
      while (resultSet.next()) {
        //This for loop is to loop through the rows data.
        for (int i = 1; i <= numberOfColumns; i = i + 3) {
          //Adds a instance of Author to an array list to be then displayed.
          Author a = new Author(resultSet.getString(i), resultSet.getString(i + 1),
              resultSet.getString(i + 2));
          //Adds the Instance of author to the observable Arraylist.
          observableList.add(a);
        }
      }
      //This associates the cells with their columns.
      tableColumns[0].setCellValueFactory(new PropertyValueFactory<>("authorId"));
      tableColumns[1].setCellValueFactory(new PropertyValueFactory<>("firstName"));
      tableColumns[2].setCellValueFactory(new PropertyValueFactory<>("lastName"));
      //this sets the Table's items to the observableList Array list and allows you to see them.
      tableColumns[0].prefWidthProperty().bind(tableView.widthProperty().divide(3));
      tableColumns[1].prefWidthProperty().bind(tableView.widthProperty().divide(3));
      tableColumns[2].prefWidthProperty().bind(tableView.widthProperty().divide(3));
      tableView.setItems(observableList);
    } catch (SQLException sqlException) {
      //prints the SQL exception.
      sqlException.printStackTrace();
    }
  }

  @FXML
  CheckBox authorIdCheckBox;

  @FXML
  private void selectAuthorId(ActionEvent event) throws IOException {
    //Feature coming soon! This space will reserve code to change the viability of the columns.
    if (authorIdCheckBox.isSelected() == false) {
      tableColumns[0].setVisible(false);
    } else if (authorIdCheckBox.isSelected() == true) {
      tableColumns[0].setVisible(true);
    }
  }

  @FXML
  CheckBox firstNameCheckBox;

  @FXML
  private void selectFirstName(ActionEvent event) throws IOException {
    //Feature coming soon! This space will reserve code to change the viability of the columns.
    if (firstNameCheckBox.isSelected() == false) {
      tableColumns[1].setVisible(false);
    } else if (firstNameCheckBox.isSelected() == true) {
      tableColumns[1].setVisible(true);
    }
  }

  @FXML
  CheckBox lastNameCheckBox;

  @FXML
  private void selectLastName(ActionEvent event) throws IOException {
    //Feature coming soon! This space will reserve code to change the viability of the columns.
    if (lastNameCheckBox.isSelected() == false) {
      tableColumns[2].setVisible(false);
    } else if (lastNameCheckBox.isSelected() == true) {
      tableColumns[2].setVisible(true);
    }
  }

  @FXML
  CheckBox showAll;

  @FXML
  private void selectAllCheckBoxes(ActionEvent event) throws IOException {
    //Feature coming soon! This space will reserve code to change the viability of the columns.
    if (showAll.isSelected() == false) {
      tableColumns[0].setVisible(false);
      tableColumns[1].setVisible(false);
      tableColumns[2].setVisible(false);
      authorIdCheckBox.setSelected(false);
      lastNameCheckBox.setSelected(false);
      firstNameCheckBox.setSelected(false);
    } else if (showAll.isSelected() == true) {
      tableColumns[0].setVisible(true);
      tableColumns[1].setVisible(true);
      tableColumns[2].setVisible(true);
      authorIdCheckBox.setSelected(true);
      lastNameCheckBox.setSelected(true);
      firstNameCheckBox.setSelected(true);
    }
  }

  @FXML
  private void addToTable(ActionEvent event)throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("AddToTableSubmission.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }

  @FXML
  private void updateTable(ActionEvent event)throws IOException {
    String databaseUrl = "jdbc:derby:lib\\books";
    String selectQuery = "SELECT authorID, firstName, lastName FROM authors";
    /**
     * This code will establish connection to the database.
     * The code is provided by Oracle in their Database tutorial in Chapter 24.
     */
    try (
        Connection connection = DriverManager.getConnection(databaseUrl, "deitel", "deitel");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectQuery)) {
      if (!(tableView.getItems() == null)) {
        tableView.getItems().clear();
      }
      // get ResultSet's meta data
      ResultSetMetaData metaData = resultSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();

      /**
       * I put a JavaDoc Comment so that its easier to recognize where the different
       * sections of code are in the initialization.
       *
       * This code creates the number of columns to be equal to the
       * number of columns in this ResultSet.
       */
      tableColumns = new TableColumn[numberOfColumns];

      //now setting the Columns names to be equal to their meta data in the ResultSet
      for (int i = 1; i <= numberOfColumns; i++) {
        tableColumns[i - 1] = new TableColumn(metaData.getColumnName(i));
      }
      //displays the columns onto the tableView
      //tableView.getColumns().addAll(tableColumns);

      /**
       * This section of code is to populate the data into the columns.
       */
      // display query results
      while (resultSet.next()) {
        //This for loop is to loop through the rows data.
        for (int i = 1; i <= numberOfColumns; i = i + 3) {
          //Adds a instance of Author to an array list to be then displayed.
          Author a = new Author(resultSet.getString(i), resultSet.getString(i + 1),
              resultSet.getString(i + 2));
          //Adds the Instance of author to the observable Arraylist.
          observableList.add(a);
        }
      }
      //This associates the cells with their columns.
      tableColumns[0].setCellValueFactory(new PropertyValueFactory<>("authorId"));
      tableColumns[1].setCellValueFactory(new PropertyValueFactory<>("firstName"));
      tableColumns[2].setCellValueFactory(new PropertyValueFactory<>("lastName"));
      //this sets the Table's items to the observableList Array list and allows you to see them.
      tableView.setItems(observableList);

    } catch (SQLException sqlException) {
      //prints the SQL exception.
      sqlException.printStackTrace();
    }
  }


  @FXML
  private void removeFromTable(ActionEvent event)throws IOException {
    Stage stage = Main.getPrimaryStage();
    Parent root = FXMLLoader.load(getClass().getResource("RemoveFromTableSubmission.fxml"));
    stage.setScene(new Scene(root, 600, 440));
    stage.show();
  }
}
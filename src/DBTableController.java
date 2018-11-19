import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DBTableController {

  @FXML
  private TableView<?> tableView;

  @FXML
  private TableColumn[] tableColumns;

  @FXML
  private ObservableList observableList = FXCollections.observableArrayList();

  @FXML
  private void initialize() {
    String DATABASE_URL = "jdbc:derby:lib\\books";
    String SELECT_QUERY = "SELECT authorID, firstName, lastName FROM authors";
    /**
     * This code will establish connection to the database.
     * The code is provided by Oracle in their Database tutorial in Chapter 24.
     */
    try (
        Connection connection = DriverManager.getConnection(
            DATABASE_URL, "deitel", "deitel");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {
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
      tableColumns[0].setCellValueFactory(new PropertyValueFactory<>("authorID"));
      tableColumns[1].setCellValueFactory(new PropertyValueFactory<>("firstName"));
      tableColumns[2].setCellValueFactory(new PropertyValueFactory<>("lastName"));
      //this sets the Table's items to the observableList Array list and allows you to see them.
      tableView.setItems(observableList);
    }// AutoCloseable objects' close methods are called now
    catch (SQLException sqlException) {
      //prints the SQL exception.
      sqlException.printStackTrace();
    }
  }// end class DisplayAuthors

  @FXML
  private void SelectAuthorID(ActionEvent event) throws IOException {
    //Feature coming soon! This space will reserve code to change the viability of the columns.
  }

  @FXML
  private void SelectFirstName(ActionEvent event) throws IOException {
    //Feature coming soon! This space will reserve code to change the viability of the columns.
  }

  @FXML
  private void SelectLastName(ActionEvent event) throws IOException {
    //Feature coming soon! This space will reserve code to change the viability of the columns.
  }

  @FXML
  private void SelectAllCheckBoxes(ActionEvent event) throws IOException {
    //Feature coming soon! This space will reserve code to change the viability of the columns.
  }
}
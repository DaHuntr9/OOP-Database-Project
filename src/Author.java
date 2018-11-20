/**
 * Programmer Name: Hunter Danielson
 * Date Created:11/19/2018
 * Description of file: This File is the class for author data to be parsed from the
 * SQL database called books.
 */

public class Author {
  private String authorId;
  private String firstName;
  private String lastName;
  /**
   *This is the Constructor Class for the Author Data that will be input into the
   *FXML table in DataBaseTableController.java.
   */
  
  Author(String authorId,String firstName,String lastName) {
    this.authorId = authorId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getAuthorId() {
    return authorId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}

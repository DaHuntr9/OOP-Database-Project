
public class Author {
  private String authorID;
  private String firstName;
  private String lastName;
  /**
   *This is the Constructor Class for the Author Data that will be input into the
   *FXML table in DBTableController.java.
   */
  Author(String authorID,String firstName,String lastName){
    this.authorID=authorID;
    this.firstName=firstName;
    this.lastName=lastName;
  }

  public String getAuthorID() {
    return authorID;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setAuthorID(String authorID) {
    this.authorID = authorID;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}

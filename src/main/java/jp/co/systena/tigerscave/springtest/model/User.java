package jp.co.systena.tigerscave.springtest.model;

public class User {
  private String user_id;
  private String password;

  public User() {

  }


  public String getUserId() {
    return this.user_id;
  }

  public void setUserId(String user_Id) {
    this.user_id = user_Id;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String Password) {
    this.password = Password;
  }
}

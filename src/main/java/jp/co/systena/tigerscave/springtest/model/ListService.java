package jp.co.systena.tigerscave.springtest.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ListService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Item> getItemList() {

    //SELECTを使用してテーブルの情報をすべて取得する
    List<Item> itemList = jdbcTemplate.query("SELECT * FROM itemlist ORDER BY item_id", new BeanPropertyRowMapper<Item>(Item.class));

    return itemList;

  }

  public String getPassword(String User_id) {

	String passwordCheckSql = "SELECT * FROM userlist WHERE user_id = '" + User_id +"'";
    List<User> userList = jdbcTemplate.query(passwordCheckSql, new BeanPropertyRowMapper<User>(User.class));

    User user = userList.get(0);
    String password = user.getPassword();

    return password;
  }

  public void insertUser(String userId, String password) {

      //1行分の値をデータベースにINSERTする
      //SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
      int insertCount = jdbcTemplate.update(
            "INSERT INTO userlist VALUES( ?, ? )", userId, password);

  }
}

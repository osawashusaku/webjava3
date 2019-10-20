package jp.co.systena.tigerscave.springtest.model;

public class Order {
  private int item_id;
  private String name;
  private int price;
  private int num;

  public Order() {

  }

  public int getItem_id() {
    return this.item_id;
  }

  public void setItemId(int item_id) {
    this.item_id = item_id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getNum() {
    return this.num;
  }

  public void setNum(int num) {
    this.num = num;
  }

}

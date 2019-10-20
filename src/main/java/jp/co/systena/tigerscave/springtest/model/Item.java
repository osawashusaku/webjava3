package jp.co.systena.tigerscave.springtest.model;

public class Item {
  private int item_id;
  private String name;
  private int price;

  public Item() {

  }

  public int getItem_id() {
    return this.item_id;
  }

  public void setItem_id(int item_Id) {
    this.item_id = item_Id;
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

}

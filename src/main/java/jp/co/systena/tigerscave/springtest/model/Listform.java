package jp.co.systena.tigerscave.springtest.model;

import java.util.List;

public class Listform {
  private int[] itemId;
  private String[] name;
  private int[] price;
  private int[] num;

  private List<Item> itemList;

  public int[] getItemId() {
    return this.itemId;
  }

  public void setItemId(int[] ItemId) {
    this.itemId = ItemId;
  }

  public String[] getName() {
    return this.name;
  }

  public void setName(String[] name) {
    this.name = name;
  }

  public int[] getPrice() {
    return this.price;
  }

  public void setPrice(int[] price) {
    this.price = price;
  }

  public int[] getNum() {
    return num;
  }

  public void setNum(int[] Num) {
    this.num = Num;
  }

  public List<Item> getL_formList() {
	    return itemList;
	  }

  public void setL_formList(List<Item> itemList) {
	    this.itemList = itemList;
	  }

}

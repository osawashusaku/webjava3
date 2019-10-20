package jp.co.systena.tigerscave.springtest.model;

import java.util.ArrayList;

public class Cart {

  private ArrayList<Order> OrderList = new ArrayList<Order>();

   // public ArrayList<Order> orderList;
  public ArrayList<Order> getOrderList() {
      return OrderList;
    }

  public void add(Order order) {
    OrderList.add(order);
  }

  }


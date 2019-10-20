package jp.co.systena.tigerscave.springtest.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systena.tigerscave.springtest.model.Cart;
import jp.co.systena.tigerscave.springtest.model.ListService;
import jp.co.systena.tigerscave.springtest.model.Listform;
import jp.co.systena.tigerscave.springtest.model.Order;
import jp.co.systena.tigerscave.springtest.model.User;
import jp.co.systena.tigerscave.springtest.model.Userform;

@Controller
public class ListController {

  @Autowired
  HttpSession session;  //セッション管理
  @Autowired
  private ListService ListService;


  @RequestMapping(value = "/", method = RequestMethod.GET)    // URLとのマッピング
  public ModelAndView show(ModelAndView mav) {

    mav.setViewName("/Login");

    return mav;
  }

  @RequestMapping(value="/Login", method = RequestMethod.POST)  // URLとのマッピング
  public ModelAndView Login(@Valid Userform u_form, ModelAndView mav,
						    @RequestParam(value = "user_id") String user_id,
				            @RequestParam(value = "password") String password) {

	//画面からのパラメータをフォームクラスのメンバ変数として定義
	u_form.setUserId(user_id);
	u_form.setPassword(password);

    User user = new User();
    user.setUserId(u_form.getUserId());
    user.setPassword(u_form.getPassword());

   if (user.getPassword().equals(ListService.getPassword(user.getUserId()))) {
      mav.setViewName("/ListView");
      mav.addObject("itemList", ListService.getItemList());
      int TotalPrice = 0;
      session.setAttribute("TotalPrice", TotalPrice);
      mav.addObject("TotalPrice", TotalPrice);
    }else {
      mav.addObject("Login_Error_Msg","パスワードが間違っています！");
      mav.setViewName("/Login");
    }

    return mav;
  }

  @RequestMapping(value = "/Subscribe", method = RequestMethod.POST)    // URLとのマッピング
  public ModelAndView Subscribe(@Valid Userform u_form, ModelAndView mav,
                                 @RequestParam(value = "user_id") String user_id,
                                 @RequestParam(value = "password") String password) {

    Userform userform = new Userform();
    userform.setUserId(user_id);
    userform.setPassword(password);

    ListService.insertUser(userform.getUserId(),userform.getPassword());

    mav.setViewName("/Login");
    mav.addObject("Insert_Msg", "ユーザー登録が完了しました。ログインできます！");

    return mav;
  }

  @RequestMapping(value="/ListView", method = RequestMethod.POST)  // URLとのマッピング
  public ModelAndView order(@Valid Listform l_form, ModelAndView mav,
                             @RequestParam(value = "details[' + __${itemStat.index}__ + '].orderId") int[] orderId,
                             @RequestParam(value = "details[' + __${itemStat.index}__ + '].orderName") String[] orderName,
                             @RequestParam(value = "details[' + __${itemStat.index}__ + '].orderPrice") int[] orderPrice,
                             @RequestParam(value = "details[' + __${itemStat.index}__ + '].orderNum") int[] orderNum
		  ) {

        // Viewのテンプレート名を設定
        mav.setViewName("/ListView");

        mav.addObject("itemList", ListService.getItemList());

        Cart cart = getCart();

        //画面からのパラメータをフォームクラスのメンバ変数として定義
//        l_form.setItemId(orderId);
//        l_form.setName(orderName);
//        l_form.setPrice(orderPrice);
//        l_form.setNum(orderNum);

        int TotalPrice = (int) session.getAttribute("TotalPrice");

        int size = ListService.getItemList().size();
        for(int i = 0; i < size; i++) {
          Order order = new Order();
          int[] OrderId = l_form.getItemId();
          String[] OrderName = l_form.getName();
          int[] OrderPrice = l_form.getPrice();
          int[] OrderNum = l_form.getNum();


          if(OrderNum[i] != 0) {
            cart.add(order);
            TotalPrice += OrderPrice[i] * OrderNum[i];
            session.setAttribute("TotalPrice", TotalPrice);
          }
       }

        mav.addObject("orderList", cart.getOrderList());

        mav.addObject("TotalPrice", TotalPrice);

        session.setAttribute("TotalPrice", TotalPrice);
        session.setAttribute("cart", cart);

        return mav;
    }

  private Cart getCart() {
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }
    return cart;
  }


}

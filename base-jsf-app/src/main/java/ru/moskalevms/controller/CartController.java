package ru.moskalevms.controller;

import ru.moskalevms.cart.CartService;
import ru.moskalevms.cart.LineItem;
import ru.moskalevms.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    @Inject
    private CartService cartService;

    public List<LineItem> getLineItems(){
       return cartService.getLineItems();
    }

    public void removeLineItem(Product product){
        cartService.removeProductQty(product, "green" , 1000);
    }

}

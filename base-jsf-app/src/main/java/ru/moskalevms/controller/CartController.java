package ru.moskalevms.controller;

import ru.moskalevms.persist.Item;
import ru.moskalevms.persist.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    private List<Item> items;

    public CartController() {
        this.items = new ArrayList<Item>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String buy(Product product) {
        int index = this.existProduct(product);
        if (index == -1) {
            this.items.add(new Item(product, 1));
        } else {
            int quantity = this.items.get(index).getQuantity() + 1;
            this.items.get(index).setQuantity(quantity);
        }
        return "cart?faces-redirect=true";
    }

    public String delete(Product product) {
        int index = this.existProduct(product);
        this.items.remove(index);
        return "cart?faces-redirect=true";
    }

    public double total() {
        double s = 0;
        for(Item item : this.items) {
            s += item.getProduct().getPrice().doubleValue() * item.getQuantity();
        }
        return s;
    }

    private int existProduct(Product product) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getProduct().getId() == product.getId()) {
                return i;
            }
        }
        return -1;
    }

}

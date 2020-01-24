package ru.moskalevms.controller;

import ru.moskalevms.cart.CartService;
import ru.moskalevms.persist.Product;
import ru.moskalevms.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {
    @Inject
    private ProductRepository productRepository;

    @Inject
    private CartService cartService;

    private Product product;

    private List<Product> products;

    public void preloadProducts(ComponentSystemEvent componentSystemEvent){
        this.products = productRepository.findAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct(){
        this.product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public List<Product> getAllProducts() throws SQLException {
        return products;
    }

    public String editProducts(Product product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProducts(Product product) {
        productRepository.delete(product.getId());
        //return "/index.xhtml?faces-redirect=true";
    }

    public String saveProduct()  {
        if(product.getId() == null){
            productRepository.insert(product);
        }else {
            productRepository.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void addToCart(Product product){
        cartService.addProdcutQty(product, "green", 1);
    }

}

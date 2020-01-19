package ru.moskalevms.controller;

import ru.moskalevms.persist.Product;
import ru.moskalevms.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
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

    private Product product;

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
        return productRepository.findAll();
    }

    public String editProducts(Product product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProducts(Product product) throws SQLException {
        productRepository.delete(product.getId());
        //return "/index.xhtml?faces-redirect=true";
    }

    public String saveProduct() throws SQLException {
        if(product.getId() == null){
            productRepository.insert(product);
        }else {
            productRepository.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

}

package ru.moskalevms.persist;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductDAO implements ProductRepository {


    @Override
    public void insert(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }
}

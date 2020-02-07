package ru.moskalevms.persist;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Local
public interface ProductRepository {

    void insert(Product product);


    void update(Product product);

    void delete(long id);

    Product findById(long id);

    List<Product> findAll();
}

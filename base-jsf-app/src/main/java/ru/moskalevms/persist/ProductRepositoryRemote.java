package ru.moskalevms.persist;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface ProductRepositoryRemote {

     List<Product> findAll();

     Future<List<Product>> findAllAsync();
}

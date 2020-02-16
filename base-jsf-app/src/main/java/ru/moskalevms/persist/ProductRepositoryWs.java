package ru.moskalevms.persist;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductRepositoryWs {

    @WebMethod
    List<Product> findAll();

    @WebMethod
    Product findById(long id);

}

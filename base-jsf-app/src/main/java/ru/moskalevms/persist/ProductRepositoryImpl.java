package ru.moskalevms.persist;

import ru.moskalevms.rest.ProductServiceRs;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
@WebService(endpointInterface = "ru.moskalevms.persist.ProductRepositoryWs", serviceName = "ProductService")
public class ProductRepositoryImpl implements ProductRepository, ProductRepositoryRemote, ProductRepositoryWs, ProductServiceRs {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    @PostConstruct
    public void init()  {
        if (findAll().size() == 0) {
            insert(new Product(-1l, "Product1", "Desc1", new BigDecimal(10)));
        }
    }

    @Override
    @TransactionAttribute
    public void insert(Product product)  {
        em.persist(product);
       }
    @Override
    @TransactionAttribute
    public void update(Product product) {
        em.merge(product);
    }

    @Override
    @TransactionAttribute
    public void delete(long id)  {
        Product product = em.find(Product.class, id);
        if(product != null) {
            em.remove(product);
        }
    }

    @Override
    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery(" from Product", Product.class).getResultList();
    }

  @Asynchronous
    @Override
    public Future<List<Product>> findAllAsync() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(em.createQuery(" from Product", Product.class).getResultList());
    }

}

package ru.moskalevms.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

@ApplicationScoped
@Named
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @PostConstruct
    public void init()  {
       if(findAll().size() == 0){
           insert(new Product(-1l, "Product1", "Desc1", new BigDecimal(10)));
       }
    }

    @Transactional
    public void insert(Product product)  {
        em.persist(product);
       }
    @Transactional
    public void update(Product product) {
        em.merge(product);
    }
    @Transactional
    public void delete(long id)  {
        Product product = em.find(Product.class, id);
        if(product != null) {
            em.remove(product);
        }
    }

    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery(" from Product", Product.class).getResultList();

    }

}

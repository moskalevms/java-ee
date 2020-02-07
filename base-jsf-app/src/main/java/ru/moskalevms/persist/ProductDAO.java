package ru.moskalevms.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class ProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @EJB
    private ProductRepositoryImpl productRepositoryImpl;


    @TransactionAttribute
    public void insert(Product product)  {
        em.persist(product);
    }

    @TransactionAttribute
    public void update(Product product) {
        em.merge(product);
    }
  
    @TransactionAttribute
    public void delete(long id)  {
        Product product = em.find(Product.class, id);
        if(product != null) {
            em.remove(product);
        }
    }

}

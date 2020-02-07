package ru.moskalevms;

import ru.moskalevms.persist.Product;
import ru.moskalevms.persist.ProductRepositoryRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

public class EjbClient {
    public static void main(String[] args) throws Exception {

        Context context = createInitialContext();

        ProductRepositoryRemote repository = (ProductRepositoryRemote) context.lookup("ejb:/base-jsf-app//ProductRepositoryImpl!ru.moskalevms.persis.ProductRepositoryRemote");
        repository.findAll().forEach(p -> System.out.println(p.getName()));

       Future<List<Product>> future =  repository.findAllAsync();
       System.out.println("Waiting for complete...");
       future.get();

    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class
                .getClassLoader()
                .getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }

}

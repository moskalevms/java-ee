import java.net.MalformedURLException;
import java.net.URL;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import ru.moskalevms.persist.Product;
import ru.moskalevms.persist.ProductRepositoryWs;
import ru.moskalevms.service.ProductService;

public class WsClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/base-jsf-app/ProductServise/ProductRepositoryImpl?wsdl");
        ProductService productServise = new ProductService(url);
        ProductRepositoryWs productRepositoryImplPort = productServise.getProductRepositoryImplPort();

        productRepositoryImplPort.findAll()
                .forEach(p -> System.out.println(p.getName()));
    }

     Product product = productRepositoryImplPort.findById(14);
     System.out.println(product.getName());
}

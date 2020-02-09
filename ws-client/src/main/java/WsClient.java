import java.net.MalformedURLException;
import java.net.URL;
import ru.moskalevms.service.ProductService;

public class WsClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/base-jsf-app/ProductServise/ProductRepositoryImpl?wsdl");
        ProductService productServise = new ProductSrvice(url);
        ProductRepositoryWs productRepositoryImplPort = productServise.getProductRepositoryImplPort();

        productRepositoryImplPort.findAll()
                .forEach(p -> System.out.println(p.getName()));
    }
}

import java.util.List;

public interface ProductDAO
{
    List<Product> findAll();
    List<Product> findByName();
    List<Product> findByDate();
    boolean insertProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
}

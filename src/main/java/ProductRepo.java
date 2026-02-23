import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product){
        products.add(product);
    }

    public boolean removeById(String id){
        return products.removeIf(p -> p.id().equals(id));
    }
}

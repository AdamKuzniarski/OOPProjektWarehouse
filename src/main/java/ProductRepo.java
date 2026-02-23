import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepo {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product){
        products.add(product);
    }

    public boolean removeById(String id){
        return products.removeIf(p -> p.id().equals(id));
    }

    public  Optional<Product> getById(String id){
        for(Product p : products){
            if(p.id().equals(id)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<Product> getAll(){
        return List.copyOf(products);
    }
}

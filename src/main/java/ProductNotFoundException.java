import java.util.NoSuchElementException;

public class ProductNotFoundException extends NoSuchElementException {
    public ProductNotFoundException(String productId) {
        super("Product with id " + productId + " not found.");
    }
}

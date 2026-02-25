import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRepoTest {

    @Test
    void add_and_getById_shouldReturnProduct() {
        // Arrange
        ProductRepo repo = new ProductRepo();
        repo.add(new Product("p1", "Testprodukt", 9.99));

        // Act
        Optional<Product> result = repo.getById("p1");

        // Assert
        Product expected = new Product("p1", "Testprodukt", 9.99);
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
//        assertEquals("Testprodukt", result.get().name());
    }

    //Move to OrderListRepoTest file
    @Test
    void removeById_shouldDeleteOrder() {
        // Arrange
        OrderRepoInterface repo = new OrderListRepo();
        repo.add(new Order("o1", List.of(new OrderItem("p1", 2)), OrderStatus.PROCESSING, Instant.now()));

        // Act
        boolean removed = repo.removeById("o1");

        // Assert
        assertTrue(removed);
//        assertTrue(repo.findById("o1").isEmpty());
//        assertEquals(0, repo.getAll().size());
    }
}

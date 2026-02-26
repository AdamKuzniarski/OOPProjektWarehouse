import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShopServiceTest {
    @Test
    void placeOrder_shouldStoreOrder_whenProductExists(){
        // Arrange
        ProductRepo productRepo = new ProductRepo();
        productRepo.add(new Product("p1", "Testprodukt", 9.99));
        productRepo.add(new Product("p2", "Testprodukt2", 9.99));

        OrderRepoInterface orderRepo = new OrderListRepo();
        IdService idService = () -> "test-id";
        ShopService service = new ShopService(productRepo, orderRepo, idService);

        // Act
        Order order = service.addOrder(List.of(
                new OrderItem("p1", 2),
                new OrderItem("p2", 1)
        ));

        // Assert
        assertNotNull(order);
        assertEquals("test-id", order.id());
        assertEquals(2, order.items().size());
        assertEquals(OrderStatus.PROCESSING, order.status());
    }

    @Test
    void placeOrder_shouldRejectUnknownProduct(){
        // Arrange
        ProductRepo productRepo = new ProductRepo();
        OrderRepoInterface orderRepo = new OrderListRepo();
        IdService idService = () -> "test-id";
        ShopService service = new ShopService(productRepo, orderRepo, idService);

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> {
            service.addOrder(List.of(new OrderItem("unknown-product", 1)));
        });
    }
}

import java.util.List;
import java.util.Optional;

public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepoInterface orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepoInterface orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Optional<Order> placeOrder(String orderId, List<OrderItem> items) {
        // Validierung der Bestellung
        if (items == null || items.isEmpty()) {
            System.out.println("Bestellung abgelehnt: keine Artikel.");
            return Optional.empty();
        }
        for (OrderItem item : items) {
            if (item.quantity() <= 0) {

                System.out.println("Bestellung abgelehnt: Mente muss > 0 sein.(" + item.productId() + ")");
                return Optional.empty();
            }

            boolean exists = productRepo.getById(item.productId()).isPresent();
            if (!exists) {
                System.out.println("Bestelleung abgelehnt: Produkt existiert nicht: " + item.productId());
                return Optional.empty();
            }


        }
        //alles ok, Bestellung anlegen
        Order order = new Order(orderId, List.copyOf(items));
        orderRepo.add(order);
        return Optional.of(order);

    }

    //Summe berechnen.
    public double calculateTotal(Order order) {
        double sum = 0.0;
        for (OrderItem item : order.items()) {
            Product product = productRepo.getById(item.productId()).orElseThrow();
            sum += product.price() * item.quantity();
        }
        return sum;
    }
}

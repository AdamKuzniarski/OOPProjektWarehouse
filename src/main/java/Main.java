import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();
        OrderRepoInterface orderRepo = new OrderMapRepo(); // oder OrderListRepo

        IdService idService = () -> UUID.randomUUID().toString();

        ShopService shopService = new ShopService(productRepo, orderRepo, idService);

        // Produkte anlegen
        productRepo.add(new Product("1", "Apfel", 0.49));
        productRepo.add(new Product("2", "Brot", 2.29));

        // Bestellung anlegen (ok)
        var order = shopService.addOrder(List.of(
                new OrderItem("1", 2),
                new OrderItem("2", 1)
        ));
        System.out.println("OK: " + order);

        // Status updaten
        var updated = shopService.updateOrder(order.id(), OrderStatus.COMPLETED);
        System.out.println("UPDATED: " + updated);

        // Filter via Streams
        //System.out.println("COMPLETED Orders: " + shopService.getOrdersByStatus(OrderStatus.COMPLETED));

        // Exception-Demo (Produkt existiert nicht)
        try {
            shopService.addOrder(List.of(new OrderItem("999", 1)));
        } catch (ProductNotFoundException e) {
            System.out.println("ERWARTETER FEHLER: " + e.getMessage());
        }
    }
}
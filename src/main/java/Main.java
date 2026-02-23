import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductRepo productRepo = new ProductRepo();
        productRepo.add(new Product("1", "Laptop", 999.99));
        productRepo.add(new Product("2", "Smartphone", 499.99));

        OrderRepoInterface orderRepo = new OrderMapRepo();

        ShopService shopService = new ShopService(productRepo, orderRepo);

        var items = List.of(
                new OrderItem("p1",3),
                new OrderItem("p2",1)
        );

        shopService.placeOrder("o1", items).ifPresent(order -> {
            System.out.println("Bestellung gespeichert: " + order);
            System.out.println("Total " + shopService.calculateTotal(order));
        });



    }

}

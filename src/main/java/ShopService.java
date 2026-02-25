import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepoInterface orderRepo;
    private final IdService idService;

    //addOrder: wirft Exception, wenn Produkt nicht existiert
    public Order addOrder(List<OrderItem> items){
        String newOrderId = idService.generateId();
        return addOrder(newOrderId, items);
    }

    public Order addOrder(String orderId, List<OrderItem> items){
        validateItemsOrThrow(items);
        Order order = new Order(orderId, items, OrderStatus.PROCESSING, Instant.now());
        orderRepo.add(order);
        return order;
    }

public Order updateOrder(String orderId, OrderStatus newStatus){
        Order existing = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Bestellung nicht gefunden: " + orderId));

        Order updated = existing.withStatus(newStatus);

        orderRepo.removeById(orderId);
        orderRepo.add(updated);

        return updated;
}

public List<Order> getAllOrders(){
        return orderRepo.getAll();
}

private void validateItemsOrThrow(List<OrderItem> items){
        if(items == null || items.isEmpty()){
            throw new IllegalArgumentException("Bestellung muss mindestens ein Produkt enthalten");
        }
        for(OrderItem item : items){
            if(item.quantity() <= 0){
                throw new IllegalArgumentException("Menge muss größer als 0 sein: " + item);
            }

            Optional<Product> productOpt = productRepo.getById(item.productId());
            if(productOpt.isEmpty()){
                throw new ProductNotFoundException(item.productId());
            }
        }
}


}

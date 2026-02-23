import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderMapRepo {
    private final Map<String, Order> orders = new HashMap<>();


    public void add(Order order) {
        orders.put(order.id(), order);
    }


    public boolean removeById(String id) {
        return orders.remove(id) != null;

    }


    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }


    public List<Order> getAll() {
        return List.copyOf(orders.values());
    }
}

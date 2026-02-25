import java.util.*;

public class OrderMapRepo implements OrderRepoInterface {
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
        return new ArrayList<>(orders.values());
//        return List.copyOf(orders.values());
    }
}

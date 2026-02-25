import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderListRepo implements OrderRepoInterface {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void add(Order order) {
        orders.add(order);
    }

    @Override
    public boolean removeById(String id) {
        return orders.removeIf(o-> o.id().equals(id));
    }

    @Override
    public Optional<Order> findById(String id) {
       for(Order o : orders){
           if(o.id().equals(id)){
               return Optional.of(o);
           }
       } return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders);
//        return List.copyOf(orders);
    }
}

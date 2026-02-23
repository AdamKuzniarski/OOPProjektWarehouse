import java.util.List;
import java.util.Optional;

public interface OrderRepoInterface {
    void add(Order order);
    boolean removeById(String id);
    Optional<Order> findById(String id);
    List<Order> getAll();
}

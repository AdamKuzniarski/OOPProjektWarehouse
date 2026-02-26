import lombok.With;

import java.time.Instant;
import java.util.List;

@With
public record Order(String id, List<OrderItem> items, OrderStatus status, Instant OrderedAt) {
}

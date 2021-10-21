package con.github.ismail2ov.ecommerce;

import java.util.List;
import java.util.UUID;

public interface BasketRepository {
    void save(UUID customerId, List<Item> items);

    List<Item> getAllBy(UUID customerId);
}

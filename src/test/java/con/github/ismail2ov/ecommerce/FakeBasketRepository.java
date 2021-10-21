package con.github.ismail2ov.ecommerce;

import java.util.*;

public class FakeBasketRepository implements BasketRepository {
    Map<UUID, List<Item>> itemsMap = new HashMap<>();

    @Override
    public void save(UUID customerId, List<Item> items) {
        itemsMap.put(customerId, items);

    }

    @Override
    public List<Item> getAllBy(UUID customerId) {
        return itemsMap.getOrDefault(customerId, new ArrayList<>());
    }
}

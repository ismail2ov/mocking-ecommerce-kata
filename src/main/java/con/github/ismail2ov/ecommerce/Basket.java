package con.github.ismail2ov.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private BasketRepository basketRepository;
    private Customer customer;
    private List<Item> items;

    public Basket(Customer customer, BasketRepository basketRepository) {
        this.customer = customer;
        this.basketRepository = basketRepository;
        this.items = this.basketRepository.getAllBy(customer.getCustomerId());
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
        basketRepository.save(customer.getCustomerId(), items);
    }

    public int getAmount() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }
}

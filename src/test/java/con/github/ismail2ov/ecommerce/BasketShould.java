package con.github.ismail2ov.ecommerce;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketShould {
    @Test
    void has_no_items_when_is_initialized() {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "Pepe");
        BasketRepository basketRepository = new FakeBasketRepository();
        Basket basket = new Basket(customer, basketRepository);

        assertThat(basket.getItems()).hasSize(0);
    }

    @Test
    void restore_last_client_basket() {
        BasketRepository basketRepository = new FakeBasketRepository();
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer(customerId, "Pepe");
        Basket basket = new Basket(customer, basketRepository);
        Item item = new Item("iPod", 99);
        basket.addItem(item);

        Basket actual = new Basket(customer, basketRepository);

        assertThat(actual.getItems()).hasSize(1);
        assertThat(actual.getItems()).extracting("name")
                .contains("iPod")
                .doesNotContain("iPad");
    }
}

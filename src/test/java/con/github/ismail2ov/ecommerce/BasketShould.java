package con.github.ismail2ov.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketShould {
    private Basket basket;
    private Customer customer;
    private BasketRepository basketRepository;

    @BeforeEach
    void setUp() {
        UUID customerId = UUID.randomUUID();
        customer = new Customer(customerId, "Pepe");
        basketRepository = new FakeBasketRepository();
        basket = new Basket(customer, basketRepository);
    }

    @Test
    void has_no_items_when_is_initialized() {
        assertThat(basket.getItems()).hasSize(0);
    }

    @Test
    void restore_last_client_basket() {
        Item item = new Item("iPod", 99);
        basket.addItem(item);

        Basket actual = new Basket(customer, basketRepository);

        assertThat(actual.getItems()).hasSize(1);
        assertThat(actual.getItems()).extracting("name")
                .contains("iPod")
                .doesNotContain("iPad");
    }
}

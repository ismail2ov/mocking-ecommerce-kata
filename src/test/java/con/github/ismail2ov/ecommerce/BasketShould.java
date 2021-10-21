package con.github.ismail2ov.ecommerce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketShould {
    @Test
    void has_no_items_when_is_initialized() {
        Basket basket = new Basket();

        assertThat(basket.getItems()).hasSize(0);
    }
}

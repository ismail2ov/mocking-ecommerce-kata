package con.github.ismail2ov.ecommerce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketShould {
    @Mock
    private Customer customer;
    @Mock
    private BasketRepository basketRepository;
    @InjectMocks
    private Basket basket;

    @Test
    void has_no_items_when_is_initialized() {
        assertThat(basket.getItems()).hasSize(0);
    }

    @Test
    void restore_last_client_basket() {
        UUID customerId = UUID.randomUUID();
        String itemName = "MacBook";
        int itemPrice = 2999;
        Item item = new Item(itemName, itemPrice);
        basket.addItem(item);
        when(customer.getCustomerId()).thenReturn(customerId);
        when(basketRepository.getAllBy(customerId)).thenReturn(List.of(item));

        Basket actual = new Basket(customer, basketRepository);

        assertThat(actual.getItems()).hasSize(1);
        assertThat(actual.getAmount()).isEqualTo(itemPrice);
        assertThat(actual.getItems()).extracting("name")
                .contains(itemName)
                .doesNotContain("iPod", "iPad");
    }
}

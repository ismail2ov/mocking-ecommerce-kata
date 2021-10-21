package con.github.ismail2ov.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceShould {
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
    void create_payment_request() {
        Item item = new Item("iPod", 99);
        basket.addItem(item);

        int amount = basket.getAmount();
        CreditCart creditCart = new CreditCart();
        PaymentRequest expected = new PaymentRequest(amount, creditCart);
        Logger logger = new DummyLoggerImpl();

        PaymentService paymentService = new PaymentService(logger);

        PaymentRequest actual = paymentService.createPaymenRequest(amount, creditCart);

        assertThat(actual).isEqualTo(expected);
    }
}

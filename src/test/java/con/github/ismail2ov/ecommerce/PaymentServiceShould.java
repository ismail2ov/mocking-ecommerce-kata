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
        OperatorRateStub operatorRate = new OperatorRateStub();
        operatorRate.when("getRate", 5);
        PaymentRequest expected = new PaymentRequest(amount, creditCart, 5);
        Logger logger = new DummyLoggerImpl();
        MailService mailService = null;

        PaymentService paymentService = new PaymentService(logger, operatorRate, mailService);

        PaymentRequest actual = paymentService.createPaymenRequest(amount, creditCart);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void send_an_email_when_payment_total_is_more_than_1000() throws Exception {
        Item item = new Item("MacBook", 2999);
        basket.addItem(item);

        int amount = basket.getAmount();
        CreditCart creditCart = new CreditCart();
        OperatorRateStub operatorRate = new OperatorRateStub();
        operatorRate.when("getRate", 5);
        PaymentRequest expected = new PaymentRequest(amount, creditCart, 5);
        Logger logger = new DummyLoggerImpl();

        MailServiceMock mailService = new MailServiceMock();
        PaymentService paymentService = new PaymentService(logger, operatorRate, mailService);

        PaymentRequest actual = paymentService.createPaymenRequest(amount, creditCart);

        assertThat(actual).isEqualTo(expected);
        mailService.verify("send");
        mailService.verifyNoMoreInteraction();
    }
}

package con.github.ismail2ov.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PaymentServiceShould {
    private CreditCart creditCart;
    private OperatorRate operatorRate;
    private Logger logger;
    private MailService mailService;

    @BeforeEach
    void setUp() {
        this.creditCart = mock(CreditCart.class);
        this.operatorRate = mock(OperatorRate.class);
        this.logger = mock(Logger.class);
        this.mailService = mock(MailService.class);
    }

    @Test
    void create_payment_request() {
        int amount = 99;
        PaymentRequest expected = new PaymentRequest(amount, creditCart, 5);
        PaymentService paymentService = new PaymentService(logger, operatorRate, mailService);
        when(operatorRate.getRate("MC")).thenReturn(5);

        PaymentRequest actual = paymentService.createPaymenRequest(amount, creditCart);

        assertThat(actual).isEqualTo(expected);
        verifyNoInteractions(mailService);
    }

    @Test
    void send_an_email_when_payment_total_is_more_than_1000() {
        int amount = 2999;
        PaymentRequest expected = new PaymentRequest(amount, creditCart, 5);
        PaymentService paymentService = new PaymentService(logger, operatorRate, mailService);
        when(operatorRate.getRate("MC")).thenReturn(5);

        PaymentRequest actual = paymentService.createPaymenRequest(amount, creditCart);

        assertThat(actual).isEqualTo(expected);

        verify(mailService).send(anyString());
        verifyNoMoreInteractions(mailService);
    }
}

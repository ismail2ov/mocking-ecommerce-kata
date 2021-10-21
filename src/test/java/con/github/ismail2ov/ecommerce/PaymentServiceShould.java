package con.github.ismail2ov.ecommerce;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceShould {
    @Mock
    private CreditCart creditCart;
    @Mock
    private OperatorRate operatorRate;
    @Mock
    private Logger logger;
    @Mock
    private MailService mailService;

    @Test
    void create_payment_request() {
        int amount = 99;
        PaymentRequest expected = new PaymentRequest(amount, creditCart, 5);
        PaymentService paymentService = new PaymentService(logger, operatorRate, mailService);
        when(operatorRate.getRate("MC")).thenReturn(5);

        PaymentRequest actual = paymentService.createPaymentRequest(amount, creditCart);

        assertThat(actual).isEqualTo(expected);
        verifyNoInteractions(mailService);
    }

    @Test
    void send_an_email_when_payment_total_is_more_than_1000() {
        int amount = 2999;
        PaymentRequest expected = new PaymentRequest(amount, creditCart, 5);
        PaymentService paymentService = new PaymentService(logger, operatorRate, mailService);
        when(operatorRate.getRate("MC")).thenReturn(5);

        PaymentRequest actual = paymentService.createPaymentRequest(amount, creditCart);

        assertThat(actual).isEqualTo(expected);

        verify(mailService).send(anyString());
        verifyNoMoreInteractions(mailService);
    }
}

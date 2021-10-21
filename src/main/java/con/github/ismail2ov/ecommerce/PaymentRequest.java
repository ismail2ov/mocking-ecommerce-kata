package con.github.ismail2ov.ecommerce;

import lombok.Value;

@Value
public class PaymentRequest {
    private final int amount;
    private final CreditCart creditCart;

    public PaymentRequest(int amount, CreditCart creditCart) {
        this.amount = amount;
        this.creditCart = creditCart;
    }
}

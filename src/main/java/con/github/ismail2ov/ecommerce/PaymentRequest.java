package con.github.ismail2ov.ecommerce;

import lombok.Value;

@Value
public class PaymentRequest {
    int amount;
    CreditCart creditCart;
    int operatorRate;

    public PaymentRequest(int amount, CreditCart creditCart, int operatorRate) {
        this.amount = amount;
        this.creditCart = creditCart;
        this.operatorRate = operatorRate;
    }
}

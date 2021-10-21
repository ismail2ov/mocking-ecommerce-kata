package con.github.ismail2ov.ecommerce;

public class PaymentService {

    private Logger logger;

    public PaymentService(Logger logger) {
        this.logger = logger;
    }

    public PaymentRequest createPaymenRequest(int amount, CreditCart creditCart) {
        logger.info("Created payment for sale with the amount: " + amount);
        return new PaymentRequest(amount, creditCart);
    }
}

package con.github.ismail2ov.ecommerce;

public class PaymentService {

    private Logger logger;
    private OperatorRate operatorRate;
    private MailService mailService;

    public PaymentService(Logger logger, OperatorRate operatorRate, MailService mailService) {
        this.logger = logger;
        this.operatorRate = operatorRate;
        this.mailService = mailService;
    }

    public PaymentRequest createPaymenRequest(int amount, CreditCart creditCart) {
        logger.info("Created payment for sale with the amount: " + amount);
        if (amount > 1000) {
            mailService.send("admin@localhost");
        }
        return new PaymentRequest(amount, creditCart, operatorRate.getRate("MC"));
    }
}

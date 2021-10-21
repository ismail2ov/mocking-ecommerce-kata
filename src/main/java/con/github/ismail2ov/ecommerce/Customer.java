package con.github.ismail2ov.ecommerce;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {
    private final UUID customerId;
    private final String name;

    public Customer(UUID customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }
}

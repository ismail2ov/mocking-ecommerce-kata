package con.github.ismail2ov.ecommerce;

import java.util.HashMap;
import java.util.Map;

public class OperatorRateStub implements OperatorRate {
    Map<String, Integer> interaction = new HashMap<>();

    @Override
    public int getRate(String operator) {
        return interaction.getOrDefault("getRate", 0);
    }

    public void when(String methodName, int rate) {
        interaction.put(methodName, rate);
    }
}

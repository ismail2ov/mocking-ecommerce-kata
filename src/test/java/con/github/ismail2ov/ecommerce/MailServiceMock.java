package con.github.ismail2ov.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class MailServiceMock implements MailService {
    List<String> interaction = new ArrayList<>();

    @Override
    public void send(String mailAddress) {
        interaction.add("send");
    }

    public void verify(String methodName) throws Exception {
        for (String name : interaction) {
            if (name.equals(methodName)) {
                interaction.remove(name);
                return;
            }
        }
        throw new Exception();
    }

    public void verifyNoMoreInteraction() throws Exception {
        if (! interaction.isEmpty()) {
            throw new Exception();
        }
    }
}

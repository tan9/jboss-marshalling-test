import nl.captcha.Captcha;
import org.jboss.marshalling.*;
import org.jboss.marshalling.river.RiverMarshallerFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JbossMarshallingTest {

    @Test
    public void testCaptchaCanBeSerializedByJBossMarshalling() throws IOException {
        Captcha captcha = new Captcha.Builder(100, 20).build();

        // create a RiverMarshaller instance
        try (Marshaller marshaller = new RiverMarshallerFactory().createMarshaller(new MarshallingConfiguration())) {
            ByteOutput byteOutput = new SimpleByteOutput() {
                @Override
                public void write(int b) throws IOException {
                    System.out.println(b);
                }
            };
            marshaller.start(byteOutput);
            Map<String, Object> map = new HashMap<>();
            map.put("captcha", captcha);
            marshaller.writeObject(map);
        }

    }
}

package client;

import com.wildbit.java.postmark.Postmark;
import com.wildbit.java.postmark.client.ApiClient;
import org.junit.jupiter.api.Test;
import javax.ws.rs.core.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by bash on 11/13/17.
 */
public class ApiClientTests {

    MultivaluedHashMap headers = Postmark.DefaultHeaders.headers();
    ApiClient client = new ApiClient(Postmark.DEFAULTS.API_URL.value, headers);

    @Test
    void getDataHandler() {
        assertNotNull(client.getDataHandler());
    }

    @Test
    void getHttpClient() {
        assertNotNull(client.getHttpClient());
    }
}

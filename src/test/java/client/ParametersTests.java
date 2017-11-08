package client;

import com.wildbit.java.postmark.client.Parameters;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Created by bash on 11/6/17.
 */
public class ParametersTests {

    private String dateString = "2017-01-01";

    private Date sampleDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(dateString);
    }

    @Test
    void init() {
        assertSame(Parameters.init().getClass(),Parameters.class);
    }

    @Test
    void stringValue() {
        Parameters parameters = Parameters.init().build("fromAddress","igor@example.com");
        assertEquals(parameters.toString(),"?fromAddress=igor@example.com");
    }

    @Test
    void integerValue() {
        Parameters parameters = Parameters.init().build("count",1);
        assertEquals(parameters.toString(),"?count=1");
    }

    @Test
    void date() throws ParseException {
        String parameters = Parameters.init().build("fromDate", sampleDate(dateString)).toString();
        assertEquals(parameters,"?fromDate=" + dateString);
    }

    @Test
    void multipleParameters() throws ParseException {
        Parameters parameters = Parameters.init().build("fromDate",sampleDate(dateString)).build("count",1).build("offset",0);
        assertEquals(parameters.toString(),"?fromDate=" + dateString + "&offset=0&count=1");
    }
}
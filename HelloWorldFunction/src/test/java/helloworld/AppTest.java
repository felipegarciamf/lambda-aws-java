package helloworld;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import java.util.List;

public class AppTest {

    @Mock
    private S3Event s3Event;



    @Before
    public void setUp() throws Exception {
        s3Event = Mockito.mock(S3Event.class);

    }

/*
    @Test
    public void successfulResponse() {
        App app = new App();
        Mockito.when(s3Event.getRecords()).thenReturn((List<S3EventNotification.S3EventNotificationRecord>) new Any());
        String result = app.handleRequest(null, null);
        assertEquals("tudo ok", result);
    }

*/
}

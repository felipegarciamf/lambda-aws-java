package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<S3Event, String> {

    private static final Logger logger = LogManager.getLogger(App.class);

    @Override
    public String handleRequest(S3Event s3Event, Context context) {

        try {

        S3EventNotification.S3EventNotificationRecord s3EventNotificationRecord = s3Event.getRecords().get(0);
        System.out.println("cheguei aqui");

        String bucketName = s3EventNotificationRecord.getS3().getBucket().getName();
        String key = s3EventNotificationRecord.getS3().getObject().getKey();

        System.out.println("ESSA É A CHAVE: " + key);

        String resultado = S3Client.builder().region(Region.US_EAST_2).build().getObject(GetObjectRequest.builder().bucket(bucketName).key(key).build(), (resp, in) -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            return line;

        });
        System.out.println(resultado);


        } catch (Exception e) {
            logger.error("Ocorreu um erro na aplicação!", e);
        }


        return "TUDO FOI OK";
    }
}

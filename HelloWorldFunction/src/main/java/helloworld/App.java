package helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<S3Event, String> {


    @Override
    public String handleRequest(S3Event s3Event, Context context) {

        List<S3EventNotification.S3EventNotificationRecord> records = s3Event.getRecords();


        for (S3EventNotification.S3EventNotificationRecord s3EventNotificationRecord : records
             ) {

            String bucketName = s3EventNotificationRecord.getS3().getBucket().getName();

            String resultado = S3Client.builder().region(Region.US_EAST_2).build().getObject(GetObjectRequest.builder().bucket(bucketName).build(), (resp, in) -> {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                return line;

            });
            System.out.println(resultado);

        }
        return "TUDO FOI OK";
    }
}

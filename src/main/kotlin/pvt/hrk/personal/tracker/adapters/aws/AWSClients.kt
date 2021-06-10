package pvt.hrk.personal.tracker.adapters.aws

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.regions.Region
import java.net.URI

@Component
class AWSClients  (@Value("\${aws.url}")val url: String,@Value("\${aws.region:us-east-1}")val region: String){

        public fun DynamoDbClient(): DynamoDbClient {
            var builder = DynamoDbClient.builder().region(Region.of(region))
            if (!url.isNullOrBlank()) builder = builder.endpointOverride(URI.create(url))
            return builder.build()
        }
}
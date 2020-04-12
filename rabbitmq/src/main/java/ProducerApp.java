import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/*
producer
 */
public class ProducerApp {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        factory.setUsername("guest");
        factory.setPassword("guest");
        Date now = new Date();

        channel.queueDeclare("products_queue", false, false, false, null);
        String message = "product details " + now.getTime();
        channel.basicPublish("", "products_queue", null, message.getBytes());

        channel.close();
        connection.close();

        System.out.println("product is success to sending");


    }
}

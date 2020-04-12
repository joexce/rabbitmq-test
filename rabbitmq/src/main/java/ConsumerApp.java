import com.rabbitmq.client.*;
import lombok.Builder;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerApp {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        factory.setUsername("guest");
        factory.setPassword("guest");

        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(
                    String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException{
                String message = new String(body, "UTF-8");
                System.out.println("get message " + message);
            }
        };

        channel.basicConsume("products_queue", true, consumer);


    }
}

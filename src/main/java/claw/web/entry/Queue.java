package claw.web.entry;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Queue {
    private String QUEUE = "testqueue";
    private Channel channel;

    public void connect() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.9.16");
        Connection connection = factory.newConnection();
        channel = connection.createChannel();

        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456a@");

        channel.queueDeclare(QUEUE, false, false, false, null);
    }

    public void push(String message) throws IOException {
        channel.basicPublish("", QUEUE, null, message.getBytes());
    }
}

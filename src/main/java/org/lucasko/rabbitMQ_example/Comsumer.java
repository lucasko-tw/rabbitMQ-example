package org.lucasko.rabbitMQ_example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;

public class Comsumer {
	private static final String QUEUE_NAME = "my-queue";

	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException,
			IOException, TimeoutException, InterruptedException {
		System.out.println("Comsumer");
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri("amqp://guest:guest@localhost");
		factory.setConnectionTimeout(30000);

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();

		channel.queueDeclare("my-queue", true, false, false, null);

		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			String message = new String(delivery.getBody(), "UTF-8");
			System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
		});

	}
}

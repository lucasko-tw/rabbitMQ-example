package org.lucasko.rabbitMQ_example;


import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;


public class Publisher 
{
    public static void main( String[] args ) throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException, TimeoutException, InterruptedException
    {
        System.out.println( "Hello World!" );
        ConnectionFactory  factory = new ConnectionFactory();
        factory.setUri("amqp://guest:guest@localhost");
        factory.setConnectionTimeout(30000);

        Connection connection = factory.newConnection();
        
		Channel channel = connection.createChannel();
        
		channel.queueDeclare("my-queue", true, false, false, null);
        
        int count = 0;
        
        while(count < 5000 ) {
        	String message = "message number = " + count;
     
			channel.basicPublish("", "my-queue", null, message.getBytes() );
        	count ++ ;
        	System.out.println(message);
        	long a =  100L;
			Thread.sleep(a);
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}

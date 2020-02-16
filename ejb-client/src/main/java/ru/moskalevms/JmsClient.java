package ru.moskalevms;

import ru.moskalevms.persist.Product;

import javax.naming.Context;
import javax.naming.NamingException;

import java.io.IOException;
import java.math.BigDecimal;

import javax.jms.*;

import static ru.moskalevms.EjbClient.createInitialContext;

public class JmsClient {
    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        ConnectionFactory connectionFactory = (ConnectionFactory ) context.lookup("jms/RemoteConnectionFactory");

        Destination destination = (Destination) context.lookup("jms/queue/ProductQueue");

        JMSContext jmsContext = connectionFactory.createContext("admin", "admin");

       JMSProducer jmsProducer =  jmsContext.createProducer();

       ObjectMessage objectMessage = jmsContext.createObjectMessage( new Product(null, "JmsProduct", "JMS Product", new BigDecimal(11111)));
       jmsProducer.setProperty("action" , "create").send(destination, objectMessage);

    }
}

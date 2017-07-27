package com.zhou.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/27.
 */
@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception{

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class,args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageListener listener = context.getBean(MessageListener.class);

        producer.sendMessage("hello,World");

        listener.latch.await(10, TimeUnit.SECONDS);

        for (int i = 0; i < 5; i++) {
            producer.sendMessageToPartion("Hello To Partioned Topic!", i);
        }
        listener.partitionLatch.await(10, TimeUnit.SECONDS);

        /*
         * Sending message to 'filtered' topic. As per listener
         * configuration,  all messages with char sequence
         * 'World' will be discarded.
         */
        producer.sendMessageToFiltered("Hello Baeldung!");
        producer.sendMessageToFiltered("Hello World!");
        listener.filterLatch.await(10, TimeUnit.SECONDS);

        /*
         * Sending message to 'greeting' topic. This will send
         * and recieved a java object with the help of
         * greetingKafkaListenerContainerFactory.
         */
        producer.sendGreetingMessage(new Greeting("Greetings", "World!"));
        listener.greetingLatch.await(10, TimeUnit.SECONDS);

        context.close();
    }

    @Bean
    public MessageProducer messageProducer(){
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener(){
        return new MessageListener();
    }

    public static class MessageProducer{

        @Autowired
        private KafkaTemplate<String,Greeting> greetingKafkaTemplate;

        @Autowired
        private KafkaTemplate<String,String> kafkaTemplate;

        @Value(value = "${message.topic.name}")
        private String topicName;

        @Value(value = "${partitioned.topic.name}")
        private String partionedTopicName;

        @Value(value = "${filtered.topic.name}")
        private String filteredTopicName;

        @Value(value = "${greeting.topic.name}")
        private String greetingTopicName;

        public void sendMessage(String message) {
            kafkaTemplate.send(topicName, message);
        }

        public void sendMessageToPartion(String message, int partition) {
            kafkaTemplate.send(partionedTopicName, partition, message);
        }

        public void sendMessageToFiltered(String message) {
            kafkaTemplate.send(filteredTopicName, message);
        }

        public void sendGreetingMessage(Greeting greeting) {
            greetingKafkaTemplate.send(greetingTopicName, greeting);
        }
    }

    public static class MessageListener{

        private CountDownLatch latch = new CountDownLatch(3);

        private CountDownLatch partitionLatch = new CountDownLatch(2);

        private CountDownLatch filterLatch = new CountDownLatch(2);

        private CountDownLatch greetingLatch = new CountDownLatch(1);

        @KafkaListener(topics = "${message.topic.name}", group = "bar", containerFactory = "barKafkaListenerContainerFactory")
        public void listenGroupBar(String message) {
            System.out.println("Received Messasge in group 'bar': " + message);
            latch.countDown();
        }

        @KafkaListener(topics = "${message.topic.name}", containerFactory = "headersKafkaListenerContainerFactory")
        public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
            System.out.println("Received Messasge: " + message + " from partition: " + partition);
            latch.countDown();
        }

        @KafkaListener(topicPartitions = @TopicPartition(topic = "${partitioned.topic.name}", partitions = { "0", "3" }))
        public void listenToParition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
            System.out.println("Received Message: " + message + " from partition: " + partition);
            this.partitionLatch.countDown();
        }

        @KafkaListener(topics = "${filtered.topic.name}", containerFactory = "filterKafkaListenerContainerFactory")
        public void listenWithFilter(String message) {
            System.out.println("Recieved Message in filtered listener: " + message);
            this.filterLatch.countDown();
        }

        @KafkaListener(topics = "${greeting.topic.name}", containerFactory = "greetingKafkaListenerContainerFactory")
        public void greetingListener(Greeting greeting) {
            System.out.println("Recieved greeting message: " + greeting);
            this.greetingLatch.countDown();
        }

    }
}

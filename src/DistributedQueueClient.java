import entities.ConsumerGroup;
import entities.Message;
import entities.Producer;
import entities.Topic;

public class DistributedQueueClient {
    public static void main(String[] args) {
        Topic topic1  =new Topic("topic1", 4);
        Topic topic2  =new Topic("topic2", 4);

        Producer producer1 = new Producer(1);
        Producer producer2 = new Producer(2);

        ConsumerGroup consumerGroup1 = new ConsumerGroup(1,3);
        ConsumerGroup consumerGroup2 = new ConsumerGroup(2, 2);

        consumerGroup1.subscribe(topic1);
        consumerGroup2.subscribe(topic2);

        Message message1 = new Message(1,"Message1");
        Message message2 = new Message(2,"Message2");
        Message message3 = new Message(3,"Message3");
        Message message4 = new Message(4,"Message4");

        producer1.produce(message1, topic1);
        producer1.produce(message2, topic1);
        producer1.produce(message3,topic1);
        producer1.produce(message4,topic1);

        producer2.produce(message1, topic2);
        producer2.produce(message2, topic2);
        producer2.produce(message3,topic2);
        producer2.produce(message4,topic2);


    }
}

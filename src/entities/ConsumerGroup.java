package entities;

import java.util.ArrayList;
import java.util.List;

public class ConsumerGroup {
    private int id;
    private List<Consumer> consumers;
    private List<Message> consumerGroupQueue;
    private int numConsumers;

    public ConsumerGroup(int id, int numConsumers) {
        this.id = id;
        this.consumers = new ArrayList<>();
        this.consumerGroupQueue = new ArrayList<>();
        this.numConsumers = numConsumers;
        for(int i=0;i<numConsumers;i++)
            consumers.add(new Consumer(i,this));
    }

    public int getId() {
        return id;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public List<Message> getConsumerGroupQueue() {
        return consumerGroupQueue;
    }

    public int getNumConsumers() {
        return numConsumers;
    }

    public void addConsumer(Consumer consumer){
        consumers.add(consumer);
    }

    public synchronized void addMessage(Message message){
        consumerGroupQueue.add(message);
    }

    public void subscribe(Topic topic){
        int numConsumers = consumers.size();
        int numPartitions = topic.getNumPartitions();
        for (int i=0;i<numPartitions;i++)
            consumers.get(i%numConsumers).listen(topic.getPartitions().get(i));

    }

    @Override
    public String toString() {
        return "ConsumerGroup{" +
                "id=" + id +
                '}';
    }
}

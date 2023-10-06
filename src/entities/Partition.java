package entities;

import java.util.ArrayList;
import java.util.List;

public class Partition {
    private int id;
    private Topic topic;
    private List<PartitionConsumer> consumers;
    private List<Message> partitionQueue;

    public Partition(int id, Topic topic) {
        this.id = id;
        this.topic = topic;
        this.consumers = new ArrayList<>();
        this.partitionQueue = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public List<PartitionConsumer> getConsumers() {
        return consumers;
    }

    public List<Message> getPartitionQueue() {
        return partitionQueue;
    }

    public void addConsumer(PartitionConsumer consumer){
        consumers.add(consumer);
    }

    public synchronized void addMessage(Message message){
        partitionQueue.add(message);
    }

    private Runnable notifyConsumer(PartitionConsumer consumer){
        return () -> consumer.getSemaphore().release();
    }

    public void notifyConsumers(){
        for (PartitionConsumer consumer: consumers){
            Thread notifierThread = new Thread(notifyConsumer(consumer));
            notifierThread.start();
        }
    }

    @Override
    public String toString() {
        return "Partition{" +
                "id=" + id +
                ", topic=" + topic +
                '}';
    }
}

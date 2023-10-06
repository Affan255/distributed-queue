package entities;

import java.util.ArrayList;
import java.util.List;

public class Topic {

    private String name;
    private int numPartitions;
    private List<Partition> partitions;

    public Topic(String name, int numPartitions) {
        this.name = name;
        this.numPartitions = numPartitions;
        this.partitions = new ArrayList<>();
        for(int i=0;i<numPartitions;i++)
            partitions.add(new Partition(i, this));
    }

    public String getName() {
        return name;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public int getNumPartitions() {
        return numPartitions;
    }

    public void publish(Message message){
        int partitionId = message.getId()%numPartitions;
        partitions.get(partitionId).addMessage(message);
        Runnable task = () -> partitions.get(partitionId).notifyConsumers();
        Thread publisherThread = new Thread(task);
        publisherThread.start();
    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }
}

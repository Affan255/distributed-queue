package entities;

public class Consumer {
    private int id;
    private ConsumerGroup consumerGroup;

    public Consumer(int id, ConsumerGroup consumerGroup) {
        this.id = id;
        this.consumerGroup = consumerGroup;
    }

    public int getId() {
        return id;
    }

    public ConsumerGroup getConsumerGroup() {
        return consumerGroup;
    }

    private Runnable listenTask(PartitionConsumer partitionConsumer){

        Consumer consumer  =partitionConsumer.getConsumer();
        return  () -> {
            while (true){
                try {
                    partitionConsumer.getSemaphore().acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int offset = partitionConsumer.getOffset();
                Message message = partitionConsumer.getPartition().getPartitionQueue().get(offset);
                partitionConsumer.setOffset(offset+1);
                System.out.println(message + " consumed by " + consumer);
                consumerGroup.addMessage(message);
            }
        };
    }

    public void listen(Partition partition){
        PartitionConsumer partitionConsumer = new PartitionConsumer(partition,this);
        partition.addConsumer(partitionConsumer);
        Thread listenerThread = new Thread(listenTask(partitionConsumer));
        listenerThread.start();
        System.out.println(this + " subscribed to " + partition);
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", consumerGroup=" + consumerGroup +
                '}';
    }
}

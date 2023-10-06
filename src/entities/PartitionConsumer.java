package entities;

import java.util.concurrent.Semaphore;

public class PartitionConsumer {
    private Partition partition;
    private Consumer consumer;
    private Semaphore semaphore;
    private int offset;

    public PartitionConsumer(Partition partition, Consumer consumer) {
        this.partition = partition;
        this.consumer = consumer;
        this.semaphore = new Semaphore(0);
        this.offset=0;
    }

    public Partition getPartition() {
        return partition;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

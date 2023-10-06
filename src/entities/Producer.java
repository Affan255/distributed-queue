package entities;

import java.util.List;

public class Producer {
    private int id;

    public Producer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void produce(Message message, Topic topic){
        topic.publish(message);
    }
}

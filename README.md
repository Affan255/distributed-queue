# distributed-queue

## about
This is a multi-threaded queue trying to imitate the real world distributed queue using multiple threads as producers and consumers. 
The producers can produce messages to multiple topics. Each topic is divided into multiple partitions. There are consumer groups where each consumer
group can have multiple consumers.

## Flow
- The consumer groups can subscribe to multiple topics. Each consumer within a group will listen to a particular subset of partitions only.
  This implies if number of partitions are equal to number of consumers within the group then each consumer will listen to only one partition.
  In case if consumers are more than partitions than extra consumers will be idle.

- Multiple producers can produce messages to multiple topics. The message is assigned a partition of that topic based on the hash of message key.
- Then the topic partition notifies the respective consumers of the message.
- The consumers maintain the offset to which they have read from their subscribed partition and consume the message.

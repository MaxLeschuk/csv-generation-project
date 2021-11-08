package csv_generation.brokers;

public interface MessageBroker {

    /**
     * Sends message to queue.
     */
    void sendMessage(Object message, String queue);
}

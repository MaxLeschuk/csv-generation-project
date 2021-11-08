package user_service.brokers;

public interface MessageBroker {

    /**
     * Sends message to queue.
     */
    void sendMessage(Object message, String queue);
}

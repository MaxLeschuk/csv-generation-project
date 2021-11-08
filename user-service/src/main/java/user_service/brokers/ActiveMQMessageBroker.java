package user_service.brokers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import user_service.services.CsvManagementService;

import java.util.logging.Logger;

@Component
public class ActiveMQMessageBroker implements MessageBroker {


    private Logger logger = Logger.getLogger(ActiveMQMessageBroker.class.getName());

    @Autowired
    private CsvManagementService csvManagementService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Object message, String queue) {
        jmsTemplate.convertAndSend(queue, message);
        logger.info("Sent for generation: " + message);
    }

    @JmsListener(destination = "saved_paths")
    public void processMessages(String path) {
        logger.info("Processing --> " + path);
        csvManagementService.saveFileData(path);
    }
}

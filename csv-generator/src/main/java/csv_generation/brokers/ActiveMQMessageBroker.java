package csv_generation.brokers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Component
public class ActiveMQMessageBroker implements MessageBroker {


    private Logger logger = Logger.getLogger(ActiveMQMessageBroker.class.getName());


    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Object message, String queue) {
        jmsTemplate.convertAndSend(queue, message);
        logger.info("Sent for generation: " + message);
    }

    @JmsListener(destination = "csv_generate")
    public void processMessages(HttpServletRequest request, HttpServletResponse res, String path) {

        logger.info("Processing --> " + path);
    }
}

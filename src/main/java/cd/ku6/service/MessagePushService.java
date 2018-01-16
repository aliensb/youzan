package cd.ku6.service;

import com.youzan.open.sdk.util.json.JsonUtils;
import lombok.Setter;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/16.
 */
@Service
public class  MessagePushService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    @Qualifier("myqueue")
    private ActiveMQQueue activeMQQueue;

    public boolean sendMsg(Destination distnation, final String msg) {
        jmsTemplate.send(distnation, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
        return true;
    }

    public boolean sendNotify(Destination destination, final Map<String, Object> params) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage message=session.createMapMessage();
                message.setString("params",JsonUtils.toJson(params));
                return message;
            }
        });
        return true;
    }
}

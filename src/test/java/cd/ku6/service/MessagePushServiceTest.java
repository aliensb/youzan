package cd.ku6.service;

import cd.ku6.YouzanApplication;
import org.hibernate.validator.internal.constraintvalidators.bv.past.PastValidatorForReadableInstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/1/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(YouzanApplication.class)
public class MessagePushServiceTest {
    @Autowired
    private  MessagePushService messagePushService;
    @Autowired
    @Qualifier("myqueue")
    private Destination destination;
    @Test
    public void sendMsg() throws Exception {
        messagePushService.sendMsg(destination,"tttttt");
    }

    @Test
    public void sendNotify() throws Exception {
    }

}
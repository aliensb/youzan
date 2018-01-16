package cd.ku6.config;


import lombok.Getter;
import lombok.Setter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by Administrator on 2018/1/16.
 */

@Setter
@Getter
@Configuration
@PropertySource("classpath:application.yml")
public class ActiveMQConfig {
//    @Value("${activemq.url}")
//    private String brokerUrl;
//    @Value("${activemq.maxConnections}")
//    private String maxConnections;
    @Value("${activemq.queueName}")
    private String queueName;

//    @Bean
//    public ActiveMQConnectionFactory activeMQConnectionFactory() {
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
//        factory.setBrokerURL(brokerUrl);
//        return factory;
//    }
//
//    @Bean
//    public PooledConnectionFactory pooledConnectionFactory() {
//        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
//        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory());
//        pooledConnectionFactory.setMaxConnections(Integer.parseInt(maxConnections));
//        return pooledConnectionFactory;
//    }
//
//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setTargetConnectionFactory(pooledConnectionFactory());
//        cachingConnectionFactory.setSessionCacheSize(Integer.parseInt(maxConnections));
//        return cachingConnectionFactory;
//    }
//
//    @Bean(name="mqJmsTemplate")
//    public JmsTemplate jmsTemplate() {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(cachingConnectionFactory());
//        return jmsTemplate;
//    }
    @Bean(name="myqueue")
    public ActiveMQQueue activeMQQueue(){
        return new ActiveMQQueue(queueName);
    }
}

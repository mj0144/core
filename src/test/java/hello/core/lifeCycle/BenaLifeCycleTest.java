package hello.core.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BenaLifeCycleTest {


    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig{
        //@Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.com");
            return networkClient;
        }
    }

}

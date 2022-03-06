package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//Junit5부터는 pulbic 안써도 됨.
public class AplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        // 정의된 빈이름
        String[] beanDefindinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefindinitionName : beanDefindinitionNames) {
            Object bean = ac.getBean(beanDefindinitionName);
            System.out.println("beanDefindinitionName = " + beanDefindinitionName + "bean  :" + bean);
        }
    }

    @Test
    @DisplayName("모든 빈 출력하기")
    void findApplicationBean(){
        // 정의된 빈이름
        String[] beanDefindinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefindinitionName : beanDefindinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefindinitionName);

            // 내가 등록한 bean들 또는 외부 라이브러리들.
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefindinitionName);
                System.out.println("bean = " + bean);
            }

        }
    }

}

package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate(){
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));


    }

    @Test
    @DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){

        DiscountPolicy rateDiscountPolicy = ac.getBean(DiscountPolicy.class);

        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);


    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanByParentTypeBean() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllbeanByParentType(){
        Map<String, DiscountPolicy> beansType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansType.size()).isEqualTo(2);
        for(String key : beansType.keySet()){
            System.out.println("key = " + key );
        }
    }



    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }


}

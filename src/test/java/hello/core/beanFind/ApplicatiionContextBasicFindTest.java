package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicatiionContextBasicFindTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isEqualTo(MemberService.class);
    }

    @Test
    @DisplayName("빈 이름없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }



    @Test
    @DisplayName("빈 구체타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    // NoSuchBeanDefinitionException 오류 발생
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //MemberService xxxx = ac.getBean("xxxx", MemberService.class);
        // 오른쪽 로직을 실행하면 왼쪽 예외가 터져야 성공함
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }
}

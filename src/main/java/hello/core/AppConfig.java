package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 앱 전체를 설정하고 구성하는 클래스
 */
// 설정정보(구성정보) 어노테이션
// 이것도 bean으로 등록됨.
@Configuration
@ComponentScan
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()



    //MemoryMemberRepository라는 역할이 잘 안보임
    //그리고 new MemoryMemberRepository()가 중복이였음.
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }

    // 역할
    // 스프링컨테이너 등록됨.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    //구현
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }



}

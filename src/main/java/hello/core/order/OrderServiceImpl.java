package hello.core.order;

import hello.core.annotation.MaindiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy ;


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MaindiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByID(memberId);
        // 할인에 대해서는 discountPolicy가 알아서 다 할거임. 여기는 할인에 대한 정보를 알지 못해도 됨.
        // 즉, 할인 관련된 변경이 들어와도 discountPolicy만 수정하면 됨.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
// dip : 추상화 의존
// ocp : 확장엔 열려있고, 변경에 유연.
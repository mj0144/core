package hello.core.order;

public interface OrderService {
    // 주문결과 반환
    Order createOrder(Long memberId, String itemName, int itemPrice);
}

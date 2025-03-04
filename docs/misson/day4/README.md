# 미션 - Day4

## SOLID에 대하여 자기만의 언어로 정리

### SRP (Single Responsibility Principle) : 단일 책임 원칙

**"객체는 한 가지 역할(책임)만 가져야 한다."**

[SRP 위반]
```java
public class Order {
    
    private final int totalPrice;
    
    public void printTotalPrice() {
        System.out.printf("주문 총 금액은 %d 입니다.", totalPrice); // SRP 위반
    }
    
    public void saveOrder() {
        orderRepository.save(this); // SRP 위반
    }
}
```

[SRP 적용]
```java
public class Order {
    
    private final int totalPrice;
    
    public int getTotalPrice() {
        return totalPrice;
    }
}

// Order 클래스에서 총 금액을 출력하는 메서드를 클래스로 분리
public class OrderOutputHandler {
    
    public void printTotalPrice(int totalPrice) {
        System.out.printf("주문 총 금액은 %d 입니다.", totalPrice); 
    }
}

// Order 클래스에서 주문정보를 저장하는 메서드를 클래스로 분리
public class OrderRepository {
    
    public void save(Order order) {
        orderStore.save(order);
    }
}
```

### OCP (Open-Closed Principle) : 개방 폐쇄 원칙

**"확장에는 열려있고, 변경에는 닫혀 있어야 한다."**

[OCP 위반]
```java
public class Order {
    
    private final int totalPrice;
    
    public void pay(String paymentMethod) {
        if ("CARD".equals(paymentMethod)) {
            System.out.printf("총 %d원을 카드로 결제 합니다.", totalPrice);
        } else if ("NAVERPAY") {
            System.out.printf("총 %d원을 네이버 페이로 결제 합니다.", totalPrice);
        } else if ("TOSSPAY") {
            System.out.printf("총 %d원을 토스 페이로 결제 합니다.", totalPrice);
        }
    }
}
```

[OCP 적용]

```java
public class Order {

    private final int totalPrice;
    private final PaymentStrategy strategy;

    public void pay() {
        strategy.pay(totalPrice);
    }
}

public interface PaymentStrategy {

    void pay(int totalPrice);
}

public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(int totalPrice) {
        System.out.printf("총 %d원을 카드로 결제 합니다.", totalPrice);
    }
}

public class NaverPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(int totalPrice) {
        System.out.printf("총 %d원을 네이버 페이로 결제 합니다.", totalPrice);
    }
}

public class TossPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(int totalPrice) {
        System.out.printf("총 %d원을 토스 페이로 결제 합니다.", totalPrice);
    }
}
```

### LSP (Liskov Substitution Principle) : 리스코프 치환 원칙

**"자식 클래스는 부모 클래스를 대체 할 수 있어야 한다."**

아래의 LSP를 위반하는 예제는 DigitalOrder 구현체의 ship() 메서드를 사용 시, 의도하지 않은 예외가 발생할 수 있다. 
즉, 부모에서 기획한 의도를 알고 구현한 원칙을 따라야 한다.  

[LSP 위반]

```java
public interface Order {

    void ship();
}

public class PhysicalOrder implements Order {

    @Override
    public void ship() {
        System.out.println("물리적 상품을 배송합니다.");
    }
}

public class DigitalOrder implements Order {

    @Override
    public void ship() {
        throw new UnsupportedOperationException("디지털 상품은 배송을 지원하지 않습니다.");
    }
}
```

[LSP 적용]

인터페이스로 분리를하여 LSP 적용을 진행한다.

```java
public interface Order {
}

public interface Shippable {

    void ship();
}

public class PhysicalOrder implements Order, Shippable {

    @Override
    public void ship() {
        System.out.println("물리적 상품을 배송합니다.");
    }
}

public class DigitalOrder implements Order {
    
}
```

### ISP (Interface Segregation Principle) : 인터페이스 분리 원칙

**"인터페이스를 클라이언트 입장에서 분리해야 한다."**

[ISP 위반]

```java
public interface Order {

    void create(Product product, int quantity); // 주문 생성

    void pay(); // 결제 

    void ship(); // 배송

    void refund(); // 환불
}

public class OfflineOrder implements Order {

    // ...(중략)...

    @Override
    public void pay() {
        throw new UnsupportedOperationException("오프라인 상품은 매장에서 직접 결제해야 합니다.");
    }

    @Override
    public void ship() {
        throw new UnsupportedOperationException("오프라인 상품은 배송을 지원하지 않습니다.");
    }
}

public class CustomOrder implements Order {

    // ...(중략)...

    @Override
    public void refund() {
        throw new UnsupportedOperationException("맞춤 제작 주문은 환불이 불가능 합니다.");
    }
}
```

[ISP 적용]

```java

public interface Order {

    void create(Product product, int quantity); // 주문 생성
}

public interface Payable {

    void pay(); // 결제
}

public interface Shippable {

    void ship(); // 배송
}

public interface Refundable {

    void refund(); // 환불
}

public class OfflineOrder implements Order, Refundable {

    // ...(중략)...

    @Override
    public void refund() {
        System.out.println("오프라인 상품 환불 진행");
    }
}

public class CustomOrder implements Order, Payable, Shippable {

    // ...(중략)...

    @Override
    public void pay() {
        System.out.println("맞춤 주문 결제 진행");
    }

    @Override
    public void ship() {
        System.out.println("맞춤 주문 배송 진행");
    }
}
```



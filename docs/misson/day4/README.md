# 미션 - Day4

<!-- TOC -->
* [미션 - Day4](#미션---day4)
  * [SOLID에 대하여 자기만의 언어로 정리](#solid에-대하여-자기만의-언어로-정리)
    * [SRP (Single Responsibility Principle) : 단일 책임 원칙](#srp-single-responsibility-principle--단일-책임-원칙)
    * [OCP (Open-Closed Principle) : 개방 폐쇄 원칙](#ocp-open-closed-principle--개방-폐쇄-원칙)
    * [LSP (Liskov Substitution Principle) : 리스코프 치환 원칙](#lsp-liskov-substitution-principle--리스코프-치환-원칙)
    * [ISP (Interface Segregation Principle) : 인터페이스 분리 원칙](#isp-interface-segregation-principle--인터페이스-분리-원칙)
    * [DIP (Dependency Inversion Principle) : 의존성 역전 원칙](#dip-dependency-inversion-principle--의존성-역전-원칙)
<!-- TOC -->

## SOLID에 대하여 자기만의 언어로 정리

### SRP (Single Responsibility Principle) : 단일 책임 원칙

**"객체는 한 가지 역할(책임)만 가져야 한다."**

**[SRP 위반]**

+ Order 클래스 안에서 총 금액 출력하는 메서드와 DB 저장하는 메서드는 SRP 원칙을 위반한 메서드이다.

```java
public class Order {
    
    private final int totalPrice;
    
    public void printTotalPrice() {
        System.out.printf("주문 총 금액은 %d 입니다.", totalPrice); // SRP 위반 🚨 
    }
    
    public void saveOrder() {
        orderRepository.save(this); // SRP 위반 🚨
    }
}
```

**[SRP 적용]**

+ SRP 원칙에 벗어나는 메서드를 각각 출력하는 클래스, DB 저장하는 클래스로 분리 시켜 SRP 원칙을 준수하고 있다.

```java
public class Order {
    
    private final int totalPrice;
    
    public int getTotalPrice() {
        return totalPrice;
    }
}

// Order 클래스에서 총 금액을 출력하는 메서드를 클래스로 분리
public class OrderOutputHandler { 
    
    public void printTotalPrice(int totalPrice) { // SRP 적용 ✅
        System.out.printf("주문 총 금액은 %d 입니다.", totalPrice); 
    }
}

// Order 클래스에서 주문정보를 저장하는 메서드를 클래스로 분리
public class OrderRepository { 
    
    public void save(Order order) { // SRP 적용 ✅
        orderStore.save(order);
    }
}
```

### OCP (Open-Closed Principle) : 개방 폐쇄 원칙

**"확장에는 열려있고, 변경에는 닫혀 있어야 한다."**

**[OCP 위반]**

+ 아래의 예제는 결제 방식이 추가 및 삭제가 일어날 때마다 변경이 되어야 하기 때문에 OCP 원칙을 위반하고 있다.

```java
public class Order {
    
    private final int totalPrice;
    
    public void pay(String paymentMethod) {
        if ("CARD".equals(paymentMethod)) { // OCP 위반 🚨
            System.out.printf("총 %d원을 카드로 결제 합니다.", totalPrice);
        } else if ("NAVERPAY") { // OCP 위반 🚨
            System.out.printf("총 %d원을 네이버 페이로 결제 합니다.", totalPrice);
        } else if ("TOSSPAY") { // OCP 위반 🚨
            System.out.printf("총 %d원을 토스 페이로 결제 합니다.", totalPrice);
        }
    }
}
```

**[OCP 적용]**

+ 결제 방식을 인터페이스로 추상화함으로써 각 결제 방식의 구현체를 만든다. 결제 방식을 추가가 일어나면 구현체만 추가하면 된다.

```java
public class Order {

    private final int totalPrice;
    private final PaymentStrategy strategy;

    public void pay() {
        strategy.pay(totalPrice); // OCP 적용 ✅
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

> 부모 클래스를 사용하는 위치에 자식 클래스를 대신 사용하였을 때 의도대로 작동해야함!

**[LSP 위반]**

+ DigitalOrder 구현체의 ship() 메서드를 사용 시, 부모 클래스가 의도하지 않은 예외가 발생할 수 있다.

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
        throw new UnsupportedOperationException("디지털 상품은 배송을 지원하지 않습니다."); // LSP 위반 🚨
    }
}
```

**[LSP 적용]**

+ 배송이 가능한 인터페이스를 추상화 하여 LSP 원칙을 적용한다. 

```java
public interface Order {
}

public interface Shippable { // LSP 적용 ✅

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

**[ISP 위반]**

+ OfflineOrder와 CustomOrder는 각각 배송 기능과 환불 기능이 필요 없는데 인터페이스 때문에 억지로 구현하면서 ISP 원칙을 무시하고 있다. 

```java
public interface Order {

    void create(Product product, int quantity); // 주문 생성

    void ship(); // 배송

    void refund(); // 환불
}

public class OfflineOrder implements Order {

    // ...(중략)...
    

    @Override
    public void ship() {
        throw new UnsupportedOperationException("오프라인 상품은 배송을 지원하지 않습니다."); // ISP 위반 🚨
    }
}

public class CustomOrder implements Order {

    // ...(중략)...

    @Override
    public void refund() {
        throw new UnsupportedOperationException("맞춤 제작 주문은 환불이 불가능 합니다."); // ISP 위반 🚨`
    }
}
```

**[ISP 적용]**

+ 클라이언트 용도에 맞는 인터페이스 만을 제공해서 ISP 원칙을 적용한다.

```java

public interface Order {

    void create(Product product, int quantity); // 주문 생성
}

public interface Shippable {

    void ship(); // 배송
}

public interface Refundable {

    void refund(); // 환불
}

public class OfflineOrder implements Order, Refundable { // ISP 적용 ✅

    // ...(중략)...

    @Override
    public void refund() {
        System.out.println("오프라인 상품 환불 진행");
    }
}

public class CustomOrder implements Order, Shippable { // ISP 적용 ✅

    // ...(중략)...

    @Override
    public void ship() {
        System.out.println("맞춤 주문 배송 진행");
    }
}
```

### DIP (Dependency Inversion Principle) : 의존성 역전 원칙

**"구현 클래스에 의존하지 말고 추상화에 의존해라"**

**[DIP 위반]**

+ 인스턴스 생성 시, 구현 클래스에 의존함으로써 DIP 원칙을 위반하고 있다.

```java
public class Main {

    public static void main(String[] args) {
        CustomOrder order = new CustomOrder(); // DIP 위반 🚨`
        order.pay();
    }
}
```

**[DIP 적용]**

+ 인스턴스 생성 시, 구현 클래스가 아닌 추상화에 의존하도록 적용하였다. 

```java
public class Main {

    public static void main(String[] args) {
        Order order = new CustomOrder(); // DIP 적용 ✅ 
        order.pay();
    }
}
```
# 발자국 - Week3

## 💡 자기만의 언어로 키워드 정리하기

### 섹션 6. Spring & JPA 기반 테스트 

**Layered Architecture**
+ 레이어드 아키텍처의 단점 : 기술에 대한 강결합이 심하다는 단점이 존재 

**Hexagonal Architecture**
+ 도메인 모델은 외부의 것들을 아예 모른다. 
+ 도메인 모델 중심 (멀티 모듈 및 시스템이 커진다면..)

**단위테스트 vs. 통합테스트**
+ 단위테스트 만으로는 커버하기 어려운 영역이 존재 (여러 모듈 및 여러 객체가 협력하기 때문에)
+ 통합테스트란? 
  + 여러 모듈이 협력하는 기능을 통합적으로 검증하는 테스트 
  + 단위 테스트만으로는 기능 전체의 신뢰성을 보장할 수 없다.

**IoC, DI, AOP**

**ORM, 패러다임의 불일치, Hibernate**

**Spring Data JPA**

**QueryDSL**

**@SpringBootTest vs @DataJpaTest**
+ @DataJpaTest는 @SpringBootTest보다 가볍다. 
+ @DataJpaTest보다는 @SpringBootTest를 더 선호 
+ @DataJpaTest는 @Transactional이 있어 롤백이 된다.
+ @SpringBootTest는 클렌징을 해주어야 한다.

**@SpringBootTest vs @WebMvcTest**
+ @SpringBootTest는 E2E 테스트, 즉 통합테스트 시 사용하는 어노테이션이다. 
+ @WebMvcTest는 Presentation Layer에 대한 단독 테스트시 사용하는 어노테이션이다. 
  + 다른 레이어들은 mocking을 통해 동작을 제어한다.

**@Transactional(readOnly = true)**
+ 테스트에서 사용 시, 롤백 되는 것에 유의 해야 한다. 
+ 트랜잭션 경계 설정을 해야한다.
+ 엔드포인트를 잘 설계해야 한다.

**Optimistic Lock, Pessimistic Lock**
+ 낙관적 락 : 데이터 충돌이 자주 발생하지 않을 것이라 낙관적으로 가정하고, 트랜잭션을 진행하는 방식
  + 데이터를 읽을 때는 락을 걸지 않고, 데이터를 업데이트 시 버전 비교하여 충돌 여부 판단
  + 성능 저하를 최소화, 동시성을 높이는 데 유리 
+ 비관적 락 : 데이터 충돌이 자주 발생할 것이라 비관적으로 가정하고, 트랜잭션이 데이터를 사용할 때 미리 잠금을 거는 방식
  + 데이터 일관성을 유지하는 데 초점
  + 트랜잭션이 완료될 때까지 다른 트랜잭션이 데이터를 수정할 수 없음 
  + 데드락 발생 가능

**CQRS**
+ 명령 조회 책임 분리 : Command Query Responsibility Segregation
+ 읽기(조회)와 쓰기(명령)의 책임을 분리하는 소프트웨어 아키텍처 패턴
+ 장점
  + 성능 최적화 
  + 확장성 증가 
  + 데이터 모델 최적화
  + 비지니스 로직의 명확한 분리
+ 단점
  + 복잡성 증가 
  + 데이터 동기화 문제 
  + 트랜잭션 관리 어려움

**@RestControllerAdvice, @ExceptionHandler**
+ @RestControllerAdvice : ControllerAdvice의 기능을 하는데 JSON으로 응답을 해주는 Advice
+ 커스텀 예외를 던지고 @RestControllerAdvice에서 @ExceptionHandler에서 예외를 처리할 수 있다.

**Spring bean validation**
+ @NotNull, @NotEmpty, @NotBlank
+ 도메인 요구사항에서 나오는 validation과 책임 분리해야한다.
+ Controller 단에서는 최소한의 validation을 통한 검증이 이루어져야 한다.

**@WebMvcTest**

**ObjectMapper**
+ Jackson 라이브러리에서 제공하는 클래스로, Java 객체와 JSON 간의 변환을 담당하는 역할
+ 직렬화, 역직렬화를 수행

**Mock, Mockito, @MockBean**
+ Mock : 실제 객체 없이 동작을 모방하여 단위 테스트를 수행하는 가짜 객체
+ Mockito : Java에서 Mock 객체를 쉽게 생성하고 관리할 수 있는 라이브러리
+ @MockBean : Spring 컨텍스트에 Mock 객체를 등록하여 실제 빈을 대체
  + @Mock : 순수한 자바에서 Spring 컨텍스트가 필요하지 않을 때 사용 
  + @MockBean : Spring 컨텍스트에서 특정 빈을 Mocking 하고 싶을 때 사용

![img.png](img.png)

![img_1.png](img_1.png)

![img_2.png](img_2.png)
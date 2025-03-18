# 미션 - Day18

## @Mock, @MockBean, @Spy, @SpyBean, @InjectMocks의 차이

### @Mock vs @MockBean

**@Mock**  

+ 순수한 Mockito 객체에 사용한다.
+ @InjectMocks나 수동으로 주입 받는다. 
+ Spring Context와 무관하다. 

```java
@ExtendWith(MockitoExtension.class) // ✅ Spring Context 없이 Mockito 사용
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // ✅ 직접 Mock 객체 생성

    @InjectMocks
    private ProductService productService; // ✅ @Mock 어노테이션이 있는 객체 자동 주입

    @Test
    void testFindAllProducts() {
        // given
        when(productRepository.findAll()).thenReturn(List.of(new Product("001", "Coffee")));

        // when
        List<Product> products = productService.getAllProducts();

        // then
        assertThat(products).hasSize(1);
    }
}
```

**@MockBean**

+ 스프링 빈을 Mocking할 때 사용
+ @Autowired를 통해 자동 주입 받는다.
+ Spring Context에서 관리한다.

💡 MockBean이 있을 경우 서버가 새로 뜨기 때문에 따로 관리하는 것을 권장한다.

```java
@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository; // ✅ Repository를 Mocking -> 따로 빼서 Support 클래스로 관리하는 것을 권장
    
    @Autowired
    private ProductService productService; 

    @Test
    void testFindAllProducts() {
        // given
        when(productRepository.findAll()).thenReturn(List.of(new Product("001", "Coffee")));
        
        // when
        List<Product> products = productService.getAllProducts();
        
        // then
        assertThat(products).hasSize(1);
    }
}
```

### @Spy vs @SpyBean

**@Spy**

+ @Mock은 객체 전체를 Mocking 했다면, @Spy는 객체의 일부 동작만 Mocking 한다.
+ @Mock과 마찬가지로 순수 Mockito 객체를 사용하며, Spring Context와 무관하다.

💡 실제 객체를 활용하기 때문에 @Spy는 when을 쓰면 안된다. doReturn을 사용하여 특정 메서드만 stubbing 된다.

```java
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Spy
    private ProductRepository productRepository = new ProductRepository(); // ✅ Spy 객체 생성

    @InjectMocks
    private ProductService productService;

    @Test
    void testFindAllProducts() {
        // given
        doReturn(List.of(new Product("001", "Coffee"))).when(productRepository).findAll(); // ✅ productRepository.findAll()만 Mocking한다.
        
        // when
        List<Product> products = productService.getAllProducts();
        
        // then
        assertThat(products).hasSize(1);
    }
}
```

**@SpyBean**

+ @Mock과 @MockBean의 관계처럼 @SpyBean도 @Spy를 Spring Context에서 관리하는 어노테이션이다. 

```java
@SpringBootTest
class ProductServiceTest {

    @SpyBean
    private ProductRepository productRepository = new ProductRepository(); // ✅ Repository를 SpyBean으로 변경

    @Autowired
    private ProductService productService;

    @Test
    void testFindAllProducts() {
        // given
        doReturn(List.of(new Product("001", "Coffee"))).when(productRepository).findAll(); // ✅ productRepository.findAll()만 Mocking한다.
        
        // when
        List<Product> products = productService.getAllProducts();
        
        // then
        assertThat(products).hasSize(1);
    }
}
```

### @InjectMocks

+ 순수한 Mockito 객체(@Mock, @Spy)를 자동으로 주입할 때 사용 
+ Spring Context에서는 @Autowired를 통해 @MockBean, @SpyBean 객체를 자동 주입 해줬다면, 순수한 Mock을 사용할 때는 @InjectMocks으로 주입을 해주어야 한다.

### 순수한 Mock vs 스프링 컨텍스트 

**Mock 사용 대상**

|              | 순수한 Mock | 스프링 컨텍스트 |
|--------------|----------|----------|
| @Mock        | ✅        | ❌        |
| @MockBean    | ❌        | ✅        |
| @Spy         | ✅        | ❌        |
| @SpyBean     | ❌        | ✅        |
| @InjectMocks | ✅        | ❌        |

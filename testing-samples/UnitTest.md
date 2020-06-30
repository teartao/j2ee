# 单元测试调研

## 目标

研究一下testNG 与 PowerMock，Mockito节后给我一个调研方案，研究逻辑单元测试的用法，一般规范，覆盖范围等



## 前言

关于单元测试网上教程很多，各工具也均有官网，学习过程中尽量以官网为参考。本文主要针对项目中的测试场景做针对分析，记录单元测试用法、规范、覆盖范围等。下面针对目前springboot项目的主要场景进行列举，并概述使用方法。



## 主要场景

- 基本运算逻辑接口：一般有私有方法，处理运算逻辑，传入不依赖环境的参数变量，运算并返回期望结果
- 普通service接口：依赖系统bean对象
- HTTP接口：主要指controller类型接口，返回http请求响应
- I/O接口：下载、读写文件之类
- 组件接口：依赖数据库 redis mq 等第三方程序的接口
- 运行时接口：依赖context启动的接口，如request/response等对象

其中后三种`I/O接口` 、`组件接口`、`运行时接口`和前面三种异曲同工，本质上还是在运行测试之前准备好连接信息、参数、期望结果等，以达到对比运行结果的目的。



## 单元测试用法说明

**Junit基本用法**

https://github.com/junit-team/junit4/wiki



### 基本运算逻辑接口

这种接口的测试相对简单（由于不依赖环境，仅有运算逻辑，故可以用main函数来替代），传入满足条件的参数，通过调用方法获得运算结果，并将运算结果和期望结果进行比较即可。

该场景下主要判断程序是否执行、是否抛出异常、是否超时、是否获得期望结果。

下面是简单样例

```java
//最基本的测试，判断方法是否执行
@Test
public void runTest() {
    //someMethod()
}

//判断方法是否抛出指定异常，抛出指定异常则成功，否则失败
@Test(expectedExceptions = {IllegalArgumentException.class, NullPointerException.class})
public void throwExceptions() {
    throw new IllegalArgumentException("参数非法");
}

//判断程序运行是否超时 4000<5000 测试运行成功，否则测试运行失败
@Test(timeOut = 5000)
public void testThisShouldPass() throws InterruptedException {
    Thread.sleep(4000);
}

//判断被测单元是否返回期望结果,此处如果是调用接口，则可以方法中初始化service对象，
//再通过@BeforeTest在测试方法之前进行前置调用，以达到初始化service的目的
//这种虽然依赖service，但是被测逻辑单元相对独立，可直接调用
private SomeService someService;

//testng注解 testng扩展注解见文末引用
@BeforeTest
void setupService() {
    someService = new SomeServiceImpl();
}

//判断方法是否返回期望值
@Test
public void testSomeService() {
    Map<String, Object> expect = new HashMap<>();
    expect.put("111", "this is testData");

    Map<String, Object> result = someService.getSomeData("111");
    Assert.assertEquals(expect, result);
}
```



### 普通service接口

该类型接口需要依赖spring容器启动，在springboot配置正确的情况下，可以启动项目并自动运行测试单元。最终显示测试单元的执行结果。


```java
@RunWith(SpringRunner.class)//通过SpringRunner启动单元测试
@SpringBootTest//(classes = TestingApplication.class)//引入主程序，可以手动指定class
//@ContextConfiguration(locations = { "classpath:spring-test-config.xml" }) //标记spring的配置文件，用于测试xml式spring项目
public class SomeServiceTest {
    @Autowired
    private SomeService someService;

    @Test
    public void testSomeService(){
        Map<String,Object> expect = new HashMap<>();
        expect.put("111","this is testData");

        Map<String,Object>  result = someService.getSomeData("111");
        Assert.assertEquals(expect,result);
    }
}
```

> 注：springboot项目中需要添加 `spring-boot-starter-test`maven依赖，通过`@SpringBootTest`运行主程序

通过Assert断言来判断接口调用结果和期望值是否一致来验证测试是否通过



### HTTP接口

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testRestUrl() throws Exception {
		//本地应用提供了一个rest风格的http接口，
        //可以通过status()  content() 等方法设置期望结果，对请求返回结果进行对比校验
        //符合期望则测试通过
        MvcResult result = mockMvc.perform(get("http://localhost:8900/someService").content("填写参数"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"msg\":\"请求成功\",\"code\":0,\"data\":{\"xx\":\"this is testData\"}}"))
                .andReturn();
    }
}
```





## Mockito用法说明

> (有点冗长。。需要提炼文档重点)

### 设置&判断对象属性/行为

mockito主要通过mock(obj)方法，创建模拟对象，并设置对象行为和属性，对被测功能进行对比和验证。

常见语法格式`when().then()`，`doXX().when(mockedList).xx()`，`verify(someObj).doSomething()`

如：

```java
SomeObj obj = mock(SomeObj.class);

// 当后续调用obj.doSomeThing()时，将返回someResult作为模拟结果
when(obj.doSomeThing()).thenXX(someResult);

//设置对象多次调用返回不同结果，第一次调用返回异常，第二次返回foo
when(mock.someMethod("some arg"))
   .thenThrow(new RuntimeException())
   .thenReturn("foo");

//同上述类似doXX().when(mockedList).xx() 会在mockedList.xx()时返回doXx()的结果
doThrow(new RuntimeException()).when(mockedList).clear();
mockedList.clear();//将返回异常
```



```java
//验证mockedList 是否调用过(1次) get方法，调用次数超过则算失败
verify(mockedList).get(anyInt());
```

常见验证有：验证是否执行过，验证执行次数，验证有无异常，验证顺序，查找多余调用





## Powermock用法说明









## 一般规范

单元测试规范主要有包/文件命名规范，路径规范

Unit Testing Guidelines  https://petroware.no/html/unittesting.html 

(译：https://blog.csdn.net/neo_ustc/article/details/22612759)



单元测试规范： https://www.cnblogs.com/M-Silencer/p/11215065.html



## 覆盖范围

覆盖范围









# 参考文献

### TestNG

官网：https://testng.org/doc/documentation-main.html

testng简易教程：https://www.yiibai.com/testng/

TestNG&junit异同：https://www.cnblogs.com/weiweiyao/p/4280062.html



### Mockito

源码：https://github.com/mockito/mockito

官网教程：https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html



### PowerMock

官方文档：https://github.com/powermock/powermock/wiki/Getting-Started

样例教程：https://github.com/powermock/powermock-examples-maven



### 扩展阅读

测试驱动开发（TestNG Junit相关）： [百度百科](https://baike.baidu.com/item/测试驱动开发/3328831)   [维基百科]([https://zh.wikipedia.org/wiki/%E6%B5%8B%E8%AF%95%E9%A9%B1%E5%8A%A8%E5%BC%80%E5%8F%91](https://zh.wikipedia.org/wiki/测试驱动开发))

行为驱动开发(Mockito相关)：  [百度百科](https://baike.baidu.com/item/%E8%A1%8C%E4%B8%BA%E9%A9%B1%E5%8A%A8%E5%BC%80%E5%8F%91/9424963)   [维基百科]([https://zh.wikipedia.org/wiki/%E8%A1%8C%E4%B8%BA%E9%A9%B1%E5%8A%A8%E5%BC%80%E5%8F%91](https://zh.wikipedia.org/wiki/行为驱动开发))



### 基本maven依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>3.8.1</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>6.14.3</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-all</artifactId>
    <version>1.10.19</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.3.3</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito</artifactId>
    <version>1.7.4</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-testng</artifactId>
    <version>2.0.5</version>
    <scope>test</scope>
</dependency>
```






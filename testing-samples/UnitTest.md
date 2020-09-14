# 单元测试调研

## 目标

研究一下testNG 与 PowerMock，Mockito节后给我一个调研方案，研究逻辑单元测试的用法，一般规范，覆盖范围等



## 前言

关于单元测试网上教程很多，各工具也均有官网，学习过程中尽量以官网为参考。本文主要针对项目中的测试场景做针对分析，记录单元测试用法、规范、覆盖范围等。下面针对目前springboot项目的主要场景进行列举，并概述使用方法。





## Junit TestNG对比

**附：Junit基本用法**

https://github.com/junit-team/junit4/wiki



### TestNg优于Junit的地方

**1.允许分组测试**

```java
@Test(groups="group1")
public void groupTest(){
}
```

然后在testng.xml中定义要包含哪些group，不包含哪些group



**2.TestNg允许只运行失败的例子**

执行完testng后，会在test-output目录下生成一些测试结果文件。如果此次测试有失败的例子，我们调试完，想再运行一下这些失败的例子时，可以运行testng-failed.xml文件。这个文件就是记录了上一次所有执行失败的例子。是不是很方便啊。



**3.TestNg允许依赖测试（类似于ant的依赖）：**

可依赖测试方法：

```java
//test1依赖test2
@Test(dependsOnMethods = { "test2" })
public void test1() {
}

@Test
public void test2() {
}
```



也可依赖群组：

```java
@Test(groups = { "init.1" })
public void test1() {
}
@Test(groups = { "init.2" })
public void test2() {
}
@Test(dependsOnGroups = { "init.*" })
public void test3() { 
}
```

**4.TestNg支持并行测试（支持测试方法(methods)，测试类(classes)，小的测试套件（tests），可以大大提高测试效率**

```xml
<!-- 则开了两个线程一个运行class1，一个运行class2。 -->
<suite name="Test-class Suite" parallel="classes" thread-count="2" >
  <test name="Test-class test" >
    <classes>
      <class name="class1" />
      <class name="class2" />
    </classes>
  </test>
</suite>
```



**5.比junit注解丰富**

| 注解                                             | 描述                                                         |
| ------------------------------------------------ | ------------------------------------------------------------ |
| [@BeforeSuite](https://github.com/BeforeSuite)   | 在该套件的所有测试都运行在注释的方法之前，仅运行一次。       |
| [@AfterSuite](https://github.com/AfterSuite)     | 在该套件的所有测试都运行在注释方法之后，仅运行一次。         |
| [@BeforeClass](https://github.com/BeforeClass)   | 在调用当前类的第一个测试方法之前运行，注释方法仅运行一次。   |
| [@AfterClass](https://github.com/AfterClass)     | 在调用当前类的第一个测试方法之后运行，注释方法仅运行一次     |
| [@BeforeTest](https://github.com/BeforeTest)     | 注释的方法将在属于<test>标签内的类的所有测试方法运行之前运行。 |
| [@AfterTest](https://github.com/AfterTest)       | 注释的方法将在属于<test>标签内的类的所有测试方法运行之后运行。 |
| [@BeforeGroups](https://github.com/BeforeGroups) | 配置方法将在之前运行组列表。  此方法保证在调用属于这些组中的任何一个的第一个测试方法之前不久运行。 |
| [@AfterGroups](https://github.com/AfterGroups)   | 此配置方法将在之后运行组列表。该方法保证在调用属于任何这些组的最后一个测试方法之后不久运行。 |
| [@BeforeMethod](https://github.com/BeforeMethod) | 注释方法将在每个测试方法之前运行。                           |
| [@AfterMethod](https://github.com/AfterMethod)   | 注释方法将在每个测试方法之后运行。                           |
| [@DataProvider](https://github.com/DataProvider) | 标记一种方法来提供测试方法的数据。 注释方法必须返回一个Object [\]   []，其中每个Object []可以被分配给测试方法的参数列表。   要从该DataProvider接收数据的@Test方法需要使用与此注释名称相等的dataProvider名称。|
| [@Factory](https://github.com/Factory)           | 将一个方法标记为工厂，返回TestNG将被用作测试类的对象。 该方法必须返回Object []。 |
| [@Listeners](https://github.com/Listeners)       | 定义测试类上的侦听器。                                       |
| [@Parameters](https://github.com/Parameters)     | 描述如何将参数传递给@Test方法。 |
| [@Test](https://github.com/Test)                 | 将类或方法标记为测试的一部分。                               |

**6.testng被@BeforeClass 和@AfterClass注释的方法可以不写成static方法**

**7.被@Test标记的方法可以有输入参数，而在junit中是不行的**

```java
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
public class ParameterizedTest1 {
    @Test
    @Parameters("myName")
    public void parameterTest(String myName) {
        System.out.println("Parameterized value is : " + myName);
    }
}
```



```xml
<!-- 在testng.xml文件中定义参数的值 -->
<?xml version="1.0" encoding="UTF-8"?><!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="Suite1">
    <test name="test1">
          <parameter name="myName" value="qiuqiu"/> 
          <classes>
              <class name="ParameterizedTest1" />
              </classes>
    </test>
</suite>
<!-- 可在<suite>标签或<test>标签下声明了参数。如果两个参数同名，在<test>标签下定义的参数优先 -->
```



**8.testNg可以通过标注的方式来顺序执行**

```java
@Test(priority=0)
//priority为0,1,2,3这样定义，然后就会按照数字从小到大那样依次执行
```

### 相同点

**1.都可以做忽略测试****

可以忽略某个测试方法(在方法上面注释)，也可以忽略某个测试类(在类的上面注释)**

 ```java
//testNg：
@Test(enabled = false)

//Junit：
@Ingore
@Test
 ```

**2.都支持数据驱动测试，只是用法不一样**

```java
//testng中可以用@DataProvider，参数化是在测试级别的，不需要通过构造函数来传递参数，它会自动映射。
//举例：

//表示这个方法将提供数据给任何声明它的data provider名为“test1”的测试方法中
@DataProvider(name = "test1")
public Object[][] createData1() { 
 return new Object[][] {
   { "Cedric", new Integer(36) },
   { "Anne", new Integer(37)},
 };
} 
//下面这个方法将要调用名为test1的data provider提供的数据
@Test(dataProvider="test1")
public void verifyDta(String n1,Integer n2){
  System.out.println(n1 + " " + n2); 
}
/*
需要注意的是@Test(dataProvider=)和@DataProvider(name=)可以在同一个类中，使用方法就如上；
如果不在同一个类中，那么必须把@DataProvider(name=)所在的类的这个方法定义成static静态方法。
并且在@Test使用的时候需要制定类。用法就是@Test(dataProvider="",dataProviderClass=（@DataProvider所在的类）.class) 
而在junit中就麻烦多了。junit中的参数化是在类级别的，需要通过构造函数来传递参数。
如下：
*/
public class Try {
     public  int result=3;
     public  int add(int n) {
        result += n;
        return result;
    }
}
 
//测试代码：
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
//步骤1.指定特殊的运行器Parameterized.class
@RunWith(Parameterized.class)
public class TryTest {
    // 步骤2：为测试类声明几个变量，分别用于存放期望值和测试所用数据。此处我只放了测试所有数据，没放期望值。
    private int param, result;
    // 步骤3：申明构造函数
    public TryTest(int param, int result) {
        super();
        this.param = param;
        this.result = result;
    }
    // 步骤4：定义测试数据的集合,该方法可以任意命名
    // 但是必须使用@Parameters标注进行修饰
    // 这个方法的框架就不予解释了，大家只需要注意其中的数据，是一个二维数组，数据两两一组
    // 每组中的这两个数据，一个是参数，一个是你预期的结果。
    // 比如我们的第一组{4, 7}，4就是参数，7就是预期的结果。分别对应上面构造函数的param和result
    @Parameters
    public static Collection<Object[]> testDate() {
        Object[][] object = { { 1, 4 }, { 3, 6 }, { 1, 3 } };
        return Arrays.asList(object);
    }
    // 步骤5：编写测试方法，使用定义的变量作为参数进行测试
    // 这里的编写方法和以前的测试方法一样
    @Test
    public void testAdd() {
        Try test = new Try();
        assertEquals(result, test.add(param));
    }
}
```


**3. 超时测试，就是在规定时间内如果没有测试完成，就认定测试失败**
```java
@Test(timeout=100)
```



**4.异常测试，就是在运行这个单元测试的时候应该要捕获到指定的异常，才算测试成功**

### testng和junit的区别
testng中子类不会运行父类中的@BeforeClass和@AfterClass
而在junit中会先运行父类的@BeforeClass，再运行自己的@BeforeClass；而@AfterClass是先运行自己的，再运行父类的





## TestNG用法说明

### 主要场景

- 基本运算逻辑接口：一般有私有方法，处理运算逻辑，传入不依赖环境的参数变量，运算并返回期望结果
- 普通service接口：依赖系统bean对象
- HTTP接口：主要指controller类型接口，返回http请求响应
- I/O接口：下载、读写文件之类
- 组件接口：依赖数据库 redis mq 等第三方程序的接口
- 运行时接口：依赖context启动的接口，如request/response等对象

其中后三种`I/O接口` 、`组件接口`、`运行时接口`和前面三种异曲同工，本质上还是在运行测试之前准备好连接信息、参数、期望结果等，以达到对比运行结果的目的。



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

（见源码demo）







## 一般规范

单元测试规范主要有包/文件命名规范，路径规范

Unit Testing Guidelines  https://petroware.no/html/unittesting.html 

(译：https://blog.csdn.net/neo_ustc/article/details/22612759)



单元测试规范： https://www.cnblogs.com/M-Silencer/p/11215065.html



## 覆盖范围

单元测试可能存在的问题

- 测试场景不够全
- 功能覆盖不够全
- 测试方法错误或不准确
- 可能存在异常处理遗漏
- 无法进行完全的安全测试
- 无法进行完整的性能测试

主要解决方法：

- 多人审查，尽可能完善测试场景和用例
- 进行安全测试、压力测试、集成测试作为补充
- 对系统异常进行统一记录，以便后续跟进修复。

  



覆盖范围：https://www.csdn.net/gather_2a/MtTaMgzsMzk5NTMtYmxvZwO0O0OO0O0O.html

覆盖率：https://www.cnblogs.com/sueyyyy/p/12571446.html











# 参考文献

**TestNG**

官网：https://testng.org/doc/documentation-main.html

testng简易教程：https://www.yiibai.com/testng/

TestNG&junit异同：https://www.cnblogs.com/weiweiyao/p/4280062.html



**Mockito**

源码：https://github.com/mockito/mockito

官网教程：https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html



**PowerMock**

官方文档：https://github.com/powermock/powermock/wiki/Getting-Started

样例教程：https://github.com/powermock/powermock-examples-maven



实践总结：https://www.jianshu.com/p/c76b8cdba30d



**扩展阅读**

测试驱动开发（TestNG Junit相关）： [百度百科](https://baike.baidu.com/item/测试驱动开发/3328831)   [维基百科]([https://zh.wikipedia.org/wiki/%E6%B5%8B%E8%AF%95%E9%A9%B1%E5%8A%A8%E5%BC%80%E5%8F%91](https://zh.wikipedia.org/wiki/测试驱动开发))

行为驱动开发(Mockito相关)：  [百度百科](https://baike.baidu.com/item/%E8%A1%8C%E4%B8%BA%E9%A9%B1%E5%8A%A8%E5%BC%80%E5%8F%91/9424963)   [维基百科]([https://zh.wikipedia.org/wiki/%E8%A1%8C%E4%B8%BA%E9%A9%B1%E5%8A%A8%E5%BC%80%E5%8F%91](https://zh.wikipedia.org/wiki/行为驱动开发))



**基本maven依赖**

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
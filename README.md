![dev_logo.jpg](dev_logo.jpg)
# jNotUnit-core
## Описание 
Библиотека содержит методы, расширяющие функционал фреймворка jUnit5.  

Как известно, jUnit5 создавался для написания и управления unit-тестами. 

Однако, многие автоматизаторы тестирования используют jUnit5 при написании высокоуровневых автотестов.

В таких случаях стандартных методов и аннотаций jUnit5 может быть не достаточно.

И вот мы здесь!

## Инсталяция maven
Добавьте в ваш pom.xml следующие зависимости:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.13nadcatiy</groupId>
        <artifactId>jNotUnit-core</artifactId>
        <version>release-1.1.0</version>
    </dependency>
</dependencies>
```
## Возможности библиотеки
### Глобальные условия запуска: beforeAllTests() и afterAllTests()
В jUnit5 есть аннотации __@BeforeAll__ и __@AfterAll__.

Они выполняются до и после каждого класса с тестами.  

При написании высокоуровневых автотестов нам может потребоваться 
сохранять какой-либо контекст, например инстанс браузера.

С этим могут помочь методы интерфейса __GlobalBeforeAndAfterCallBack__.

Пример использования:
```java
package com.example;

import dev.pivozavr.jnotunit.core.GlobalBeforeAndAfterCallBack;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestBase.class)
public class TestBase implements GlobalBeforeAndAfterCallBack {

    public static ImportantManager manager;

    @Override
    public void beforeAllTests() {
        manager = new ImportantManager();
    }

    @Override
    public void afterAllTests() {
        manager.stop();
    }
}
```

В родительском классе, от которого наследуются классы с тестами, 
обязательно необходимо прописать аннотацию __@ExtendWith__, 
указывающую на класс в котором реализованы методы интерфейса 
__GlobalBeforeAndAfterCallBack__.  
Но для удобства, в данном примере они реализуются 
в самом родительском классе - это не запрещается.

### DataProviderBuilder : упрощенный способ параметризации тестов
jUnit5 позволяет параметризировать тестовый метод:
```java
@ParameterizedTest
    @MethodSource("provideTestData")
    void testAddition(int a, int b, int expectedSum) {
        assertEquals(expectedSum, a + b);
    }
```
В данном случае, параметры должны определяться в методе provideTestData().
Для этого потребуется использовать стримы.

Методы класса __DataProviderBuilder__ внутри себя реализуют работу со стримами, предоставляя простой fluent-интерфейс для создания списка параметров теста.

Было:
```java
    public static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(2, 3, 5), 
                Arguments.of(10, 15, 25)
    );
}
```
Стало:
```java
    public static Stream<Arguments> provideTestData() {
        return new DataProviderBuilder()
                .add(1, 2, 3)
                .add(2, 3, 5)
                .add(10, 15, 25)
                .getStream();
    }
```
### ObjectToJsonStringSerializable: преобразование объекта в строку, формата json
Для того чтобы вывести информацию по объекту в виде строки, необходимо переопределить в нем метод toString().

Это можно сделакть как средствами генерации IDE, так и с помощью аннотации lombok.

Пример класса:
```java
@Data
@Builder
@ToString
public class TestBean {
    Integer id;
    String name;
    ArrayList<Integer> numbers;
}
```
Пример вывод строки:
```text
TestBean(id=123, name=TestName, numbers=[8, 0, 0, 8, 5])
```

Если нам нужно, например для allure-отчетов, выводить текстом тело объекта - такой вариант форматирования может быть не удобным. Особенно еслитело объекта очень большое.

В этом случае будет удобнее преобразовывать объекта в формат json.

Для этого есть интерфейс __ObjectToJsonStringSerializable__ и метод __toJsonString()__.

Обернув toJsonString() в toString() мы получим на выходе красивый вывод строки по-умолчанию.

Пример класса:
```java
@Data
@Builder
public class TestBean implements ObjectToJsonStringSerializable {
    Integer id;
    String name;
    ArrayList<Integer> numbers;

    public String toString() {
        return toJsonString();
    }
}
```

Пример вывод строки:
```text
{
  "id" : 123,
  "name" : "TestName",
  "numbers" : [ 8, 0, 0, 8, 5 ]
}
```
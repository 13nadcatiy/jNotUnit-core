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
        <version>release-1.05</version>
    </dependency>
</dependencies>
```
## Методы библиотеки
### beforeAllTests() и afterAllTests()
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
Но для удобства, в данном примере они реализуются в самом родительском классе - это не запрещается.
package dev.pivozavr.jnotunit.core.tostring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ObjectToJsonStringTests {


    @Test
    public void objectToStringTest(){
        String expectedResult = "{\n" +
                "  \"id\" : 123,\n" +
                "  \"name\" : \"TestName\",\n" +
                "  \"numbers\" : [ 8, 0, 0, 8, 5 ]\n" +
                "}";

        ArrayList<Integer> num = new ArrayList<>();
        num.add(8);
        num.add(0);
        num.add(0);
        num.add(8);
        num.add(5);

        TestBean bean = TestBean.builder()
                .id(123)
                .name("TestName")
                .numbers(num)
                .build();

        Assertions.assertEquals(expectedResult, bean.toString());
    }
}

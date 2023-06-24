package com.hongvengers.edu.springbootstudy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {
    @Test
    void simpleHelloServiceTest() {
        SimpleHelloService helloService = new SimpleHelloService();

        String hello = helloService.sayHello("Test name");

        Assertions.assertThat(hello).isEqualTo("sayHello Test name");
    }

    @Test
    void helloDecoratorTest() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);

        String hello = helloDecorator.sayHello("Test");
        Assertions.assertThat(hello).contains("Test");
    }
}

package com.hongvengers.edu.springbootstudy;

public class HelloController {
    public String hello(String name) {
        return String.format("Hello: %s", name);
    }
}

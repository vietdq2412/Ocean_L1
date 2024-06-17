package com.globits.da.dto.myFirstDto;

public class MyFirstDto {
    private String code;
    private String name;
    private Integer age;

    public MyFirstDto() {
    }

    @Override
    public String toString() {
        return "MyFirstDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

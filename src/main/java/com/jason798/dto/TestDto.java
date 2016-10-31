package com.jason798.dto;


public class TestDto {
    private int i=0;
    private String s;
    public TestDto(int a){
        this.i=a;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "i=" + i +
                ", s='" + s + '\'' +
                '}';
    }
}

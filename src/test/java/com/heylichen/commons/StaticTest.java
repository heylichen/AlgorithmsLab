package com.heylichen.commons;

public class StaticTest
{
    public static void main(String[] args)
    {
        staticFunction();
    }

    static StaticTest st = new StaticTest();// clinit

    static
    {
        System.out.println("1");// clinit
    }

    {
        System.out.println("2");//init
    }

    StaticTest()
    {
        System.out.println("3");
        System.out.println("a="+a+",b="+b);//init
    }

    public static void staticFunction(){
        System.out.println("4");
    }

    int a=110;//init
    static int b =112;  // clinit
}
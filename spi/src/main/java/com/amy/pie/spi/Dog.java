package com.amy.pie.spi;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/4/4 10:36
 */
public class Dog implements IShout {
    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}

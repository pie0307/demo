package com.amy.pie.spi;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/4/4 10:35
 */
public class Cat implements IShout  {
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}

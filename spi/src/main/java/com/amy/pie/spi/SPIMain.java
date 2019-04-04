package com.amy.pie.spi;

import java.util.ServiceLoader;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/4/4 10:38
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            s.shout();
        }
    }
}

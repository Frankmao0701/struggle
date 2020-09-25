package com.frank.struggle;

/**
 * @author maowenqiang
 * @desc
 */
public class Singleton {
    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingleonHolder.mInstance;
    }

    private static class SingleonHolder {
        private static final Singleton mInstance = new Singleton();
    }
}

package com.frank.struggle.designmode.factory;

/**
 * @author maowenqiang
 * @desc
 */
public class Q5Factory extends CarFactory {
    @Override
    public ITire createITire() {
        return new NormalTire();
    }
}

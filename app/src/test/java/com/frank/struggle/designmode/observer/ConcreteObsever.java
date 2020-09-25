package com.frank.struggle.designmode.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author maowenqiang
 * @desc
 */
public class ConcreteObsever implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Observable  = " + o.toString() + "---更新了新的内容 = " + arg + "--" + this.hashCode());
    }
}

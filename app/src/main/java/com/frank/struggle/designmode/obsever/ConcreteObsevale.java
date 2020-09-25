package com.frank.struggle.designmode.obsever;

import java.util.Observable;

/**
 * @author maowenqiang
 * @desc
 */
public class ConcreteObsevale extends Observable {
    public void postNewPublication(String content){
        // 标识状态或者内容发生改变
        setChanged();
        notifyObservers();
    }
}

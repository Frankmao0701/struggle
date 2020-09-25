package com.frank.struggle.designmode.observer;

/**
 * @author maowenqiang
 * @desc
 */
public class ObserverClient {
    public static void publish(String content) {
        ConcreteObsever obsever1 = new ConcreteObsever();
        ConcreteObsever obsever2 = new ConcreteObsever();
        ConcreteObsevale obsevalle = new ConcreteObsevale();
        obsevalle.addObserver(obsever1);
        obsevalle.addObserver(obsever2);
        obsevalle.postNewPublication("发送新的消息");
    }
}

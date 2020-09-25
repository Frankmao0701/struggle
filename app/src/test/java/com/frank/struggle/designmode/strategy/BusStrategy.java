package com.frank.struggle.designmode.strategy;

/**
 * @author maowenqiang
 * @desc
 */
public class BusStrategy implements CalculateStrategy {

    @Override
    public int calculatePrice(int km) {
        // 处理计算逻辑 此处省略
        return 10;
    }
}

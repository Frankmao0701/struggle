package com.frank.struggle.designmode.strategy;

/**
 * @author maowenqiang
 * @desc
 */
public class SubwayStrategy implements CalculateStrategy{
    @Override
    public int calculatePrice(int km) {
        // 此处计算逻辑 省略
        return 15;
    }
}

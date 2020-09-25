package com.frank.struggle.designmode.strategy;

/**
 * @author maowenqiang
 * @desc
 */
public class TranficCalculator {
    private CalculateStrategy mStrategy;

    public static void main(String[] args) {
        TranficCalculator tranficCalculator = new TranficCalculator();
        tranficCalculator.setStrategy(new BusStrategy());
        tranficCalculator.calculater(10);
    }

    public void setStrategy(CalculateStrategy strategy) {
        mStrategy = strategy;
    }

    public int calculater(int km) {
        if (mStrategy != null) {
            return mStrategy.calculatePrice(km);
        }
        return 0;
    }
}

package com.frank.struggle.designmode.proxy;

/**
 * @author maowenqiang
 * @desc 被代理类
 */
public class XiaoMao implements ILawsuit {
    @Override
    public void submit() {
        System.out.println("submit....");
    }

    @Override
    public void burden() {
        System.out.println("burden....");
    }

    @Override
    public void defend() {
        System.out.println("defend....");
    }

    @Override
    public void finish() {
        System.out.println("finish....");
    }
}

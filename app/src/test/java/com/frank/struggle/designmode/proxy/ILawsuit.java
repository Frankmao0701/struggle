package com.frank.struggle.designmode.proxy;

/**
 * @author maowenqiang
 * @desc
 */
public interface ILawsuit {
    // 提交申请
    void submit();
    // 进行举证
    void burden();
    // 开始辩护
    void defend();
    // 完成诉讼
    void finish();
}

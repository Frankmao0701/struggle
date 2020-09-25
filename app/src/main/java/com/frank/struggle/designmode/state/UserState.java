package com.frank.struggle.designmode.state;

import android.content.Context;

/**
 * @author maowenqiang
 * @desc
 */
public interface UserState {
    public void forward(Context context);
    public void comment(Context context);
}

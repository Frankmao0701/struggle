package com.frank.struggle.designmode.state;

import android.content.Context;
import android.util.Log;

/**
 * @author maowenqiang
 * @desc
 */
public class LoginedState implements UserState {
    public static final String TAG = "LoginedState";

    @Override
    public void forward(Context context) {
        Log.d(TAG, "已登录，直接执行转发逻辑");
    }

    @Override
    public void comment(Context context) {
        Log.d(TAG, "已登录，直接执行评论逻辑");
    }
}

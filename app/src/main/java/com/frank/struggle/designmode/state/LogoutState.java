package com.frank.struggle.designmode.state;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author maowenqiang
 * @desc
 */
public class LogoutState implements UserState {
    public static final String TAG = "LogoutState";

    @Override
    public void forward(Context context) {
        Log.d(TAG, "转发未登录执行登录操作");
        Intent intent = new Intent(context, StateLoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void comment(Context context) {
        Intent intent = new Intent(context, StateLoginActivity.class);
        context.startActivity(intent);
        Log.d(TAG, "评论未登录执行登录操作");
    }
}

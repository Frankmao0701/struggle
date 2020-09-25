package com.frank.struggle.designmode.state;

import android.content.Context;

/**
 * @author maowenqiang
 * @desc
 */
public class LoginContext {
    private UserState mState = new LogoutState();

    private LoginContext() {

    }

    public static LoginContext getLoginContext() {
        return LoginContextHolder.instance;
    }

    private static class LoginContextHolder {
        private static LoginContext instance = new LoginContext();
    }

    public void setState(UserState state) {
        this.mState = state;
    }

    public void forward(Context context) {
        mState.forward(context);
    }

    public void comment(Context context) {
        mState.comment(context);
    }
}

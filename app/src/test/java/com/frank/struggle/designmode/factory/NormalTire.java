package com.frank.struggle.designmode.factory;

import android.util.Log;

/**
 * @author maowenqiang
 * @desc
 */
public class NormalTire implements ITire {
    @Override
    public void tire() {
        Log.d("mwq", "普通轮胎...");
    }
}

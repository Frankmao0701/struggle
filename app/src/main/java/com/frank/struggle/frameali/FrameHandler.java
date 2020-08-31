package com.frank.struggle.frameali;

import android.os.Handler;
import android.os.Message;

public class FrameHandler extends Handler {
    private static final int MSG_UPDATE_FRAME = 1001;

    private IConsumer a;

    public FrameHandler(IConsumer paramIConsumer) {
        this.a = paramIConsumer;
    }

    private void updateFrame() {
        if (this.a != null) {
            this.a.consume();
        }
    }

    public void handleMessage(Message paramMessage) {
        switch (paramMessage.what) {
            case MSG_UPDATE_FRAME:
                updateFrame();
                break;
        }
    }

    public void sendUpdateFrameMsg(int paramInt) {
        Message message = new Message();
        message.what = MSG_UPDATE_FRAME;
        sendMessageDelayed(message, paramInt);
    }
}

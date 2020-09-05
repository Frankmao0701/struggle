package com.frank.struggle.frameali;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;

public class FrameAnimator implements Animatable {
    private boolean isRunning;

    private FrameConsumer mConsumer;

    private FrameProducer mProducer;

    private AnimationRes mAnimRes;

    private static final int DEFAULT_SIZE = 1;

    private ArrayBlockingQueue<Drawable> mFrameQueue;

    private ArrayBlockingQueue<Bitmap> mRecyQueue;

    public FrameAnimator(Context paramContext, ImageView paramImageView, int paramInt, boolean oneShot) {
        mAnimRes = parseAnimRes(paramContext, paramInt);
        init(paramContext, paramImageView, DEFAULT_SIZE, oneShot);
    }

    public FrameAnimator(Context paramContext, ImageView paramImageView,
                         int[] paramArrayOfInt, int duration, boolean oneShot) {
        if (paramArrayOfInt == null || paramArrayOfInt.length < 1) {
            return;
        }
        mAnimRes = new AnimationRes();
        byte b1;
        for (b1 = 0; b1 < paramArrayOfInt.length; b1++) {
            mAnimRes.addDrawable(paramArrayOfInt[b1]);
            mAnimRes.addDuration(duration);
        }
        init(paramContext, paramImageView, 5, oneShot);
    }

    private AnimationRes parseAnimRes(Context paramContext, int paramInt) {
        try {
            return AnimationParser.parseAnim(paramContext, paramInt);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void init(Context paramContext, ImageView paramImageView, int paramInt, boolean oneShot) {
        mFrameQueue = new ArrayBlockingQueue(paramInt);
        mRecyQueue = new ArrayBlockingQueue(paramInt);
        if (mAnimRes != null) {
            int[] drawables = mAnimRes.getDrawables();
            int[] duratons = mAnimRes.getDurations();
            Log.d("FrameAnimator", "drawables length = " + drawables.length +
                    "  durations length = " + drawables.length);
            mProducer = new FrameProducer(paramContext, mFrameQueue, mRecyQueue, drawables, drawables.length, oneShot);
            mConsumer = new FrameConsumer(this, paramImageView,
                    mFrameQueue, mRecyQueue, duratons, duratons.length, oneShot);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setFrameListener(IFrameAnimListener paramIFrameAnimListener) {
        if (mConsumer != null) {
            mConsumer.setFrameListener(paramIFrameAnimListener);
        }
    }

    public void start() {
        Log.d("FrameAnimator", "anim start. queue = " + mFrameQueue);
        if (mProducer != null) {
            isRunning = true;
            (new Thread(mProducer)).start();
            mConsumer.consume();
        }
    }

    public void start(Executor paramExecutor) {
        Log.d("FrameAnimator", "anim start. queue = " + mFrameQueue);
        if (mProducer != null) {
            isRunning = true;
            paramExecutor.execute(mProducer);
            mConsumer.consume();
        }
    }

    public void stop() {
        if (mProducer != null) {
            mProducer.stop();
        }
        if (mConsumer != null) {
            mConsumer.stop();
        }
        isRunning = false;
    }
}

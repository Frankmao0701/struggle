package com.frank.struggle.frameali;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;

public class FrameConsumer implements IConsumer {
    private static final String TAG = "FrameConsumer";
    private int mSize;

    private int mIndex;

    private int[] mDurations;

    private BlockingQueue<Drawable> mBitmapQueue;

    private FrameHandler mFrameHandler;

    private IFrameAnimListener mListener;

    private WeakReference<ImageView> mTargetRef;

    private WeakReference<FrameAnimator> mAnimatorRef;

    private BlockingQueue<Bitmap> mRecyQueue;

    private volatile boolean isStoped;

    private boolean isOneShot = true;

    public FrameConsumer(FrameAnimator paramFrameAnimator, ImageView paramImageView,
                         BlockingQueue<Drawable> paramBlockingQueue1,
                         BlockingQueue<Bitmap> paramBlockingQueue2, int[] paramArrayOfInt, int paramInt) {
        mAnimatorRef = new WeakReference(paramFrameAnimator);
        mTargetRef = new WeakReference(paramImageView);
        mBitmapQueue = paramBlockingQueue1;
        mSize = paramInt;
        mDurations = paramArrayOfInt;
        mFrameHandler = new FrameHandler(this);
        mRecyQueue = paramBlockingQueue2;
    }

    public FrameConsumer(FrameAnimator paramFrameAnimator, ImageView paramImageView,
                         BlockingQueue<Drawable> paramBlockingQueue1,
                         BlockingQueue<Bitmap> paramBlockingQueue2,
                         int[] paramArrayOfInt, int paramInt, boolean oneShot) {
        this(paramFrameAnimator, paramImageView, paramBlockingQueue1, paramBlockingQueue2, paramArrayOfInt, paramInt);
        this.isOneShot = oneShot;
    }

    private void recycle() {
        while (mRecyQueue != null && !mRecyQueue.isEmpty()) {
            mRecyQueue.clear();
        }
    }

    private void clear(BlockingQueue<Drawable> queue) {
        while (queue != null && !queue.isEmpty()) {
            queue.clear();
        }
    }

    public void consume() {
        ImageView target = (ImageView) mTargetRef.get();
        FrameAnimator frameAnimator = mAnimatorRef.get();
        if (frameAnimator == null) {
            frameAnimator = (FrameAnimator) mAnimatorRef.get();
            if (frameAnimator != null) {
                frameAnimator.stop();
            }
            clear(mBitmapQueue);
            recycle();
            Log.d(TAG, "queue mSize = " + mSize + " comsume index = " + mIndex + " stop = true");
            return;
        }
        if (mBitmapQueue != null && mIndex < mSize) {
            Drawable drawable = (Drawable) mBitmapQueue.poll();
            if (drawable != null) {
                if (isOneShot) {
                    mIndex++;
                } else {
                    mIndex = (mIndex + 1) % mSize;
                }
                Drawable oldDrawable = target.getDrawable();
                target.setImageDrawable(drawable);
                if (oldDrawable != null && oldDrawable instanceof BitmapDrawable) {
                    Bitmap bitmap = ((BitmapDrawable) oldDrawable).getBitmap();
                    if (bitmap != null && mRecyQueue != null) {
                        mRecyQueue.offer(bitmap);
                    }
                }
            }
            if (mFrameHandler != null && mDurations != null && mIndex < mDurations.length) {
                if (!isStoped) {
                    mFrameHandler.sendUpdateFrameMsg(mDurations[mIndex]);
                } else {
                    clear(mBitmapQueue);
                    recycle();
                    Log.d(TAG, "queue mSize = " + mSize + " comsume index = " + mIndex + " stop = true");
                    return;
                }
            }
        }
        if (mListener != null) {
            mListener.onFrameUpdate(mIndex, mSize);
        }
        if (mIndex == mSize) {
            if (mListener != null) {
                mListener.onFrameComplete();
            }
            recycle();
            Log.d(TAG, "anim-finish. queue = " + mBitmapQueue);
        }
        Log.d(TAG, "queue mSize = " + mSize + " comsume index = " + mIndex);
    }

    protected void setFrameListener(IFrameAnimListener paramIFrameAnimListener) {
        mListener = paramIFrameAnimListener;
    }

    public void stop() {
        isStoped = true;
    }
}

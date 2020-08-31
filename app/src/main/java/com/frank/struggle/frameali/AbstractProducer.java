package com.frank.struggle.frameali;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class AbstractProducer<T> extends Object implements IProducer<T>, Runnable {
    private static final String TAG = "AbstractProducer";

    private int size;

    private int count;

    private volatile boolean isStoped;

    private ArrayBlockingQueue<T> queue;

    private boolean isOneShot = true;

    public AbstractProducer(ArrayBlockingQueue<T> paramBlockingQueue, int paramInt) {
        queue = paramBlockingQueue;
        this.size = paramInt;
        this.count = 0;
    }

    public AbstractProducer(ArrayBlockingQueue<T> paramBlockingQueue, int paramInt, boolean oneShot) {
        this(paramBlockingQueue, paramInt);
        this.isOneShot = oneShot;
    }

    public int getCount() {
        return this.count;
    }

    public abstract T produce();

    public void run() {
        while (queue != null && this.count < this.size && !this.isStoped) {
            T obj = produce();
            if (isOneShot) {
                this.count++;
            } else {
                this.count = (this.count + 1) % size;
            }

            try {
                queue.offer(obj, 500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException object) {
                Log.d(TAG, "InterruptedException");
                object.printStackTrace();
                this.isStoped = true;
            }
        }
        Log.d(TAG, "producer thread end. size = " + this.size + " count = " + this.count);
    }

    public void stop() {
        this.isStoped = true;
    }
}

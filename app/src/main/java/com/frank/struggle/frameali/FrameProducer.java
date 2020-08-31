package com.frank.struggle.frameali;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;

public class FrameProducer extends AbstractProducer<Drawable> {
    private int[] resArray;

    private Context mContext;

    private ArrayBlockingQueue<Bitmap> recycleQueue;

    private int mProduceCount;

    public FrameProducer(Context paramContext,
                         ArrayBlockingQueue<Drawable> frameQueue,
                         ArrayBlockingQueue<Bitmap> recyQueue,
                         int[] paramArrayOfInt, int size) {
        super(frameQueue, size);
        mContext = paramContext.getApplicationContext();
        resArray = paramArrayOfInt;
        recycleQueue = recyQueue;
    }

    public FrameProducer(Context paramContext,
                         ArrayBlockingQueue<Drawable> frameQueue,
                         ArrayBlockingQueue<Bitmap> recyQueue,
                         int[] paramArrayOfInt, int size, boolean oneShot) {
        super(frameQueue, size, oneShot);
        mContext = paramContext.getApplicationContext();
        resArray = paramArrayOfInt;
        recycleQueue = recyQueue;
    }

    private Drawable produce(int res) {
        if (mContext != null) {
            Bitmap recyBitmap = null;
            if (recycleQueue != null) {
                recyBitmap = (Bitmap) recycleQueue.poll();
            }
            if (recyBitmap != null) {
                this.mProduceCount++;
                Log.d("FrameProducer", "use recycle count = " + this.mProduceCount);
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            options.inBitmap = recyBitmap;
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), res, options);
            if (bitmap != null) {
                Log.d("FrameProducer", " decoded bitmap");
                return new BitmapDrawable(mContext.getResources(), bitmap);
            }
        }
        return null;
    }

    public Drawable produce() {
        int i = getCount();
        return (resArray != null && i < resArray.length) ? produce(resArray[i]) : null;
    }
}

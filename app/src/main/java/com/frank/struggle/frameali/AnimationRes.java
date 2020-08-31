package com.frank.struggle.frameali;

import java.util.ArrayList;
import java.util.List;

public class AnimationRes {

    private List<Integer> drawables = new ArrayList();

    private List<Integer> durations = new ArrayList();

    public void addDrawable(int res) {
        drawables.add(res);
    }

    public void addDuration(int duration) {
        durations.add(duration);
    }

    public int[] getDrawables() {
        int[] arrayOfInt = new int[drawables.size()];
        for (byte b1 = 0; b1 < drawables.size(); b1++) {
            arrayOfInt[b1] = ((Integer) drawables.get(b1)).intValue();
        }
        return arrayOfInt;
    }

    public int[] getDurations() {
        int[] arrayOfInt = new int[durations.size()];
        for (byte b1 = 0; b1 < durations.size(); b1++) {
            arrayOfInt[b1] = ((Integer) durations.get(b1)).intValue();
        }
        return arrayOfInt;
    }
}

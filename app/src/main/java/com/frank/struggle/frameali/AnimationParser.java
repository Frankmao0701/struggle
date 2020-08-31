package com.frank.struggle.frameali;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;

import com.frank.struggle.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class AnimationParser {
    public static AnimationRes parseAnim(Context paramContext, int paramInt)
            throws XmlPullParserException, IOException {
        XmlResourceParser xmlResourceParser = paramContext.getResources().getAnimation(paramInt);
        AttributeSet attributeSet = Xml.asAttributeSet(xmlResourceParser);
        AnimationRes animationRes = null;
        paramInt = xmlResourceParser.getDepth() + 1;
        while (true) {
            int i = xmlResourceParser.next();
            if (i != 1) {
                int j = xmlResourceParser.getDepth();
                if (j >= paramInt || i != 3) {
                    if (i == 2 && j <= paramInt + 1 && xmlResourceParser.getName().equals("item")) {
                        AnimationRes animationRes1 = animationRes;
                        if (animationRes == null) {
                            animationRes1 = new AnimationRes();
                        }
                        TypedArray array = paramContext.obtainStyledAttributes(attributeSet,
                                R.styleable.FrameAnimation);
                        int duration = array.getInt(R.styleable.FrameAnimation_duration, -1);
                        if (duration < 0) {
                            throw new XmlPullParserException(xmlResourceParser.getPositionDescription() +
                                    ": <item> tag requires a 'duration' attribute");
                        }
                        int res = array.getResourceId(R.styleable.FrameAnimation_drawable, 0);
                        if (res == 0) {
                            throw new XmlPullParserException(xmlResourceParser.getPositionDescription() +
                                    ": <item> tag requires a 'duration' attribute");
                        }
                        animationRes1.addDrawable(res);
                        animationRes1.addDuration(duration);
                        array.recycle();
                        animationRes = animationRes1;
                    }
                    continue;
                }
            }
            break;
        }
        return animationRes;
    }
}

package com.frank.struggle;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.frank.struggle.frameali.FrameAnimator;
import com.frank.struggle.framebaidu.AnimationsContainer;

/**
 * @author maowenqiang
 * @desc
 */
public class FrameAnimActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_xml;
    private Button btn_ali;
    private Button btn_baidu;
    private Button btn_stop;
    private Button btn_single;
    private Button btn_other;
    private ImageView img_anim;
    private AnimationDrawable animationDrawable;

    private FrameAnimator mFrameAnim;

    private AnimationsContainer.FramesSequenceAnimation mParticleAnim;

    private FrameAnimation frameAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);
        btn_xml = findViewById(R.id.btn_xml);
        btn_ali = findViewById(R.id.btn_ali);
        btn_baidu = findViewById(R.id.btn_baidu);
        btn_stop = findViewById(R.id.btn_stop);
        btn_single = findViewById(R.id.btn_single);
        btn_other = findViewById(R.id.btn_other);
        img_anim = findViewById(R.id.img_anim);


        btn_xml.setOnClickListener(this);
        btn_ali.setOnClickListener(this);
        btn_baidu.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_single.setOnClickListener(this);
        btn_other.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_xml:
                showXmlAnim();
                break;
            case R.id.btn_ali:
                // ali
                mFrameAnim = new FrameAnimator(getApplicationContext(), img_anim, R.drawable.particel_frame_anim_ali, false);
                mFrameAnim.start();
                break;
            case R.id.btn_baidu:
                // baidu
                mParticleAnim = AnimationsContainer.getInstance().createImgAnim(img_anim,
                        R.array.voice_print_particle_anim_fullscreen, 100);
                mParticleAnim.start();
                break;
            case R.id.btn_single:
                img_anim.setImageResource(R.mipmap.particle_fullscreen_0000);
                break;
            case R.id.btn_other:
                frameAnimation = new FrameAnimation(img_anim, getRes(), 100, true);
                frameAnimation.restartAnimation();
                break;
            case R.id.btn_stop:
                release();
                break;

        }
    }

    private void showXmlAnim() {
        img_anim.setImageResource(R.drawable.particel_frame_anim);
        // 1. 设置动画
        animationDrawable = (AnimationDrawable) img_anim.getDrawable();
        // 2. 获取动画对象
        animationDrawable.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }

    private void release() {
        if (animationDrawable != null) {
            animationDrawable.stop();
            animationDrawable = null;
        }
        if (mFrameAnim != null) {
            mFrameAnim.stop();
            mFrameAnim = null;
        }
        if (mParticleAnim != null) {
            mParticleAnim.stop();
            mParticleAnim = null;
        }
        if (frameAnimation != null) {
            frameAnimation.release();
            frameAnimation = null;
        }
    }

    private int[] getRes() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.particel_anim);
        int len = typedArray.length();
        int[] resId = new int[len];
        for (int i = 0; i < len; i++) {
            resId[i] = typedArray.getResourceId(i, -1);
        }
        typedArray.recycle();
        return resId;
    }
}

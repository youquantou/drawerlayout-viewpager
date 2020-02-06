package com.qingying.jizhang.jizhang.utils_;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.DrawableUtils;
import androidx.viewpager.widget.ViewPager;

import com.qingying.jizhang.jizhang.frame_.PiaoJuFragment;

public class MainViewPager extends ViewPager {


    private float startX;
    private float startY;
    private String TAG = "mainviewpager_jyl";

    public MainViewPager(@NonNull Context context) {
        super(context);
    }

    public MainViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                startX = ev.getX();
                startY = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                //来到新的坐标
                float endX = ev.getX();
                float endY = ev.getY();
                //计算偏移量
                float distanceX = endX - startX;
                float distanceY = endY - startY;
                //判断滑动方向
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    //水平方向滑动
//                   当滑动到ViewPager的第0个页面，并且是从左到右滑动
//
                    if (getCurrentItem() == 0) {
                        if (distanceX > 0)
                            getParent().requestDisallowInterceptTouchEvent(false);
                        else if (distanceX < 0) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }

                    }

                    else {
                        if (distanceX < 0) {//从右到左滑动
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }else{
                            getParent().requestDisallowInterceptTouchEvent(false);

                        }

                    }
                } else {
                    //竖直方向滑动
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}

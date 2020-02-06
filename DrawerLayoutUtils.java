package com.qingying.jizhang.jizhang.utils_;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;

import androidx.customview.widget.ViewDragHelper;
import androidx.drawerlayout.widget.DrawerLayout;

import java.lang.reflect.Field;

public class DrawerLayoutUtils {
    private static String TAG = "DrawerLayoutUtils_jyl";
    private static ViewDragHelper leftDragger;
    private static Field edgeSizeField;

    public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) {
            Log.i(TAG, "setDrawerLeftEdgeSize: null");
            return;
        }
        try {

            // 找到 ViewDragHelper 并设置 Accessible 为true
            Field leftDraggerField =
                    drawerLayout.getClass().getDeclaredField("mLeftDragger");//Right
            leftDraggerField.setAccessible(true);
            leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);

            // 找到 edgeSizeField 并设置 Accessible 为true
            edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            Log.i(TAG, "edgeSize: " + edgeSize);
            // 设置新的边缘大小
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            Log.i(TAG, "setDrawerLeftEdgeSize: " + displaySize.x);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x *
                    displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

    public static void setDrawerLayoutSLideWidth(int width) {
        try {
            if (edgeSizeField == null || leftDragger == null) {
                Log.e(TAG, "setDrawerLayoutSLideWidth: null");
                return;
            }
            edgeSizeField.setInt(leftDragger, width);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

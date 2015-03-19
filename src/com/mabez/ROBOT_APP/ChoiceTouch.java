package com.mabez.ROBOT_APP;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Scott on 19/03/2015.
 */
public class ChoiceTouch implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return true;
        }
        else {
            return false;
        }

    }
}
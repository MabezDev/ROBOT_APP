package com.mabez.ROBOT_APP;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Scott on 19/03/2015.
 *
 *Custom TouchListener That starts a drag event
 *
 */
public class ChoiceTouch extends ChoiceDrag implements View.OnTouchListener{

    @Override
    public boolean onTouch(View v, MotionEvent event) {






        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            TextView t = (TextView) v;
            String id1 = t.getText().toString();




            ClipData data = ClipData.newPlainText(id1,null);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            return true;
        }



        return false;

    }





}

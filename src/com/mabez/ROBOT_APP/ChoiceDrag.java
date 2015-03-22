package com.mabez.ROBOT_APP;

import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Scott on 19/03/2015.
 */
public class ChoiceDrag extends ProgramScreen implements View.OnDragListener {

    @Override
    public boolean onDrag(View v, DragEvent event) {
        View Current = (View) event.getLocalState();
        TextView Stringed = (TextView) Current;


        String Texted = Stringed.getText().toString();
       //need to get dropped psoiton and add to "addCommand"


        switch(event.getAction()){
            case DragEvent.ACTION_DRAG_STARTED:
                Current.setVisibility(View.INVISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Current.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DROP:

                System.out.println("Printing: "+Texted);
                addCommand(Texted,0);//HERE ADD POSITION ONCE FOUND
                break;
            default:
                break;


        }
        return true;
    }
    @Override
    public void addCommand(String command, int position){
        System.out.println("Printing form child: ");
        super.addCommand(command,position);
    }

}

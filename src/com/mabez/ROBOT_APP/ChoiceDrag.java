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
        int position = 0;


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
                 View now = (View) event.getLocalState();
                int x = (int)now.getX();
                int y = (int)now.getY();

                System.out.println("Printing: "+Texted);
                addCommand(Texted,x,y);//HERE ADD POSITION ONCE FOUND
                break;
            default:
                break;


        }
        return true;
    }
    @Override
    public void addCommand(String command,int x,int y){
        System.out.println("Printing form child: ");
        super.addCommand(command,x,y);
    }

}

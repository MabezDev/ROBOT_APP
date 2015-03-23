package com.mabez.ROBOT_APP;

import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Scott on 19/03/2015.
 */
public class ChoiceDrag extends ProgramScreen implements View.OnDragListener {

    private int finalposition;
    private int x;
    private int y;

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
                System.out.print("end");

                Current.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DROP:
                System.out.println("DROp");


                addCommand(Texted,x,y);//HERE ADD POSITION ONCE FOUND
                break;
            default:
                break;


        }
        return true;
    }
    @Override
    public void addCommand(String command,int x,int y){

        super.addCommand(command,this.x,this.y);
    }

    protected void setPosition(int x,int y){//USELESS ATM

        this.x = x;
        this.y = y;
        System.out.println("X in parent: "+this.x);
        System.out.println("Y in parent: "+this.y);
    }

}

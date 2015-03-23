package com.mabez.ROBOT_APP;

import android.content.ClipData;
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
    private String command;

    @Override
    public boolean onDrag(View v, DragEvent event) {
        View Current = (View) event.getLocalState();
        TextView Stringed = (TextView) Current;
        int position = 0;
        command = "";

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
                System.out.println("DROP");
                System.out.println(event.getY());
                System.out.println(event.getX());

                command = event.getClipData().getDescription().getLabel().toString();
                System.out.println("ChoiceDrag Recieved: "+command);
                this.x = (int)event.getX();
                this.y = (int)event.getY();


                addCommand(command,x,y);//HOLYSHIT IT WORKS

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


}

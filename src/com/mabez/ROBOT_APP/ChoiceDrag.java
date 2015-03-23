package com.mabez.ROBOT_APP;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Scott on 19/03/2015.
 *
 * Custom DragListener
 *
 */
public class ChoiceDrag extends ProgramScreen implements View.OnDragListener {

    private int x;
    private int y;
    private String command;
    private int originalPosition;

    @Override
    public boolean onDrag(View v, DragEvent event) {

        View Current = (View) event.getLocalState();
        command = "";




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
                command = event.getClipData().getDescription().getLabel().toString();
                String original = (String)event.getClipData().getItemAt(0).getText();
                if(original!=null){
                    originalPosition = Integer.valueOf(original);
                    System.out.println("Original Position: "+originalPosition);
                }

                System.out.println("Position"+original);
                System.out.println("Choice Drag Received: "+command);

                this.x = (int)event.getX();
                this.y = (int)event.getY();

                addCommand(command,x,y,originalPosition);//HOLY SHIT IT WORKS
                break;
            default:
                break;


        }
        return true;
    }


    @Override
    public void addCommand(String command,int x,int y,int orig){

        super.addCommand(command,x,y,orig);
    }


}

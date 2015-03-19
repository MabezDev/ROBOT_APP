package com.mabez.ROBOT_APP;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by Scott on 19/03/2015.
 */
public class ProgramScreen extends Activity {

    private TextView forward,right,left,loop;
    private ListView CodeArea;
    private ArrayAdapter<String> myAdapter;
    private ArrayList<String> Code = new ArrayList<String>();
    private View.OnDragListener myDrag;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programscreen);
        forward = (TextView) findViewById(R.id.forward);
        right = (TextView) findViewById(R.id.right);
        left = (TextView) findViewById(R.id.left);
        CodeArea = (ListView) findViewById(R.id.CodeArea);

        Code = new ArrayList<String>();


        myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Code);
        CodeArea.setAdapter(myAdapter);

        Setup();
    }

    private void Setup(){//needs work

        Code.add("Papipls");
        myAdapter.notifyDataSetChanged();
        /*
        deletion on tap
        */

        /*
        set drag listener on text views
         */
        forward.setOnTouchListener(new ChoiceTouch());
        right.setOnTouchListener(new ChoiceTouch());
        left.setOnTouchListener(new ChoiceTouch());


        CodeArea.setOnDragListener(new ChoiceDrag());



    }

    protected void addCommand(String command){
        myAdapter.add(command);
        myAdapter.notifyDataSetChanged();
    }

}

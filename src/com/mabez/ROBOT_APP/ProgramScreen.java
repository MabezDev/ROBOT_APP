package com.mabez.ROBOT_APP;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
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
    private static ArrayAdapter<String> myAdapter;
    private static ArrayList<String> Code = new ArrayList<String>();
    private View.OnDragListener myDrag;
    private static ArrayList<View> Items;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programscreen);
        forward = (TextView) findViewById(R.id.forward);
        right = (TextView) findViewById(R.id.right);
        left = (TextView) findViewById(R.id.left);
        CodeArea = (ListView) findViewById(R.id.CodeArea);

        Code = new ArrayList<String>();
        Items = new ArrayList<View>();


        myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Code);
        CodeArea.setAdapter(myAdapter);

        Setup();
    }

    private void Setup(){//needs work



        /*
        deletion on tap
        */
        CodeArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String toDelete = myAdapter.getItem(position);
                myAdapter.remove(toDelete);
                myAdapter.notifyDataSetChanged();
            }
        });

        CodeArea.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String toDelete = myAdapter.getItem(position);
                myAdapter.remove(toDelete);
                myAdapter.notifyDataSetChanged();
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
        });



        /*
        set drag listener on text views
         */
        forward.setOnTouchListener(new ChoiceTouch());
        right.setOnTouchListener(new ChoiceTouch());
        left.setOnTouchListener(new ChoiceTouch());


        CodeArea.setOnDragListener(new ChoiceDrag());
    }

    private void setChildOnDrag(){
        if(Items != null){
            Items.clear();
        }

        for(int i = 0;i<Code.size();i++){
            Items.add(CodeArea.getChildAt(i));
        }

        for(View Item: Items){
            Item.setOnTouchListener(new ChoiceTouch());
        }
    }

    public void addCommand(String command,int position){
        System.out.println("Got From Sub Class: "+command);
        myAdapter.insert(command,position);
        myAdapter.notifyDataSetChanged();


    }

}

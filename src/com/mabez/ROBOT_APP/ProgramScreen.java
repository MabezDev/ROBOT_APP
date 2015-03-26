package com.mabez.ROBOT_APP;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;



/**
 * Created by Scott on 19/03/2015.
 *
 * Main Programming Screen
 *
 */
public class ProgramScreen extends Activity {

    private TextView forward,right,left,loop;
    private Button SubmitButton;
    private static ListView CodeArea;
    private static ArrayAdapter<String> myAdapter;
    private static ArrayList<String> Code = new ArrayList<String>();
    private View.OnDragListener myDrag;
    private static ArrayList<View> Items;

    private static final int PROCESS_OK = 1;

    private ActionBar myBar;




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programscreen);

        //myBar = getActionBar();




        /*
        Initialize Variables and link Objects to layout
         */
        SubmitButton = (Button)findViewById(R.id.SubmitButton);
        forward = (TextView) findViewById(R.id.forward);
        right = (TextView) findViewById(R.id.right);
        left = (TextView) findViewById(R.id.left);
        CodeArea = (ListView) findViewById(R.id.CodeArea);

        Code = new ArrayList<String>();
        Items = new ArrayList<View>();


        myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Code);//add custom layout down the line
        CodeArea.setAdapter(myAdapter);

        Setup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.myactionbar, menu);
        return true;
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                System.out.println("Tapped Icon");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Setup(){



        /*
        Deletion on tap
        */
        CodeArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Code.set(position,"");
                myAdapter.notifyDataSetChanged();
            }
        });

        /*
        Add Long click to rearrange
         */

        CodeArea.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                String toDelete = myAdapter.getItem(position);
                ClipData data = ClipData.newPlainText(toDelete, Integer.toString(position));//main text, then original position

                myAdapter.insert("", position);
                Code.remove(position + 1);

                myAdapter.notifyDataSetChanged();

                View toFind = null;
                if(toDelete.equals("Forward")){
                    toFind = forward;
                }
                if(toDelete.equals("Right")){
                    toFind = left;
                }
                if(toDelete.equals("Left")){
                    toFind = right;
                }

                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(toFind);
                view.startDrag(data, shadowBuilder, toFind, 0);
                return true;
            }
        });



        /*
        Set touch drag listener on text views
         */
        forward.setOnTouchListener(new ChoiceTouch());
        right.setOnTouchListener(new ChoiceTouch());
        left.setOnTouchListener(new ChoiceTouch());

        /*
        Set Drag listener on The CodeArea(ListView)
         */
        CodeArea.setOnDragListener(new ChoiceDrag());

        /*
        Add Buffer to Coding Area
         */

        for(int i = 0;i<10;i++){//used to add code slots so each thing can be addressed
            myAdapter.add("");
            myAdapter.notifyDataSetChanged();
        }

        /*
        Add Click Listener for button
         */

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToProcessing = new Intent(ProgramScreen.this,Processing.class);
                GoToProcessing.putStringArrayListExtra("codearray",Code);//send codes through to processing
                startActivityForResult(GoToProcessing,PROCESS_OK);//FIND OUT HOW TO USE THIS
            }
        });

    }
    /*
    Find out if the sound algorithm was played or not
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PROCESS_OK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                System.out.println("Result Okay");

                // Do something with the contact here (bigger example below)
            }
        }
    }

    /*
    Handles adding and rearranging Items to CodeArea(ListView)
     */

    public void addCommand(String command,int x,int y, int origInt){
        System.out.println("Got X:"+x);
        System.out.println("Got Y:"+y);
        System.out.println("Got From Sub Class: "+command);

        int position = CodeArea.pointToPosition(x,y);

        System.out.println("Dragged to: "+(position+1));


        if(position!=-1) {//for some reason returning -1 if you pick an item up and put it back in the same place
            Code.set(position, command);
            myAdapter.add("");
            myAdapter.notifyDataSetChanged();
        } else {
            Code.set(origInt, command);
            myAdapter.notifyDataSetChanged();

        }


    }




}

package com.mabez.ROBOT_APP;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import java.util.ArrayList;

/**
 * Created by Scott on 23/03/2015.
 *
 * Handles the sound out of Device, including timings
 *
 */
public class Processing extends Activity{

    private static final int START_DURATION = 1;
    private static final int FORWARD_DURATION = 2;
    private static final int RIGHT_DURATION = 3;
    private static final int LEFT_DURATION = 4;
    private static final int END_DURATION= 7;

    private ProgressBar CompletionBar;
    private Button GoBack;

    private ArrayList<String> Code;
    private ArrayList<Integer> durations;
    private PlaySound p;
    ArrayList<byte[]> preGenTone;
    private Thread sound;
    private boolean DONE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent initial = getIntent();
        p = new PlaySound();

        Code = initial.getStringArrayListExtra("codearray");
        durations = new ArrayList<Integer>();

        setContentView(R.layout.processing);

        CompletionBar = (ProgressBar) findViewById(R.id.progressBar);
        GoBack = (Button) findViewById(R.id.GoBackButton);

        CompletionBar.setProgress(50);

        GoBack.setText("Start");

        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Done State:"+ DONE);
                if(DONE == false) {
                    setResult(RESULT_CANCELED);
                    finish();
                }else{
                    GoBack.setText("Cancel");
                    DONE = false;
                    sound.start();

                }
            }
        });

        SortCodeArray();


    }



    private void SortCodeArray() {
        //make sure no stray values are in the array
        if(durations.size()>0){
            durations.clear();
        }
        durations.add(START_DURATION);
        for(String item: Code){
            // ignore blank spaces
            if(item!=""){
                if(item.equals("Forward")){
                    durations.add(FORWARD_DURATION);
                }
                if(item.equals("Right")){
                    durations.add(RIGHT_DURATION);
                }
                if(item.equals("Left")){
                    durations.add(LEFT_DURATION);
                }
            }
        }
        durations.add(END_DURATION);
        System.out.println("INT ARRAY: "+durations.toString());
        sound = new Thread(new Runnable() {
            @Override
            public void run() {
                playSounds();
            }
        });
        //give time for ui to load

    }


    private void playSounds() {
        for(int i = 0;i< durations.size();i++){
           PlaySound p = new PlaySound();
            p.setDuration(durations.get(i)*2);
            p.genTone();
            try {
                Thread.sleep(durations.get(i)*750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}

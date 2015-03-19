package com.mabez.ROBOT_APP;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends Activity {
    /**
     * Called when the activity is first created.
     */

    private TextView Title;
    private ImageButton JimmyStart;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Title = (TextView) findViewById(R.id.TitleText);
        JimmyStart = (ImageButton) findViewById(R.id.JimmyStartButton);
        JimmyStart.setImageResource(R.drawable.ic_launcher);

        SetupPage();
    }

    private void SetupPage(){
        JimmyStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(Main.this,ProgramScreen.class);
                startActivity(change);
            }
        });
    }
}

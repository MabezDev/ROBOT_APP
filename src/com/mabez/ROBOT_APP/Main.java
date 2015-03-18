package com.mabez.ROBOT_APP;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageButton;
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
    }
}

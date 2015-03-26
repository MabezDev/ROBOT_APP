package com.mabez.ROBOT_APP;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * Created by Scott on 26/03/2015.
 */
public class PlaySound {

    public double duration = 0;
    // seconds
    double freqOfTone = 1800;           // hz
    int sampleRate = 4000;              // a number
    double dnumSamples = 0;
    int numSamples = 0;
    double sample[] = null;
    byte generatedSnd[];
    AudioTrack audioTrack;


    public void GetdNum(){
        dnumSamples = Math.ceil((duration) * sampleRate);

    }

    public void GetNum(){
        numSamples = (int) (dnumSamples);

    }
    public void sample(){
        sample = new double[numSamples];

    }

    public void setDuration(int millis){
        this.duration = millis;
    }



    public void genTone(){
        GetdNum();
        GetNum();
        sample();
        generatedSnd = new byte[2 * numSamples];

        // fill out the array

        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (double dVal : sample) {
            short val = (short) (dVal * 32767);
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }
        playSound();


    }

    private void playSound(){

        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                8000, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                AudioTrack.MODE_STREAM);

        audioTrack.play();
        audioTrack.write(generatedSnd, 0, numSamples);
        audioTrack.flush();
        audioTrack.stop();



    }
}


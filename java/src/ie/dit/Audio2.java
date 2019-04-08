package ie.dit;

import ddf.minim.AudioInput;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio2 extends PApplet{

    public static int SAMPLE_RATE = 44100;
    public static int RESOLUTION = 16;
    public static int FRAME_SIZE = 1024;

    Minim minim;
    AudioInput ai;
    float lerpedAverage = 0;

    public void settings(){
        size(FRAME_SIZE, 800);
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, FRAME_SIZE, SAMPLE_RATE, RESOLUTION);

    }

    public void draw(){
        colorMode(HSB, FRAME_SIZE);
        background(0);

        float halfH = height / 2;
        float total = 0;


        for (int i = 0; i < ai.bufferSize(); i++) {
            stroke(i, FRAME_SIZE, FRAME_SIZE);
            line(i, halfH, i, halfH + ai.left.get(i) * halfH);
            total += (float) Math.abs(ai.left.get(i));

        }

        float average = (float) total / FRAME_SIZE;
        lerpedAverage = lerp(lerpedAverage, average, 0.5f);

        fill(random(255), random(255), random(255));
        ellipse(width/ 2, halfH, lerpedAverage * 5000, lerpedAverage * 5000);

    }

}
package example;

import processing.core.*;

public class Wave 
{
    MyVisual mv;
    float wave_height = 0;

    public Wave(MyVisual mv) {
        this.mv = mv;
        wave_height = mv.height / 2;

    }

    public void render()
    {
        mv.colorMode(PApplet.HSB);

        for(int i = 0; i < mv.getFFT().specSize(); i++)
        {
            mv.stroke(255, 100, 100);

            //mv.rect(i, wave_height, i, mv.getFFT().getBand(i) * 10);

        }
    }
}

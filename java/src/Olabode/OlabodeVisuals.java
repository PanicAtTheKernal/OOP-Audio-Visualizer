package Olabode;

import ie.tudublin.MyVisual;
import processing.core.PApplet;

public class OlabodeVisuals extends MyVisual
{
    MyVisual mv;
    float wave_height;
    float wave_width;
    float average;
    float sum;
    float wave_amp;

    float[] lerpedBuffer;

    float random_var_x = random(0, 2);

    int random_var = (int) random_var_x;

    public OlabodeVisuals(MyVisual mv) {
        this.mv = mv;
        wave_height = this.mv.height / 2;
        wave_width = this.mv.width / 2;

        lerpedBuffer = new float[this.mv.width];

        average = this.mv.getAudioBuffer().size();
        wave_amp = this.mv.getSmoothedAmplitude();
    }

    public void lerpedBuffer()
    {
        for(int i = 0; i < mv.getAudioBuffer().size(); i++)
        {
            lerpedBuffer[i] = lerp(lerpedBuffer[i], mv.getAudioBuffer().get(i), 0.05f);
        }
    }
    
    

  
    public void render()
    {

        //Lerp function
        lerpedBuffer();

        //Linear waveform
        if(random_var == 0)
        {

            for(int i = 0; i < mv.getAudioBuffer().size(); i+=10)
            {

                float wave_color = PApplet.map(i, 0, mv.getAudioBuffer().size(),  0, 255);

                //float wave_color = PApplet.map(mv.getAudioBuffer().get(i), -1, 1, 0, 255);

                float f = lerpedBuffer[i] * wave_height * 4.0f;

                
                mv.stroke(wave_color, 255, 255);
    
    
                mv.line(i, wave_height - f, i, wave_height + f);
            }
        }
        else
        {
            for(int i = 0; i < mv.getAudioBuffer().size(); i++)
            {
                float amplitude = mv.getSmoothedAmplitude(); //amplitude of wave
                float t = 0; 
                float frequency = 0;
                float y = 0; //Float which is used to calculate the sine wave
                float r = 15; //radius of circle

                float wave_color = PApplet.map(i, 0, mv.getAudioBuffer().size(),  0, 255);

                //Sinusodial Waveform
                //Need the formula Y = amplitude + sin(2 PI * t / frequency)
                t = i; //Make ith cycle
                frequency = mv.getFFT().getFreq(i); //calculate the frequency of position i
                y = amplitude + sin(TWO_PI * t / frequency); //Formula for sine wave 


                mv.stroke(wave_color, 255, 255);
                mv.ellipse(i * r, wave_height + (y * r), r, r); //Drawing circles
                
            }
        }
    }
}

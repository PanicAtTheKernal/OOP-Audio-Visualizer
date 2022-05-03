package Olabode;

import ie.tudublin.MyVisual;

import ie.tudublin.MainWindow;

import processing.core.PApplet;
import processing.core.PConstants;

public class OlabodeVisual extends MyVisual{
    
    float wave_height = 0; //maximum height of the wave
    float wave_width = 0; //maximum height of the wave
    float wave_amp; //amplitude of the wave
    float wave_color = 0; //wave color

    float[] lerpedBuffer;

    private float scale = 0.3f; 
    private float minScale = 0.3f;

    public OlabodeVisual(MainWindow window, String name)
    {
        super(window, name);
        wave_height = (window.height / 2); //sets the wave height to be exactly half of the screen
        wave_width = (window.width / 2); //sets the wave width to be exactly half of the screen
        
    }

    


    //Creates a sinusodial wave
    public void Sinusodial()
    {
        float frequency = 0; //frequency
        float prev_freq = 0; //previous frequency value, to be used to plot prev and curr points on wave
        
        float y = 0; //Float which is used to calculate the sine wave
        float prev_y = 0; //previous y value
        float r = 6.5f; //radius of circle
        float amplitude = 0; //amplitude of wave
        float prev_wave_amp = 0; //previous amplitude
        
        for(int i = 0; i < window.getAudioBuffer().size(); i++)
        {
            //Calls the function to calculate the amplitude
            window.calculateAverageAmplitude();

            wave_amp = PApplet.map(window.getAudioBuffer().get(i), 0, 0.5f, 0, wave_height - 30f); //sets the amplitude of the wave to be 

            frequency= window.getFFT().getBand(i) ; //calculate the frequency position of i
            
            //Calculating the sine wave in the form y = amp + sin(2πt)
            float period = PConstants.TWO_PI * i; //period of wave -> 2πt where i is t
            y = wave_amp + PApplet.sin(period / frequency); 

            //gathers the previous values of the Sin Wave to plot a line across the prev and current points after the starting point
            if(i > 1)
            {
                prev_wave_amp = PApplet.map(window.getAudioBuffer().get(i-1), 0, 0.5f, 0, wave_height - 30f);
                prev_freq = window.getFFT().getBand(i-1);
                float prev_period = PConstants.TWO_PI * (i - 1);
                prev_y = prev_wave_amp + PApplet.sin(prev_period / prev_freq);
            }

            //Changes the weight of the stroke based on the sound intensity
            window.strokeWeight(0.9f * window.getIntensity());

            //draws the color of the wave
            window.stroke(wave_color, 255, 255);

            //draws an array of circles in the form of a sine wave, maps lines to connect the current point to the previous point except if the point is 0   
            window.ellipse((i * r), ((wave_height + y) + (wave_height + y) * window.getAudioBuffer().get(i) ), r * scale, r * scale); //Drawing the circular wave in the form of a sine wave
            if(i > 1) 
            {
                window.line(((i - 1) * r), ((wave_height + prev_y) + (wave_height + prev_y) * window.getAudioBuffer().get(i - 1) ), (i * r), ((wave_height + y) + (wave_height + y) * window.getAudioBuffer().get(i))) ;
            }
        }
    }



   @Override
   public void keyPressed() {
       
   }

   @Override
   public void update() {
       //Changes the color of the wave based on the intensity
       scale = minScale + (window.getIntensity() * 0.02f);
       wave_color = scale * 100;
   }

   //Renders the sinusodial array in singlemode
   @Override
   public void render() {

      if(isSingleMode())
      {
          window.background(0);
      }

      Sinusodial();

   }
}



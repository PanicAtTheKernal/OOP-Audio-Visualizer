package Olabode;

import ie.tudublin.MyVisual;

import ie.tudublin.MainWindow;

import processing.core.PApplet;
import processing.core.PConstants;

public class OlabodeVisual extends MyVisual{
    
    int mode = 0;
    float wave_height = 0;
    float wave_width = 0;
    float wave_amp;
    float wave_color = 0;

    float[] lerpedBuffer;

    private float scale = 0.3f; 
    private float minScale = 0.3f;

    public OlabodeVisual(MainWindow window, String name)
    {
        super(window, name);
        wave_height = window.height / 2;
        wave_width = window.width / 2;

        lerpedBuffer = new float[window.width];
        
    }


    //Creates a sinusodial wave
    public void Sinusodial()
    {
        float frequency = 0;
        float y = 0; //Float which is used to calculate the sine wave
        float r = 6.5f; //radius of circle
        float amplitude = 0;
        
        for(int i = 0; i < window.getAudioBuffer().size(); i++)
        {
            
            window.calculateAverageAmplitude();
            amplitude = window.getSmoothedAmplitude();

            wave_amp = PApplet.map(window.getAudioBuffer().get(i), 0, 0.5f, 0, wave_height - 30f);

            frequency= window.getFFT().getBand(i) ; //calculate the frequency position of i
            
            //Calculating the sine wave in the form y = amp + sin(2pi*t)
            float period = PConstants.TWO_PI * i;
             y = wave_amp + PApplet.sin(period / frequency);

            //Changes the color of the wave
            


            window.stroke(wave_color, 255, 255);

                
            window.ellipse((i * r), (wave_height + y) + (wave_height + y) * window.getAudioBuffer().get(i) , r, r); //Drawing the circular wave in the form of a sine wave
            if(i > 1)
            {
                window.line((i - 1) * r, (wave_height + y) + (wave_height + y) * window.getAudioBuffer().get(i - 1), (i * r), (wave_height + y) + (wave_height + y) * window.getAudioBuffer().get(i));
            }
            //window.ellipse(i * 10, wave_height + y, r, r); //Drawing the circular wave in the form of a sine wave
            //window.ellipse((i * r), (wave_height + (y * r)) - 50, r, r); //Drawing the circular wave in the form of a sine wave

        }
    }



   @Override
   public void keyPressed() {
       
   }

   @Override
   public void update() {
       scale = minScale + (window.getIntensity() * 25);
       wave_color = scale;
   }

   @Override
   public void render() {
       
      if(isSingleMode())
      {
          window.background(0);
      }

      Sinusodial();

   }
}



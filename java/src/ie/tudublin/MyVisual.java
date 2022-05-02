package ie.tudublin;

import example.AudioBandsVisual;
import example.Wave;
import example.WaveForm;
import Olabode.OlabodeVisuals;

public class MyVisual extends Visual
{    
    WaveForm wf;
    AudioBandsVisual abv;
    Wave wav;
    OlabodeVisuals olab_wav;

    public void settings()
    {
        size(1024, 500);
        
        // Use this to make fullscreen
        //fullScreen();

        // Use this to make fullscreen and use P3D for 3D graphics
        //fullScreen(P3D, SPAN); 
    }

    public void setup()
    {
        startMinim();
                
        // Call loadAudio to load an audio file to process 
        loadAudio("rasputin.mp3"); 

        //loadAudio("ark.mp3"); 


        
        // Call this instead to read audio from the microphone
        //startListening(); 
        
        wf = new WaveForm(this);
        abv = new AudioBandsVisual(this);
        wav = new Wave(this);
        olab_wav = new OlabodeVisuals(this);
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
    }

    public void draw()
    {
        background(0);
        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        // Call this is you want to use frequency bands
        calculateFrequencyBands(); 

        // Call this is you want to get the average amplitude
        calculateAverageAmplitude();        
        //wf.render();
        //wav.render();
        //abv.render();
        olab_wav.render();
    }
}

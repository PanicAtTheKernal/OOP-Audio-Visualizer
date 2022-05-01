package ie.tudublin;

import daniel.*;
import c123456.*;

import java.util.ArrayList;

import processing.core.PApplet;

public class MainWindow extends Visual {
    private ArrayList<MyVisual> ourVisuals = new ArrayList<MyVisual>();
    private int currentVisual = 0;
    private int intensity = 1;
    private int maxIntensity = 10;
    private boolean singleMode = true;
    private int defaultColor = 0;
    

    public void settings()
    {
        size(800, 800, P3D);

    }
    
    public void setup()
    {
        colorMode(HSB);
        noCursor();
        
        //Adding all our visual to an arrayList
        ourVisuals.add(new BryansVisual(this, "Line", 200));
        ourVisuals.add(new BryansVisual(this, "Cheese" ,400));
        ourVisuals.add(new BryansVisual(this, "2",600));
        ourVisuals.add(new DanielsVisual(this, "Fireworks"));

        startMinim();
        loadAudio("rasputin.mp3");
        getAudioPlayer().play();

        background(defaultColor);
    }

    public void keyPressed()
    {
        if (key > '0' && key <= '9')
        {
            // This is done so instead of pressing 0 and 1 you can press 1 and 2 instead 
            char keyOffest = (char)((int)key - 1);
            int newIndex = asciiToInt(keyOffest);
            if(newIndex < ourVisuals.size())
            {
                currentVisual = newIndex;                    
            }

            if (currentVisual < ourVisuals.size())
            {
                if (singleMode)
                    ourVisuals.get(currentVisual).keyPressed();
                ourVisuals.get(currentVisual).toggleRender();
            }
        }
        
        if (key == '0' && !singleMode)
        {
            currentVisual = ourVisuals.size();
        }

        if (key == 'b')
        {
            toggleSingleMode();
            // Set the current visual to 0 to stop the all index bug from triggering
            if(singleMode)
                currentVisual = 0;
        }

        if (key == 'v' && intensity > 1)
            intensity--;

        if (key == 'n' && intensity < maxIntensity)
            intensity++;

    }

    public int asciiToInt(char key)
    {
        return (int)key - (int)'0'; 
    }

    public void toggleSingleMode()
    {
        singleMode = !singleMode;

        for(MyVisual v: ourVisuals)
        {
            v.setSingleMode(singleMode);
        }
    }

    public void drawInfoUI()
    {
        float heigthBox = 30f;
        String mode;
        String toggled;
        String name;

        if(singleMode)
        {
            mode = "Single Mode";
            toggled = "Current: ";
        }
        else 
        {
            mode = "Multi-mode";
            toggled = "Toggled: ";
        }

        if(currentVisual < ourVisuals.size())
        {
            name = ourVisuals.get(currentVisual).getName();
        }
        else
        {
            name = "All";
        }

        fill(0);
        noStroke();
        rect(0, height - heigthBox, width, heigthBox);
        
        fill(255);
        textSize(20);
        textAlign(LEFT, CENTER);
        text("Mode: "+mode, 20, height-15);
        
        textAlign(CENTER, CENTER);
        text(toggled+name, width/2, height-15);

        textAlign(RIGHT, CENTER);
        text("Intensity: "+intensity, width-20, height-15);
    }

    public void draw()
    {
        try
        {
            // Call this if you want to use FFT data
            calculateFFT(); 
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }

        if(currentVisual < ourVisuals.size())
        {       
            if (singleMode) 
            {
                ourVisuals.get(currentVisual).update();
                ourVisuals.get(currentVisual).render();
            }
            else 
            {
                background(defaultColor);
                for(MyVisual v:ourVisuals)
                {
                    if(v.shouldRender())
                    {
                        v.update();
                        v.render();
                    }
                }
            }
        }
        else if(currentVisual == ourVisuals.size())
        {
            background(defaultColor);
            for(MyVisual v:ourVisuals)
            {
                v.update();
                v.render();
            }
        }

        drawInfoUI();
    }

    public int getIntensity() {
        return intensity;
    }

}

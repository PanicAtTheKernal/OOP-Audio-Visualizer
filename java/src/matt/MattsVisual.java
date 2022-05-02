package matt;

import java.util.ArrayList;

import ie.tudublin.MainWindow;
import ie.tudublin.MyVisual;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

// My Visual is fireworks show that synchronization with the beat of the song
public class MattsVisual extends MyVisual {

    float var;
    float cy = 400f;
    float radius = 200;
    int kickamt;
    private ArrayList<Ring> rings = new ArrayList<Ring>();
    public MattsVisual(MainWindow window, String name) {
        super(window, name);
        
        kickamt=0;
        
    }

    @Override
    public void update() {
        
        
        if(window.getBeat().isKick())
        {
            // kickamt+=1;
            // if(kickamt>2)
            // {
            //     rings.add(new Ring(window, 10, 255));
            //     for(Ring r: rings)
            //     {
            //         r.done = false;
            //     }
            //     kickamt=0;
            // }
            
            
        }
        
        
        
        
    }
    
    @Override
    public void render() {
        if(isSingleMode()) window.background(0); 

        // for(Ring r: rings)
        // {
        //     r.update();
        // }
        for(Ring r: rings)
        { 
            if(r.done == false)
            {
                r.shootRing();  
            } 
            else
            {
                r = null;
            }
        }

        

            
        // window.circle(center, center, var);
        // window.colorMode(PApplet.HSB);
        // for(int i = window.getAudioBuffer().size()-1 ; i >=0  ; i--)
        // {
        //     window.fill(
        //         PApplet.map(i, 0, window.getAudioBuffer().size(), 0, 255)
        //         , 255
        //         , 255
        //     );
            
        //     window.circle(cy, cy, 1000 * window.getAudioBuffer().get(i));
        
        // }



    }

    @Override
    public void keyPressed() {
        // PApplet.println(window.key);
    }

}
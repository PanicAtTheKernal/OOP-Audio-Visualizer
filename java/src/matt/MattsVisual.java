package matt;

import java.util.ArrayList;

import ie.tudublin.MainWindow;
import ie.tudublin.MyVisual;
import processing.core.PApplet;

//my visual is the circle that shoots out rings
public class MattsVisual extends MyVisual {

    float cy = 400f;
    float radius = 600;
    int kickamt;
    int startcolour;
    int beatFreq, beatSpeed;
    float startR, lerpR;
    int count;

    private ArrayList<Ring> rings = new ArrayList<Ring>();

    public MattsVisual(MainWindow window, String name) 
    {
        super(window, name);
        
        kickamt=0;
        startcolour = 0;
        count = 0;
    }

    @Override
    public void update() 
    {
        if(window.getIntensity()<=3)
        {
            beatFreq = 4;
        }
        if(window.getIntensity()>=4)
        {
            beatFreq = 3;
        }
        if(window.getIntensity()>=7)
        {
            beatFreq = 2;
        }

        beatSpeed = 10 + window.getIntensity();
        
    }
    
    
    @Override
    public void render() {
        if(isSingleMode()) window.background(0); 

        for(Ring r: rings)
        { 
            if(r.done == false)
            {
                r.shootRing();  
            } 
            else
            {
                r = null;
                rings.remove(r);
                
            }
        }

        

            
        //window.circle(cy, center, var);
        window.colorMode(PApplet.HSB);
        window.fill(startcolour, 255, 255);
        startcolour+=5;
        if(startcolour>255)
        {
            startcolour = 0;
        }
        if(count==0)
        {
            startR = radius * window.getAudioBuffer().get(1);

        }
        if(count%3==0)
            {   
                lerpR = radius * window.getAudioBuffer().get(1);
                count = 1;
           }
        if(startR<0)
        {
            startR= -startR;
        }
        if(lerpR<0)
        {
            lerpR= -lerpR;
        }
        startR = PApplet.lerp(startR, lerpR,0.25f);
        count++;
        
        

        if(window.getBeat().isKick())
        {
            kickamt+=1;
            if(kickamt>beatFreq)
            {
                rings.add(new Ring(window, beatSpeed, startcolour, startR));
                for(Ring r: rings)
                {
                    r.done = false;
                }
                kickamt=0;
            }
            
            
        }
        window.circle(cy, cy, startR);



    }

    @Override
    public void keyPressed() {
        // PApplet.println(window.key);
    }

}
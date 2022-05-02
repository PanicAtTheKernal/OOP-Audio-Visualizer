package matt;

import java.util.ArrayList;

import ie.tudublin.MainWindow;
import processing.core.PApplet;


public class Ring {
    private MainWindow window;
    private float speed;
    private int colour;
    float radius;
    float cy;
    float i, angle, x1, y1,x2,y2;
    boolean done;
    public Ring(MainWindow window, float speed, int colour)
    {   
        this.window = window;
        this.speed = speed;
        this.colour = colour;
        done = true;
        radius = 30;
        cy = 400f;
        
    }
    public void shootRing()
    {
        radius += speed;
        this.render();
        if(radius>600)
        {
            done = true;
            radius = 30;
        }

    }
    public void render()
    {
        window.colorMode(PApplet.HSB);
        window.stroke(colour,255,255);
        
        for(i = 0; i < 360; i += 0.1)
        {
                angle = i;
                x1 = (float) (radius * Math.cos(angle * PApplet.PI / 180));
                y1 = (float) (radius * Math.sin(angle * PApplet.PI / 180));
                x2 = (float) (radius*1.5 * Math.cos(angle * PApplet.PI / 180));
                y2 = (float) (radius*1.5 * Math.sin(angle * PApplet.PI / 180));
                window.line(x1+cy,y1+cy,x2+cy,y2+cy);
        }
    }
    
}

package daniel;

import ie.tudublin.MainWindow;
import processing.core.PVector;
import processing.core.PApplet;

public class FireworkParticle {
    private int lifetime;  
    private int intialLifetime;
    private int speed;
    private int radius;
    private PVector cords;
    private int decay;
    private MainWindow window;
    
    public FireworkParticle(MainWindow window, int lifetime, int speed, int radius, PVector cords) {
        this.window = window;
        this.lifetime = lifetime;
        this.intialLifetime = lifetime;
        this.speed = speed;
        this.radius = radius;
        this.cords = cords;
        decay = 5;
    }

    public void render() 
    {
        if(lifetime > 0)
        {
            int radiusLifetime = (int)PApplet.map(lifetime, 0, intialLifetime, 0, radius);
            window.ellipse(cords.x, cords.y, radiusLifetime, radiusLifetime);
            lifetime = lifetime - decay;
            // cords.y -= 1;
        }
        window.stroke(255);
        window.pushMatrix();
        window.translate(cords.x, cords.y);
        for(int i = 0; i < 20; i++)
        {
            float x1 = PApplet.map(i,0,20,-100,100);
            float y1 = PApplet.map(i,0,20,-100,100);;
            // if (x1 < 0)
            // {
            //     y1 = PApplet.map(i,0,10,0,100);
            // }
            // else
            // {
            //     y1 = PApplet.map(i,10,20, 100, 0);
            // }

            window.line(x1, y1, 0,0);
        }
        // for(int i = 0; i < 20; i++)
        // {
        //     float x1 = PApplet.map(i,0,20,-100,100);
        //     float y1 = 0;
        //     if (x1 < 0)
        //     {
        //         y1 = PApplet.map(i,0,10,0,-100);
        //     }
        //     else
        //     {
        //         y1 = PApplet.map(i,10,20, -100, 0);
        //     }

        //     window.line(x1, y1, 0,0);
        // }
        window.popMatrix();
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}

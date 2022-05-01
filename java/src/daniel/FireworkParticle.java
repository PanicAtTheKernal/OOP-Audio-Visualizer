package daniel;

import ie.tudublin.MainWindow;
import processing.core.PVector;

public class FireworkParticle {
    private float speed;
    private float radius;
    private float MaxRadius;
    private PVector targeCords;
    private PVector currentCords;
    private PVector secondCords;
    private MainWindow window;
    
    public FireworkParticle(MainWindow window, int MaxRadius, float speed, PVector targeCords) {
        this.window = window;
        this.speed = speed;
        this.radius = radius;
        this.targeCords = targeCords;
        this.currentCords = new PVector(0,0);
        this.secondCords = new PVector(0,0);
    }

    public void render() 
    {
        window.fill(255);
        window.stroke(255);
        // have to compare it as greater than because float values are not arccurate
        if(PVector.dist(currentCords, targeCords) > 1f)
        {
            currentCords.lerp(targeCords, speed);
            window.ellipse(a, b, c, d);
        }
        else
        {
            secondCords.lerp(targeCords, speed);
            window.line(targeCords.x, targeCords.y, secondCords.x, secondCords.y);
            
        }


        // if(lifetime > 0)
        // {
        //     int radiusLifetime = (int)PApplet.map(lifetime, 0, intialLifetime, 0, radius);
        //     window.ellipse(currentCords.x, currentCords.y, radiusLifetime, radiusLifetime);
        //     lifetime = lifetime - decay;
        //     // cords.y -= 1;
        // }

    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}
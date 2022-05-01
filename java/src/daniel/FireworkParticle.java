package daniel;

import ie.tudublin.MainWindow;
import processing.core.PApplet;
import processing.core.PVector;

public class FireworkParticle {
    private float speed;
    private float radius;
    private float secondRadius;
    private float maxRadius;
    private PVector targeCords;
    private MainWindow window;
    
    public FireworkParticle(MainWindow window, float maxRadius, float speed, PVector targeCords) {
        this.window = window;
        this.speed = speed;
        this.maxRadius = maxRadius;
        this.targeCords = targeCords;
        this.radius = 0;
        this.secondRadius = maxRadius;
    }

    public void render() 
    {
        // have to compare it as greater than because float values are not arccurate
        if((maxRadius - radius) > 1f)
        {
            radius = PApplet.lerp(radius, maxRadius, speed);
            window.ellipse(targeCords.x, targeCords.y, radius, radius);
        }
        else
        {
            secondRadius = PApplet.lerp(secondRadius, 0, speed);
            if (secondRadius > 1f)
                window.ellipse(targeCords.x, targeCords.y, secondRadius, secondRadius);
            
        }
    }

}

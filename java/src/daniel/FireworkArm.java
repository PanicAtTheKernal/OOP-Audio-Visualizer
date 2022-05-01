package daniel;

import ie.tudublin.MainWindow;
import processing.core.PVector;

public class FireworkArm {

    private float speed;
    private PVector targeCords;
    private PVector currentCords;
    private PVector secondCords;
    private MainWindow window;
    
    public FireworkArm(MainWindow window, float speed, PVector targeCords) {
        this.window = window;
        this.speed = speed;
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
            window.line(0, 0, currentCords.x, currentCords.y);
        }
        else
        {
            secondCords.lerp(targeCords, speed);
            window.line(targeCords.x, targeCords.y, secondCords.x, secondCords.y);
            
        }


    }
}

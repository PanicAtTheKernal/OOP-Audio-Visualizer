package daniel;

import java.util.ArrayList;

import ie.tudublin.MainWindow;
import processing.core.PApplet;
import processing.core.PVector;

public class Firework {
    private MainWindow window;
    private ArrayList<FireworkParticle> particles;
    private ArrayList<FireworkArm> arms;
    private boolean explode;
    private boolean particleOrArm = false;
    private float maxRadius = 10f;
    private float speed = 0.1f;
    private boolean exploded = false;
    private int time = 100;

    public Firework(MainWindow window)
    {   
        this.explode = false;
        this.window = window;
        this.particles = new ArrayList<FireworkParticle>();
        this.arms = new ArrayList<FireworkArm>();
        setup();
    }

    private void setup()
    {
                // This is for half the geometry graph
                for(int i = 0; i < 9; i++)
                {
                    float x1 = PApplet.map(i,0,8,-100,100);
                    float y1 = 0;
                    if (x1 < 0)
                        y1 = PApplet.map(i,0,4,0,100);
                    else
                        y1 = PApplet.map(i,4,8, 100, 0);
                    addArmOrParticle(x1, y1);
                }
                // This is for the other half of the geometry graph
                for(int i = 1; i < 8; i++)
                {
                    float x1 = PApplet.map(i,0,8,-100,100);
                    float y1 = 0;
                    if (x1 < 0)
                        y1 = PApplet.map(i,0,4,0,-100);
                    else
                        y1 = PApplet.map(i,4,8, -100, 0);
        
                    addArmOrParticle(x1, y1);
                }
    }

    public void render()
    {
        window.fill(255);
        window.stroke(255);

        if(!explode)
        {
            window.fill(255);
            window.ellipse(0, 0, 5, 5);
        }
        else
        {
            for(int j = 0; j < arms.size(); j++)
            {
                arms.get(j).render();
                particles.get(j).render();
            }
            time--;
            if(time == 0) exploded = true;
        }

    }

    private void addArmOrParticle(float x, float y)
    {
        if(particleOrArm)
        {
            particles.add(new FireworkParticle(window, maxRadius, speed, new PVector(x,y)));
            particleOrArm = !particleOrArm;
        }
        else
        {
            arms.add(new FireworkArm(window, speed, new PVector(x,y)));
            particleOrArm = !particleOrArm;
        }
    }

    public void startExplodsion()
    {
        explode = true;
    }

    public boolean isExploded() {
        return exploded;
    }
}

package daniel;

import java.util.ArrayList;

import ie.tudublin.MainWindow;
import processing.core.PApplet;
import processing.core.PVector;

public class Firework {
    private MainWindow window;
    private ArrayList<FireworkParticle> particles;
    private ArrayList<FireworkArm> arms;
    private PVector location;
    private boolean explode;
    private boolean particleOrArm = false;
    private float maxRadius = 10f;
    private float speed = 0.1f;
    private boolean exploded = false;
    private int colour = 255;
    private int time = 15;
    private float angle = PApplet.TWO_PI/16;

    public Firework(MainWindow window, float speed, int colour,PVector location)
    {   
        this.explode = false;
        this.window = window;
        this.location = location;
        this.particles = new ArrayList<FireworkParticle>();
        this.arms = new ArrayList<FireworkArm>();
        this.speed = speed;
        this.colour = colour;
        setup();
    }

    private void setup()
    {
        for (int i = 0; i < 16; i++) {
            addArmOrParticle(0, -100, i*angle);
        }
    }

    public void render()
    {
        window.fill(colour, 255, 255);
        window.stroke(colour, 255, 255);

        if(!explode)
        {
            window.ellipse(0, 0, 5, 5);
        }
        else
        {
            for(int j = 0; j < arms.size(); j++)
            {
                arms.get(j).render();
                particles.get(j).render();
            }
            if(time > 0)time--;
            if(time == 0) exploded = true;
        }

    }

    private void addArmOrParticle(float x, float y, float rotation)
    {
        if(particleOrArm)
        {
            particles.add(new FireworkParticle(window, maxRadius, speed, rotation,new PVector(x,y)));
            particleOrArm = !particleOrArm;
        }
        else
        {
            arms.add(new FireworkArm(window, speed, rotation,new PVector(x,y)));
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

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public PVector getLocation() {
        return location;
    }
}
package daniel;

import ie.tudublin.MainWindow;
import processing.core.PVector;

public class Firework {
    private MainWindow window;
    private int lifetime;
    private int arms;
    private int particleCount;
    private FireworkParticle[] particles;
    private boolean explode;
    private PVector intialPosition;

    public Firework(MainWindow window, PVector intialPosition)
    {   
        this.explode = false;
        this.window = window;
        this.intialPosition = intialPosition;
        this.particles = new FireworkParticle[particleCount];
    }

    public void render()
    {
        if(!explode)
            window.fill(255);
            window.ellipse(intialPosition.x, intialPosition.y, 5, 5);
    }

    public void startExplodsion()
    {
        explode = true;
    }
}

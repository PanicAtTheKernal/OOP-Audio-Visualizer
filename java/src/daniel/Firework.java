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
    private FireworkParticle fp;

    public Firework(MainWindow window, PVector intialPosition)
    {   
        this.explode = false;
        this.window = window;
        this.intialPosition = intialPosition;
        this.particles = new FireworkParticle[particleCount];
        this.fp = new FireworkParticle(window, 400, 50, 100, intialPosition);
    }

    public void render()
    {
        fp.render();
        // if(!explode)
        //     window.fill(255);
        //     window.ellipse(intialPosition.x, intialPosition.y, 5, 5);
    }

    public void startExplodsion()
    {
        explode = true;
    }
}

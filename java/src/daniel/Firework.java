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
        this.fp = new FireworkParticle(window, 400, 0.5f, 100, new PVector(-100,0));
    }

    public void render()
    {
        // window.translate(intialPosition.x, intialPosition.y);
        fp.render();
        // if(!explode)
        //     window.fill(255);
        //     window.ellipse(intialPosition.x, intialPosition.y, 5, 5);

                // window.pushMatrix();
        // window.translate(targeCords.x, targeCords.y);
        // for(int i = 0; i < 20; i++)
        // {
        //     float x1 = PApplet.map(i,0,20,-100,100);
        //     float y1 = PApplet.map(i,0,20,-100,100);;
        //     // if (x1 < 0)
        //     // {
        //     //     y1 = PApplet.map(i,0,10,0,100);
        //     // }
        //     // else
        //     // {
        //     //     y1 = PApplet.map(i,10,20, 100, 0);
        //     // }

        //     window.line(x1, y1, 0,0);
        // }
        // // for(int i = 0; i < 20; i++)
        // // {
        // //     float x1 = PApplet.map(i,0,20,-100,100);
        // //     float y1 = 0;
        // //     if (x1 < 0)
        // //     {
        // //         y1 = PApplet.map(i,0,10,0,-100);
        // //     }
        // //     else
        // //     {
        // //         y1 = PApplet.map(i,10,20, -100, 0);
        // //     }

        // //     window.line(x1, y1, 0,0);
        // // }
        // window.popMatrix();
    }

    public void startExplodsion()
    {
        explode = true;
    }
}

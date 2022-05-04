package daniel;

import java.util.ArrayList;

import ie.tudublin.MainWindow;
import ie.tudublin.MyVisual;
import processing.core.PApplet;
import processing.core.PVector;

// My Visual is fireworks show that synchronization with the beat of the song
public class DanielsVisual extends MyVisual {
    private int noFireworks = 10;
    private int maxSize = 40;
    private float scale = 0.3f; 
    private float minScale = 0.3f;
    private ArrayList<Firework> fireworks = new ArrayList<Firework>();
    private PVector cords;

    public DanielsVisual(MainWindow window, String name) {
        super(window, name);
    }

    @Override
    public void update() {
        if(window.getBeat().isHat() && fireworks.size() < noFireworks)
        {
            cords = new PVector(window.random(maxSize, window.width-maxSize), window.random(maxSize, window.height-maxSize));
            int colour = (int)PApplet.map(window.getFFT().getBand(0), 0f, 30f, 0, 255);
            fireworks.add(new Firework(window, 0.5f, colour,cords));
        }
        
        scale = minScale + (window.getIntensity() * 0.1f);
        if(window.getBeat().isKick())
        {
            explodeFirework();
        }
    }

    @Override
    public void render() {
        if(isSingleMode()) window.background(0); 

        window.strokeWeight(4);

        for(Firework f: fireworks)
        {
            window.pushMatrix();
            window.translate(f.getLocation().x, f.getLocation().y);
            window.scale(scale);
            f.render();
            window.popMatrix();
        }
    }

    @Override
    public void keyPressed() {}
    
    public void explodeFirework()
    {
        if(fireworks.size() > 0)
        {
            fireworks.get(0).startExplodsion();
            if(fireworks.get(0).isExploded())
                fireworks.remove(0); 
        }
    }
}

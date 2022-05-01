package daniel;

import java.net.http.HttpResponse.PushPromiseHandler;
import java.util.ArrayList;

import ie.tudublin.MainWindow;
import ie.tudublin.MyVisual;
import processing.core.PApplet;
import processing.core.PVector;

public class DanielsVisual extends MyVisual {
    private int y;
    private float color = window.random(0,255);
    private int noFireworks = 100;
    private int maxSize = 40;

    private ArrayList<Firework> fireworks = new ArrayList<Firework>();

    private Firework firework;
    private PVector cords;

    public DanielsVisual(MainWindow window, String name) {
        super(window, name);
        firework = new Firework(window);
    }

    @Override
    public void update() {
        if(window.getBeat().isKick() && fireworks.size() < noFireworks)
        {
            cords = new PVector(window.random(maxSize, window.width-maxSize), window.random(maxSize, window.height-maxSize));
            fireworks.add(new Firework(window));
        }
        
    }

    @Override
    public void render() {
        if(isSingleMode()) window.background(0); 

        for(Firework f: fireworks)
        {
            window.pushMatrix();
            window.translate(cords.x, cords.y);
            window.scale(0.3f);
            f.render();
            window.popMatrix();
        }

        if(window.getBeat().isHat())
        {
            explodeFirework();
        }
    }

    @Override
    public void keyPressed() {
        // TODO Auto-generated method stub
        PApplet.println(window.key);
    }
    
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

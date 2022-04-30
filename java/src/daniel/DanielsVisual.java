package daniel;

import java.util.ArrayList;

import ie.tudublin.MainWindow;
import ie.tudublin.MyVisual;
import processing.core.PApplet;
import processing.core.PVector;

public class DanielsVisual extends MyVisual {
    private int y;
    private float color = window.random(0,255);
    private int noFireworks = 10;
    private int maxSize = 40;

    private ArrayList<Firework> fireworks = new ArrayList<Firework>();

    public DanielsVisual(MainWindow window, String name) {
        super(window, name);
    }

    @Override
    public void update() {
        if(window.getBeat().isKick() && fireworks.size() < noFireworks)
        {
            PVector randCords = new PVector(window.random(maxSize, window.width-maxSize), window.random(maxSize, window.height-maxSize));
            fireworks.add(new Firework(window, randCords));
        }
        
    }

    @Override
    public void render() {
        if(isSingleMode()) window.background(0); 

        for(Firework f: fireworks)
        {
            f.render();
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
        if(0 < fireworks.size())
        {
            Firework firework = fireworks.remove(0);
            firework.startExplodsion();
        }
    }
}

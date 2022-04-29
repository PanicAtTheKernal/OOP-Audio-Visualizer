package daniel;

import ie.tudublin.MainWindow;
import ie.tudublin.MyVisual;
import processing.core.PApplet;

public class DanielsVisual extends MyVisual {
    int y;
    float color = window.random(0,255);

    public DanielsVisual(MainWindow window, String name, int y) {
        super(window, name);
        this.y = y;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render() {
        if(isSingleMode()) window.background(color, 50, 50); 
        window.stroke(255);
        window.strokeWeight(4*window.getIntensity());
        window.line(50, y, 750, y);
    }

    @Override
    public void keyPressed() {
        // TODO Auto-generated method stub
        PApplet.println(window.key);
    }
    
}

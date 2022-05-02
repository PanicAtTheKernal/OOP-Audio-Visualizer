package orin;


import java.util.ArrayList;

import example.MyVisual;
import ie.tudublin.MainWindow;
import jogamp.newt.driver.awt.WindowDriver;
import processing.core.PApplet;
import processing.core.PVector;


public class BouncingObject{
    private MainWindow window;
    private int colour = 255;
    private float radius = 10f;
    private PVector position;
    private PVector velocity;


    public BouncingObject(MainWindow window,int colour, float x, float y, float radius) {
        this.window = window;
        this.colour = colour;
        this.radius = radius;
        this.position = new PVector(x, y);
        this.velocity = PVector.random2D();
    }

    void update(){
        position.add(velocity);
    }

    void render(){
        window.noStroke();
        window.fill(colour,255,255);
        window.circle(position.x, position.y, radius*2);
    }

    void checkWallCollision(){
        if(position.x < radius){
            position.x = radius;
            velocity.x = velocity.x * -1;
        }
        else if(position.x > window.width-radius){
            position.x = window.width-radius;
            velocity.x = velocity.x * -1;
        }

        if(position.y < radius){
            position.y = radius;
            position.y = position.y * -1;
        }
        else if(position.y > window.height - radius){
            position.y = window.height - radius;
            position.y = position.y * -1;
        }
    }




    @Override
    public String toString() {
        return "BouncingObject [colour=" + colour + ", position=" + position + ", radius=" + radius + ", velocity="
                + velocity + ", window=" + window + "]";
    }



    public MainWindow getWindow() {
        return window;
    }

    public void setWindow(MainWindow window) {
        this.window = window;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public PVector getposition() {
        return position;
    }

    public void setposition(PVector position) {
        this.position = position;
    }

    public PVector getVelocity() {
        return velocity;
    }

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    

    
    

    
}

package orin;


import java.lang.annotation.Target;
import java.util.ArrayList;

import example.MyVisual;
import ie.tudublin.MainWindow;
import jogamp.newt.driver.awt.WindowDriver;
import processing.core.PApplet;
import processing.core.PVector;


public class BouncingObject{
    private MainWindow window;
    private float speed;
    private float colour = 255;
    private float radius = 25f;
    private float originalRadius;
    private float minRadius;
    private float maxRadius;
    private float effectRadius = 10;
    private PVector position;
    private PVector velocity;
    private PVector explosionCenterVect;
    private float minimumSpeed;
    private boolean explosionEffectToggle = false;
    private boolean decreaseSize = false;



    public BouncingObject(MainWindow window,float colour, float x, float y, float radius, float speed) {
        this.window = window;
        this.colour = colour;
        this.radius = radius;
        this.effectRadius = effectRadius;
        this.position = new PVector(x, y);
        this.velocity = PVector.random2D();
        this.velocity.mult(speed);
        this.explosionCenterVect = new PVector();
        this.originalRadius = radius;
        this.maxRadius = originalRadius + 8;
        this.minRadius = originalRadius - 6;
        this.speed = speed;
        this.minimumSpeed = velocity.mag();
    }

    void update(){
        position.add(velocity);
        updateRadius();
        updateColour();
        slowDown();
    }

    void RadiusToggle(boolean increase){
        if(increase == true){
            decreaseSize = false;
        }
        else{
            decreaseSize = true;
        }
    }

    void updateRadius(){
        if(decreaseSize == true && radius > minRadius){
            radius = radius - 2;
        }
        else if(decreaseSize == false && radius < maxRadius){
            radius = radius + 2;
        }
    }

    void updateColour(){
        // window.calculateAverageAmplitude();
        // colour = PApplet.map(window.getSmoothedAmplitude(), 0, 1, 0, 255);
        // System.out.println(window.getSmoothedAmplitude());
    }

    void middleExplosion(float x, float y){
        explosionCenterVect = new PVector(x,y);
        PVector fromExplosionCenterVect = new PVector();

        explosionEffectToggle = true;//Togle the explosion effect

        if(position.x > explosionCenterVect.x){
            fromExplosionCenterVect.x = position.x - explosionCenterVect.x;
        }
        else{
            fromExplosionCenterVect.x = explosionCenterVect.x - position.x;
            fromExplosionCenterVect.x = fromExplosionCenterVect.x * -1;
        }

        if(position.y > explosionCenterVect.y){
            fromExplosionCenterVect.y = position.y - explosionCenterVect.y;
        }
        else{
            fromExplosionCenterVect.y = explosionCenterVect.y - position.y;
            fromExplosionCenterVect.y = fromExplosionCenterVect.y * -1;
        }

        fromExplosionCenterVect.normalize();
        fromExplosionCenterVect.mult(speed);
        fromExplosionCenterVect.mult(4);

        velocity = fromExplosionCenterVect;
    }


    void slowDown(){//Slows down the objects to normal speed after they have been accelerated from the explosion
        if(velocity.mag() > minimumSpeed){
            float newMag = velocity.mag() - minimumSpeed/7;
            velocity.setMag(newMag);
        }
    }

    void render(){
        window.noStroke();
        window.fill(colour,255,255);
        window.circle(position.x, position.y, radius*2);

        if(explosionEffectToggle == true){
            window.strokeWeight(8);
            window.smooth();
            window.fill(0,0,0);
            window.stroke(0, 0, 230);
            window.circle(explosionCenterVect.x, explosionCenterVect.y, effectRadius);
            effectRadius = effectRadius*1.05f + 10;   
            window.noStroke();
        }
        if(effectRadius > 200){
            explosionEffectToggle = false;
            effectRadius = 10;
        }
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

        else if(position.y < radius){
            position.y = radius;
            velocity.y = velocity.y * -1;
        }
        else if(position.y > window.height - radius - 30){
            position.y = window.height - radius - 30;
            velocity.y = velocity.y * -1;
        }
    }
















    @Override
    public String toString() {
        return "BouncingObject [colour=" + colour + ", position=" + position + ", radius=" + radius + ", speed=" + speed
                + ", velocity=" + velocity + ", window=" + window + "]";
    }

    public MainWindow getWindow() {
        return window;
    }

    public void setWindow(MainWindow window) {
        this.window = window;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getColour() {
        return colour;
    }

    public void setColour(float colour) {
        this.colour = colour;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public PVector getVelocity() {
        return velocity;
    }

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    

    


    

    
    

    
}

package orin;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import ie.tudublin.MainWindow;
import ie.tudublin.MyVisual;
import processing.core.PApplet;
import processing.core.PVector;


public class OrinsVisuals extends MyVisual{
    private float colour = window.random(0,255);
    private float radius = window.random(20, 32);
    private int maxObjects = 4 + 1*window.getIntensity();
    private float speed = window.random(2,8);
    private float lastExplosion = 0;

    private float x = window.random(100,window.width - 100);
    private float y = window.random(100,window.height - 100);

    private static ArrayList<BouncingObject> bouncingObjects = new ArrayList<BouncingObject>();



    public OrinsVisuals(MainWindow window, String name) {
        super(window, name);
    }

    @Override
    public void update() {
        //base kick mid snare hat
        

        //Add more or removes objects depending in Intensity level
        maxObjects = 4 + 1*window.getIntensity();
        if(bouncingObjects.size() > maxObjects){
            bouncingObjects.remove(0);
        }


        //Add objects on a hat 
        if(window.getBeat().isHat() && bouncingObjects.size() < maxObjects){
            colour = window.random(0,255);
            speed = window.random(2,8);
            radius = window.random(20, 32);
            bouncingObjects.add(new BouncingObject(window, colour, window.width/2, window.height/2, radius, speed));
        }


        //Generate an explosion on a snare
        boolean explode = false;
        x = window.random(100,window.width - 100);
        y = window.random(100,window.height - 100);
        if(window.getBeat().isSnare()){
            if((window.millis() - lastExplosion)>250 && explode == false){
                explode = true;
                lastExplosion = window.millis();
            }
        }

        

        //Apply all effects to each object
        int i = -1;
        for(BouncingObject b: bouncingObjects){
            b.update();
            i = i ++;

            //Call Explosion
            if(explode == true && i < bouncingObjects.size()){
                b.middleExplosion(x, y);
            }
            else if(i > bouncingObjects.size()){
                explode = false;
            }

            //Increase Radius on a hat
            if(window.getBeat().isHat()){
                b.RadiusToggle(true);
            }

            //Decrease radius on a kick
            if(window.getBeat().isKick()){
                b.RadiusToggle(false);
            }
        }

        //Reset Explosion
        if(explode == true){
            explode = false;
        }


    }


    //Generate explosion where mouse was pressed
    public static void mousePressed(float x, float y){
        for(BouncingObject b: bouncingObjects){
            b.middleExplosion(x, y);
        }
    }


    @Override //Render next frame
    public void render() {
        if(isSingleMode()) window.background(0); 


        for(BouncingObject b: bouncingObjects){
            b.render();
            b.checkWallCollision();
        }
    }

    @Override
    public void keyPressed() {   

    }
    
}

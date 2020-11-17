/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic3d;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author metal
 */
public class projectSpace extends Applet {
    public static void main(){
        
    }
    public void init(){
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);
        BranchGroup bg = createSceneGraph();
        bg.compile();
        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(bg);
    }
    private BranchGroup createSceneGraph(){
        BranchGroup root = new BranchGroup();
        Appearance ap = new Appearance();
        ap.setMaterial(new Material());
        TransformGroup tgSolar = new TransformGroup();  
        root.addChild(tgSolar);
        //image for planets
        ImageComponent2D image = new ImageComponent2D(); //suppose to import a picture of space
        Background background = new Background();//the background for the project
        
        //spheres of the planets
        Sphere sun = new Sphere();
        Sphere mercury = new Sphere();
        Sphere venus = new Sphere();
        Sphere earth = new Sphere();
        Sphere moon = new Sphere();
        Sphere mars = new Sphere();
        Sphere jupiter = new Sphere();
        Sphere saturn = new Sphere();
        Sphere uranus = new Sphere();
        Sphere neptune = new Sphere();
        Sphere pluto = new Sphere();//optional
        //place each sphere in its transform group
        Transform3D tr = new Transform3D();
         tr.setScale(1.5);
        //TransformGroup tg = new TransformGroup(tr);
       // spin.addChild(tg);
        //tg.addChild(sun);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(0f,0f,0f));
       
        
        TransformGroup tgSun = new TransformGroup(tr);//coordinates 0f,0f,0f
        tgSolar.addChild(tgSun);
        tgSun.addChild(sun);
      //  tr.setScale(1.5);//set the scale of the sun
       // tr.setIdentity();//i'm not too sure
        //tr.setTranslation(new Vector3f(0f,0f,0f));
        
        tr.setScale(.2);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(-.2f,.15f,.15f));        
        TransformGroup tgMercury = new TransformGroup(tr);//-.2f,.15f,.15f
        tgSolar.addChild(tgMercury);
        tgMercury.addChild(mercury);
        
        tr.setScale(.4);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(-.3f,.35f,.2f));
        TransformGroup tgVenus = new TransformGroup(tr); //-.3f,.35f,.2f
        tgSolar.addChild(tgVenus);
        tgVenus.addChild(venus);
        
        TransformGroup tgMoon = new TransformGroup(tr);
        
        tr.setScale(.45);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.45f,.57f,.45f));
        TransformGroup tgEarth = new TransformGroup(tr); //.45f,.57f,.45f
        tgSolar.addChild(tgEarth);
        tgEarth.addChild(earth);
        
        tr.setScale(.3);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.55f,.4f,.6f));
        TransformGroup tgMars = new TransformGroup(tr);//.55f,.4f,.6f
        tgSolar.addChild(tgMars);
        tgMars.addChild(mars);
        
        tr.setScale(1.3);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.8f,-.8f,-.45f));
        TransformGroup tgJupiter = new TransformGroup(tr); //.8f,-.8f,-.45f
        tgSolar.addChild(tgJupiter);
        tgJupiter.addChild(jupiter);

        tr.setScale(1.2);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(0.89f,-0.9f,-0.45f));
        TransformGroup tgSaturn = new TransformGroup(tr); //.89f,-.9f,.-45f
        tgSolar.addChild(tgSaturn);
        tgSaturn.addChild(saturn);
        
        tr.setScale(1.1);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(1.5f,1f,1f));        
        TransformGroup tgUranus = new TransformGroup(tr);//1.5f,1f,1f
        tgSolar.addChild(tgUranus);
        tgUranus.addChild(uranus);

        tr.setScale(1.);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(01f,0f,0f));        
        TransformGroup tgNeptune = new TransformGroup(tr);//1f,0f,0f
        tgSolar.addChild(tgNeptune);
        tgNeptune.addChild(neptune);
        
        tr.setScale(.2);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.9f,1.5f,1.5f));
        TransformGroup tgPluto = new TransformGroup(tr);//.9f,1.5f,1.5f
        tgSolar.addChild(tgPluto);
        tgPluto.addChild(pluto);
        
        //light for the sun
        PointLight light = new PointLight(new Color3f(Color.orange),new Point3f(0f,0f,0f),new Point3f(1f,0.1f,0f));//please check over this
        BoundingSphere bounds = new BoundingSphere();
        light.setInfluencingBounds(bounds);
        root.addChild(light);
    }
    
    
}

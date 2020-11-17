/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic3d;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.net.URL;
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
import com.sun.j3d.utils.image.*;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;
import javax.vecmath.Point3d;


/**
 *
 * @author metal
 */
public class projectSpace extends Applet {
            int orbitTimeMs = 4000;
            int rotationTimeMs = 1500;
            
           int earthRotationTimeMs = 500;
           int earthOrbitTimeMs = 1000;
           
           int moonRotationTimeMs = 500;
           int moonOrbitTimeMs = 1000;
           
           int mercuryRotationTimeMs = 500;
           int mercuryOrbitTimeMs = 1000;
           
           int venusRotationTimeMs = 500;
           int venusOrbitTimeMs = 1000;           
           
           int marsRotationTimeMs = 500;
           int marsOrbitTimeMs = 1000;
           
           int jupiterRotationTimeMs = 500;
           int jupiterOrbitTimeMs = 1000;
           
           int saturnRotationTimeMs = 500;
           int saturnOrbitTimeMs = 1000;
           
           int uranusRotationTimeMs = 500;
           int uranusOrbitTimeMs = 1000;
           
           int neptuneRotationTimeMs = 500;
           int neptuneOrbitTimeMs = 1000;
           
           private static BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0,0.0,0.0), Double.MAX_VALUE);
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
        Appearance ap = createAppearance();
        //ap.setMaterial(new Material());
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
        TransformGroup tgMercuryRotat =createRotationTransformGroup(mercuryRotationTimeMs, true);//rotation
        tgSolar.addChild(tgMercury);
        tgMercury.addChild(mercury);
        tgMercuryRotat.addChild(tgMercury);
        
        tr.setScale(.4);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(-.3f,.35f,.2f));
        TransformGroup tgVenus = new TransformGroup(tr); //-.3f,.35f,.2f
        TransformGroup tgVenusRotat =createRotationTransformGroup(venusRotationTimeMs, true);//rotation
        tgSolar.addChild(tgVenus);
        tgVenus.addChild(venus);
        tgVenusRotat.addChild(tgVenus);
        
        TransformGroup tgMoon = new TransformGroup(tr);
        
        tr.setScale(.45);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.45f,.57f,.45f));
        TransformGroup tgEarth = new TransformGroup(tr); //.45f,.57f,.45f
        TransformGroup tgEarthRotat =createRotationTransformGroup(earthRotationTimeMs, true);//rotation
        tgSolar.addChild(tgEarth);
        tgEarth.addChild(earth);
        tgEarthRotat.addChild(tgEarth);
        
        tr.setScale(.3);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.55f,.4f,.6f));
        TransformGroup tgMars = new TransformGroup(tr);//.55f,.4f,.6f
        TransformGroup tgMarsRotat =createRotationTransformGroup(marsRotationTimeMs, true);//rotation
        tgSolar.addChild(tgMars);
        tgMars.addChild(mars);
        tgMarsRotat.addChild(tgMars);
        
        tr.setScale(1.3);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.8f,-.8f,-.45f));
        TransformGroup tgJupiter = new TransformGroup(tr); //.8f,-.8f,-.45f
        TransformGroup tgJupRotat =createRotationTransformGroup(jupiterRotationTimeMs, true);//rotation
        tgSolar.addChild(tgJupiter);
        tgJupiter.addChild(jupiter);
        tgJupRotat.addChild(tgJupiter);

        tr.setScale(1.2);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(0.89f,-0.9f,-0.45f));
        TransformGroup tgSaturn = new TransformGroup(tr); //.89f,-.9f,.-45f
        TransformGroup tgSatRotat =createRotationTransformGroup(saturnRotationTimeMs, true);//rotation
        tgSolar.addChild(tgSaturn);
        tgSaturn.addChild(saturn);
        tgSatRotat.addChild(tgSaturn);
        
        tr.setScale(1.1);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(1.5f,1f,1f));        
        TransformGroup tgUranus = new TransformGroup(tr);//1.5f,1f,1f
        TransformGroup tgUranRotat =createRotationTransformGroup(uranusRotationTimeMs, true);//rotation
        tgSolar.addChild(tgUranus);
        tgUranus.addChild(uranus);
        tgUranRotat.addChild(tgUranus);

        tr.setScale(1.);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(01f,0f,0f));        
        TransformGroup tgNeptune = new TransformGroup(tr);//1f,0f,0f
        TransformGroup tgNepRotat =createRotationTransformGroup(neptuneRotationTimeMs, true);//rotation
        tgSolar.addChild(tgNeptune);
        tgNeptune.addChild(neptune);
        tgNepRotat.addChild(tgNeptune);
        
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
        
        root.addChild(tgSolar);
        return root;
    }
    
    
    Appearance createAppearance(){
        Appearance ap = new Appearance();
        URL filename = getClass().getClassLoader().getResource("C:\\Users\\metal\\OneDrive\\Documents\\NetBeansProjects\\Graphic3D\\src\\graphic3d\\pictures\\mercury.jpg");
        TextureLoader loadPlanet = new TextureLoader(filename, this);
        ImageComponent2D image1 = loadPlanet.getImage(); //mercury
        
        filename = getClass().getClassLoader().getResource("C:\\Users\\metal\\OneDrive\\Documents\\NetBeansProjects\\Graphic3D\\src\\graphic3d\\pictures\\venus.jpg");
        loadPlanet = new TextureLoader(filename, this);
        ImageComponent2D image2 = loadPlanet.getImage();//venus
        
        filename = getClass().getClassLoader().getResource("C:\\Users\\metal\\OneDrive\\Documents\\NetBeansProjects\\Graphic3D\\src\\graphic3d\\pictures\\earth.jpg");
        loadPlanet = new TextureLoader(filename, this);
        ImageComponent2D image3 = loadPlanet.getImage();//earth
        
        filename = getClass().getClassLoader().getResource("C:\\Users\\metal\\OneDrive\\Documents\\NetBeansProjects\\Graphic3D\\src\\graphic3d\\pictures\\mars.jpg");
        loadPlanet = new TextureLoader(filename, this);
        ImageComponent2D image4 = loadPlanet.getImage();//mars
        
        filename = getClass().getClassLoader().getResource("C:\\Users\\metal\\OneDrive\\Documents\\NetBeansProjects\\Graphic3D\\src\\graphic3d\\pictures\\jupiter.jpg");
        loadPlanet = new TextureLoader(filename, this);
        ImageComponent2D image5 = loadPlanet.getImage();//jupiter
        
        filename = getClass().getClassLoader().getResource("C:\\Users\\metal\\OneDrive\\Documents\\NetBeansProjects\\Graphic3D\\src\\graphic3d\\pictures\\saturn.png");
        loadPlanet = new TextureLoader(filename, this);
        ImageComponent2D image6 = loadPlanet.getImage();//saturn
        
        filename = getClass().getClassLoader().getResource("C:\\Users\\metal\\OneDrive\\Documents\\NetBeansProjects\\Graphic3D\\src\\graphic3d\\pictures\\uranus.jpg");
        loadPlanet = new TextureLoader(filename, this);
        ImageComponent2D image7 = loadPlanet.getImage();//uranus
        
        filename = getClass().getClassLoader().getResource("C:\\Users\\metal\\OneDrive\\Documents\\NetBeansProjects\\Graphic3D\\src\\graphic3d\\pictures\\neptune.jpg");
        loadPlanet = new TextureLoader(filename, this);
        ImageComponent2D image8 = loadPlanet.getImage();//neptune
    }
    
    
    
private static TransformGroup createRotationTransformGroup(
        int rotationTimeMs, boolean forward)
    {
        TransformGroup rotationTransformGroup = new TransformGroup();
        rotationTransformGroup.setCapability(
            TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha rotationAlpha = new Alpha(-1, rotationTimeMs);
        float angle = forward ? (float) (2 * Math.PI) : (float)(-2 * Math.PI);
        RotationInterpolator rotationInterpolator = 
            new RotationInterpolator(rotationAlpha, rotationTransformGroup, 
                new Transform3D(), 0.0f, angle);
        rotationInterpolator.setSchedulingBounds(boundingSphere);
        rotationTransformGroup.addChild(rotationInterpolator);
        return rotationTransformGroup;
    }
}
//https://www.ehow.co.uk/how_6281318_make-rainbows-prisms.html

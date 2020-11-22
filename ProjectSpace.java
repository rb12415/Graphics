package projectSpace;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.applet.Applet;
import java.awt.*;


/**
 *
 * @author metal
 */
public class ProjectSpace extends Applet {
    private static boolean forward;
    int orbitTimeMs = 4000;
    int rotationTimeMs = 1500;

    int earthRotationTimeMs = 190;
    int earthOrbitTimeMs = 200;

    int moonRotationTimeMs = 48;
    int moonOrbitTimeMs = 50;

    int mercuryRotationTimeMs = 90;
    int mercuryOrbitTimeMs = 100;

    int venusRotationTimeMs = 140;
    int venusOrbitTimeMs = 150;

    int marsRotationTimeMs = 240;
    int marsOrbitTimeMs = 250;

    int jupiterRotationTimeMs = 290;
    int jupiterOrbitTimeMs = 300;

    int saturnRotationTimeMs = 340;
    int saturnOrbitTimeMs = 350;

    int uranusRotationTimeMs = 390;
    int uranusOrbitTimeMs = 400;

    int neptuneRotationTimeMs = 440;
    int neptuneOrbitTimeMs = 450;

    int plutoRotationTimeMs = 490;
    int plutoOrbitTimesMs = 500;

    int sunRotationTimeMs = 99;
    int sunOrbitTimesMs = 100;


    private static BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0,0.0,0.0), Double.MAX_VALUE);

    public static void main(String[] args){
        new MainFrame(new ProjectSpace(), 800,600);



    }
    public void init(){
        //canvas
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);
        BranchGroup bg = createSceneGraph();
        bg.compile();
        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(bg);
        // su.addBranchGraph(bg);
        Transform3D viewPlatformTransform = new Transform3D();
        Transform3D t0 = new Transform3D();
        t0.setTranslation(new Vector3d(0,0,780));
        Transform3D t1 = new Transform3D();
        t1.rotX(Math.toRadians(-30));
        viewPlatformTransform.mul(t1, t0);
        su.getViewer().getView().setBackClipDistance(1000);
        su.getViewingPlatform().
                getViewPlatformTransform().setTransform(viewPlatformTransform);;
    }

    private BranchGroup createSceneGraph(){
        BranchGroup root = new BranchGroup();
        // Appearance ap = createAppearance();
        //ap.setMaterial(new Material());
        TransformGroup tgSolar = new TransformGroup();
        root.addChild(tgSolar);

        //image for planets
//        ImageComponent2D image0 = new ImageComponent2D(); //suppose to import a picture of space
//        Background background = new Background();//the background for the project
//        background.setImage(image0);

        //spheres of the planets
        Sphere sun = new Sphere(75f);
        Sphere mercury = new Sphere(.5f);
        Sphere venus = new Sphere(1.2f);
        Sphere earth = new Sphere(1.5f);
        Sphere moon = new Sphere(.375f);
        Sphere mars = new Sphere(.75f);
        Sphere jupiter = new Sphere(16.5f);
        Sphere saturn = new Sphere(13.5f);
        Sphere uranus = new Sphere(6f);
        Sphere neptune = new Sphere(5.8f);
        Sphere pluto = new Sphere(.5f);//optional
        //.375f for the size of moon

        Material mat = new Material();
        mat.setEmissiveColor(255, 0, 0);
        Appearance app = new Appearance();
        Color3f color = new Color3f(Color.white);
        Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        mat.setSpecularColor(new Color3f(Color.red));
        mat.setShininess(100f);
        app.setMaterial(mat);
        sun.setAppearance(app);
        mercury.setAppearance(app);
        venus.setAppearance(app);
        earth.setAppearance(app);
        mars.setAppearance(app);
        jupiter.setAppearance(app);
        saturn.setAppearance(app);
        uranus.setAppearance(app);
        neptune.setAppearance(app);
        pluto.setAppearance(app);
        moon.setAppearance(app);

        //place each sphere in its transform group
        Transform3D tr = new Transform3D();
        // tr.setScale(1.5);
        //TransformGroup tg = new TransformGroup(tr);
        // spin.addChild(tg);
        //tg.addChild(sun);
        //tr.setIdentity();
        //tr.setTranslation(new Vector3f(0f,0f,0f));


        // TransformGroup tgSun = new TransformGroup(tr);//coordinates 0f,0f,0f
        // tgSolar.addChild(tgSun);
        // tgSun.addChild(sun);
        //  tr.setScale(1.5);//set the scale of the sun
        // tr.setIdentity();//i'm not too sure
        //tr.setTranslation(new Vector3f(0f,0f,0f));

        //tr.setScale(.002); was orignally .2
        // tr.setIdentity();

        //Sun
        tr.setTranslation(new Vector3f(0,.0f,.0f)); //distance to center
        TransformGroup tgSun = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgSunRotat =createRotationTransformGroup(sunRotationTimeMs);//rotation
        TransformGroup tgSunOrbit = createOrbitTransformGroup(sunOrbitTimesMs);
        tgSun.addChild(sun);
        tgSunRotat.addChild(tgSun);
        tgSunOrbit.addChild(tgSunRotat);
        root.addChild(tgSunOrbit);

        //Mercury
        tr.setTranslation(new Vector3f(75f,.0f,.0f)); //distance to center
        TransformGroup tgMercury = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgMercuryRotat =createRotationTransformGroup(mercuryRotationTimeMs);//rotation
        TransformGroup tgMercuryOrbit = createOrbitTransformGroup(mercuryOrbitTimeMs);
        tgMercury.addChild(mercury);
        tgMercuryRotat.addChild(tgMercury);
        tgMercuryOrbit.addChild(tgMercuryRotat);
        root.addChild(tgMercuryOrbit);

        //Venus
        tr.setTranslation(new Vector3f(85f,.0f,.0f)); //distance to center
        TransformGroup tgVenus = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgVenusRotat =createRotationTransformGroup(venusRotationTimeMs);//rotation
        TransformGroup tgVenusOrbit = createOrbitTransformGroup(venusOrbitTimeMs);
        tgVenus.addChild(venus);
        tgVenusRotat.addChild(tgVenus);
        tgVenusOrbit.addChild(tgVenusRotat);
        root.addChild(tgVenusOrbit);

        //Earth
        tr.setTranslation(new Vector3f(95f,.0f,.0f)); //distance to center
        TransformGroup tgEarth = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgEarthRotat =createRotationTransformGroup(earthRotationTimeMs);//rotation
        TransformGroup tgEarthOrbit = createOrbitTransformGroup(earthOrbitTimeMs);
        tgEarth.addChild(earth);
        tgEarthRotat.addChild(tgEarth);
        tgEarthOrbit.addChild(tgEarthRotat);
        root.addChild(tgEarthOrbit);

        //Moon
        tr.setTranslation(new Vector3f(5f,.0f,.0f)); //distance to center
        TransformGroup tgMoon = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgMoonRotat =createRotationTransformGroup(moonRotationTimeMs);//rotation
        TransformGroup tgMoonOrbit = createOrbitTransformGroup(moonOrbitTimeMs);
        tgMoon.addChild(moon);
        tgMoonRotat.addChild(tgMoon);
        tgMoonOrbit.addChild(tgMoonRotat);
        tgEarth.addChild(tgMoonOrbit);

        //Mars
        tr.setTranslation(new Vector3f(105f,.0f,.0f)); //distance to center
        TransformGroup tgMars = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgMarsRotat =createRotationTransformGroup(marsRotationTimeMs);//rotation
        TransformGroup tgMarsOrbit = createOrbitTransformGroup(marsOrbitTimeMs);
        tgMars.addChild(mars);
        tgMarsRotat.addChild(tgMars);
        tgMarsOrbit.addChild(tgMarsRotat);
        root.addChild(tgMarsOrbit);

        //Jupiter
        tr.setTranslation(new Vector3f(140f,.0f,.0f)); //distance to center
        TransformGroup tgJupiter = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgJupiterRotat =createRotationTransformGroup(jupiterRotationTimeMs);//rotation
        TransformGroup tgJupiterOrbit = createOrbitTransformGroup(jupiterOrbitTimeMs);
        tgJupiter.addChild(jupiter);
        tgJupiterRotat.addChild(tgJupiter);
        tgJupiterOrbit.addChild(tgJupiterRotat);
        root.addChild(tgJupiterOrbit);

        //Saturn
        tr.setTranslation(new Vector3f(195f,.0f,.0f)); //distance to center
        TransformGroup tgSaturn = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgSaturnRotat =createRotationTransformGroup(saturnRotationTimeMs);//rotation
        TransformGroup tgSaturnOrbit = createOrbitTransformGroup(saturnOrbitTimeMs);
        tgSaturn.addChild(saturn);
        tgSaturnRotat.addChild(tgSaturn);
        tgSaturnOrbit.addChild(tgSaturnRotat);
        root.addChild(tgSaturnOrbit);

        //Uranus
        tr.setTranslation(new Vector3f(230f,.0f,.0f)); //distance to center
        TransformGroup tgUranus = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgUranusRotat =createRotationTransformGroup(uranusRotationTimeMs);//rotation
        TransformGroup tgUranusOrbit = createOrbitTransformGroup(uranusOrbitTimeMs);
        tgUranus.addChild(uranus);
        tgUranusRotat.addChild(tgUranus);
        tgUranusOrbit.addChild(tgUranusRotat);
        root.addChild(tgUranusOrbit);

        //Neptune
        tr.setTranslation(new Vector3f(250f,.0f,.0f)); //distance to center
        TransformGroup tgNeptune = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgNeptuneRotat =createRotationTransformGroup(neptuneRotationTimeMs);//rotation
        TransformGroup tgNeptuneOrbit = createOrbitTransformGroup(neptuneOrbitTimeMs);
        tgNeptune.addChild(neptune);
        tgNeptuneRotat.addChild(tgNeptune);
        tgNeptuneOrbit.addChild(tgNeptuneRotat);
        root.addChild(tgNeptuneOrbit);

        //Pluto
        tr.setTranslation(new Vector3f(265,.0f,.0f)); //distance to center
        TransformGroup tgPluto = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgPlutoRotat =createRotationTransformGroup(plutoRotationTimeMs);//rotation
        TransformGroup tgPlutoOrbit = createOrbitTransformGroup(plutoOrbitTimesMs);
        tgPluto.addChild(pluto);
        tgPlutoRotat.addChild(tgPluto);
        tgPlutoOrbit.addChild(tgPlutoRotat);
        root.addChild(tgPlutoOrbit);





        /*tgSolar.addChild(tgMercuryRotat);//review this. should it be tgMercury
        tgMercuryRotat.addChild(tgMercury);// should rotat be after tgmercury
        tgMercury.addChild(mercury);*/

       /* tr.setScale(.4);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(-.3f,.35f,.2f));
        TransformGroup tgVenus = new TransformGroup(tr); //-.3f,.35f,.2f
        TransformGroup tgVenusRotat =createRotationTransformGroup(venusRotationTimeMs, true);//rotation
        tgSolar.addChild(tgVenusRotat);
        tgVenusRotat.addChild(tgVenus);
        tgVenus.addChild(venus);

        TransformGroup tgMoon = new TransformGroup(tr);

        tr.setScale(.45);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.45f,.57f,.45f));
        TransformGroup tgEarth = new TransformGroup(tr); //.45f,.57f,.45f
        TransformGroup tgEarthRotat =createRotationTransformGroup(earthRotationTimeMs, true);//rotation
        tgSolar.addChild(tgEarthRotat);
        tgEarthRotat.addChild(tgEarth);
        tgEarth.addChild(earth);


        tr.setScale(.225);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.55f,.4f,.6f));
        TransformGroup tgMars = new TransformGroup(tr);//.55f,.4f,.6f
        TransformGroup tgMarsRotat =createRotationTransformGroup(marsRotationTimeMs, true);//rotation
        tgSolar.addChild(tgMarsRotat);
        tgMarsRotat.addChild(tgMars);
        tgMars.addChild(mars);

        tr.setScale(1.3);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.8f,-.8f,-.45f));
        TransformGroup tgJupiter = new TransformGroup(tr); //.8f,-.8f,-.45f
        TransformGroup tgJupRotat =createRotationTransformGroup(jupiterRotationTimeMs, true);//rotation
        tgSolar.addChild(tgJupRotat);
        tgJupRotat.addChild(tgJupiter);
        tgJupiter.addChild(jupiter);

        tr.setScale(1.2);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(0.89f,-0.9f,-0.45f));
        TransformGroup tgSaturn = new TransformGroup(tr); //.89f,-.9f,.-45f
        TransformGroup tgSatRotat =createRotationTransformGroup(saturnRotationTimeMs, true);//rotation
        tgSolar.addChild(tgSatRotat);
        tgSatRotat.addChild(tgSaturn);
        tgSaturn.addChild(saturn);

        tr.setScale(1.1);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(1.5f,1f,1f));
        TransformGroup tgUranus = new TransformGroup(tr);//1.5f,1f,1f
        TransformGroup tgUranRotat =createRotationTransformGroup(uranusRotationTimeMs, true);//rotation
        tgSolar.addChild(tgUranRotat);
        tgUranRotat.addChild(tgUranus);
        tgUranus.addChild(uranus);
        tr.setScale(1.);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(01f,0f,0f));
        TransformGroup tgNeptune = new TransformGroup(tr);//1f,0f,0f
        TransformGroup tgNepRotat =createRotationTransformGroup(neptuneRotationTimeMs, true);//rotation
        tgSolar.addChild(tgNepRotat);
        tgNepRotat.addChild(tgNeptune);
        tgNeptune.addChild(neptune);

        tr.setScale(.2);
        tr.setIdentity();
        tr.setTranslation(new Vector3f(.9f,1.5f,1.5f));
        TransformGroup tgPluto = new TransformGroup(tr);//.9f,1.5f,1.5f
        tgSolar.addChild(tgPluto);
        tgPluto.addChild(pluto); */

        //light for the sun
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),Double.MAX_VALUE);
        Background background = new Background(0.0f, 0.0f, 0.0f);
        background.setApplicationBounds(bounds);
        root.addChild(background);

        PointLight light = new PointLight();//please check over this
        light.setColor(new Color3f(Color.white));
        light.setAttenuation(10, 0, 0);
        light.setPosition(0f,.0f,.0f);
        light.setInfluencingBounds(bounds);
        root.addChild(light);

        //root.addChild(tgSolar);
        return root;
    }


/*    Appearance createAppearance(){
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
    }*/



    private static TransformGroup createRotationTransformGroup(
            int rotationTimeMs) {
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

    private static TransformGroup createOrbitTransformGroup
            (int orbitTimeMs) {
        TransformGroup orbitTransformGroup = new TransformGroup();
        orbitTransformGroup.setCapability(
                TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha orbitAlpha = new Alpha(-1, orbitTimeMs);
        RotationInterpolator orbitInterpolator =
                new RotationInterpolator(orbitAlpha, orbitTransformGroup);
        orbitInterpolator.setSchedulingBounds(boundingSphere);
        orbitTransformGroup.addChild(orbitInterpolator);
        return orbitTransformGroup;
    }
}
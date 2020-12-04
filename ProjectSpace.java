package projectSpace;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.*;
import java.applet.Applet;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author Roneil Boyce and David Tate
 */

public class ProjectSpace extends Applet {
    private static boolean forward;


    int moonRotationTimeMs = 5000;
    int moonOrbitTimeMs = 10000;
    //int moonDistanceFromCenter = 5;

    int mercuryRotationTimeMs = 6000;
    int mercuryOrbitTimeMs = 10000;
    int mercuryDistanceFromCenter = 200;

    int venusRotationTimeMs = 6000;
    int venusOrbitTimeMs = 12000;
    int venusDistanceFromCenter = 210;

    int earthRotationTimeMs = 6000;
    int earthOrbitTimeMs = 15000;
    int earthDistanceFromCenter = 220;

    int marsRotationTimeMs = 6000;
    int marsOrbitTimeMs = 18000;
    int marsDistanceFromCenter = 230;

    int jupiterRotationTimeMs = 5000;
    int jupiterOrbitTimeMs = 22000;
    int jupiterDistanceFromCenter = 260;

    int saturnRotationTimeMs = 6000;
    int saturnOrbitTimeMs = 25000;
    int saturnDistanceFromCenter = 320;

    int uranusRotationTimeMs = 6000;
    int uranusOrbitTimeMs = 28000;
    int uranusDistanceFromCenter = 355;

    int neptuneRotationTimeMs = 6000;
    int neptuneOrbitTimeMs = 31000;
    int neptuneDistanceFromCenter = 375;

    int plutoRotationTimeMs = 6000;
    int plutoOrbitTimesMs = 33000;
    int plutoDistanceFromCenter = 390;

    int sunRotationTimeMs = 10000;

    private static BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0,0.0,0.0), Double.MAX_VALUE);
    private TransformGroup viewtrans = null;

    public static void main(String[] args){
        new MainFrame(new ProjectSpace(), 800,600);

    }

    PickCanvas pc;

    public void init(){
        //canvas
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());

        add(cv, BorderLayout.CENTER);
        BranchGroup bg = createSceneGraph();
        bg.compile();
        pc = new PickCanvas(cv, bg);
        pc.setMode(PickTool.GEOMETRY);
        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(bg);

        //Key navigtor
        viewtrans = su.getViewingPlatform().getViewPlatformTransform();
        KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewtrans);
        keyNavBeh.setSchedulingBounds(boundingSphere);
        PlatformGeometry platformGeom = new PlatformGeometry();
        platformGeom.addChild(keyNavBeh);

        su.getViewingPlatform().setPlatformGeometry(platformGeom);

        // su.addBranchGraph(bg);
        Transform3D viewPlatformTransform = new Transform3D();
        Transform3D t0 = new Transform3D();
        t0.setTranslation(new Vector3d(0,0,800));
        Transform3D t1 = new Transform3D();
        t1.rotX(Math.toRadians(0));
        viewPlatformTransform.mul(t1, t0);
        su.getViewer().getView().setBackClipDistance(1000);
        su.getViewingPlatform().getViewPlatformTransform().setTransform(viewPlatformTransform);

        //mouse movement
        OrbitBehavior orbit = new OrbitBehavior(cv);
        orbit.setSchedulingBounds(boundingSphere);

        orbit.setRotXFactor(2);//or any other value
        orbit.setRotYFactor(0);
        su.getViewingPlatform().setViewPlatformBehavior(orbit);


        //su.addBranchGraph(bg);
    }

    private BranchGroup createSceneGraph(){
        BranchGroup root = new BranchGroup();

        int primFlags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

        //Mercury Texture
        Appearance mercuryApp = new Appearance();
        URL filenameMercury = getClass().getClassLoader().getResource("images/mercury.jpg");
        TextureLoader loadMercury = new TextureLoader(filenameMercury, this);
        ImageComponent2D mercuryImage = loadMercury.getImage();
        if(mercuryImage == null){
            System.out.print("Cant find");
        }
        Texture2D mercuryTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, mercuryImage.getWidth(),
                mercuryImage.getHeight());
        mercuryTexture.setImage(0, mercuryImage);
        mercuryTexture.setEnable(true);
        mercuryApp.setTexture(mercuryTexture);
        //Mercury Texture Attributes
        TextureAttributes mercuryTexAttr = new TextureAttributes();
        mercuryTexAttr.setTextureMode(TextureAttributes.MODULATE);
        mercuryApp.setTextureAttributes(mercuryTexAttr);
        //Planet Textures
        Material planetMaterial = new Material();
        planetMaterial.setSpecularColor(new Color3f(Color.WHITE));
        planetMaterial.setDiffuseColor(new Color3f(Color.WHITE));
        mercuryApp.setMaterial(planetMaterial);

        //Venus Texture
        Appearance venusApp = new Appearance();
        URL filenameVenus = getClass().getClassLoader().getResource("images/venus.jpg");
        TextureLoader loadVenus = new TextureLoader(filenameVenus, this);
        ImageComponent2D VenusImage = loadVenus.getImage();
        if(VenusImage == null){
            System.out.print("Cant find");
        }
        Texture2D venusTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, VenusImage.getWidth(), VenusImage.getHeight());
        venusTexture.setImage(0, VenusImage);
        venusTexture.setEnable(true);
        venusApp.setTexture(venusTexture);
        //Venus Texture Attributes
        TextureAttributes venusTexAttr = new TextureAttributes();
        venusTexAttr.setTextureMode(TextureAttributes.MODULATE);
        venusApp.setTextureAttributes(venusTexAttr);
        //Planet Textures
        venusApp.setMaterial(planetMaterial);

        //Earth Texture
        Appearance earthApp = new Appearance();
        URL filename = getClass().getClassLoader().getResource("images/earth.jpg");
        TextureLoader loadEarth = new TextureLoader(filename, this);
        ImageComponent2D earthImage = loadEarth.getImage();
        if(earthImage == null){
            System.out.print("Cant find");
        }
        Texture2D earthTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, earthImage.getWidth(), earthImage.getHeight());
        earthTexture.setImage(0, earthImage);
        earthTexture.setEnable(true);
        earthApp.setTexture(earthTexture);
        //Earth Texture Attributes
        TextureAttributes earthTexAttr = new TextureAttributes();
        earthTexAttr.setTextureMode(TextureAttributes.MODULATE);
        earthApp.setTextureAttributes(earthTexAttr);
        //Planet Textures
        earthApp.setMaterial(planetMaterial);

        //Moon Texture
        Appearance moonApp = new Appearance();
        URL filenameMoon = getClass().getClassLoader().getResource("images/moon.jpg");
        TextureLoader loadMoon = new TextureLoader(filenameMoon, this);
        ImageComponent2D moonImage = loadMoon.getImage();
        if(moonImage == null){
            System.out.print("Cant find");
        }
        Texture2D MoonTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, moonImage.getWidth(),
                moonImage.getHeight());
        MoonTexture.setImage(0, moonImage);
        MoonTexture.setEnable(true);
        moonApp.setTexture(MoonTexture);
        //Moon Texture Attributes
        TextureAttributes moonTexAttr = new TextureAttributes();
        moonTexAttr.setTextureMode(TextureAttributes.MODULATE);
        moonApp.setTextureAttributes(moonTexAttr);
        //Planet Textures
        moonApp.setMaterial(planetMaterial);


        //Mars Texture
        Appearance marsApp = new Appearance();
        URL filenameMars = getClass().getClassLoader().getResource("images/mars.jpg");
        TextureLoader loadMars = new TextureLoader(filenameMars, this);
        ImageComponent2D marsImage = loadMars.getImage();
        if(marsImage == null){
            System.out.print("Cant find");
        }
        Texture2D marsTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, marsImage.getWidth(),
                marsImage.getHeight());
        marsTexture.setImage(0, marsImage);
        marsTexture.setEnable(true);
        marsApp.setTexture(marsTexture);
        //Mars Texture Attributes
        TextureAttributes marsTexAttr = new TextureAttributes();
        marsTexAttr.setTextureMode(TextureAttributes.MODULATE);
        marsApp.setTextureAttributes(marsTexAttr);
        //Planet Textures
        marsApp.setMaterial(planetMaterial);

        //Jupiter Texture
        Appearance jupiterApp = new Appearance();
        URL filenameJup = getClass().getClassLoader().getResource("images/jupiter.jpg");
        TextureLoader loadJupiter = new TextureLoader(filenameJup, this);
        ImageComponent2D jupiterImage = loadJupiter.getImage();
        if(jupiterImage == null){
            System.out.print("Cant find");
        }
        Texture2D jupiterTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, jupiterImage.getWidth(),
                jupiterImage.getHeight());
        jupiterTexture.setImage(0, jupiterImage);
        jupiterTexture.setEnable(true);
        jupiterApp.setTexture(jupiterTexture);
        //Jupiter Texture Attributes
        TextureAttributes jupiterTexAttr = new TextureAttributes();
        jupiterTexAttr.setTextureMode(TextureAttributes.MODULATE);
        jupiterApp.setTextureAttributes(jupiterTexAttr);
        //Planet Textures
        jupiterApp.setMaterial(planetMaterial);

        //Saturn Texture
        Appearance saturnApp = new Appearance();
        URL filenameSat = getClass().getClassLoader().getResource("images/saturn.jpg");
        TextureLoader loadSaturn = new TextureLoader(filenameSat, this);
        ImageComponent2D saturnImage = loadSaturn.getImage();
        if(saturnImage == null){
            System.out.print("Cant find");
        }
        Texture2D saturnTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, saturnImage.getWidth(),
                saturnImage.getHeight());
        saturnTexture.setImage(0, saturnImage);
        saturnTexture.setEnable(true);
        saturnApp.setTexture(saturnTexture);
        //Saturn Texture Attributes
        TextureAttributes saturnTexAttr = new TextureAttributes();
        saturnTexAttr.setTextureMode(TextureAttributes.MODULATE);
        saturnApp.setTextureAttributes(saturnTexAttr);
        //Planet Textures
        saturnApp.setMaterial(planetMaterial);

        //Uranus Texture
        Appearance uranusApp = new Appearance();
        URL filenameUra = getClass().getClassLoader().getResource("images/uranus.jpg");
        TextureLoader loadUranus = new TextureLoader(filenameUra, this);
        ImageComponent2D UranusImage = loadUranus.getImage();
        if(UranusImage == null){
            System.out.print("Cant find");
        }
        Texture2D uranusTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, UranusImage.getWidth(),
                UranusImage.getHeight());
        uranusTexture.setImage(0, UranusImage);
        uranusTexture.setEnable(true);
        uranusApp.setTexture(uranusTexture);
        //Uranus Texture Attributes
        TextureAttributes uranusTexAttr = new TextureAttributes();
        uranusTexAttr.setTextureMode(TextureAttributes.MODULATE);
        uranusApp.setTextureAttributes(uranusTexAttr);
        //Planet Textures
        uranusApp.setMaterial(planetMaterial);

        //Neptune Texture
        Appearance neptuneApp = new Appearance();
        URL filenameNep = getClass().getClassLoader().getResource("images/neptune.jpg");
        TextureLoader loadNeptune = new TextureLoader(filenameNep, this);
        ImageComponent2D NeptuneImage = loadNeptune.getImage();
        if(NeptuneImage == null){
            System.out.print("Cant find");
        }
        Texture2D neptuneTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, NeptuneImage.getWidth(),
                NeptuneImage.getHeight());
        neptuneTexture.setImage(0, NeptuneImage);
        neptuneTexture.setEnable(true);
        neptuneApp.setTexture(neptuneTexture);
        //Neptune Texture Attributes
        TextureAttributes neptuneTexAttr = new TextureAttributes();
        neptuneTexAttr.setTextureMode(TextureAttributes.MODULATE);
        neptuneApp.setTextureAttributes(neptuneTexAttr);
        //Planet Textures
        neptuneApp.setMaterial(planetMaterial);

        //Pluto Texture
        Appearance plutoApp = new Appearance();
        URL filenamePlu = getClass().getClassLoader().getResource("images/pluto.jpg");
        TextureLoader loadPluto = new TextureLoader(filenamePlu, this);
        ImageComponent2D PlutoImage = loadPluto.getImage();
        if(PlutoImage == null){
            System.out.print("Cant find");
        }
        Texture2D PlutoTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, PlutoImage.getWidth(),
                PlutoImage.getHeight());
        PlutoTexture.setImage(0, PlutoImage);
        PlutoTexture.setEnable(true);
        plutoApp.setTexture(PlutoTexture);
        //Pluto Texture Attributes
        TextureAttributes PlutoTexAttr = new TextureAttributes();
        PlutoTexAttr.setTextureMode(TextureAttributes.MODULATE);
        plutoApp.setTextureAttributes(PlutoTexAttr);
        //Planet Textures
        plutoApp.setMaterial(planetMaterial);


        //Saturn Ring Texture
        Appearance saturnRingApp = new Appearance();
        URL fileNameSaturnRing = getClass().getClassLoader().getResource("images/saturn ring.jpg");
        TextureLoader loadsaturnRing = new TextureLoader(fileNameSaturnRing, this);
        ImageComponent2D saturnRingImage = loadsaturnRing.getImage();
        if(saturnRingImage == null){
            System.out.print("Cant find");
        }
        Texture2D saturnRingTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, saturnRingImage.getWidth(),
                saturnRingImage.getHeight());
        saturnRingTexture.setImage(0, saturnRingImage);
        saturnRingTexture.setEnable(true);
        saturnRingApp.setTexture(saturnRingTexture);
        //Pluto Texture Attributes
        TextureAttributes saturnRingTexAttr = new TextureAttributes();
        saturnRingTexAttr.setTextureMode(TextureAttributes.MODULATE);
        saturnRingApp.setTextureAttributes(saturnRingTexAttr);
        //Planet Textures
        saturnRingApp.setMaterial(planetMaterial);

        //Neptune Ring Texture
        Appearance neptuneRingApp = new Appearance();
        URL fileNameNeptuneRing = getClass().getClassLoader().getResource("images/neptune ring.jpg");
        TextureLoader loadNeptuneRing = new TextureLoader(fileNameNeptuneRing, this);
        ImageComponent2D neptuneRingImage = loadNeptuneRing.getImage();
        if(neptuneRingImage == null){
            System.out.print("Cant find");
        }
        Texture2D neptuneRingTexture= new Texture2D(Texture.BASE_LEVEL, Texture.RGBA, neptuneRingImage.getWidth(),
                neptuneRingImage.getHeight());
        neptuneRingTexture.setImage(0, neptuneRingImage);
        neptuneRingTexture.setEnable(true);
        neptuneRingApp.setTexture(neptuneRingTexture);
        //Pluto Texture Attributes
        TextureAttributes neptuneRingTexAttr = new TextureAttributes();
        neptuneRingTexAttr.setTextureMode(TextureAttributes.MODULATE);
        neptuneRingApp.setTextureAttributes(neptuneRingTexAttr);
        //Planet Textures
        neptuneRingApp.setMaterial(planetMaterial);

        //spheres of the planets
        Sphere sun = new Sphere(163f,primFlags, 100);
        Sphere mercury = new Sphere(.5f, primFlags,100, mercuryApp);
        Sphere venus = new Sphere(1.2f, primFlags,100, venusApp);
        Sphere earth = new Sphere(1.5f, primFlags,100, earthApp);
        Sphere moon = new Sphere(.375f,primFlags,100, moonApp);
        Sphere mars = new Sphere(.75f, primFlags,100, marsApp);
        Sphere jupiter = new Sphere(16.5f, primFlags,100,jupiterApp);
        jupiter.setPickable(true);
        Sphere saturn = new Sphere(13.5f, primFlags,100, saturnApp);
        Sphere uranus = new Sphere(6f, primFlags,100, uranusApp);
        Sphere neptune = new Sphere(5.8f, primFlags,100, neptuneApp);
        Sphere pluto = new Sphere(.5f, primFlags,100, plutoApp);
        Shape3D saturnRing = new Torus(12, 15);
        Shape3D neptuneRing = new Torus(5, 8);

        saturn.addChild(saturnRing);
        saturnRing.setAppearance(saturnRingApp);
        neptune.addChild(neptuneRing);
        neptuneRing.setAppearance(neptuneRingApp);

        //Sun Material
        Material sunMaterial = new Material();
        sunMaterial.setEmissiveColor(new Color3f(1.0f, 0.898f, .8f));
        //sunMaterial.setShininess(100f);
        Appearance sunApp = new Appearance();
        sunApp.setMaterial(sunMaterial);
        sun.setAppearance(sunApp);

        //Moon Material
        Material moonMaterial = new Material();
        moonMaterial.setEmissiveColor(new Color3f(1.0f, 1.0f, 1.0f));
        moonApp.setMaterial(moonMaterial);
        moon.setAppearance(moonApp);

        Transform3D tr = new Transform3D();

        //Sun
        tr.setTranslation(new Vector3f(0,0f,0f)); //distance to center
        TransformGroup tgSun = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgSunRotat = createRotationTransformGroup(sunRotationTimeMs);//rotation
        tgSun.addChild(sun);
        tgSunRotat.addChild(tgSun);
        root.addChild(tgSunRotat);

        //Mercury
        TransformGroup tgMercury = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgMercuryRotate =createRotationTransformGroup(mercuryRotationTimeMs);//rotation
        TransformGroup tgMercuryDist = createTranslatingTransformGroup(mercuryDistanceFromCenter);
        TransformGroup tgMercuryOrbit = createOrbitTransformGroup(mercuryOrbitTimeMs);
        tgMercury.addChild(mercury);
        tgMercuryRotate.addChild(tgMercury);
        tgMercuryDist.addChild(tgMercuryRotate);
        tgMercuryDist.addChild(planetText(6,"Mercury", -10, 3));
        tgMercuryOrbit.addChild(tgMercuryDist);
        root.addChild(tgMercuryOrbit);

        //Venus
        TransformGroup tgVenus = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgVenusRotate =createRotationTransformGroup(venusRotationTimeMs);//rotation
        TransformGroup tgVenusDist = createTranslatingTransformGroup(venusDistanceFromCenter);
        TransformGroup tgVenusOrbit = createOrbitTransformGroup(venusOrbitTimeMs);
        tgVenus.addChild(venus);
        tgVenusRotate.addChild(tgVenus);
        tgVenusDist.addChild(tgVenusRotate);
        tgVenusOrbit.addChild(tgVenusDist);
        root.addChild(tgVenusOrbit);

        //Earth
        TransformGroup tgEarth = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgEarthRotate =createRotationTransformGroup(earthRotationTimeMs);//rotation
        TransformGroup tgEarthDist = createTranslatingTransformGroup(earthDistanceFromCenter);
        TransformGroup tgEarthOrbit = createOrbitTransformGroup(earthOrbitTimeMs);
        tgEarth.addChild(earth);
        tgEarthRotate.addChild(tgEarth);
        tgEarthDist.addChild(tgEarthRotate);
        tgEarthOrbit.addChild(tgEarthDist);
        root.addChild(tgEarthOrbit);

        //Moon
        tr.setTranslation(new Vector3f(5f,0f,0f)); //distance to center
        TransformGroup tgMoon = new TransformGroup(tr);//-.2f,.15f,.15f
        TransformGroup tgMoonRotate =createRotationTransformGroup(moonRotationTimeMs);//rotation
        TransformGroup tgMoonOrbit = createOrbitTransformGroup(moonOrbitTimeMs);
        tgMoon.addChild(moon);
        tgMoonRotate.addChild(tgMoon);
        tgMoonOrbit.addChild(tgMoonRotate);
        tgEarth.addChild(tgMoonOrbit);

        //Mars
        //tr.setTranslation(new Vector3f(105f,0f,0f)); //distance to center
        TransformGroup tgMars = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgMarsRotate =createRotationTransformGroup(marsRotationTimeMs);//rotation
        TransformGroup tgMarsDist = createTranslatingTransformGroup(marsDistanceFromCenter);
        TransformGroup tgMarsOrbit = createOrbitTransformGroup(marsOrbitTimeMs);
        tgMars.addChild(mars);
        tgMarsRotate.addChild(tgMars);
        tgMarsDist.addChild(tgMarsRotate);
        tgMarsOrbit.addChild(tgMarsDist);
        root.addChild(tgMarsOrbit);

        //Jupiter
        TransformGroup tgJupiter = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgJupiterRotate =createRotationTransformGroup(jupiterRotationTimeMs);//rotation
        TransformGroup tgJupiterDist = createTranslatingTransformGroup(jupiterDistanceFromCenter);
        TransformGroup tgJupiterOrbit = createOrbitTransformGroup(jupiterOrbitTimeMs);
        tgJupiter.addChild(jupiter);
        //tgJupiter.addChild(shape);
        tgJupiterRotate.addChild(tgJupiter);
        tgJupiterDist.addChild(tgJupiterRotate);
        tgJupiterDist.addChild(planetText(25,"Jupiter", -35, 20));
        tgJupiterOrbit.addChild(tgJupiterDist);
        root.addChild(tgJupiterOrbit);


        Font3D saturnFont = new Font3D(new Font("SansSerif", Font.PLAIN, 25),
                new FontExtrusion());
        Transform3D transformSaturnText = new Transform3D();
        transformSaturnText.setRotation(new Matrix3d());
        transformSaturnText.rotY(180);
        Text3D saturnText = new Text3D(saturnFont, "Saturn");
        saturnText.setPosition(new Point3f(-40,15,0));
        //text.setCharacterSpacing(2);
        TransformGroup saturnTextTG = new TransformGroup(transformSaturnText);
        //Shape3D saturnTextShape = new Shape3D(saturnText, ap);
        //saturnTextTG.addChild(saturnTextShape);

        //Saturn
        TransformGroup tgSaturn = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgSaturnRotate =createRotationTransformGroup(saturnRotationTimeMs);//rotation
        TransformGroup tgSaturnDist =createTranslatingTransformGroup( saturnDistanceFromCenter);//rotation
        TransformGroup tgSaturnOrbit = createOrbitTransformGroup(saturnOrbitTimeMs);
        tgSaturn.addChild(saturn);
        tgSaturnRotate.addChild(tgSaturn);
        tgSaturnDist.addChild(tgSaturnRotate);
        tgSaturnDist.addChild(planetText(25,"Saturn", -35, 20));
        tgSaturnOrbit.addChild(tgSaturnDist);
        root.addChild(tgSaturnOrbit);

        //Uranus
        TransformGroup tgUranus = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgUranusRotate =createRotationTransformGroup(uranusRotationTimeMs);//rotation
        TransformGroup tgUranusDist =createTranslatingTransformGroup(uranusDistanceFromCenter);//rotation
        TransformGroup tgUranusOrbit = createOrbitTransformGroup(uranusOrbitTimeMs);
        tgUranus.addChild(uranus);
        tgUranusRotate.addChild(tgUranus);
        tgUranusDist.addChild(tgUranusRotate);
        tgUranusOrbit.addChild(tgUranusDist);
        root.addChild(tgUranusOrbit);

        //Neptune
        TransformGroup tgNeptune = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgNeptuneRotate =createRotationTransformGroup(neptuneRotationTimeMs);//rotation
        TransformGroup tgNeptuneDist =createTranslatingTransformGroup(neptuneDistanceFromCenter);//rotation
        TransformGroup tgNeptuneOrbit = createOrbitTransformGroup(neptuneOrbitTimeMs);
        tgNeptune.addChild(neptune);
        tgNeptuneRotate.addChild(tgNeptune);
        tgNeptuneDist.addChild(tgNeptuneRotate);
        tgNeptuneOrbit.addChild(tgNeptuneDist);
        root.addChild(tgNeptuneOrbit);

        //Pluto
        TransformGroup tgPluto = new TransformGroup();//-.2f,.15f,.15f
        TransformGroup tgPlutoRotat =createRotationTransformGroup(plutoRotationTimeMs);//rotation
        TransformGroup tgPlutoDist =createTranslatingTransformGroup(plutoDistanceFromCenter);//rotation
        TransformGroup tgPlutoOrbit = createOrbitTransformGroup(plutoOrbitTimesMs);
        tgPluto.addChild(pluto);
        tgPlutoRotat.addChild(tgPluto);
        tgPlutoDist.addChild(tgPlutoRotat);
        tgPlutoOrbit.addChild(tgPlutoDist);
        root.addChild(tgPlutoOrbit);

        //light for the sun
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),Double.MAX_VALUE);
        Background background = createBackGround();
        background.setApplicationBounds(bounds);
        root.addChild(background);

        PointLight light = new PointLight();
        light.setColor(new Color3f(1.0f,0.898f,0.8f));
        light.setCollidable(true);
        light.setAttenuation(1.5f, 0.0f, 0.0f);
        light.setPosition(0f,0f,0f);
        light.setInfluencingBounds(bounds);
        root.addChild(light);

        return root;

    }


    public javax.media.j3d.TransformGroup planetText(int size, String name, int xPos, int yPos){
        Appearance ap = new Appearance();
        ap.setMaterial(new Material());
        Font3D font = new Font3D(new Font("SansSerif", Font.PLAIN, size),
                new FontExtrusion());
        Transform3D transform3D = new Transform3D();
        transform3D.setRotation(new Matrix3d());
        transform3D.rotY(180);
        Text3D text = new Text3D(font, name);
        text.setPosition(new Point3f(xPos,yPos,0));
        TransformGroup tg = new TransformGroup(transform3D);
        Shape3D shape = new Shape3D(text, ap);
        tg.addChild(shape);
        return tg;
    }

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

    private static TransformGroup createTranslatingTransformGroup(
            double distanceFromCenter)
    {
        TransformGroup translationTransformGroup = new TransformGroup();
        Transform3D translationTransform = new Transform3D();
        translationTransform.setTranslation(
                new Vector3d( distanceFromCenter, 0, 0));
        translationTransformGroup.setTransform(translationTransform);
        return translationTransformGroup;
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

    Background createBackGround(){
        Background background = new Background();
        BranchGroup bg = new BranchGroup();
        Sphere sphere = new Sphere(.5f, Sphere.GENERATE_NORMALS |
                Sphere.GENERATE_NORMALS_INWARD |
                Sphere.GENERATE_TEXTURE_COORDS, 100);
        Appearance ap = sphere.getAppearance();
        bg.addChild(sphere);
        background.setGeometry(bg);

        URL filename =
                getClass().getClassLoader().getResource("images/space.jpg");
        TextureLoader loader = new TextureLoader(filename, this);
        Texture texture = loader.getTexture();
        ap.setTexture(texture);
        return background;
    }

}
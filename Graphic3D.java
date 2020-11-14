/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic3d;

/**
 *https://sites.google.com/view/flying-java-3d/videos_earlier_3/how-to-put-obj-models-into-java-3d-without-converting-them-to-wrl_2
 * @author metal
 */
import java.applet.*;
import java.awt.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.behaviors.keyboard.*;

import com.sun.j3d.loaders.Scene;

import com.sun.j3d.loaders.objectfile.ObjectFile;

import java.io.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Graphic3D extends Applet implements KeyListener{
        private SimpleUniverse universe = null;
	private Canvas3D canvas = null;
	private TransformGroup viewtrans = null;

	private TransformGroup tg = null;
	private Transform3D t3d = null;
	private Transform3D t3dstep = new Transform3D();
	private Matrix4d matrix = new Matrix4d();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              System.setProperty("sun.awt.noerasebackground", "true");
        new MainFrame(new Graphic3D, 640, 480);
  // TODO code application logic here
    }
    public Graphic3D(){
        setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

		canvas = new Canvas3D(config);
		add("Center", canvas);
		universe = new SimpleUniverse(canvas);

		BranchGroup scene = createSceneGraph();
		universe.getViewingPlatform().setNominalViewingTransform();

		universe.getViewer().getView().setBackClipDistance(100.0);

		canvas.addKeyListener(this);

		universe.addBranchGraph(scene);
	
    }
    public void init() {
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
    
    private BranchGroup createSceneGraph() {
        BranchGroup root = new BranchGroup();
        TransformGroup spin = new TransformGroup();
        spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(spin);
        //object
        Appearance ap = new Appearance();
        ap.setMaterial(new Material());
        PolygonAttributes pa = new PolygonAttributes();
        pa.setBackFaceNormalFlip(true);
        pa.setCullFace(PolygonAttributes.CULL_NONE);
        ap.setPolygonAttributes(pa);
        
        
        
        BranchGroup objRoot = new BranchGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(), 10000.0);

        viewtrans = universe.getViewingPlatform().getViewPlatformTransform();

        KeyNavigatorBehavior keyNavBeh = new KeyNavigatorBehavior(viewtrans);
            keyNavBeh.setSchedulingBounds(bounds);
	PlatformGeometry platformGeom = new PlatformGeometry();
	platformGeom.addChild(keyNavBeh);
	universe.getViewingPlatform().setPlatformGeometry(platformGeom);

	objRoot.addChild(createLadybird());

		Background background = new Background();
		background.setColor(0.75f, 0.69f, 0.680f);
		background.setApplicationBounds(bounds);
		objRoot.addChild(background);

		return objRoot;
}
//https://sites.google.com/view/flying-java-3d/videos_earlier_3/how-to-handle-problems-that-occur-while-loading-obj-models-into-java-3d
    
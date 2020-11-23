import javax.vecmath.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.net.URL;
import javax.media.j3d.*;
 import com.sun.j3d.utils.universe.*;
 import com.sun.j3d.utils.geometry.*;
 import com.sun.j3d.utils.image.*;
 import com.sun.j3d.utils.behaviors.mouse.*;
 import com.sun.j3d.utils.behaviors.vp.*;
 //import chapter7.Axes;
 import java.applet.*;
 import com.sun.j3d.utils.applet.MainFrame;

 public class TaurusCreate extends Applet {
 public static void main(String[] args) {
 new MainFrame(new TaurusCreate(), 480, 480);
 }
public void init() {
 // create canvas
 GraphicsConfiguration gc =
 SimpleUniverse.getPreferredConfiguration();
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

 Transform3D tr = new Transform3D();
 tr.setScale(0.2);
 tr.setRotation(new AxisAngle4d(1, 0, 0, Math.PI/6));
 TransformGroup tg = new TransformGroup(tr);
 spin.addChild(tg);

 // object
 Shape3D torus1 = new Torus(0.1, 0.3);
 Appearance ap = new Appearance();
 ap.setMaterial(new Material());
 torus1.setAppearance(ap);
 tg.addChild(torus1);

 Shape3D torus2 = new Torus(0.1, 0.2);
 ap = new Appearance();
 ap.setMaterial(new Material());
 ap.setTransparencyAttributes(
 new TransparencyAttributes
 (TransparencyAttributes.BLENDED, 0.5f));
 torus2.setAppearance(ap);
 Transform3D tr2 = new Transform3D();
 tr2.setRotation(new AxisAngle4d(1, 0, 0, Math.PI/2));
 tr2.setTranslation(new Vector3d(0.5,0,0));
 TransformGroup tg2 = new TransformGroup(tr2);
 tg.addChild(tg2);
 tg2.addChild(torus2);

 Alpha alpha = new Alpha(-1, 8000);
 RotationInterpolator rotator = new RotationInterpolator
 (alpha, spin);
 BoundingSphere bounds = new BoundingSphere();
 rotator.setSchedulingBounds(bounds);
 spin.addChild(rotator);

 // background and lights
 Background background = new Background(1.0f, 1.0f, 1.0f);
 background.setApplicationBounds(bounds);
 root.addChild(background);
 AmbientLight light = new AmbientLight
 (true, new Color3f(Color.blue));
 light.setInfluencingBounds(bounds);
 root.addChild(light);
 PointLight ptlight = new PointLight(new Color3f(Color.white),
 new Point3f(3f,3f,3f), new Point3f(1f,0f,0f));
 ptlight.setInfluencingBounds(bounds);
 root.addChild(ptlight);
 return root;
 }
 }
class Torus extends Shape3D {
 public Torus(double r1, double r2) {
 int m = 10;
 int n = 20;
 Point3d[] pts = new Point3d[m];
 pts[0] = new Point3d(r1+r2, 0, 0);
 double theta = 2.0 * Math.PI / m;
double c = Math.cos(theta);
double s = Math.sin(theta);
 double[] mat = {c, -s, 0, r2*(1-c),
 s, c, 0, -r2*s,
 0, 0, 1, 0,
 0, 0, 0, 1};
Transform3D rot1 = new Transform3D(mat);
 for (int i = 1; i < m; i++) {
 pts[i] = new Point3d();
 rot1.transform(pts[i-1], pts[i]);
 }

 Transform3D rot2 = new Transform3D();
 rot2.rotY(2.0*Math.PI/n);
 IndexedQuadArray qa = new IndexedQuadArray(m*n,
 IndexedQuadArray.COORDINATES, 4*m*n);
 int quadIndex = 0;
 for (int i = 0; i < n; i++) {
 qa.setCoordinates(i*m, pts);
 for (int j = 0; j < m; j++) {
 rot2.transform(pts[j]);
 int[] quadCoords = {i*m+j, ((i+1)%n)*m+j,
 ((i+1)%n)*m+((j+1)%m), i*m+((j+1)%m)};
 qa.setCoordinateIndices(quadIndex, quadCoords);
 quadIndex += 4;
 }
 }
 GeometryInfo gi = new GeometryInfo(qa);
 NormalGenerator ng = new NormalGenerator();
 ng.generateNormals(gi);
 this.setGeometry(gi.getGeometryArray());
 }
 }

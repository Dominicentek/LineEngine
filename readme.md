# Line Engine

A Java library for drawing lines.
To download the library, there's the JAR in the repo.

## Examples
### Draw a simple line
```
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import com.line.renderer.Renderer;

public class LineExample extends JPanel {
  public static JFrame frame = new JFrame("LineExample");
  public void paint(Graphics g) {
    Renderer renderer = new Renderer(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
    renderer.setAbsolutePosition(100, 100);
    renderer.drawLine(200, 200);
    g.drawImage(renderer.getImage().toBufferedImage(), 0, 0, this);
  }
  public static void main(String[] args) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(100, 100);
    frame.getContentPane().setPreferredSize(new Dimension(640, 360));
    frame.pack();
    frame.setResizable(false);
    frame.add(new LineExample());
    frame.setVisible(true);
  }
}
```
### Draw a colored line
```
Renderer renderer = new Renderer(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
renderer.setAbsolutePosition(100, 100);
renderer.setColor(new Color(255, 0, 0));
renderer.drawLine(200, 200);
```
### Draw a square
```
LineObject object = new LineObject();
object.add(new Line(100, 100, 200, 100));
object.add(new Line(200, 100, 200, 200));
object.add(new Line(200, 200, 100, 200));
object.add(new Line(100, 200, 100, 100));
renderer.drawObject(object);
```
### Transforming an object
```
object.move(50, 50); // Move object relatively
object.rotate(40); // Rotate by 40 degrees
object.scale(2); // Scale the object 2x
renderer.drawObject(object);
```

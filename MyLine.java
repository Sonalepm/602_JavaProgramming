package package_painter;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;



public class MyLine extends MyShape
{
// call default superclass constructor
public MyLine()
{
   super();
} // end MyLine no-argument constructor

// call superclass constructor passing parameters
public MyLine(int x1, int y1, int x2, int y2, Paint color,Stroke stroke)
{
   super(x1, y1, x2, y2, color,stroke);
} // end MyLine constructor

// draw line in specified color
public void draw(Graphics2D G)
{
  
   G.setPaint(getPaint());
   G.setStroke(getStroke());
   G.drawLine(getX1(), getY1(), getX2(), getY2());
} // end method draw
} // end class MyLine


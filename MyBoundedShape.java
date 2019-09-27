package package_painter;

import java.awt.Paint;
import java.awt.Stroke;

public abstract class MyBoundedShape extends MyShape
{
private boolean filled; // whether this shape is filled

// call default superclass constructor
public MyBoundedShape()
{
   super();
   setFilled(false);
} // end MyBoundedShape no-argument constructor

// call superclass constructor passing parameters
public MyBoundedShape(int x1, int y1, int x2, int y2,Paint color, boolean isFilled,Stroke stroke)
{
   super(x1, y1, x2, y2,color,stroke);
   setFilled(isFilled);
} // end MyBoundedShape constructor

// get upper left x coordinate
public int getUpperLeftX()
{
   return Math.min(getX1(), getX2());
} // end method getUpperLeftX

// get upper left y coordinate
public int getUpperLeftY()
{
   return Math.min(getY1(), getY2());
} // end method getUpperLeftY

// get shape width
public int getWidth()
{
   return Math.abs(getX2() - getX1());
} // end method getWidth

// get shape height
public int getHeight()
{
   return Math.abs(getY2() - getY1());
} // end method getHeight

// determines whether this shape is filled
public boolean isFilled()
{
   return filled;
} // end method is filled

// sets whether this shape is filled
public void setFilled(boolean isFilled)
{
   filled = isFilled;
} // end method setFilled
} // end class MyBoundedShape


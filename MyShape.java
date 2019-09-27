package package_painter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.BasicStroke;

public abstract class MyShape
{
   private int x1; // x coordinate of first endpoint
   private int y1; // y coordinate of first endpoint
   private int x2; // x coordinate of second endpoint
   private int y2; // y coordinate of second endpoint
   private Paint paint; // color of this shape
   private Stroke stroke; //basic stroke of this shape
   
   // default constructor initializes values with 0
   public MyShape()
   {
	// call constructor to set default values
      this.x1 = 0;
      this.y1 = 0;
      this.x2 = 0;
      this.y2 = 0;
      this.paint = Color.BLACK;
      this.stroke = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
      
   } // end MyShape no-argument constructor

   // constructor
   public MyShape(int x1, int y1, int x2, int y2, Paint paint,Stroke stroke)
   {
      setX1(x1); // set x coordinate of first endpoint
      setY1(y1); // set y coordinate of first endpoint
      setX2(x2); // set x coordinate of second endpoint
      setY2(y2); // set y coordinate of second endpoint
      setPaint(paint); // set the color
      setStroke(stroke); //set the stroke
   } // end MyShape constructor

   // set the x-coordinate of the first point
   public void setX1(int x1)
   {
     this.x1 = (x1 >= 0 ? x1 : 0);
   } // end method setX1

   // get the x-coordinate of the first point
   public int getX1()
   {
     return x1;
   } // end method getX1

   // set the x-coordinate of the second point
   public void setX2(int x2)
   {
     this.x2 = (x2 >= 0 ? x2 : 0);
   } // end method setX2

   // get the x-coordinate of the second point
   public int getX2()
   {
     return x2;
   } // end method getX2

   // set the y-coordinate of the first point
   public void setY1(int y1)
   {
     this.y1 = (y1 >= 0 ? y1 : 0);
   } // end method setY1

   // get the y-coordinate of the first point
   public int getY1()
   {
     return y1;
   } // end method getY1

   // set the y-coordinate of the second point
   public void setY2(int y2)
   {
     this.y2 = (y2 >= 0 ? y2 : 0);
   } // end method setY2

   // get the y-coordinate of the second point
   public int getY2()
   {
     return y2;
   } // end method getY2

   // set the color
   public void setPaint(Paint paint)
   {
      this.paint = paint;
   } // end method setPaint

   // get the color
   public Paint getPaint()
   {
      return paint;
   } // end method getPaint

   //set the Stroke
   public void setStroke(Stroke stroke)
   {
	   this.stroke = stroke;
   }
   
   //get the STroke
   public Stroke getStroke()
   {
	   return stroke;
   }
   // abstract draw method
   public abstract void draw(Graphics2D G);
} // end class MyShape
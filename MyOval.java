package package_painter;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public class MyOval extends MyBoundedShape
{
   // call default superclass constructor
   public MyOval()
   {
      super();
   } // end MyOval no-argument constructor

   // call superclass constructor passing parameters
   public MyOval(int x1, int y1, int x2, int y2,Paint color, boolean isFilled,Stroke isStroked)
   {
      super(x1, y1, x2, y2, color, isFilled,isStroked);
   } // end MyOval constructor

   // draw oval
   public void draw(Graphics2D G)
   {
	  
      G.setPaint(getPaint());
      G.setStroke(getStroke());
      //if Filled
      if (isFilled())
         G.fillOval(getUpperLeftX(), getUpperLeftY(),getWidth(), getHeight());
      else
         G.drawOval(getUpperLeftX(), getUpperLeftY(),getWidth(), getHeight());
      
      
      
   } // end method draw
} // end class MyOval
package package_painter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel
{
private ArrayList<MyShape> shapes; // array containing all the shapes
private int shapeType; // the type of shape to draw
private MyShape currentShape; // the current shape being drawn
private Paint paint; // the color of the shape
private boolean filledShape; // whether this shape is filled
private Color color1; //first color
private Color color2; //second color
private float lineWidth; //line width
private boolean gradient; //whether gradient is selected or not
private boolean isstroke; // whether stroked or not 
private Stroke currentStroke; // basic stroke
private float dashlength=15; //dash length
private JLabel statusLabel; // label displaying mouse coordinates
private Stroke s1;

// constructor
public DrawPanel(JLabel status)
{
   shapes = new ArrayList<MyShape>(); // create the array of upto 100 shapes
   setShapeType(0); // initially draw lines
   setPaint(Color.BLACK); // start drawing with black
   setFilledShape(false);// not filled by default
   currentShape = null; // not drawing anything initially
   setGradient(false); // no gradient by default      
   setBackground(Color.WHITE); // set a white background
   setisStroke(false); //by default no stroke
   currentStroke = new BasicStroke();
   // add the mouse listeners
   MouseHandler mouseHandler = new MouseHandler();
   addMouseListener(mouseHandler);
   addMouseMotionListener(mouseHandler);
   
   // set the status label for displaying mouse coordinates
   statusLabel = status;
} // end DrawPanel constructor


public void paintComponent(Graphics g)
{
   super.paintComponent(g);
   Graphics2D G = (Graphics2D) g;
   
   for (MyShape currentshape : shapes){
     
       if(currentshape!= null)
          currentshape.draw(G);
   }
   
   
} // end method paintComponent

// sets the type of shape to draw
public void setShapeType(int shapeType)
{
   if (shapeType < 0 || shapeType > 2)
      shapeType = 0;
   
   this.shapeType = shapeType;
} // end method setShapeType

//sets color 1
public void setColor1 (Color color1){
    this.color1 = color1;
}
//sets color 2
public void setColor2 (Color color2){
    this.color2 = color2;
}

// sets the drawing color
public void setPaint(Paint paint)
{
   this.paint = paint ;
} // end method setPaint

//sets whether to draw a filled shape
public void setFilledShape(boolean isFilled)
{
this.filledShape = isFilled;
} // end method setFilledShape

//sets whether gradient or not
public void setGradient(boolean isgradient){
    this.gradient = isgradient;
}

//set whether line is stroked or not
public void setisStroke(boolean isstroke){
    this.isstroke = isstroke;
}

//set line width
public void setLineWidth(float lineWidth){
    this.lineWidth = lineWidth;
}

//set length of dash
public void setDashLength(float dashlength){
    this.dashlength= dashlength;
}

//set stroke
public void setStroke(Stroke currentStroke){
    this.currentStroke = currentStroke;
}

// clears the last shape drawn
public void clearLastShape()
{
	shapes.remove(shapes.size()-1);
    super.repaint();
} // end method clearLastShape

// clears all drawings on this panel
public void clearDrawing()
{
	  shapes.clear();
      super.repaint();
} // end method clearDrawing

public float getDashlength() {
	return dashlength;
}

public void setDashlength(float dashlength) {
	this.dashlength = dashlength;
	
}

// handles mouse events for this JPanel
private class MouseHandler extends MouseAdapter
   implements MouseMotionListener
{
   // creates and sets the initial position for the new shape
   public void mousePressed(MouseEvent e)
   {
     
      
      //if Gradient
      if (gradient){
          Paint paint_grad = new GradientPaint(0,0,color1,50,50,color2,true);
           setPaint(paint_grad);
        }
        else{
        	
        	setPaint(color1);
        }
   // if Stroked
      if (isstroke == true){
    	  
          float dashes[] = {dashlength};
          s1 = new BasicStroke(lineWidth, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 10, dashes, 0);
          setStroke(s1);
         //repaint();
      }
      else{
          Stroke s2 = new BasicStroke(lineWidth);
          setStroke(s2);
        //repaint();
      }
      
      // create the appropriate shape based on shapeType
      switch (shapeType)
      {
         case 0:
            currentShape = new MyLine(e.getX(), e.getY(),e.getX(), e.getY(), paint,currentStroke);      
            shapes.add(currentShape);
           
            //repaint();
            break;
            
         case 1:
            currentShape = new MyOval(e.getX(), e.getY(),e.getX(), e.getY(), paint, filledShape,currentStroke);      
            shapes.add(currentShape);
          
            //repaint();
            break;
         case 2:
            currentShape = new MyRect(e.getX(), e.getY(),e.getX(), e.getY(), paint, filledShape,currentStroke);      
            shapes.add(currentShape);
        
           // repaint();
            break;
      } // end switch
   } // end method mousePressed

   // fixes the current shape onto the panel
   public void mouseReleased(MouseEvent e)
   {
     
      // set the second point on the shape
      currentShape.setX2(e.getX());
      currentShape.setY2(e.getY());
      //shapes.add(currentShape);
      // set the shape only if there is room in the array
      repaint();
   } // end method mouseReleased

   // update the shape to the current mouse position while dragging
   public void mouseDragged(MouseEvent e)
   {
	   
         currentShape.setX2(e.getX());
         currentShape.setY2(e.getY());
         repaint();
      
      mouseMoved(e); // update status bar
   } // end method mouseDragged

   // updates the status bar to show the current mouse coordinates
   public void mouseMoved(MouseEvent e)
   {
      statusLabel.setText(
         String.format("(%d,%d)", e.getX(), e.getY()));
   } // end method mouseMoved
} // end class MouseHandler
} // end class DrawPanel



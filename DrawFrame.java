package package_painter;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DrawFrame extends JFrame
   implements ItemListener, ActionListener
{
   // Array of possible colors
   private Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN,
      Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
      Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE,
      Color.YELLOW};
   
   // Array of names corresponding to the possible colors
   private String[] colorNames = {"Black", "Blue", "Cyan", "Dark Gray",
      "Gray", "Green", "Light Gray", "Magenta", "Orange", "Pink", "Red",
      "White", "Yellow"};
   
   // Array of possible shapes
   private String[] shapes = {"Line", "Oval", "Rectangle"};
   
   private DrawPanel drawPanel; // panel that handles the drawing
   
   private JButton undoButton; // button to undo the last shape drawn
   private JButton clearButton; // button to clear all shapes
   private JComboBox<String> colorChoices; // combo box for selecting the color
   private JComboBox<String> shapeChoices; // combo box for selecting shapes
   private JCheckBox filledCheckBox; // check box to toggle filled shapes
   private JCheckBox filledGradCheckBox; // check box to toggle gradient fill
   private JCheckBox filledDashedCheckBox;// check box to toggle dashed line
   private JButton selectColour1; // button to select 1st colour
   private JButton selectColour2; // button to select 2nd colour
   private Color color1 = Color.LIGHT_GRAY; // default color value1
   private Color color2 = Color.LIGHT_GRAY; // default color value2
   private JTextField lineWidth; // text field for width of line
   private JLabel lineWidth1; // label for text field
   private JLabel dashLength1;// text field for length of dash
   private JTextField dashLength; // label for text field
   
   // constructor
   public DrawFrame()
   {
      super("Painter");
      
     
       JPanel topPanel = new JPanel(); // create a panel to store the components at the top of the frame 
       JPanel firstRowPanel= new JPanel(); // create a panel to store the first row of top panel
       JPanel secondRowPanel = new JPanel();// create a panel to store the second row of top panel
       
       topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // make a box lauout to stack 2 panels in form of rows
       
       // add the row panel to the frame
       add(topPanel, BorderLayout.NORTH);
       //add both rows to top
       topPanel.add(firstRowPanel);
       topPanel.add(secondRowPanel);
       

      // create a combobox for choosing colors
      colorChoices = new JComboBox<String>(colorNames);
      colorChoices.addItemListener(this);
      firstRowPanel.add(colorChoices);

      // create a combobox for choosing shapes
      shapeChoices = new JComboBox<String>(shapes);
      shapeChoices.addItemListener(this);
      firstRowPanel.add(shapeChoices);      

      // create a checkbox to determine whether the shape is filled
      filledCheckBox = new JCheckBox("Filled");
      filledCheckBox.addItemListener(this);
      firstRowPanel.add(filledCheckBox);

      // create a button for clearing the last drawing
      undoButton = new JButton("Undo");
      undoButton.addActionListener(this);
      firstRowPanel.add(undoButton);
      
      // create a button for clearing all drawings
      clearButton = new JButton("Clear");
      clearButton.addActionListener(this);
      firstRowPanel.add(clearButton);
      
      // create a checkbox to use gradient
      filledGradCheckBox = new JCheckBox("Use Gradient");
      filledGradCheckBox.addItemListener(this);
      secondRowPanel.add(filledGradCheckBox);
      
      //create button and event listner for first color selection
      selectColour1 = new JButton("1st color");
      selectColour1.addActionListener(
    		 new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					color1 = JColorChooser.showDialog(secondRowPanel.getComponent(2), "Choose first Color",color1);
					drawPanel.setColor1(color1);
				}
    			 
    		 } 
    		  );
      secondRowPanel.add(selectColour1);
      
      //create button and event listner for first color selection
      selectColour2 = new JButton("2nd color");
      selectColour2.addActionListener(
     		 new ActionListener() {

 				@Override
 				public void actionPerformed(ActionEvent e) {
 					// TODO Auto-generated method stub
 					color2 = JColorChooser.showDialog(secondRowPanel.getComponent(2), "Choose second Color",color2);
 					drawPanel.setColor2(color2);
 				}
     			 
     		 } 
     		  );
      secondRowPanel.add(selectColour2);
      
      //create label for line width
      lineWidth1 = new JLabel("Line Width");
      secondRowPanel.add(lineWidth1);
      
      //create field for line width
      lineWidth = new JTextField("1", 2);
      lineWidth.addActionListener(
    		  new ActionListener() {

   				@Override
   				public void actionPerformed(ActionEvent e) {
   					float set_width;
   	              
   	               set_width = Float.parseFloat(lineWidth.getText());
   					drawPanel.setLineWidth(set_width);
   				}
    		  }
    		  );
      secondRowPanel.add(lineWidth);
      
      
      //create label for dash length
      dashLength1 = new JLabel("Dash Length");
      secondRowPanel.add(dashLength1);
      
      //create text field for dash length
      dashLength = new JTextField("15", 2);
      dashLength.addActionListener(
    		  new ActionListener() {

   				@Override
   				public void actionPerformed(ActionEvent e) {
    				float set_length;
   	                set_length = Float.parseFloat(dashLength.getText());
   	                drawPanel.setDashLength(set_length);
   				}
    		  }
    		  );
      secondRowPanel.add(dashLength);
      
      
      
      // create chechbox for type of line solid or dashed
      filledDashedCheckBox = new JCheckBox("Dashed");
      filledDashedCheckBox.addItemListener(this);
      secondRowPanel.add(filledDashedCheckBox);

      
      // create a label for the status bar
      JLabel statusLabel = new JLabel("(0,0)");

      // add the status bar at the bottom
      add(statusLabel, BorderLayout.SOUTH);
            
      // create the DrawPanel with its status bar label
      drawPanel = new DrawPanel(statusLabel);
      
      add(drawPanel); // add the drawing area to the center      
   } // end DrawFrame constructor

   // handle selections made to a combobox or checkbox
   public void itemStateChanged(ItemEvent e)
   {
      if (e.getSource() == shapeChoices) // choosing a shape
         drawPanel.setShapeType(shapeChoices.getSelectedIndex());
      else if (e.getSource() == colorChoices) // choosing a color
         drawPanel.setColor1(colors[colorChoices.getSelectedIndex()]);
      else if (e.getSource() == filledCheckBox) // filled/unfilled
         drawPanel.setFilledShape(filledCheckBox.isSelected());
      else if (e.getSource() == filledGradCheckBox)
         drawPanel.setGradient(filledGradCheckBox.isSelected());
      else if (e.getSource() == filledDashedCheckBox)
    	 drawPanel.setisStroke(filledDashedCheckBox.isSelected());
      
   } // end method itemStateChanged


   // handle button clicks
   public void actionPerformed(ActionEvent e)
   {
      if (e.getSource() == undoButton)
         drawPanel.clearLastShape();
      else if (e.getSource() == clearButton)
         drawPanel.clearDrawing();
      
   } // end method actionPerformed
   
  
} // end class DrawFrame


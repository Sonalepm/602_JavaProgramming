package package_painter;

import javax.swing.JFrame;

public class Painter
{
   public static void main(String[] args)
   {
      DrawFrame application = new DrawFrame();
      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.setSize(700, 700);
      application.setVisible(true);
   } // end main
} // end class Painter

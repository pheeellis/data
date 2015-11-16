//adopted and modified from code given by textbook solution
//added anonymous class of mouselistener to enable barframe as controller

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

/**
  A class that implements an Observer object that displays a barchart view of
  a data model.
*/
public class BarFrame extends JFrame implements ChangeListener
{
   /**
      Constructs a BarFrame object
      @param dataModel the data that is displayed in the barchart
   */
   public BarFrame(DataModel dataModel)
   {
      this.dataModel = dataModel;
      a = dataModel.getData();

      setLocation(0,200);
      setLayout(new BorderLayout());

      Icon barIcon = new Icon()
      {
         public int getIconWidth() { return ICON_WIDTH; }
         public int getIconHeight() { return ICON_HEIGHT; }
         public void paintIcon(Component c, Graphics g, int x, int y)
         {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(Color.red);

            double max =  (a.get(0)).doubleValue();
            for (Double v : a)
            {
               double val = v.doubleValue();
               if (val > max)
                  max = val;
            }

            double barHeight = getIconHeight() / a.size();

            int i = 0;
            for (Double v : a)
            {
               double value = v.doubleValue();

               double barLength = getIconWidth() * value / max;

               Rectangle2D.Double rectangle = new Rectangle2D.Double
                  (0, barHeight * i, barLength, barHeight);
               i++;
               g2.fill(rectangle);
            }
         }
      };

     //anonymous class for mouselistener
     this.addMouseListener(new MouseListener()
     {
    	 public void mousePressed(MouseEvent e)
    	 {
    		 int location = 0;
    		 double value = 0;
    		 double x = e.getX();
    		 double y =  e.getY();
    		 //0-49
    		 if (y < ICON_HEIGHT/4)
    		 {
    			 location = 0;
    		 }
    		 //50-100
    		 else if (y >= ICON_HEIGHT/4 && y <= ICON_HEIGHT/2)
    		 {
    			 location = 1;
    		 }
    		 //151-200
    		 else if (y > (3 * ICON_HEIGHT) /4)
    		 {
    		     location = 3;
    		 }
    		 //101-150
    		else
    		 {
    			 location = 2;
    		 }
    			
    		double proportion = ICON_WIDTH/x;
    		double max =  (a.get(0)).doubleValue();
    	    for (Double v : a)
    	    {
    	        double val = v.doubleValue();
                if (val > max)
                {
    	            max = val;
    	        }
    	    }
    		value = Math.ceil(max/proportion);
    		dataModel.update(location, value);
    	}

			public void mouseClicked(MouseEvent e) 
			{
				return;
			}

			public void mouseReleased(MouseEvent e) 
			{
				return;
			}

			public void mouseEntered(MouseEvent e) 
			{
				return;
			}

			public void mouseExited(MouseEvent e) 
			{
				return;
			}
     });
     
      add(new JLabel(barIcon));

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }

   /**
      Called when the data in the model is changed.
      @param e the event representing the change
   */
   public void stateChanged(ChangeEvent e)
   {
      a = dataModel.getData();
      repaint();
   }


   private ArrayList<Double> a;
   private DataModel dataModel;

   private static final int ICON_WIDTH = 200;
   private static final int ICON_HEIGHT = 200;

}

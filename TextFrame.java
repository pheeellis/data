//adopted and modified from code given by textbook solution
//made textfield into a view by implementing changelistener with statechanged method

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.ArrayList;

/**
  A class for displaying the model as a column of textfields in a frame.
*/
public class TextFrame extends JFrame implements ChangeListener
{
   /**
      Constructs a JFrame that contains the textfields containing the data
      in the model.
      @param d the model to display
   */
   public TextFrame(DataModel d)
   {
      dataModel = d;

      final Container contentPane = this.getContentPane();
      setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

      a = dataModel.getData();
      fieldList = new JTextField[a.size()];

      // A listener for action events in the text fields
      ActionListener l = new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            // Figure out which field generated the event
            JTextField c = (JTextField) e.getSource();
            int i = 0;
            int count = fieldList.length;
            while (i < count && fieldList[i] != c)
               i++;

            String text = c.getText().trim();

            try
            {
               double value = Double.parseDouble(text);
               dataModel.update(i, value);
            }
            catch (Exception exc)
            {
               c.setText("Error.  No update");
            }
         }
      };

      final int FIELD_WIDTH = 11;
      for (int i = 0; i < a.size(); i++)
      {
         JTextField textField = new JTextField(a.get(i).toString(),FIELD_WIDTH);
         textField.addActionListener(l);
         add(textField);
         fieldList[i] = textField; 
        }
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();
      setVisible(true);
   }
   
   /**
    * Called when the data in the model is changed.
    * @param e the event representing the change
    * @postcondition changes in data are reflected in view
    */
   public void stateChanged(ChangeEvent e)
   {
      a = dataModel.getData();
    
      int i = 0;
  
     for (JTextField f: fieldList)
     {
    	 f.setText(a.get(i).toString());
    	 i++;
     }
   }
   
   DataModel dataModel;
   JTextField[] fieldList;
   ArrayList<Double> a;
   
}

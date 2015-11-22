import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TesterForCalendar 
{
	public static void main(String[] arg)
	
	{
		
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		
		MyCalendar c = new MyCalendar();
		CalTable caltable = new CalTable(c);
		
		c.addChangeListener(caltable);
		
		frame.add(caltable);
		
		
		JButton previous = new JButton("<");
		JButton next = new JButton(">");
		
		JPanel moveCalendar = new JPanel();
		
		moveCalendar.add(previous);
		moveCalendar.add(next);
		
		frame.add(moveCalendar, BorderLayout.NORTH);
		
		
		previous.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				c.previous();
				
				//System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
				
				System.out.println(c.getToday().get(Calendar.MONTH) + "/" + c.getToday().get(Calendar.DAY_OF_MONTH));
				//repaint day
				
				
			}
		});
		
		next.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				c.next();
				
				//System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
				
				System.out.println(c.getToday().get(Calendar.MONTH) + "/" + c.getToday().get(Calendar.DAY_OF_MONTH));
				//repaint day
			
				
			}
		});
		
		
		
		
		
		
		
		
		//DayView dayview = new DayView(c);
		//frame.add(dayview, BorderLayout.EAST);
		//c.addChangeListener(dayview);
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class MonthTable extends JTable
{
	private MyCalendar c;
	
	public MonthTable(Object[][] data, String[] columnNames, MyCalendar c) {
		super(data, columnNames);
		this.c = c;
		
		addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 1) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      Object data = target.getModel().getValueAt(row, column);
			      // do some action if appropriate column
			      JFrame frame = new JFrame();
			      frame.setSize(25,25);
			      JLabel label = new JLabel(String.valueOf(data));
			      frame.add(label);
			      frame.setVisible(true);
			      
			      c.getToday().set(Calendar.DAY_OF_MONTH, (int) data);
			      System.out.println(c.getToday().get(Calendar.MONTH) + "/" + c.getToday().get(Calendar.DAY_OF_MONTH));
			    }
			  }
			});
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
	
	
}
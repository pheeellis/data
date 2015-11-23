import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;


public class MonthTable extends JTable
{
    private MyCalendar c;

    public MonthTable(Object[][] data, String[] columnNames, MyCalendar cal)
    {
        super(data, columnNames);
        this.c = cal;

        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if(e.getClickCount() == 1)
                {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    Object data = target.getModel().getValueAt(row, column);

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

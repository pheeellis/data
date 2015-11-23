import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Calendar;


public class CalTable extends JPanel implements ChangeListener
{
    private String[]   columnNames = {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
    private Object[][] stuff       = new Object[7][7];
    private MonthTable mo;
    private MyCalendar c;
    static  int        today;

    private int selectedRow    = 0;
    private int selectedColumn = 0;

    public CalTable(MyCalendar cal)
    {
        this.c = cal;
        mo = new MonthTable(stuff, columnNames, c);
        mo.setCellSelectionEnabled(true);

        today = c.getToday().get(Calendar.DAY_OF_MONTH);
        System.out.println("caltable: " + today);
        c.getToday().set(Calendar.DAY_OF_MONTH, c.getToday().getActualMinimum(Calendar.DAY_OF_MONTH));
        int space = c.getToday().get(Calendar.DAY_OF_WEEK) - 1;
        c.getToday().set(Calendar.DAY_OF_MONTH, today);
        int max = c.getToday().getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(space);
        int m = 0;
        int i = 1;



        for(int k = 0; k < stuff[0].length; k++)
        {
            stuff[0][k] = columnNames[m];
            m++;
        }
        for(int p = 0; p < stuff[0].length; p++)
        {
            if(p < space && space != 6)
            {
                stuff[1][p] = "";
            }

            else
            {
                stuff[1][p] = i;
                i++;
            }
            if(p == today)
            {
//				mo.setRowSelectionInterval(1, 1);
//				mo.setColumnSelectionInterval(p,p);
                selectedRow = 1;
                selectedColumn = p;
            }

        }


        for(int j = 2; j < stuff.length; j++)
        {

            for(int k = 0; k < stuff[0].length; k++)
            {


                if(i <= max)
                {
                    stuff[j][k] = i;
                    if(i == today)
                    {
//						mo.setRowSelectionInterval(j, j);
//						mo.setColumnSelectionInterval(k,k);
                        selectedRow = j;
                        selectedColumn = k;
                    }
                    i++;
                }

            }

        }
        changeSelection(selectedRow, selectedColumn);
        add(mo);

    }

    public MonthTable getMo()
    {
        return mo;
    }

    public void changeCal(MyCalendar cal)
    {
        c = cal;
        today = c.getToday().get(Calendar.DAY_OF_MONTH);
        mo = new MonthTable(stuff, columnNames, c);
        mo.revalidate();
        revalidate();
        System.out.println("changeCal: " + today);

    }


    public void changeSelection(int row, int col)
    {
        selectedRow = row;
        selectedColumn = col;
        mo.setRowSelectionInterval(row, row);
        mo.setColumnSelectionInterval(col, col);
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        System.out.println("today:" + today);
        System.out.println("changed: " + c.getToday().get(Calendar.DAY_OF_MONTH));
        today = c.getToday().get(Calendar.DAY_OF_MONTH);
        System.out.println("todayChanged:" + today);
        if(today - c.getToday().get(Calendar.DAY_OF_MONTH) >= 0)
        {
            changeSelection(selectedRow, selectedColumn + 1);
        }
        else if(today - c.getToday().get(Calendar.DAY_OF_MONTH) < 0)
        {
            changeSelection(selectedRow + 1, selectedColumn);
        }

        System.out.println("changed: " + c.getToday().get(Calendar.DAY_OF_MONTH));


        mo.revalidate();
        revalidate();
    }
}

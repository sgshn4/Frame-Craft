package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class DTM extends DefaultTableModel {

    public DTM (Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    };

    @Override
    public Object getValueAt(int row, int column) {
        Vector rowVector = (Vector)dataVector.elementAt(row);
        Dayd d = (Dayd)rowVector.elementAt(column);
        return d.getIntColor();
    }

    public Object getCellObject(int row, int column) {
        Vector rowVector = (Vector)dataVector.elementAt(row);
        Dayd d = (Dayd)rowVector.elementAt(column);
        return d;
    }

}

package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

class Renderer1 extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
        Dayd dayd = new Dayd((int) value);
        //c.setBackground(dayd.getColor());
        c.setIcon(dayd.getImage());
        c.setText("");
        return c;
    }

}

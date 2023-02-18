package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.*;

public class UpdateScreen {
    public void update(JTable screen) {
        for (int i = 0; i < 9; i++) {
            screen.getColumnModel().getColumn(i).setCellRenderer(new Renderer1());
        }
    }
    public void slotUpdate(JTable screen) {
        for (int i = 0; i < screen.getColumnCount(); i++) {
            screen.getColumnModel().getColumn(i).setCellRenderer(new Renderer1());
            screen.getColumnModel().getColumn(i).setMaxWidth(32);
        }
    }
}

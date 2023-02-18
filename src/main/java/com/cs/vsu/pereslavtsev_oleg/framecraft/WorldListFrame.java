package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class WorldListFrame extends JFrame {
    String name;
    String path;
    int gamemode;
    int biome;

    SettingsUtils settingsUtils = new SettingsUtils();
    WorldUtils worldUtils = new WorldUtils();

    Object[] header = {"Image", "Name", "Biome"};
    DefaultTableModel defaultTableModel = new DefaultTableModel();
    List<WorldItem> worldItemList = worldUtils.getWorldList();
    Object[][] worlds = worldUtils.worldListToOject(worldItemList);

    JTable list = new JTable(defaultTableModel);
    JPanel buttons = new JPanel();
    JButton play = new JButton("Play");
    JButton createNewWorld = new JButton("New World");

    public WorldListFrame() {
        final JFrame frame = new JFrame("FrameCraft");
        frame.setBounds(settingsUtils.x, settingsUtils.y, settingsUtils.widht, settingsUtils.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        if (settingsUtils.fullscreen == 1) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        play.setEnabled(false);

        defaultTableModel.setColumnIdentifiers(header);

        for (int i = 0; i < worlds.length; i++) {
            defaultTableModel.insertRow(defaultTableModel.getRowCount(), worlds[i]);
        }

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable obj = (JTable) e.getSource();
                int row = obj.rowAtPoint(e.getPoint());
                name = worldItemList.get(row).name;
                path = worldItemList.get(row).path;
                biome = worldItemList.get(row).biome;
                gamemode = worldItemList.get(row).gamemode;
                play.setEnabled(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame(name, path, gamemode, biome);
                frame.dispose();
            }
        });

        createNewWorld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateWorldFrame createWorldFrame = new CreateWorldFrame();
                frame.dispose();
            }
        });

        buttons.add(createNewWorld);
        buttons.add(play);
        frame.add(BorderLayout.SOUTH, buttons);
        frame.add(BorderLayout.NORTH, list);
        frame.setVisible(true);
    }

}

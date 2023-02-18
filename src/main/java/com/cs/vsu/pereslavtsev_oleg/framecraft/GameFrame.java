package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GameFrame extends JFrame {
    //GameUtils
    SettingsUtils settingsUtils = new SettingsUtils();
    WorldUtils worldUtils = new WorldUtils();

    //GameMap
    Object[] columnNames = {"-4", "-3", "-2", "-1", "0", "1", "2", "3", "4"};
    Object[] rowNames = {"-4", "-3", "-2", "-1", "0", "1", "2", "3", "4"};
    Object[][] vek;
    DTM model;
    List<Block> world;

    //SlotsMap
    Object[] columnSlotNames = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    Object[][] itemsSlot = new Object[][]{{new Dayd(1000), new Dayd(1001), new Dayd(1002),
            new Dayd(1003), new Dayd(1004), new Dayd(1006), new Dayd(1007),
            new Dayd(1008), new Dayd(1009)}};
    int[] itemsValues = {0, 1, 2, 3, 4, 6, 7, 8, 9};
    DTM slots = new DTM(itemsSlot, columnSlotNames);

    //GameVariables
    int x = 0;
    int y = 0;
    Dayd hand = new Dayd(1);

    public GameFrame(String name, String path, int gamemode, int biome) {
        this.world = worldUtils.readWorld(path);
        this.vek = worldUtils.setTable(world);
        model = new DTM(vek, columnNames);
        final JFrame frame = new JFrame("FrameCraft");
        frame.setBounds(settingsUtils.x, settingsUtils.y, settingsUtils.widht, settingsUtils.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        if (settingsUtils.fullscreen == 1) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        JPanel inventoryPanel = new JPanel();
        JTable screen = new JTable(model);
        JTable slotsTable = new JTable(slots);
        inventoryPanel.add(slotsTable);
        JLabel coordinates = new JLabel("X: 0 Y: 0");

        UpdateScreen updateScreen = new UpdateScreen();
        updateScreen.update(screen);
        updateScreen.slotUpdate(slotsTable);
        screen.setRowHeight(70);
        screen.setRowSelectionAllowed(false);
        Dayd source = (Dayd) model.getCellObject(4, 4);
        source.setD(100);

        slotsTable.setRowHeight(32);
        slotsTable.setRowSelectionAllowed(false);

        screen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                JTable obj = (JTable) e.getSource();
                int row = obj.rowAtPoint(e.getPoint());
                int col = obj.columnAtPoint(e.getPoint());
                if (row == 4 && col == 4) {

                } else {
                    if (hand.getIntColor() == 10 || hand.getIntColor() == 11) {
                        if (worldUtils.isOnCoordinateMap(world, x + Integer.parseInt(String.valueOf(columnNames[col])),
                                y + Integer.parseInt(String.valueOf(rowNames[row])))) {
                            world = worldUtils.changeBlock(world, x + Integer.parseInt(String.valueOf(columnNames[col])),
                                    y + Integer.parseInt(String.valueOf(rowNames[row])), biome);
                        } else {
                            world = worldUtils.createBlock(world, x + Integer.parseInt(String.valueOf(columnNames[col])),
                                    y + Integer.parseInt(String.valueOf(rowNames[row])), biome);
                        }
                        Dayd d = (Dayd) model.getCellObject(row, col);
                        itemsSlot[0] = worldUtils.addItemSlot(itemsSlot[0], d.getIntColor() + 1000);
                        itemsValues = worldUtils.addItemvalue(itemsValues, d.getIntColor());
                        columnSlotNames = worldUtils.addColumnName(columnSlotNames);
                        slots.addColumn(itemsSlot[itemsSlot.length - 1]);
                        slots.removeRow(0);
                        slots.insertRow(0, itemsSlot[0]);
                        slotsTable.repaint();
                        d.setD(biome);
                        obj.repaint();
                    } else if (hand.getIntColor() == 12) {

                    } else {
                        if (worldUtils.isOnCoordinateMap(world, x + Integer.parseInt(String.valueOf(columnNames[col])),
                                y + Integer.parseInt(String.valueOf(rowNames[row])))) {
                            world = worldUtils.changeBlock(world, x + Integer.parseInt(String.valueOf(columnNames[col])),
                                    y + Integer.parseInt(String.valueOf(rowNames[row])), hand.getIntColor());
                        } else {
                            world = worldUtils.createBlock(world, x + Integer.parseInt(String.valueOf(columnNames[col])),
                                    y + Integer.parseInt(String.valueOf(rowNames[row])), hand.getIntColor());
                        }
                        Dayd d = (Dayd) model.getCellObject(row, col);
                        d.setD(hand.getIntColor());
                        obj.repaint();
                    }
                }
            }
        });

        screen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                        y = y - 1;
                        updateScreen((JTable) e.getSource());
                        break;
                    case 's':
                        y = y + 1;
                        updateScreen((JTable) e.getSource());
                        break;
                    case 'a':
                        x = x - 1;
                        updateScreen((JTable) e.getSource());
                        break;
                    case 'd':
                        x = x + 1;
                        updateScreen((JTable) e.getSource());
                        break;
                    case 'l':
                        int response = JOptionPane.showConfirmDialog(null,
                                "Are you sure to exit to menu?",
                                "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            System.out.println(worldUtils.saveWorld(path,
                                    name, world));
                            MainFrame mainFrame = new MainFrame();
                            frame.dispose();
                        }
                    case '1':
                        changeHand(0);
                        break;
                    case '2':
                        changeHand(1);
                        break;
                    case '3':
                        changeHand(2);
                        break;
                    case '4':
                        changeHand(3);
                        break;
                    case '5':
                        changeHand(4);
                        break;
                    case '6':
                        changeHand(5);
                        break;
                    case '7':
                        changeHand(6);
                        break;
                    case '8':
                        changeHand(7);
                        break;
                    case '9':
                        changeHand(8);
                        break;
                }
            }

            private void changeHand(int slot) {
                hand = new Dayd(itemsValues[slot]);
            }

            private void updateScreen(JTable obj) {
                coordinates.setText("X: " + x + " Y: " + y);
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        Dayd source = (Dayd) model.getCellObject(i, j);
                        if (i == 4 && j == 4) {
                            source.setD(100);
                        } else {
                            if (!worldUtils.isOnCoordinateMap(world,
                                    x + Integer.parseInt(String.valueOf(columnNames[j])),
                                    y + Integer.parseInt(String.valueOf(rowNames[i])))) {
                                worldUtils.generateY(world, x + Integer.parseInt(String.valueOf(columnNames[j])),
                                        y + Integer.parseInt(String.valueOf(rowNames[i])), biome);
                            }
                            if (!worldUtils.isOnCoordinateMap(world,
                                    x + Integer.parseInt(String.valueOf(columnNames[j])),
                                    y + Integer.parseInt(String.valueOf(rowNames[i])))) {
                                worldUtils.generateX(world, x + Integer.parseInt(String.valueOf(columnNames[j])),
                                        y + Integer.parseInt(String.valueOf(rowNames[i])), biome);
                            }
                            source.setD(worldUtils.seachCoordinates(world,
                                    x + Integer.parseInt(String.valueOf(columnNames[j])),
                                    y + Integer.parseInt(String.valueOf(rowNames[i]))));
                        }
                        obj.repaint();
                    }
                }
            }

            private void updateslot(JTable table) {
                for (int i = 0; i < 7; i++) {
                    Dayd source = (Dayd) slots.getCellObject(0, i);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        JPanel panel1 = new JPanel();
        panel1.add(BorderLayout.NORTH, screen);
        frame.add(BorderLayout.SOUTH, inventoryPanel);
        frame.add(BorderLayout.PAGE_START, coordinates);
        frame.add(BorderLayout.CENTER, panel1);
        frame.setVisible(true);
    }


}





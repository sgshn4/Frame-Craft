package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateWorldFrame {
    SettingsUtils settingsUtils = new SettingsUtils();
    WorldUtils worldUtils = new WorldUtils();

    String[] biomeNames = {"Grass", "Snow", "Sand"};

    private JTextField worldname = new JTextField();
    private JComboBox biomesSelector = new JComboBox(biomeNames);
    private JButton gamemode = new JButton("Creative");
    private JButton create = new JButton("Create");

    private int gameMode = 0;

    public CreateWorldFrame() {
        final JFrame frame = new JFrame("FrameCraft");
        frame.setBounds(settingsUtils.x, settingsUtils.y, settingsUtils.widht, settingsUtils.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        if (settingsUtils.fullscreen == 1) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        worldname.setText("World" + worldUtils.getWorldList().size());

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worldUtils.createWorld(String.valueOf(worldname.getText()), biomesSelector.getSelectedIndex(), gameMode);
                GameFrame gameFrame = new GameFrame(worldname.getText(), worldUtils.folder
                        + worldname.getText() + ".txt", gameMode, biomesSelector.getSelectedIndex());
            }
        });

        frame.add(new JLabel("World name"));
        frame.add(worldname);
        frame.add(new JLabel("Biome:"));
        frame.add(biomesSelector);
        frame.add(new JLabel("Game mode"));
        frame.add(gamemode);
        frame.add(create);
        frame.setLayout(new GridLayout(7, 1));
        frame.setVisible(true);
    }
}

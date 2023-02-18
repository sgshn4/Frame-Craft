package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {
    SettingsUtils settingsUtils = new SettingsUtils();

    private String[] resolutionNames = {"640x480", "800x600", "1024x768", "1152x864", "1280x720", "1280x768",
            "1366x768", "1400x1050", "1600x1200", "1680x1050", "1920x1080"};
    private Integer[] resolutionValues = {640, 480, 800, 600, 1024, 768, 1152, 864, 1280, 720, 1280, 768, 1366, 768,
            1400, 1050, 1600, 1200, 1680, 1050, 1920, 1080};

    private JComboBox resolutionSelector = new JComboBox(resolutionNames);
    private JCheckBox checkBoxFullscreen = new JCheckBox();
    private JSlider slider = new JSlider(0, 100);
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel5 = new JPanel();
    private JButton back = new JButton("Back");

    private int index = settingsUtils.index;
    private int fullscreen = settingsUtils.fullscreen;
    private int skin = settingsUtils.skin;
    private int soundVolume = settingsUtils.soundVolume;

    public SettingsFrame() {
        final JFrame frame = new JFrame("FrameCraft");
        frame.setBounds(settingsUtils.x, settingsUtils.y, settingsUtils.widht, settingsUtils.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        if (settingsUtils.fullscreen == 1) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }
        frame.setResizable(true);

        slider.setValue(soundVolume);
        resolutionSelector.setEditable(false);
        if (fullscreen == 1) {
            checkBoxFullscreen.setSelected(true);
        } else {
            checkBoxFullscreen.setSelected(false);
        }

        resolutionSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                index = comboBox.getSelectedIndex();
                settingsUtils.saveFile(resolutionValues[index * 2], resolutionValues[index * 2 + 1], fullscreen,
                        soundVolume, skin);
            }
        });

        checkBoxFullscreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                if (checkBox.isSelected()) {
                    fullscreen = 1;
                } else {
                    fullscreen = 0;
                }
                settingsUtils.saveFile(resolutionValues[index * 2], resolutionValues[index * 2 + 1], fullscreen,
                        soundVolume, skin);
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider slider1 = (JSlider) e.getSource();
                soundVolume = slider1.getValue();
                settingsUtils.saveFile(resolutionValues[index * 2], resolutionValues[index * 2 + 1], fullscreen,
                        soundVolume, skin);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                frame.dispose();
            }
        });

        jPanel1.add(back);
        jPanel2.add(new JLabel("Resolution"));
        jPanel2.add(resolutionSelector);
        jPanel3.add(new JLabel("Full screen"));
        jPanel3.add(checkBoxFullscreen);
        frame.add(jPanel1);
        frame.add(jPanel2);
        frame.add(jPanel3);

        frame.setLayout(new GridLayout(3, 1));
        frame.setVisible(true);
    }
}
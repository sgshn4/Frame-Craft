package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    SettingsUtils settingsUtils = new SettingsUtils();

    Container container;

    private JButton play = new JButton("Play");
    private JButton settings = new JButton("Settings");
    private JButton about = new JButton("About");
    private JButton exit = new JButton("Exit");
    private JButton example = new JButton("How to play");


    public MainFrame() {
        final JFrame frame = new JFrame("FrameCraft");
        frame.setBounds(settingsUtils.x, settingsUtils.y, settingsUtils.widht, settingsUtils.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
        if (settingsUtils.fullscreen == 1) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        container = this.getContentPane();

        play.setAlignmentX(CENTER_ALIGNMENT);
        settings.setAlignmentX(CENTER_ALIGNMENT);
        example.setAlignmentX(CENTER_ALIGNMENT);
        about.setAlignmentX(CENTER_ALIGNMENT);
        exit.setAlignmentX(CENTER_ALIGNMENT);

        frame.add(play, BorderLayout.CENTER);
        frame.add(settings, BorderLayout.CENTER);
        frame.add(example, BorderLayout.CENTER);
        frame.add(about, BorderLayout.CENTER);
        frame.add(exit, BorderLayout.CENTER);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                WorldListFrame gameFrame = new WorldListFrame();
            }
        });

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SettingsFrame settingsFrame = new SettingsFrame();
            }
        });

        example.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "Dobro pozhalovat' v FrameCraft!\n" +
                        "Igra yavlyaetsa svoeobraznim klonom vsemirnoizvestnoi igri Minecraft.\n" +
                        "Na dannii moment v igre dostupen tol'ko creative-mode.\n" +
                        "Dlya perekluchenia mezhdu blokami ispol'zuite 1-9.\n" +
                        "Dlya peremeshenia vkluchite English raskladku i vikluchite CapsLock,\n" +
                        "ispol'zuite klavishi wasd.\n" +
                        "Dlya razmesheniya bloka na karte, viberite nuzhniy i nazhmite LKM na yacheike.\n" +
                        "Sohranenie osush'estvlyaetsa cherez knopku L\n" +
                        "Good Luck <3";
                JOptionPane.showMessageDialog(null, message);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure to exit?",
                        "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "FrameCraft\nv1.0\nMade in Cumputer Science Faculty\nBy Oleg Pereslavtsev";
                JOptionPane.showMessageDialog(null, message);
            }
        });

    }

}

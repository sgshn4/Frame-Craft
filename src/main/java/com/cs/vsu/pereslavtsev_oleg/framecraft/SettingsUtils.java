package com.cs.vsu.pereslavtsev_oleg.framecraft;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class SettingsUtils {

    String path = "settings.txt";
    int widht;
    int height;
    int x;
    int y;
    int fullscreen;
    int skin;
    int index;
    int soundVolume;

    public SettingsUtils() {
        List<Integer> result = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(path).getAbsoluteFile()));
            String line = reader.readLine();
            while (line != null) {
                List<Integer> list = new ArrayList<>();
                String[] nums = line.split(" ");
                for (String n : nums) {
                    list.add(Integer.parseInt(n));
                }
                this.widht = list.get(0);
                this.height = list.get(1);
                this.x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - widht / 2;
                this.y = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2;
                this.fullscreen = list.get(2);
                this.skin = list.get(3);
                this.soundVolume = list.get(4);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void saveFile(int widht, int height, int fullscreen, int soundVolume, int skin) {
        String result = "";
        try {
            result = widht + " " + height + " " + fullscreen + " " + soundVolume + " " + skin;
            FileWriter fileWriter = new FileWriter(new File(path).getAbsoluteFile(), false);
            fileWriter.write(result);
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println("World doesn't saved. Exception: " + e);
        }
    }
}

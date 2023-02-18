package com.cs.vsu.pereslavtsev_oleg.framecraft;

import javax.swing.*;
import java.awt.*;

class Dayd {
    WorldUtils worldUtils = new WorldUtils();
    private int colorint;
    private ImageIcon imageIcon;
    private boolean isSelected;

    public Dayd (int intCol) {
        setD(intCol);
    }

    public void setD (int d) {
        this.colorint = d;
        switch (d) {
            case 0:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/grass.jpg");
                break;
            case 1:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/snow.jpg");
                break;
            case 2:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/sand.jpg");
                break;
            case 3:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/plank.jpg");
                break;
            case 4:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/cubblestone.jpg");
                break;
            case 5:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/empty.png");
                break;
            case 6:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/wood.jpg");
                break;
            case 7:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/leaves.jpg");
                break;
            case 8:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/stone.jpg");
                break;
            case 9:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/ore.jpg");
                break;
            case 100:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/player.jpg");
                break;
            case 1000:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/grass_m.jpg");
                break;
            case 1001:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/snow_m.jpg");
                break;
            case 1002:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/sand_m.jpg");
                break;
            case 1003:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/plank_m.jpg");
                break;
            case 1004:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/cubblestone_m.jpg");
                break;
            case 1005:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/empty_m.png");
                break;
            case 1006:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/wood_m.jpg");
                break;
            case 1007:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/leaves_m.jpg");
                break;
            case 1008:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/stone_m.jpg");
                break;
            case 1009:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/ore_m.jpg");
                break;
            case 1010:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/ironpick_m.jpg");
                break;
            case 1011:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/ironaxe_m.jpg");
                break;
            case 1012:
                this.imageIcon = new ImageIcon(worldUtils.folder + "drawable/ironsword_m.jpg");
                break;
        }

    }

    public void incrementCount() {
        this.colorint = 1;
    }

    public ImageIcon getImage() {
        return imageIcon;
    }


    public Color getColor () {
        Color color = Color.WHITE;
        if (colorint == 0) {
            color = Color.LIGHT_GRAY;
        }
        else if (colorint == 1) {
            color = Color.RED;
        }
        else if (colorint == 100) {
            color = Color.MAGENTA;
        }
        else if (colorint == 3) {
            color = Color.YELLOW;
        }

        return color;
    }
    public int getIntColor () {
        return colorint;
    }

}
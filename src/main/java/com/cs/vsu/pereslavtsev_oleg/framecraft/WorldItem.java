package com.cs.vsu.pereslavtsev_oleg.framecraft;

public class WorldItem {
    String name;
    String path;
    int biome;
    int gamemode;
    public WorldItem(String name, String path, int biome, int gamemode) {
        this.name = name;
        this.path = path;
        this.biome = biome;
        this.gamemode = gamemode;
    }
}

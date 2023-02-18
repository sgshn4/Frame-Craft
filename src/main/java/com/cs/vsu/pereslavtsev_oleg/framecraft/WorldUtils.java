package com.cs.vsu.pereslavtsev_oleg.framecraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldUtils {
    File f = new File("worldList.txt");
    String path = f.getAbsolutePath();
    File f2 = new File("");
    String folder = f2.getAbsolutePath() + "/";
    File s = new File("settings.txt");
    String settings = s.getAbsolutePath();
    int treeRandom = new Random().nextInt(30);
    int stoneRandom = new Random().nextInt(60);

    public void checkFiles() {
        System.out.println(settings);
        File world = new File(path);
        if (!world.canRead()) {
            createWorldList();
        }
        File set = new File(settings);
        if (!set.canRead()) {
            createSettingsFile();
        }
    }

    public void createWorldList() {
        try {
            File file = new File(path);
            file.createNewFile();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createSettingsFile(){
        try {
            String values = "640 480 0 77 62";
            File file = new File(settings);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
            fileWriter.write(values);
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addWorldtoList(WorldItem worldItem) {
        List<WorldItem> worlds = getWorldList();
        worlds.add(worldItem);
        String result = "";
        try {
            for (int i = 0; i < worlds.size(); i++) {
                result += worlds.get(i).name + " " + worlds.get(i).path + " " + worlds.get(i).biome + " "
                        + worlds.get(i).gamemode + "\n";
            }
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(result);
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Object[][] worldListToOject(List<WorldItem> worldItems) {
        Object[][] result = new Object[worldItems.size()][3];
        try {
            for (int i = 0; i < worldItems.size(); i++) {
                result[i][0] = worldItems.get(i).biome;
                result[i][1] = worldItems.get(i).name;
                switch (worldItems.get(i).biome) {
                    case 0:
                        result[i][2] = "Biome: grass";
                        break;
                    case 1:
                        result[i][2] = "Biome: snow";
                        break;
                    case 2:
                        result[i][2] = "Biome: sand";
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }


    public List<WorldItem> getWorldList() {
        List<WorldItem> result = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                List<String> list = new ArrayList<>();
                String[] nums = line.split(" ");
                for (String n : nums) {
                    list.add(n);
                }
                result.add(new WorldItem(list.get(0), list.get(1), Integer.parseInt(list.get(2)),
                        Integer.parseInt(list.get(3))));
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public void createWorld(String name, int biome, int gamemode) {
        String world = "";
        int x = -4;
        int y = -4;
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    world += x + " " + y + " " + biome + "\n";
                    x = x + 1;
                }
                x = -4;
                y = y + 1;
            }
            File file = new File(folder + name + ".txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(folder + name + ".txt", false);
            fileWriter.write(world);
            fileWriter.flush();
            addWorldtoList(new WorldItem(name, folder + name + ".txt", biome, gamemode));
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public Object[][] setTable(List<Block> world) {
        Object[][] readarray = new Object[9][9];
        int x = -4;
        int y = -4;
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    readarray[i][j] = new Dayd(seachCoordinates(world, x, y));
                    x = x + 1;
                }
                x = -4;
                y = y + 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return readarray;
    }

    public int seachCoordinates(List<Block> world, int x, int y) {
        int result = 0;
        for (int i = 0; i < world.size(); i++) {
            if (world.get(i).x == x && world.get(i).y == y) {
                result = world.get(i).id;
                break;
            }
        }
        return result;
    }

    public boolean isOnCoordinateMap(List<Block> world, int x, int y) {
        boolean result = false;
        for (int i = 0; i < world.size(); i++) {
            if (world.get(i).x == x && world.get(i).y == y) {
                result = true;
                break;
            }
        }
        return result;
    }

    public List<Block> readWorld(String path) {
        List<Block> result = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                List<Integer> list = new ArrayList<>();
                String[] nums = line.split(" ");
                for (String n : nums) {
                    list.add(Integer.parseInt(n));
                }
                result.add(new Block(list.get(0), list.get(1), list.get(2)));
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public String saveWorld(String path, String name, List<Block> world) {
        String result = "";
        String message = "";
        try {
            for (int i = 0; i < world.size(); i++) {
                result += world.get(i).x + " " + world.get(i).y + " " + world.get(i).id + "\n";
            }
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(result);
            fileWriter.flush();
            message = "World " + name + " successfully saved at " + path;
        } catch (Exception e) {
            System.out.println("World doesn't saved. Exception: " + e);
        }
        return message;
    }

    public List<Block> changeBlock(List<Block> world, int x, int y, int id) {
        List<Block> result = world;
        for (int i = 0; i < world.size(); i++) {
            if (result.get(i).x == x && result.get(i).y == y) {
                result.set(i, new Block(x, y, id));
                break;
            }
        }
        return result;
    }

    public List<Block> createBlock(List<Block> world, int x, int y, int id) {
        List<Block> result = world;
        result.add(new Block(x, y, id));
        return result;
    }

    public List<Block> generateY(List<Block> world, int x, int y, int biome) {
        List<Block> result = world;
        treeRandom--;
        stoneRandom--;
        for (int i = x - 4; i < x + 4; i++) {
            if (!isOnCoordinateMap(world, x, y)) {
                result.add(new Block(i, y, biome));
            }
            if (treeRandom == 0) {
                generateTree(world, x, y);
                treeRandom = new Random().nextInt(30);
            }
            if (stoneRandom == 0) {
                generateStone(world, x, y);
                stoneRandom = new Random().nextInt(60);
            }
        }
        return result;
    }

    public List<Block> generateX(List<Block> world, int x, int y, int biome) {
        List<Block> result = world;
        treeRandom--;
        stoneRandom--;
        for (int i = y - 4; i < y + 4; i++) {
            if (!isOnCoordinateMap(world, x, y)) {
                result.add(new Block(x, i, biome));
            }
            if (treeRandom == 0) {
                generateTree(world, x, y);
                treeRandom = new Random().nextInt(30);
            }
            if (stoneRandom == 0) {
                generateStone(world, x, y);
                stoneRandom = new Random().nextInt(60);
            }
        }
        return result;
    }

    private List<Block> generateTree(List<Block> world, int x, int y) {
        List<Block> result = world;
        Tree tree = new Tree(x, y);
        for (int i = 0; i < tree.tree.size(); i++) {
            result = createBlock(result, tree.tree.get(i).x, tree.tree.get(i).y, tree.tree.get(i).id);
        }
        return result;
    }

    private List<Block> generateStone(List<Block> world, int x, int y) {
        List<Block> result = world;
        Stone stone = new Stone(x, y);
        for (int i = 0; i < stone.stone.size(); i++) {
            result = createBlock(result, stone.stone.get(i).x, stone.stone.get(i).y, stone.stone.get(i).id);
        }
        return result;
    }

    public Object[] addItemSlot(Object[] slots, int item) {
        Object[] result = new Object[slots.length + 1];
        Dayd d = new Dayd(item);
        for (int i = 0; i < slots.length; i++) {
            result[i] = slots[i];
        }
        result[slots.length] = d;
        return result;
    }

    public int[] addItemvalue(int[] values, int item) {
        int[] result = new int[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i];
        }
        result[values.length] = item;
        return result;
    }

    public Object[] addColumnName(Object[] values) {
        Object[] result = new Object[values.length + 1];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i];
        }
        result[values.length] = (Object) String.valueOf(result.length);
        return result;
    }

}

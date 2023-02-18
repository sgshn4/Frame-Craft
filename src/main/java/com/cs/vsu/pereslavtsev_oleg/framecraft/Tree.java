package com.cs.vsu.pereslavtsev_oleg.framecraft;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    List<Block> tree = new ArrayList<>();
    public Tree(int x, int y) {
        int k = 2;
        tree.add(new Block(x, y, 6));
        tree.add(new Block(x, y - 1, 6));
        for (int j = 0; j < 2; j++) {
            for (int i = -2; i <= 2; i++) {
                tree.add(new Block(x + i, y - k, 7));
            }
            k = k + 1;
        }
        for (int i = -1; i <= 1; i++) {
            tree.add(new Block(x + i, y - k, 7));
        }
    }
}

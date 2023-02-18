package com.cs.vsu.pereslavtsev_oleg.framecraft;

import java.util.ArrayList;
import java.util.List;

public class Stone {
    List<Block> stone = new ArrayList<>();
    public Stone(int x, int y) {
        stone.add(new Block(x - 2, y, 8));
        stone.add(new Block(x - 1, y, 9));
        for (int i = 0; i < 2; i++) {
            stone.add(new Block(x + i, y, 8));
        }
        stone.add(new Block(x - 2, y - 1, 8));
        stone.add(new Block(x - 1, y - 1, 8));
        stone.add(new Block(x, y - 1, 8));
        stone.add(new Block(x + 1, y - 1, 9));
        stone.add(new Block(x - 1, y - 2, 9));
        stone.add(new Block(x, y - 2, 8));
        stone.add(new Block(x + 1, y - 2, 8));
    }
}

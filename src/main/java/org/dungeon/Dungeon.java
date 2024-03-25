package org.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private final Block[][] area;

    public Dungeon(Block[][] area) {
        this.area = area;
    }

    public static enum Block {
        GROUND("🧱"),
        AIR("👾");
        private final String symbol;

        Block(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public List<Integer> getEntrances() {
        List<Integer> entrances = new ArrayList<>();
        for (int y = 0; y < area[0].length; y++) {
            if (area[0][y] == Block.AIR) {
                entrances.add(y);
            }
        }
        return entrances;
    }

    public List<Integer> getExits() {
        List<Integer> exits = new ArrayList<>();
        for (int y = 0; y < area[0].length; y++) {
            if (area[area.length - 1][y] == Block.AIR) {
                exits.add(y);
            }
        }
        return exits;
    }

    public boolean isOpen() {
        return !getEntrances().isEmpty() && !getExits().isEmpty();
    }

    public boolean isConnectable(Dungeon otherDungeon) {
        if (otherDungeon == null) {
            return false;
        }

        List<Integer> exits = this.getExits();
        List<Integer> otherEntrances = otherDungeon.getEntrances();

        for (int exit : exits) {
            if (otherEntrances.contains(exit)) {
                return true;
            }
        }

        return false;
    }

    public void printDungeon() {
        System.out.println(super.toString());
        for (Block[] row : area) {
            for (Block block : row) {
                System.out.print(block.getSymbol());
            }
            System.out.println();
        }
    }
}

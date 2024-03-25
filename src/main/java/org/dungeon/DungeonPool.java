package org.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonPool {
    private final List<Dungeon> dungeons;

    public DungeonPool(List<Dungeon> dungeons) {
        this.dungeons = validateDungeons(dungeons);
    }

    public List<Dungeon> createXSequence(int length) {
        List<Dungeon> sequence = new ArrayList<>();

        Dungeon previousDungeon = null;

        for (int i = 0; i < length; i++) {
            Dungeon nextDungeon = getNextDungeon(previousDungeon);
            sequence.add(nextDungeon);
            nextDungeon.printDungeon();
            previousDungeon = nextDungeon;
        }

        return sequence;
    }

    private Dungeon getNextDungeon(Dungeon previousDungeon) {
        List<Dungeon> comparableDungeons = new ArrayList<>();

        if (previousDungeon == null) {
            return dungeons.get(new Random().nextInt(dungeons.size()));
        }

        for (Dungeon dungeon: dungeons) {
            if (previousDungeon.isConnectable(dungeon)) {
                comparableDungeons.add(dungeon);
            }
        }

        if (comparableDungeons.isEmpty()) {
            throw new RuntimeException("Невозможно сгенерировать последовательность с заданными подземельями");
        }

        return comparableDungeons.get(new Random().nextInt(comparableDungeons.size()));
    }

    private List<Dungeon> validateDungeons(List<Dungeon> dungeons) {
        List<Dungeon> validatedDungeons = new ArrayList<>();

        for (Dungeon dungeon : dungeons) {
            if (dungeon.isOpen()) {
                validatedDungeons.add(dungeon);
            }
        }

        if (validatedDungeons.isEmpty()) {
            throw new RuntimeException("Нет открытых подземелий для создания последовательности");
        }

        return validatedDungeons;
    }
}
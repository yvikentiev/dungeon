package org.dungeon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DungeonPoolTest {
    @Test
    public void testCreateXSequence() {
        System.out.println("Создать пул из 10 подземелий размером 7 на 4");
        DungeonPool dungeonPool = new DungeonPool(DungeonGenerator
                .generateDungeons(14,7,100));

        System.out.println("Выводим последовательность подземелья в последовательности открыты и сопоставимы");
        List<Dungeon> sequence = dungeonPool.createXSequence(3);

        Assertions.assertEquals(3, sequence.size());

        System.out.println("👾👾👾Проверяем, что все подземелья в последовательности открыты и сопоставимы");
        for (int i = 0; i < sequence.size() - 1; i++) {
            Dungeon currentDungeon = sequence.get(i);
            Dungeon nextDungeon = sequence.get(i + 1);

            Assertions.assertTrue(currentDungeon.isOpen());
            Assertions.assertTrue(nextDungeon.isOpen());
            Assertions.assertTrue(currentDungeon.isConnectable(nextDungeon));
        }

        System.out.println("Проверяем, что подземелья в последовательности не повторяются");
        for (int i = 0; i < sequence.size() - 1; i++) {
            Dungeon currentDungeon = sequence.get(i);
            Dungeon nextDungeon = sequence.get(i + 1);

            Assertions.assertNotEquals(currentDungeon, nextDungeon);
        }
    }
}
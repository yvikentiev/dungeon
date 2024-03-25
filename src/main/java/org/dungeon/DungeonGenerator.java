package org.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonGenerator {

    public static List<Dungeon> generateDungeons(int width, int height, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be greater than 0");
        }

        List<Dungeon> dungeonList = new ArrayList<>();
        while (count-- > 0) {
            dungeonList.add(DungeonGenerator.generateDungeon(width, height));
        }

        return dungeonList;
    }

    public static Dungeon generateDungeon(int width, int height) {
        Dungeon.Block[][] block = new Dungeon.Block[height][width];
        Random random = new Random();
        // Заполняем лабиринт землей
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                block[i][j] = Dungeon.Block.GROUND;
            }
        }
        // TODO: Для тестирования сделан простой алгоритм генерации лабиринта.
        // Необходимо реализовать алгоритм генерации лабиринта с использованием рекурсии.
        // Например, можно добавить лабиринт с одним входом и два выхода.
        // Для генерации лабиринта можно использовать алгоритм генерации проходов.
        int j = random.nextInt(width); // Выбираем случайный вход
        for (int i = 0; i < height; i++) {
            int size = random.nextInt(3) + 1; // Генерируем случайный размер прохода от 1 до 4
            for (int k = 0; k < size; k++) {
                if (j + k < width) {
                    block[i][j + k] = Dungeon.Block.AIR;
                }
            }
            int step = random.nextInt(size); // Выбираем случайный сдвиг
            j += Math.max(step - size, 0);
        }
        Dungeon dungeon = new Dungeon(block);
        dungeon.printDungeon();
        return dungeon;
    }
}

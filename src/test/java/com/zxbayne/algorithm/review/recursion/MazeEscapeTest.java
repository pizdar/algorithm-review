package com.zxbayne.algorithm.review.recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MazeEscapeTest {

    @Test
    public void testEscape() {
        int[][] map = new int[8][7];
        // 给迷宫的上下置为1，作为墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = MazeEscape.WALL;
            map[7][i] = MazeEscape.WALL;
        }
        // 给迷宫的左右置为1，作为墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = MazeEscape.WALL;
            map[i][6] = MazeEscape.WALL;
        }
        // 给迷宫设置挡板
        map[3][1] = MazeEscape.WALL;
        map[3][2] = MazeEscape.WALL;
        map[3][3] = MazeEscape.WALL;

        map[5][4] = MazeEscape.WALL;
        map[5][5] = MazeEscape.WALL;

        // 给迷宫设置起点终点
        MazeEscape.Position start = new MazeEscape.Position(1, 1);
        MazeEscape.Position destination = new MazeEscape.Position(6, 5);
        MazeEscape mazeEscape = new MazeEscape(map.clone(), start, destination);

        System.out.println("走的出的迷宫:");
        print2DArray(map);

        // 走的出的迷宫测试
        int[][] result = mazeEscape.go();
        System.out.println("结果:");
        print2DArray(result);

        Assertions.assertEquals(MazeEscape.WALKED, map[start.x][start.y]);
        Assertions.assertEquals(MazeEscape.WALKED, map[destination.x][destination.y]);
    }

    public void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.printf("%d", array[i][j]);
            }
            System.out.println();
        }
    }
}

package com.zxbayne.algorithm.review.recursion;

/**
 * 递归之迷宫回溯，
 */
public class MazeEscape {
    // 迷宫二维数组
    private int[][] maze;
    // 在迷宫中表示可以走的路径，并且这个点没被走过
    public static final int UNKNOWN = 0;
    // 在迷宫中表示墙的数字（无法移动）
    public static final int WALL = 1;
    // 在迷宫中表示走过的点
    public static final int WALKED = 2;
    // 在迷宫中表示该店已经被探索过了，但是走不通
    public static final int OUTREACH = 3;

    private final Position start;
    private final Position destination;

    public MazeEscape(int[][] maze, Position start, Position destination) {
        this.maze = maze;
        this.start = start;
        this.destination = destination;
    }

    /**
     * 解决迷宫回溯
     * @return 最后的迷宫解决方案，数组中标为0的是可以走的空间，1为墙壁不能走，2为最后的结果，3为尝试的路径
     * @throws RuntimeException 没用结果时抛出异常
     */
    public int[][] go() {
        boolean canReach = explore(start.x, start.y);
        if (!canReach) {
            throw new RuntimeException("没有结果");
        }
        return maze;
    }

    /**
     * 通过递归的方式从某个位置开始找通路
     * 走迷宫的策略：下→右→上→左
     *
     * @param posRow 开始位置的行号(起始是0)
     * @param posCol 开始位置的列号(起始是0)
     * @return 找到通路则返回true，否则返回false
     */
    public boolean explore(int posRow, int posCol) {
        int current = maze[posRow][posCol];
        // 如果终点被标为PATH，则代表走过终点，返回true
        if (maze[destination.x][destination.y] == WALKED) {
            return true;
        }

        if (current == UNKNOWN) { // 当前路径还没走过，但是可以走
            // 走过当前的点
            maze[posRow][posCol] = WALKED;

            // 向上走
            if (explore(posRow - 1, posCol)) {
                return true;
            }
            // 向右走
            if (explore(posRow, posCol + 1)) {
                return true;
            }
            // 向下走
            if (explore(posRow + 1, posCol)) {
                return true;
            }
            // 向左走
            if (explore(posRow, posCol - 1)) {
                return true;
            }
            maze[posRow][posCol] = OUTREACH;
        }
        return false;
    }

    public static class Position {
        public int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

package com.zxbayne.algorithm.review.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 * @date 2021/02/01
 */
public class NQueen {
    private final int n;
    private List<int[]> result;
    private int[] board;


    public NQueen(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n can't less than zero!");
        }
        if (n > 1 && n < 4) {
            throw new IllegalArgumentException("no solution in current situation");
        }
        this.n = n;
        result = new ArrayList<>();
        board = new int[n];
    }

    public List<int[]> solveNQueens() {
        result = new ArrayList<>();
        board = new int[n];

        // 从棋盘第0行开始处理
        backtrace(0);
        return result;
    }

    /**
     *
     * @param row n表示回溯棋盘的第几行
     */
    private void backtrace(int row) {
        // 通过递归 row 会一直递增，row == n 时说明将棋盘所有的行都递归完毕
        if (row == n) {
            // print();
            result.add(board.clone());
            return;
        }
        // 对每一列进行回溯
        for (int i = 0; i < n; i++) {
            board[row] = i;
            if (isValid(row)) {
                backtrace(row + 1);
            }
        }
    }

    private boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            if (board[row] == board[i] || Math.abs(row - i) == Math.abs(board[row] - board[i])) {
                return false;
            }
        }
        return true;
    }
}
package com.zxbayne.algorithm.review.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 *
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class UndirectedGraph {
    private final int[][] edges;
    private int size;

    public UndirectedGraph(int limit) {
        this.edges = new int[limit][limit];
        this.size = limit;
    }

    public void addEdge(int from, int to, int weight) {
        edges[from][to] = weight;
        edges[to][from] = weight;
    }

    public int[][] getEdges() {
        return edges;
    }


    private void dfs(int i, boolean[] visited) {
        System.out.print(i + " ");
        visited[i] = true;

        for (int j = 0; j < size; j++) {
            if (edges[i][j] != 0 && !visited[j]) {
                dfs(j, visited);
            }
        }
    }

    public void dfs(int root) {
        dfs(root, new boolean[size]);
    }

    public void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[size];

        queue.offer(root);
        visited[root] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            System.out.print(node + " ");

            for (int i = 0; i < size; i++) {
                if (edges[node][i] != 0 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}

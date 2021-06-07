package com.zxbayne.algorithm.review.graph;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class GraphTest {
    private UndirectedGraph uGraph;

    private final PrintStream origin = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void init() {
        uGraph = new UndirectedGraph(8);

        uGraph.addEdge(0, 1, 1);
        uGraph.addEdge(0, 2, 1);
        uGraph.addEdge(1, 3, 1);
        uGraph.addEdge(1, 4, 1);
        uGraph.addEdge(3, 7, 1);
        uGraph.addEdge(4, 7, 1);
        uGraph.addEdge(2, 5, 1);
        uGraph.addEdge(2, 6, 1);
        uGraph.addEdge(5, 6, 1);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restore() {
        System.setOut(origin);
    }

    @Test
    public void testDFS() {
        uGraph.dfs(0);
        String result = outContent.toString();
        Assertions.assertEquals("0 1 3 7 4 2 5 6 ", result);
    }

    @Test
    public void testBFS() {
        uGraph.bfs(0);
        String result = outContent.toString();
        Assertions.assertEquals("0 1 2 3 4 5 6 7 ", result);
    }
}

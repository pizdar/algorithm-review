package com.zxbayne.algorithm.review.tree;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class TreeTest {
    private TreeNode root;

    // backup
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    public void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void contextLoad() {
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrefix() {
        TreeTravel.prefixOrderTravel(root);
        Assertions.assertEquals("1\r\n2\r\n4\r\n5\r\n3\r\n", outContent.toString());
    }

    @Test
    public void testInfix() {
        TreeTravel.infixOrderTravel(root);
        Assertions.assertEquals("4\r\n2\r\n5\r\n1\r\n3\r\n", outContent.toString());

    }

    @Test
    public void testPostfix() {
        TreeTravel.postfixOrderTravel(root);
        Assertions.assertEquals("4\r\n5\r\n2\r\n3\r\n1\r\n", outContent.toString());
    }

    @Test
    public void deleteNode() {
        TreeDelete.delNode(root, 5);
        TreeTravel.prefixOrderTravel(root);
        Assertions.assertEquals("1\r\n2\r\n4\r\n3\r\n", outContent.toString());
    }

    @Test
    public void testLevelOrder() {
        TreeTravel.levelOrderTravel(root);
        Assertions.assertEquals("1\r\n2\r\n3\r\n4\r\n5\r\n", outContent.toString());
    }
}

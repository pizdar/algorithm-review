package com.zxbayne.algorithm.review.recursion;

/**
 * 汉诺塔的求解
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 */
public class HannoSolution {
    public static void hanno(int nTop, char from, char temp, char to) {
        if (nTop == 1) {
            System.out.printf("Disk %d from %c to %c%n", nTop, from, to);
        } else {
            hanno(nTop - 1, from, to, temp);
            System.out.printf("Disk %d from %c to %c%n", nTop, from, to);
            hanno(nTop - 1, temp, from, to);
        }
    }
}

package com.freetymekiyan.algorithms.level.medium;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * <p>
 * For example, given the following matrix:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 * <p>
 * Company Tags: Apple, Airbnb, Facebook
 * Tags: Dynamic Programming
 * Similar Problems: (H) Maximal Rectangle
 */
public class MaximalSquare {

    /**
     * DP.
     * Finding the largest square's area is the same as finding the edge length.
     * The recurrence relation here is:
     * Suppose the largest edge length formed by current grid at matrix[i-1][j-1] is dp[i][j].
     * If matrix[i - 1][j - 1] = 1, dp[i][j] = min(dp[i-1][j], dp[i][j], dp[i][j-1]) + 1.
     * If matrix[i - 1][j - 1] = 0, dp[i][j] = 0.
     * <p>
     * To understand the recurrence relation, draw a matrix.
     * Iff grid [i,j] is 1 and it's just a corners of other 1s, can the length expand.
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int r = matrix.length;
        int c = r == 0 ? 0 : matrix[0].length;
        int[][] dp = new int[r + 1][c + 1]; // First row and column are all 0.
        int maxLen = 0;
        for (int i = 1; i <= r; i++) { // Traverse dp array. Note the equal sign.
            for (int j = 1; j <= c; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    // Update dp[i][j] and the max length.
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen; // Return AREA here.
    }

    /**
     * DP. Space optimized.
     * Only the previous row and previous column are needed.
     * So reduce space usage to an array and an integer.
     */
    public int maximalSquareB(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        int r = matrix.length;
        int c = r == 0 ? 0 : matrix[0].length;
        int[] dp = new int[c + 1]; // Only need one row.
        int prev = 0; // Store dp[i-1][j-1].
        int maxLen = 0;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                int temp = dp[j]; // Store dp[i-1][j-1] of next iteration.
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prev) + 1;
                    maxLen = Math.max(maxLen, dp[j]);
                } else {
                    dp[j] = 0; // Have to update when grid is 0.
                }
                prev = temp; // dp[j] before update is the dp[i-1][j-1] for the next loop.
            }
        }
        return maxLen * maxLen; // Return the AREA here.
    }

}

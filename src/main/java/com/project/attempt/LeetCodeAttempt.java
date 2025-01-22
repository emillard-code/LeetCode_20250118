package com.project.attempt;

import java.util.Arrays;

public class LeetCodeAttempt {

    public static void main(String[] args) {

        int[][] grid1 = new int[][]{{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        System.out.println(MinimumCostToMakeAtLeastOneValidPathInAGrid(grid1));

        int[][] grid2 = new int[][]{{1,1,3},{3,2,2},{1,1,4}};
        System.out.println(MinimumCostToMakeAtLeastOneValidPathInAGrid(grid2));

        int[][] grid3 = new int[][]{{1,2},{4,3}};
        System.out.println(MinimumCostToMakeAtLeastOneValidPathInAGrid(grid3));

    }

    public static int MinimumCostToMakeAtLeastOneValidPathInAGrid(int[][] grid) {

        // Each number in the 2-dimensional array represent one of these directions each.
        // 1: Right
        // 2: Left
        // 3: Down
        // 4: Up

        return minimumCostValidPath(grid, 0, 0, 0);

    }

    private static int minimumCostValidPath(int[][] grid, int i, int j, int cost) {

//        System.out.println("i:" + i + ", j:" + j + ", cost:" + cost);
//        int pos = i * grid[0].length + j + 1;
//        System.out.println("pos:" + pos + ", cost:" + cost);
//        System.out.println(Arrays.deepToString(grid));

        if (isValidPath(grid)) {
//            System.out.println(Arrays.deepToString(grid));
//            System.out.println("Valid: " + cost);
            if (cost <= 3) {
                System.out.println(Arrays.deepToString(grid));
                System.out.println("Valid: " + cost);
            }
            return cost;
        }

        if (i < grid.length - 1 || j < grid[0].length - 1) {

            int i2 = i;
            int j2 = j;

            if (j < grid[0].length - 1) {
                j2 = j + 1;
            } else if (j == grid[0].length - 1) {
                i2 = i + 1;
                j2 = 0;
            }

            int[][] grid1 = copy2DimensionalArray(grid);
            grid1[i][j] = 1;
            int[][] grid2 = copy2DimensionalArray(grid);
            grid2[i][j] = 2;
            int[][] grid3 = copy2DimensionalArray(grid);
            grid3[i][j] = 3;
            int[][] grid4 = copy2DimensionalArray(grid);
            grid4[i][j] = 4;

            if (grid[i][j] == 1) {

                return Math.min(minimumCostValidPath(grid1, i2, j2, cost),
                        Math.min(minimumCostValidPath(grid2, i2, j2, cost + 1),
                                Math.min(minimumCostValidPath(grid3, i2, j2, cost + 1),
                                        minimumCostValidPath(grid4, i2, j2, cost + 1))));

            } else if (grid[i][j] == 2) {

                return Math.min(minimumCostValidPath(grid1, i2, j2, cost + 1),
                        Math.min(minimumCostValidPath(grid2, i2, j2, cost),
                                Math.min(minimumCostValidPath(grid3, i2, j2, cost + 1),
                                        minimumCostValidPath(grid4, i2, j2, cost + 1))));

            } else if (grid[i][j] == 3) {

                return Math.min(minimumCostValidPath(grid1, i2, j2, cost + 1),
                        Math.min(minimumCostValidPath(grid2, i2, j2, cost + 1),
                                Math.min(minimumCostValidPath(grid3, i2, j2, cost),
                                        minimumCostValidPath(grid4, i2, j2, cost + 1))));

            } else if (grid[i][j] == 4) {

                return Math.min(minimumCostValidPath(grid1, i2, j2, cost + 1),
                        Math.min(minimumCostValidPath(grid2, i2, j2, cost + 1),
                                Math.min(minimumCostValidPath(grid3, i2, j2, cost + 1),
                                        minimumCostValidPath(grid4, i2, j2, cost))));

            }

        }

        return 100;

    }

    private static boolean isValidPath(int[][] grid) {

        int i = 0;
        int j = 0;
        int loopbreaker = 0;

        try {

            while (i < grid.length - 1 || j < grid[0].length - 1) {

                if (loopbreaker > grid.length * grid[0].length) { return false; }

                if (grid[i][j] == 1) { j++; }
                else if (grid[i][j] == 2) { j--; }
                else if (grid[i][j] == 3) { i++; }
                else if (grid[i][j] == 4) { i--; }

                loopbreaker++;

            }

        } catch (Exception e) {

            return false;

        }

        return true;

    }

    private static int[][] copy2DimensionalArray(int[][] array) {

        int[][] copyArray = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {

                copyArray[i][j] = array[i][j];

            }

        }

        return copyArray;

    }

}

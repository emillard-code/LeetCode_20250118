package com.project.attempt;

public class LeetCodeAttempt {

    public static void main(String[] args) {

        int[][] grid1 = new int[][]{{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        System.out.println(minimumCostToMakeAtLeastOneValidPathInAGrid(grid1));

        int[][] grid2 = new int[][]{{1,1,3},{3,2,2},{1,1,4}};
        System.out.println(minimumCostToMakeAtLeastOneValidPathInAGrid(grid2));

        int[][] grid3 = new int[][]{{1,2},{4,3}};
        System.out.println(minimumCostToMakeAtLeastOneValidPathInAGrid(grid3));

    }

    // This method will return the minimum cost for a valid path to the final grid.
    // It will make use of a recursive function in order to arrive at this result.
    public static int minimumCostToMakeAtLeastOneValidPathInAGrid(int[][] grid) {

        return minimumCostValidPath(grid, 0, 0, 0);

    }

    // We will use this recursive function to test all possible combinations for every grid.
    // Testing every possible combination allows us to ensure the minimum possible cost beyond any doubt.
    // Each call of this method will modify the arrows/integers in each grid to help test all possibilities.
    // The grid[][] array represents the current grid (which will be repeatedly modified in recursive calls)
    // being tested, and int row represents the current vertical row while int column represents the current
    // horizontal row. We will basically be iterating through every index in grid[][] with the help of
    // int row and int column. Finally, int cost represents the number of modifications needed to reach
    // the current iteration of the grid[][] array.
    private static int minimumCostValidPath(int[][] grid, int row, int column, int cost) {

        // At the start of every call of this method, we will check whether the current grid is valid or not.
        // If the grid contains a valid path from the start index to final index,
        // We return the int cost that represents how many modifications were needed to reach this point.
        if (isValidPath(grid)) { return cost; }

        // We then check to make sure if the current row and column aren't both at their final values.
        // If they are both at their maximum values, it means we have finished iterating through the
        // entire array and there's nothing left to check. We simply skip the logic here as it also
        // means that a valid path had not been found (otherwise this specific call of the recursive
        // function would have ended at the above if-statement) and we return Integer.MAX_VALUE at the
        // end to show that this specific grid combination is a failure.
        if (row < grid.length - 1 || column < grid[0].length - 1) {

            // Otherwise, we check our current index in the array and properly iterate to the next index.
            // The next index's coordinates will be stored in row2 and column2 and will be used in the
            // arguments of the recursive calls later on. We do this as we still need the original row
            // and column for some of our later logic.
            int row2 = row;
            int column2 = column;

            if (column < grid[0].length - 1) {
                column2 = column + 1;
            } else if (column == grid[0].length - 1) {
                row2 = row + 1;
                column2 = 0;
            }

            // Here we will create 4 versions of the current grid[][] array that are completely
            // identical *except* for the current grid[row][colum] index only. According to the
            // challenge specifications, each index can be 1, 2, 3 or 4, each of which represents
            // one of the four main directions. So we will test all possibilities and do a recursive
            // call for each possible variant later.
            int[][] grid1 = copy2DimensionalArray(grid);
            grid1[row][column] = 1;
            int[][] grid2 = copy2DimensionalArray(grid);
            grid2[row][column] = 2;
            int[][] grid3 = copy2DimensionalArray(grid);
            grid3[row][column] = 3;
            int[][] grid4 = copy2DimensionalArray(grid);
            grid4[row][column] = 4;

            // Depending on what the value of the current grid[row][column] is,
            // we will have to make sure not to append a cost to its associated direction.
            // For example, if grid[row][column] is 3, which represents the downwards direction,
            // then we must make sure the recursive call that *doesn't* change its value (which
            // in this case will be the one that uses grid3[][] in its argument) will only use (cost)
            // and not (cost+1) within its argument, as we did not need to change the arrow to explore
            // that possibility. Otherwise, all other recursive calls must use (cost+1) as they require
            // the arrows to be modified, and we want to test all possible directions at the current
            // index that we're looking at. We use the Math library to return the minimum value between
            // these calls to get the minimum number of modifications needed for a valid path.
            if (grid[row][column] == 1) {

                return Math.min(minimumCostValidPath(grid1, row2, column2, cost),
                        Math.min(minimumCostValidPath(grid2, row2, column2, cost + 1),
                                Math.min(minimumCostValidPath(grid3, row2, column2, cost + 1),
                                        minimumCostValidPath(grid4, row2, column2, cost + 1))));

            } else if (grid[row][column] == 2) {

                return Math.min(minimumCostValidPath(grid1, row2, column2, cost + 1),
                        Math.min(minimumCostValidPath(grid2, row2, column2, cost),
                                Math.min(minimumCostValidPath(grid3, row2, column2, cost + 1),
                                        minimumCostValidPath(grid4, row2, column2, cost + 1))));

            } else if (grid[row][column] == 3) {

                return Math.min(minimumCostValidPath(grid1, row2, column2, cost + 1),
                        Math.min(minimumCostValidPath(grid2, row2, column2, cost + 1),
                                Math.min(minimumCostValidPath(grid3, row2, column2, cost),
                                        minimumCostValidPath(grid4, row2, column2, cost + 1))));

            } else if (grid[row][column] == 4) {

                return Math.min(minimumCostValidPath(grid1, row2, column2, cost + 1),
                        Math.min(minimumCostValidPath(grid2, row2, column2, cost + 1),
                                Math.min(minimumCostValidPath(grid3, row2, column2, cost + 1),
                                        minimumCostValidPath(grid4, row2, column2, cost))));

            }

        }

        // If the method ever reaches this point, it means it's iterated through all indexes
        // in the grid[][] array and that this specific version of the grid did not produce
        // a valid path at all. Since we need to return something, we return Integer.MAX_Value
        // as that will never surpass or disrupt the Math.min operations being conducted in the
        // main logic. We know this as even the absolute largest modifications for a valid path
        // will top out at row.size * column.size (which is literally every single grid being
        // modified).
        return Integer.MAX_VALUE;

    }

    // This is a helper method to check if this grid contains a valid path from the starting grid
    // to the ending grid.
    private static boolean isValidPath(int[][] grid) {

        int i = 0;
        int j = 0;

        // Since it is possible that certain grid[][] combinations will result in endless loops
        // being iterated through (such as the result of circular directions in the grid layout),
        // we have a special int to help break out of such cases.
        int loopbreaker = 0;

        // Otherwise, we simply start from the beginning tile (0,0) and follow its directions
        // to wherever it may end. If the directions push it out of bounds, we immediately catch
        // the error and return false. If the iterations end up looping for more than what is
        // considered reasonable (basically more than the total number of tiles that even exist),
        // then we use the loopbreaker if-condition to break out of the while loop. If at no point
        // does the loop break by being pushed out of bounds or being trapped in a circular loop,
        // and the i and j indexes reach their final values without issue, then we will finally
        // return true at the very end to show that the path is valid.
        try {

            // This while loop will keep going as long as both indexes aren't at their final values
            // yet, or if some kind of exception hasn't occurred yet or loopbreaker doesn't trigger.
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

    // This is a helper method to create a new array that doesn't point to the same memory of an
    // older array whose contents we want to completely carry over as it is.
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

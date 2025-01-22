package com.project;

import com.project.solution.LeetCodeSolution;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeSolutionTest {

    @Test
    public void minimumCostToMakeAtLeastOneValidPathInAGridTest() {

        int[][] grid1 = new int[][]{{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        assertEquals(3, LeetCodeSolution.minCost(grid1));

        int[][] grid2 = new int[][]{{1,1,3},{3,2,2},{1,1,4}};
        assertEquals(0, LeetCodeSolution.minCost(grid2));

        int[][] grid3 = new int[][]{{1,2},{4,3}};
        assertEquals(1, LeetCodeSolution.minCost(grid3));

    }

}

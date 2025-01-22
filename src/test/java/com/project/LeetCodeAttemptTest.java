package com.project;

import com.project.attempt.LeetCodeAttempt;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LeetCodeAttemptTest {

    @Test
    public void minimumCostToMakeAtLeastOneValidPathInAGridTest() {

        int[][] grid1 = new int[][]{{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
        assertEquals(3, LeetCodeAttempt.minimumCostToMakeAtLeastOneValidPathInAGrid(grid1));

        int[][] grid2 = new int[][]{{1,1,3},{3,2,2},{1,1,4}};
        assertEquals(0, LeetCodeAttempt.minimumCostToMakeAtLeastOneValidPathInAGrid(grid2));

        int[][] grid3 = new int[][]{{1,2},{4,3}};
        assertEquals(1, LeetCodeAttempt.minimumCostToMakeAtLeastOneValidPathInAGrid(grid3));

    }

}

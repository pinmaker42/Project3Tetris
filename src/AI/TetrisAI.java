package AI;

import edu.vt.cs5044.tetris.*;

/**
 *
 * Project 3: Creating an AI program to play the Tetris game and have the AI to read the game boards
 * and pick the best placement for the different board shapes
 *
 * @author James Smith
 * @version 1.0
 *
 */
public class TetrisAI implements AI {

    @Override
    public Placement findBestPlacement(Board currentBoardTetris, Shape shapeTetris) {

        int tempCost = 0;
        int overallCost = Integer.MAX_VALUE;
        Placement tempPlacement;
        Placement bestPlacement = new Placement(Rotation.NONE, 0);

        for (Rotation currentRotation : shapeTetris.getValidRotationSet()) {
            for (int col = 0; col < Board.WIDTH; col++) {
                int availableWidth = Board.WIDTH - col;
                if (shapeTetris.getWidth(currentRotation) <= availableWidth) {

                    tempPlacement = new Placement(currentRotation, col);
                    Board tempBoard = currentBoardTetris.getResultBoard(shapeTetris, tempPlacement);
                    tempCost = (4 * getAverageColumnHeight(tempBoard))
                            + (8 * getColumnHeightRange(tempBoard))
                            + (0 * getColumnHeightVariance(tempBoard))
                            + (12 * getTotalGapCount(tempBoard));

                    if (tempCost < overallCost) {
                        overallCost = tempCost;
                        bestPlacement = tempPlacement;
                    }
                }
            }
        }
        return bestPlacement;
    }

    @Override
    public int getAverageColumnHeight(Board boardTetris) {
        int totalHeights = 0;

        for (int col = 0; col < Board.WIDTH; col++) {
            totalHeights += getColumnHeight(boardTetris, col);
        }
        return totalHeights / Board.WIDTH;
    }

    /**
     *
     * Helper method that is used to determine the smallest column
     *
     * @param boardTetris Board object
     * @return smallest column on the board
     */
    private int getSmallestColumn(Board boardTetris) {
        int smallest = 99;

        for (int col = 0; col < Board.WIDTH; col++) {
            int column = getColumnHeight(boardTetris, col);
            if (column < smallest) {
                smallest = column;
            }
        }
        return smallest;
    }

    /**
     *
     * Helper method to determine the smallest column on the board
     *
     * @param boardTetris Board object
     * @return largest column on the board
     */
    private int getLargestColumn(Board boardTetris) {
        int largest = 0;

        for (int col = 0; col < Board.WIDTH; col++) {
            int column = getColumnHeight(boardTetris, col);
            if (column > largest) {
                largest = column;
            }
        }
        return largest;
    }

    @Override
    public int getColumnHeightRange(Board boardTetris) {
        int columnHeightRange = getLargestColumn(boardTetris) - getSmallestColumn(boardTetris);
        return columnHeightRange;
    }

    @Override
    public int getColumnHeightVariance(Board boardTetris) {
        int variance = 0;
        for (int col = 0; col < Board.WIDTH - 1; col++) {
            variance += Math.abs(getColumnHeight(boardTetris, col) - getColumnHeight(boardTetris, col + 1));
        }
        return variance;
    }

    @Override
    public int getTotalGapCount(Board boardTetris) {
        int gapCount = 0;
        for (int col = 0; col < Board.WIDTH; col++) {
            gapCount += countGaps(boardTetris, col);
        }
        return gapCount;
    }

    /**
     *
     * Helper method to figure out what and count the gaps between columns.  Identifies gaps
     * defined as blank space where blocks are found about it
     *
     * @param boardTetris Board object
     * @param colTetris column iteration
     * @return number of gaps in a column
     */
    private int countGaps(Board boardTetris, int colTetris) {
        boolean[] colBlocks = boardTetris.getFixedBlocks()[colTetris];
        int height = getColumnHeight(boardTetris, colTetris);
        int gap = 0;

        for (int i = 0; i < height; i++) {
            if (!colBlocks[i]) {
                gap++;
            }
        }
        return gap;
    }

    /**
     *
     * Helper method to figure out the height of the column
     *
     * @param boardTetris Board object
     * @param colTetris column iteration
     * @return height of column
     */
    private int getColumnHeight(Board boardTetris, int colTetris) {
        boolean[] colBlocks = boardTetris.getFixedBlocks()[colTetris];
        int columnHeight = Board.HEIGHT;

        for (int i = Board.HEIGHT; i > columnHeight - 1; i--) {
            if (columnHeight == 0) {
                return columnHeight;
            }
            if (!colBlocks[i - 1]) {
                columnHeight--;
            }
        }
        return columnHeight;
    }

}
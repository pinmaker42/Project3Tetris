package AI;

import edu.vt.cs5044.tetris.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TetrisAITest {

    private AI brain;
    private Board emptyBoard;
    private Board apiBoard;
    private Board testBoard1;
    private Board testBoard2;
    private Board testBoard3;
    private Board testBoard4;
    private Board testBoard5;

    private Board boardMinorChanges1;
    private Board boardMinorChanges2;

    @Before
    public void setup() {
        brain = new TetrisAI();
        emptyBoard = new Board();
        apiBoard = new Board(

                "          ",
                "## ##    #",
                "# ##### ##",
                "#### #####",
                "# ##### ##",
                "## #######",
                "######### ",
                " #########",
                " #########",
                "###  #####",
                "####### ##",
                "######## #",
                " #### ####");

        testBoard1 = new Board(

                "          ",
                "## ## ## #",
                " ##### ###",
                "## # ### #",
                "##  #### #",
                "## ## ####",
                "##### ### ",
                " # ## ## #",
                " ### #####",
                "##   ### #",
                "    ### ##",
                "## ##### #",
                "#### # ###");

        testBoard2 = new Board(

                "          ",
                "## ## #  #",
                "# ## ## ##",
                "#### #####",
                "# ##### ##",
                "## ### ###",
                "##  ##### ",
                " ### #### ",
                " ## ### ##",
                " #######  ",
                "###  ## # ",
                " ####  # #",
                "##### ## #");

        testBoard3 = new Board(

                "          ",
                "### # # ##",
                "# # ### ##",
                "###  ## ##",
                "# # ### ##",
                "### ## ###",
                "#   ######",
                "###  #####",
                "### ## ###",
                " ### ### #",
                "#### ## # ",
                " ####  # #",
                "##### ##  ");

        testBoard4 = new Board(

                "          ",
                "# #  ##  #",
                "# # ### ##",
                "# # ### ##",
                "###  ## ##",
                "####### # ",
                " #########",
                "#### #####",
                "### ## ###",
                "#### ### #",
                "####### ##",
                " ####  # #",
                " ####### #");

        testBoard5 = new Board(

                "          ",
                "####### ##",
                "###### ###",
                "##### ####",
                "#### #####",
                "###### ###",
                "### ######",
                "####### ##",
                "## #######",
                "######## #",
                "# ########",
                "######### ",
                " #########");

        boardMinorChanges2 = new Board(
                "     ## # ",
                "# ####### ",
                "#### #####");

        boardMinorChanges1 = new Board(
                "#    #  # ",
                "##  ######",
                "### ######");
    }

    @Test
    public void testPlacementShapeT() {
        Board placeBoardT = new Board(
                "####   ###",
                "##### ####",
                "##########");

        Placement expectedTest = new Placement(Rotation.CCW_180, 4);
        Placement actualTest = brain.findBestPlacement(placeBoardT, Shape.T);
        assertEquals(expectedTest, actualTest);
    }

    @Test
    public void testPlacementShapeO() {
        Board placeBoardO = new Board(
                "####  ####",
                "####  ####",
                "##########");

        Placement expectedTest = new Placement(Rotation.NONE, 4);
        Placement actualTest = brain.findBestPlacement(placeBoardO, Shape.O);
        assertEquals(expectedTest, actualTest);
    }

    @Test
    public void testPlacementShapeI() {
        Board placeBoardI = new Board(
                "#### #####",
                "#### #####",
                "#### #####",
                "#### #####",
                "##########");

        Placement expectedTest = new Placement(Rotation.NONE, 4);
        Placement actualTest = brain.findBestPlacement(placeBoardI, Shape.I);
        assertEquals(expectedTest, actualTest);
    }

    @Test
    public void testPlacementShapeL() {
        Board placeBoardL = new Board(
                "#### #####",
                "#### #####",
                "####  ####",
                "##########");

        Placement expectedTest = new Placement(Rotation.CCW_180, 3);
        Placement actualTest = brain.findBestPlacement(placeBoardL, Shape.L);
        assertEquals(expectedTest, actualTest);
    }

    @Test
    public void testPlacementShapeS() {
        Board placeBoardS = new Board(
                "#####  ###",
                "####  ####",
                "#### #####",
                "##########");

        Placement expectedTest = new Placement(Rotation.NONE, 5);
        Placement actualTest = brain.findBestPlacement(placeBoardS, Shape.S);
        assertEquals(expectedTest, actualTest);
    }

    @Test
    public void testTotalGapCount() {
        assertEquals(0, brain.getTotalGapCount(emptyBoard));
        assertEquals(14, brain.getTotalGapCount(apiBoard));
        assertEquals(31, brain.getTotalGapCount(testBoard1));
        assertEquals(29, brain.getTotalGapCount(testBoard2));
        assertEquals(21, brain.getTotalGapCount(testBoard3));
        assertEquals(15, brain.getTotalGapCount(testBoard4));
        assertEquals(11, brain.getTotalGapCount(testBoard5));
    }

    @Test
    public void testAverageColumnHeight() {
        assertEquals(0, brain.getAverageColumnHeight(emptyBoard));
        assertEquals(11, brain.getAverageColumnHeight(apiBoard));
        assertEquals(11, brain.getAverageColumnHeight(testBoard1));
        assertEquals(11, brain.getAverageColumnHeight(testBoard2));
        assertEquals(10, brain.getAverageColumnHeight(testBoard3));
        assertEquals(10, brain.getAverageColumnHeight(testBoard4));
        assertEquals(11, brain.getAverageColumnHeight(testBoard5));
    }

    @Test
    public void testColumnHeightRange() {
        assertEquals(0, brain.getColumnHeightRange(emptyBoard));
        assertEquals(2, brain.getColumnHeightRange(apiBoard));
        assertEquals(1, brain.getColumnHeightRange(testBoard1));
        assertEquals(2, brain.getColumnHeightRange(testBoard2));
        assertEquals(8, brain.getColumnHeightRange(testBoard3));
        assertEquals(5, brain.getColumnHeightRange(testBoard4));
        assertEquals(1, brain.getColumnHeightRange(testBoard5));
        assertEquals(2, brain.getColumnHeightRange(boardMinorChanges2));
        assertEquals(3, brain.getColumnHeightRange(boardMinorChanges1));
    }

    @Test
    public void testColumnHeightVariance() {
        assertEquals(0, brain.getColumnHeightVariance(emptyBoard));
        assertEquals(6, brain.getColumnHeightVariance(apiBoard));
        assertEquals(6, brain.getColumnHeightVariance(testBoard1));
        assertEquals(8, brain.getColumnHeightVariance(testBoard2));
        assertEquals(26, brain.getColumnHeightVariance(testBoard3));
        assertEquals(24, brain.getColumnHeightVariance(testBoard4));
        assertEquals(2, brain.getColumnHeightVariance(testBoard5));
    }
}
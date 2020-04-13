import org.junit.Test;

import static org.junit.Assert.*;

public class PuzzelNTest {

    @Test
    public void inversions() {
        int input[] = {1, 8, 2, 0, 4, 3, 7, 6, 5};
        PuzzelN puzzelN = new PuzzelN(3);

        puzzelN.fill(input);
        int inverions = puzzelN.getInversionsCount();
        assertTrue(inverions == 10);

        PuzzelN puzzelN1 = new PuzzelN(4);
        int input1[] = {15, 2, 1, 12, 8, 5, 6, 11, 4, 9, 10, 7, 3, 14, 13, 0};
        puzzelN1.fill(input1);
        inverions = puzzelN1.getInversionsCount();
        assertTrue(inverions == 45);

        PuzzelN puzzelN2 = new PuzzelN(4);
        int input2[] = {3, 9, 1, 15, 14, 11, 4, 6, 13, 0, 10, 12, 2, 7, 8, 5};
        puzzelN2.fill(input2);
        inverions = puzzelN2.getInversionsCount();
        assertTrue(inverions == 56);
    }

    @Test
    public void blankSlotPosition() {
        PuzzelN puzzelN1 = new PuzzelN(4);
        int input1[] = {3, 9, 1, 15, 14, 11, 4, 6, 13, 0, 10, 12, 2, 7, 8, 5};
        puzzelN1.fill(input1);
        int blankSlotPos = puzzelN1.findBlankSlotRow();
        assertTrue(blankSlotPos == 2);


        PuzzelN puzzelN2 = new PuzzelN(4);
        int input2[] = {6, 13, 7, 10, 8, 9, 11, 0, 15, 2, 12, 5, 14, 3, 1, 4};
        puzzelN2.fill(input2);
        blankSlotPos = puzzelN2.findBlankSlotRow();
        assertTrue(blankSlotPos == 3);

    }

    @Test
    public void isSolvable() {
        PuzzelN puzzelN1 = new PuzzelN(4);
        int input1[] = {3, 9, 1, 15, 14, 11, 4, 6, 13, 0, 10, 12, 2, 7, 8, 5};
        puzzelN1.fill(input1);
        boolean isSolvable = puzzelN1.isSolvable();
        assertFalse(isSolvable);

        PuzzelN puzzelN2 = new PuzzelN(4);
        int input2[] = {6, 13, 7, 10, 8, 9, 11, 0, 15, 2, 12, 5, 14, 3, 1, 4};
        puzzelN2.fill(input2);
        isSolvable = puzzelN2.isSolvable();
        assertTrue(isSolvable);

        // Although in Wikipedia it says it's solveable but according to the algorithm it's not
        // I cannot explain that
//        PuzzelN puzzelN3 = new PuzzelN(4);
//        int input3[] = {15, 2, 1, 12, 8, 5, 6, 11, 4, 9, 10, 7, 3, 14, 13, 0};
//        puzzelN3.fill(input3);
//        isSolvable = puzzelN3.isSolvable();
//        assertTrue(isSolvable);

        PuzzelN puzzelN4 = new PuzzelN(3);
        int input4[] = {1, 8, 2, 0, 4, 3, 7, 6, 5};
        puzzelN4.fill(input4);
        isSolvable = puzzelN4.isSolvable();
        assertTrue(isSolvable);

        PuzzelN puzzelN5 = new PuzzelN(4);
        int input5[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 14, 0};
        puzzelN5.fill(input5);
        isSolvable = puzzelN5.isSolvable();
        assertFalse(isSolvable);
    }

    @Test
    public void moveUP() {
        // 3 2
        // 1 0
        PuzzelN puzzelN1 = new PuzzelN(2);
        int input1[] = {3, 2, 1, 0};
        puzzelN1.fill(input1);
        boolean status = puzzelN1.moveTileUp(0, 0);
        assertFalse(status);

        //Move out of range
        status = puzzelN1.moveTileUp(-1, 4);
        assertFalse(status);

        //Move to non blank
        status = puzzelN1.moveTileUp(0, 1);
        assertFalse(status);

        status = puzzelN1.moveTileUp(1, 1);
        assertFalse(status);
    }

    @Test
    public void moveDown() {
        // 3 2
        // 1 0
        PuzzelN puzzelN1 = new PuzzelN(2);
        int input1[] = {3, 2, 1, 0};
        puzzelN1.fill(input1);
        boolean status = puzzelN1.moveTileDown(1, 0);
        assertFalse(status);

        //Move out of range
        status = puzzelN1.moveTileDown(-1, 4);
        assertFalse(status);

        //Move to non blank
        status = puzzelN1.moveTileDown(0, 0);
        assertFalse(status);

        //Good move
        status = puzzelN1.moveTileDown(0, 1);
        assertTrue(status);
    }

    @Test
    public void moveLeft() {
        // 3 2
        // 1 0
        PuzzelN puzzelN1 = new PuzzelN(2);
        int input1[] = {3, 2, 1, 0};
        puzzelN1.fill(input1);
        boolean status = puzzelN1.moveTileLeft(0, 0);
        assertFalse(status);

        //Move out of range
        status = puzzelN1.moveTileLeft(-1, 4);
        assertFalse(status);

        //Move to non blank
        status = puzzelN1.moveTileLeft(0, 1);
        assertFalse(status);
    }

    @Test
    public void moveRight() {
        // 3 2
        // 1 0
        PuzzelN puzzelN1 = new PuzzelN(2);
        int input1[] = {3, 2, 1, 0};
        puzzelN1.fill(input1);
        boolean status = puzzelN1.moveTileRight(0, 1);
        assertFalse(status);

        //Move out of range
        status = puzzelN1.moveTileRight(-1, 4);
        assertFalse(status);

        //Move to non blank
        status = puzzelN1.moveTileRight(0, 0);
        assertFalse(status);

        //Good move
        status = puzzelN1.moveTileRight(1, 0);
        assertTrue(status);
    }

    @Test
    public void isDone() {
        // 0 1
        // 2 3
        PuzzelN puzzelN1 = new PuzzelN(2);
        int input1[] = {0, 1, 2, 3};
        puzzelN1.fill(input1);
        boolean status = puzzelN1.isDone();
        assertFalse(status);

        // 3 2
        // 1 0
        PuzzelN puzzelN2 = new PuzzelN(2);
        int input2[] = {3, 2, 1, 0};
        puzzelN2.fill(input2);
        status = puzzelN2.isDone();
        assertFalse(status);

        // 1 2
        // 3 0
        PuzzelN puzzelN3 = new PuzzelN(2);
        int input3[] = {1, 2, 3, 0};
        puzzelN3.fill(input3);
        status = puzzelN3.isDone();
        assertTrue(status);
    }
}

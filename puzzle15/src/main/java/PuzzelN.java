public class PuzzelN {
    private int dimension;
    private int totalElements;
    private int totalSlots;
    private int puzzel[];

    public PuzzelN(int dimention) {
        this.dimension = dimention;
        this.totalSlots = dimention * dimention;
        puzzel = new int[this.totalSlots];
        totalElements = this.totalSlots - 1;
    }

    /***
     * populate input array
     * @param input - inputted data
     */
    public void fill(int[] input) {
        //In real world, this validation should log the error and throw exception
        if (input.length != totalSlots) {
            System.out.println("invalid input");
            return;
        }
        System.arraycopy(input, 0, puzzel, 0, totalSlots);
    }

    /***
     * Print the puzzle array as a matrix
     */
    public void print() {
        for (int i = 0; i < this.totalSlots; i++) {
            if (i % dimension == 0) { //New Row
                System.out.println();
            }
            System.out.print(this.puzzel[i] + " ");
        }
        System.out.println();
    }

    /***
     * This is O(N*N)
     * @return number of inversions
     */
    public int getInversionsCount() {
        int invCount = 0;
        for (int i = 0; i < totalElements; i++) {
            for (int j = i + 1; j < totalSlots; j++) {
                if (puzzel[i] > 0 && puzzel[j] > 0 && puzzel[i] > puzzel[j])
                    invCount++;
            }
        }
        return invCount;
    }

    /***
     * Finds the blank slot row position from the bottom
     * @return
     */
    public int findBlankSlotRow() {
        int blankSlotIndex;
        for (blankSlotIndex = totalElements; blankSlotIndex >= 0; blankSlotIndex--) {
            if (puzzel[blankSlotIndex] == 0) {
                break;
            }
        }
        int rowFromBottom = dimension - blankSlotIndex / dimension;
        return rowFromBottom;
    }

    private boolean isOdd(int number) {
        return (number & 1) == 1;
    }

    private boolean isEven(int number) {
        return (number & 1) == 0;
    }

    // This function returns
    /***
     * If dimension is odd, then puzzle instance is solvable if number of inversions is even in the input state
     * dimension is even, puzzle instance is solvable if
     * 1. the blank is on an even row counting from the bottom (second-last, fourth-last, etc.) and number of inversions is odd
     * OR
     * 2. the blank is on an odd row counting from the bottom (last, third-last, fifth-last, etc.) and number of inversions is even
     * @return true if puzzle is solvable, false otherwise
     */
    public boolean isSolvable() {
        int inversions = getInversionsCount();
        int blankSlotRowFromBottom = findBlankSlotRow();

        if (isOdd(dimension)) {
            if (isEven(inversions)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (isEven(blankSlotRowFromBottom) && isOdd(inversions)) {
                return true;
            } else if (isOdd(blankSlotRowFromBottom) && isEven(inversions)) { //case 2
                return true;
            } else {
                return false;
            }
        }
    }

    /***
     * Move the tile one step to the left. The move is done only if the tile indices are valid
     * @param row - tile row
     * @param col - tile column
     * @return true if the the indices are valid and the left tile is blank, false otherwise
     */
    public boolean moveTileLeft(int row, int col) {
        if (!isValidTile(row, col)) {
            //log the incident
            return false;
        }
        if (col == 0) {
            //cannot move left
            return false;
        }
        int arrayIndex = row * dimension + col;
        if (puzzel[arrayIndex - 1] > 0) {
            //Cannot move to non blank tile
            return false;
        }
        //Valid move, swap values
        puzzel[arrayIndex - 1] = puzzel[arrayIndex];
        puzzel[arrayIndex] = 0;
        return true;
    }
    /***
     * Move the tile one step to the right. The move is done only if the tile indices are valid
     * @param row - tile row
     * @param col - tile column
     * @return true if the the indices are valid and the right tile is blank, false otherwise
     */
    public boolean moveTileRight(int row, int col) {
        if (!isValidTile(row, col)) {
            //log the incident
            return false;
        }
        if (col == dimension - 1) {
            //cannot move right
            return false;
        }
        int arrayIndex = row * dimension + col;
        if (puzzel[arrayIndex + 1] > 0) {
            //Cannot move to non blank tile
            return false;
        }
        //Valid move, swap values
        puzzel[arrayIndex + 1] = puzzel[arrayIndex];
        puzzel[arrayIndex] = 0;
        return true;
    }
    /***
     * Move the tile one step up. The move is done only if the tile indices are valid
     * @param row - tile row
     * @param col - tile column
     * @return true if the the indices are valid and upper tile is blank, false otherwise
     */
    public boolean moveTileUp(int row, int col) {
        if (!isValidTile(row, col)) {
            //log the incident
            return false;
        }
        if (row == 0) {
            //cannot move up
            return false;
        }
        int arrayIndex = row * dimension + col;
        if (puzzel[arrayIndex - dimension] > 0) {
            //Cannot move to non blank tile
            return false;
        }
        //Valid move, swap values
        puzzel[arrayIndex - dimension] = puzzel[arrayIndex];
        puzzel[arrayIndex] = 0;
        return true;
    }
    /***
     * Move the tile one step down. The move is done only if the tile indices are valid
     * @param row - tile row
     * @param col - tile column
     * @return true if the the indices are valid and lower tile is blank, false otherwise
     */
    public boolean moveTileDown(int row, int col) {
        if (!isValidTile(row, col)) {
            //log the incident
            return false;
        }
        if (row == dimension - 1) {
            //cannot move down
            return false;
        }
        int arrayIndex = row * dimension + col;
        if (puzzel[arrayIndex + dimension] > 0) {
            //Cannot move to non blank tile
            return false;
        }
        //Valid move, swap values
        puzzel[arrayIndex + dimension] = puzzel[arrayIndex];
        puzzel[arrayIndex] = 0;
        return true;
    }

    /***
     * check that the tile indices are in the dimension range
     * @param row
     * @param col
     * @return true if in the dimension range, false otherwise
     */
    private boolean isValidTile(int row, int col) {
        return (row >= 0 && row < dimension) && (col >= 0 && col < dimension);
    }

    /***
     * The game is done when the array is sorted in ascending order (all the elements)
     * and 0 is the last element
     * @return
     */
    public boolean isDone() {
        if (puzzel[totalElements] != 0) {
            return false;
        }
        for (int i = 0; i < totalElements - 1; i++) {
            if (puzzel[i] > puzzel[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

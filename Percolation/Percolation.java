public class Percolation {
    private int ROW;
    private int COL;
    private int BLOCKED = -1;
    private int OPEN;
    private int TOTAL_SITES;
    private int [] GRID;
    private int [] SIZE;
    private int vTop, vBottom;
    private int GRID_LIMIT;
        
    public Percolation(int N) {
		if (N <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
        ROW = N;
        COL = N;
        TOTAL_SITES = ROW * COL;
        vTop = TOTAL_SITES;
        vBottom = TOTAL_SITES+1;
        GRID_LIMIT = TOTAL_SITES+2;
        GRID = new int[GRID_LIMIT];
        SIZE = new int[GRID_LIMIT];
        
        for (int i = 0; i < TOTAL_SITES; i++) {
            GRID[i] = BLOCKED;
            SIZE[i] = 1;
        }
        GRID[vTop] = vTop;
        GRID[vBottom] = vBottom;
        SIZE[vTop] = 1;
        SIZE[vBottom] = 1;
    }
    
    private int getIndex(int row, int col) {
        return ((row-1)*ROW)+(col-1);     
    }
    
    private int getTop(int row, int col) {
        if (row == 1)
            return vTop;
        else if (row > 1 && row <= ROW)
            return (getIndex(row, col)-ROW);
        else 
            return -1;
    }
    
    private int getBottom(int row, int col) {
         if (row == ROW)
             return vBottom;
         else if (row < ROW && row >= 1)
             return (getIndex(row, col)+ROW);
         else
             return -1;
    }
    
    private int getLeft(int row, int col) {
        if (col > 1 && col <= COL)
            return (getIndex(row, col)-1);
        else
            return -1;
            
    }
    
    private int getRight(int row, int col) {
        if (col >= 1 && col < COL)
            return (getIndex(row, col)+1);
        else
            return -1;
    }
    
    private boolean isClosed(int index) {
         return GRID[index] == -1;
    }
    private int getRoot(int index) {
		int indexLoc = index;
        if (!isClosed(indexLoc)) {
            while (GRID[indexLoc] != indexLoc) {
                indexLoc = GRID[indexLoc];
            }
            return indexLoc;
        } else {
            return -1;
        }
    }
    private void union(int cel1, int cel2) {
        int root1 = getRoot(cel1);
        int root2 = getRoot(cel2);
        if (root1 == -1 || root2 == -1)
            return;
        if (SIZE[root1] >= SIZE[root2]) {
            GRID[root2] = root1;
            SIZE[root1] = SIZE[root1]+SIZE[root2];
        } else {
            GRID[root1] = root2;
            SIZE[root2] = SIZE[root1]+SIZE[root2];
        }
    }

    
    public void open(int i, int j) {
		if (i < 1 || i > ROW) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		if (j < 1 || j > COL) {
			throw new java.lang.IndexOutOfBoundsException();
		}
        if (!isOpen(i, j)) {
            int currCel = getIndex(i, j);
            OPEN = currCel;
            GRID[currCel] = OPEN;
            int leftN = getLeft(i, j);
            int rightN = getRight(i, j);
            int topN = getTop(i, j);
            int bottomN = getBottom(i, j);
            if (topN != -1 && !isClosed(topN)) {
                // Connect
                union(topN, currCel);
            }
            if (leftN != -1 && !isClosed(leftN)) {
                // Connect
                 union(leftN, currCel);
            }
            if (rightN != -1 && !isClosed(rightN)) {
                // Connect
                union(rightN, currCel);
            }
           
            if (bottomN != -1 && !isClosed(bottomN)) {
                // Connect
                 union(bottomN, currCel);
            }
        }
            
    }
    public boolean isFull(int i, int j) {
		if (i < 1 || i > ROW) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		if (j < 1 || j > COL) {
			throw new java.lang.IndexOutOfBoundsException();
		}
        int currCel = getIndex(i, j);
        int currRoot = getRoot(currCel);
        return currRoot == getRoot(vTop);
        
    }
    public boolean percolates() {
        return (getRoot(vBottom) == getRoot(vTop));
    }
    
    public boolean isOpen(int i, int j) {
		if (i < 1 || i > ROW) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		if (j < 1 || j > COL) {
			throw new java.lang.IndexOutOfBoundsException();
		}
         int index = getIndex(i, j);
         return GRID[index] != -1;
    }
}
    

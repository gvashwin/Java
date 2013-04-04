public class PercolationStats {
    private double gridSize;
    private double [] pThresholdList;
    private int trials;
    public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
          gridSize = N*N;
          trials = T;
          pThresholdList = new double[trials];
          for (int t = 0; t < trials; t++) {
            Percolation p = new Percolation(N);
            int openCount = 0;
            while (!p.percolates()) {
                int i = StdRandom.uniform(1, N+1);
                int j = StdRandom.uniform(1, N+1);
                if (!p.isOpen(i, j)) {
                    p.open(i, j);
                    openCount++;
                }
            }
            pThresholdList[t] = ((double) openCount/gridSize);
        }
    }
    public double mean() {
		return StdStats.mean(pThresholdList);
	}
    public double stddev() {
		return StdStats.stddev(pThresholdList);
	}
	
	public double confidenceLo() {
		double N = 1.96*StdStats.stddev(pThresholdList);
		double D = Math.sqrt(trials);
		return StdStats.mean(pThresholdList) - (N/D);
	}
	public double confidenceHi() {
		double N = 1.96*StdStats.stddev(pThresholdList);
		double D = Math.sqrt(trials);
		return StdStats.mean(pThresholdList) + (N/D);
	}
    
    public static void main(String [] args) {
        int gridSize = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        StdRandom.setSeed(System.currentTimeMillis());
        PercolationStats pstat = new PercolationStats(gridSize, trials);
        System.out.println("mean \t\t\t\t= "+pstat.mean());
		System.out.println("stddev \t\t\t\t= "+pstat.stddev());
		System.out.println("95% confidence interval \t= "+pstat.confidenceLo()+", "+pstat.confidenceHi());
    }
}
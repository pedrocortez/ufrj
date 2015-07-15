package mv;


public class Main {
	
	public static void main(String[] args) {
		
		int totalFrames             = 64;
		int totalFramesThread       = 4;
		int totalThreads            = 20;
		int totalPagesCreateThreads = 50;
		int rangePages 				= 50;
		
		
		ManagerVM.getInstance(totalFrames, totalFramesThread);
		ThreadMV[] threads = new ThreadMV[totalThreads];
		int numT = 0;
		while (numT < threads.length) {
			ThreadMV threadMV = new ThreadMV(totalPagesCreateThreads, rangePages);
			threads[numT] = threadMV;
			numT++;
			threadMV.sleep3seconds();
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}
}

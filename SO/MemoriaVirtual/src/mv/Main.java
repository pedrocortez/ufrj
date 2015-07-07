package mv;


public class Main {
	
	public static void main(String[] args) {
		
		int totalFrames             = 5;
		int totalFramesThread       = 2;
		int totalThreads            = 4;
		int totalPagesCreateThreads = 6;
		
		
		ManagerVM.getInstance(totalFrames, totalFramesThread);
		ThreadMV[] threads = new ThreadMV[totalThreads];
		int numT = 0;
		while (numT < threads.length) {
			ThreadMV threadMV = new ThreadMV(totalPagesCreateThreads);
			threads[numT] = threadMV;
			numT++;
			threadMV.sleep3seconds();
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}
}

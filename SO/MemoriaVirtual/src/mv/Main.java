package mv;


public class Main {
	
	public static void main(String[] args) {
		ManagerVM.getInstance(64, 4);
		ThreadMV[] threads = new ThreadMV[20];
		int numT = 0;
		while (numT < threads.length) {
			ThreadMV threadMV = new ThreadMV();
			threads[numT] = threadMV;
			numT++;
			threadMV.sleep3seconds();
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
	}
}

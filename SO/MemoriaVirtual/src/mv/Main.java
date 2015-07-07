package mv;


public class Main {
	
	public static void main(String[] args) {
		ManagerVM.getInstance(5);
		
		int numT = 0;
		while (numT < 3) {
			ThreadMV threadMV = new ThreadMV();
			numT++;
			threadMV.sleep3seconds();
		}
	}
}

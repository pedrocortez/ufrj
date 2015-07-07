package mv;

import java.util.Random;

public class ThreadMV implements Runnable{

	private Thread thread;
	private int totalPaginas = 0;
	ManagerVM managerVM;
	
	public ThreadMV() {
		thread = new Thread(this);
		thread.start();
		
		managerVM = ManagerVM.getInstance();
		
	}
	
	public void join() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while (totalPaginas < 10) {
			long tid = this.thread.getId();
			
			Frame frame = new Frame((int) tid, new Random().nextInt(50));
			//managerVM.printThreadMV(frame.threadId);
			managerVM.addFrame(frame);
			//managerVM.printThreadMV(frame.threadId);
			
			totalPaginas++;
			sleep3seconds();
		}
		
	}
	
	public void sleep3seconds() {
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

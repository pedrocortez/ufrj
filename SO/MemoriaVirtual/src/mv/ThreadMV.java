package mv;

import java.util.Random;

public class ThreadMV implements Runnable{


	
	private Thread thread;
	private int totalPages = 0;
	ManagerVM managerVM;
	private int limitPages;
	
	public ThreadMV() {
		limitPages = 50;
		thread = new Thread(this);
		thread.start();
		managerVM = ManagerVM.getInstance();
		
	}

	public ThreadMV(int limit) {
		this();
		this.limitPages = limit;
		
		
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
		
		while (totalPages < limitPages) {
			long tid = this.thread.getId();
			
			Frame frame = new Frame((int) tid, new Random().nextInt(5));
			//managerVM.printThreadMV(frame.threadId);
			managerVM.addFrame(frame);
			//managerVM.printThreadMV(frame.threadId);
			
			totalPages++;
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

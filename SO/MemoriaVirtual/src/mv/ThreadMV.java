package mv;

import java.util.Random;

public class ThreadMV implements Runnable{


	
	private Thread thread;
	private int totalPages = 0;
	ManagerVM managerVM;
	private int limitPages;
	private int rangePages;
	
	public ThreadMV() {
		limitPages = 50;
		rangePages = 50;
		thread = new Thread(this);
		thread.start();
		managerVM = ManagerVM.getInstance();
		
	}

	public ThreadMV(int limit) {
		this();
		this.limitPages = limit;
		
		
	}
	public ThreadMV(int limit, int qtdPages) {
		this();
		this.limitPages = limit;
		this.rangePages = qtdPages;
		
		
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
			
			Frame frame = new Frame((int) tid, new Random().nextInt(rangePages));
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

package mv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ManagerVM {

	private LinkedList<Frame> frames;
	private Map<Integer, List<Frame>> hashMap;
	private int limitFrames;
	private int limitFramesPerThread;
	private static ManagerVM instance;
	private static final int LIMITFRAMESPERTHREAD = 4;
	private static final int LIMITFRAMESTOTAL = 64;

	
	public static ManagerVM getInstance() {
		if (instance == null) {
			instance = new ManagerVM(LIMITFRAMESTOTAL, LIMITFRAMESPERTHREAD);
		}
		return instance;
	}
	
	public static ManagerVM getInstance(int limit) {
		if (instance == null) {
			instance = new ManagerVM(limit, LIMITFRAMESPERTHREAD);
		}
		return instance;
	}

	public static ManagerVM getInstance(int limit, int limitFramesPerThread) {
		if (instance == null) {
			instance = new ManagerVM(limit, limitFramesPerThread);
		}
		return instance;
	}

	

	private ManagerVM(int limitFrames, int limitFramesPerThread) {
		this.limitFrames = limitFrames;
		this.limitFramesPerThread = limitFramesPerThread;
		frames = new LinkedList<Frame>();
		hashMap = new HashMap<Integer, List<Frame>>();
	}

	public synchronized void  addFrame(Frame frame) {
		
		boolean lpt = false;
		boolean lpf = false;
		
		Frame frameRemoved = null;
		List<Frame> list = hashMap.get(frame.threadId);

		System.out.println();
		printThreadMV(frame.threadId);
		System.out.println(Arrays.toString(frames.toArray()));
		
		if(list != null && list.contains(frame)) {
			frameRemoved = frame;
			frames.remove(frameRemoved);
			frames.add(frame);
			
			System.out.println( "Trocando: " + frame);
			System.out.println(Arrays.toString(frames.toArray()) + " lpt: " + lpt + " lpf: " + lpf);
			System.out.println();
			return;
		}
		
		
		
		if (list != null && list.size() >= limitFramesPerThread) {
			lpt = true;
			frameRemoved = list.remove(0);
			hashMap.get(frame.threadId).remove(frameRemoved);
			frames.remove(frameRemoved);
		}

		if (frames.size() >= limitFrames) {
			lpf = true;
			frameRemoved = frames.removeFirst();
			hashMap.get(frameRemoved.threadId).remove(frameRemoved);
		}

		if (list == null) {
			hashMap.put(frame.threadId, new ArrayList<Frame>());
		}
		
		add(frame);
		
		System.out.println("Entrando: " + frame + " - Saindo: "+ frameRemoved);
		System.out.println("Size " + frames.size() + " - " +Arrays.toString(frames.toArray()) + " lpt: " + lpt + " lpf: " + lpf);
		System.out.println();
	}
	
	
	private void add(Frame frame) {
		hashMap.get(frame.threadId).add(frame);
		frames.add(frame);
	}

	public final List<Frame> getFrames() {
		return Collections.unmodifiableList(frames);
	}
	
	
	public void printThreadMV(int tid) {
		List<Frame> list = hashMap.get(tid);
		if(list != null) {
			System.out.println(Arrays.toString(list.toArray()));
		} else {
			System.out.println("[]");
		}
	}

	public void clearMV() {
		instance = null;
	}

}

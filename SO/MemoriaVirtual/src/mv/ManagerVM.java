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
	private int limit;
	private static ManagerVM instance;
	private Disk disk;
	private static final int limitFramesPerThread = 2;
	private static final int limitFramesTotal = 10;

	public static ManagerVM getInstance(int limit) {
		if (instance == null) {
			instance = new ManagerVM(limit);
		}
		return instance;
	}

	public static ManagerVM getInstance() {
		if (instance == null) {
			instance = new ManagerVM(limitFramesTotal);
		}
		return instance;
	}

	private ManagerVM(int limit) {
		this.limit = limit;
		frames = new LinkedList<Frame>();
		disk = new Disk();
		hashMap = new HashMap<Integer, List<Frame>>();
	}

	public synchronized void  addFrame(Frame frame) {
		System.out.print("Antes:  " + Arrays.toString(frames.toArray()) + " - ");
		
		Frame frameRemoved = null;
		List<Frame> list = hashMap.get(frame.threadId);

		if(list != null && list.contains(frame)) {
			frameRemoved = frame;
			frames.remove(frameRemoved);
			frames.add(frame);
			System.out.println("Depois: " + Arrays.toString(frames.toArray()));
			return;
		}
		
		if (list != null && list.size() >= limitFramesPerThread) {
			frameRemoved = removeFrame(list, frame);
		}

		if (frames.size() >= limit) {
			frameRemoved = frames.removeFirst();
			hashMap.get(frameRemoved.threadId).remove(frameRemoved);
		}

		disk.saveFrameInDisk(frameRemoved);
		if (list == null) {
			hashMap.put(frame.threadId, new ArrayList<Frame>());
		}
		
		add(frame);
		
		System.out.println("Depois: " + Arrays.toString(frames.toArray()));
	}
	
	private Frame removeFrame(List<Frame> list, Frame frame) {
		Frame frameRemoved = list.remove(0);
		hashMap.get(frame.threadId).remove(frameRemoved);
		frames.remove(frameRemoved);
		return frameRemoved;
	}
	
	private void add(Frame frame) {
		hashMap.get(frame.threadId).add(frame);
		frames.add(frame);
	}

	public final List<Frame> getFrames() {
		return Collections.unmodifiableList(frames);
	}
	
	public void printMV() {
		System.out.println(Arrays.toString(frames.toArray()));
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

package mv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ManagerVM {

	// TODO: Criar area de swap
	// verificar limite da area -> sem limite
	// verificar toda vez que for entrar alguma coisa na memoria
	// ver se existe na area de swap e se existir, trocar do swap para memoria.

	// Testar com o random das paginas de threads menor, para ter mais colisoes

	private LinkedList<Frame> frames;
	private Map<Integer, List<Frame>> hashMap;
	private Map<Integer, List<Frame>> swap;
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
		swap = new HashMap<Integer, List<Frame>>();
	}

	public synchronized void addFrame(Frame frame) {

		boolean lpt = false;
		boolean lpf = false;

		Frame frameRemoved = null;
		List<Frame> list = hashMap.get(frame.threadId);

		System.out.println();
		System.out.println("Swap:   " + swap);
		printThreadMV(frame.threadId);
		System.out.println("Frames: " + Arrays.toString(frames.toArray()));
		

		if (list != null && list.contains(frame)) {
			frameRemoved = frame;
			frames.remove(frameRemoved);
			frames.add(frame);

			System.out.println("Trocando: " + frame);
			System.out.println(Arrays.toString(frames.toArray()) + " lpt: "
					+ lpt + " lpf: " + lpf);
			System.out.println();
			return;
		}

		if (isFrameOnSwap(frame)) {
			removeFromSwap(frame);
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

		
		add(frame);

		System.out.println("Entrando: " + frame + " - Saindo: " + frameRemoved);
		addSwap(frameRemoved);
		System.out.println("Size " + frames.size() + " - Frames: "  
				+ Arrays.toString(frames.toArray()) + " lpt: " + lpt + " lpf: "
				+ lpf);
		System.out.println();
	}

	private void add(Frame frame) {
		if (hashMap.get(frame.threadId) == null) {
			hashMap.put(frame.threadId, new ArrayList<Frame>());
		}
		hashMap.get(frame.threadId).add(frame);
		frames.add(frame);
	}

	private void addSwap(Frame frame) {
		if(frame == null)
			return;
		if (swap.get(frame.threadId) == null) {
			swap.put(frame.threadId, new ArrayList<Frame>());
		}
		System.out.println("Entrando swap: " + frame );
		swap.get(frame.threadId).add(frame);
	}

	public boolean isFrameOnSwap(Frame frame) {
		if (swap.get(frame.threadId) != null) {
			return swap.get(frame.threadId).contains(frame);
		}
		return false;
	}

	private void removeFromSwap(Frame frame) {
		if (swap.get(frame.threadId) != null) {
			System.out.println("Saindo swap: " + frame );
			swap.get(frame.threadId).remove(frame);
		}
	}

	public final Map<Integer, List<Frame>> getSwap() {
		return swap;
	}

	public final List<Frame> getFrames() {
		return Collections.unmodifiableList(frames);
	}

	public void printThreadMV(int tid) {
		List<Frame> list = hashMap.get(tid);
		if (list != null) {
			System.out.println("Frame Thread: "
					+ Arrays.toString(list.toArray()));
		} else {
			System.out.println("Frame Thread: " + "[]");
		}
	}

	public void clearMV() {
		instance = null;
	}

}

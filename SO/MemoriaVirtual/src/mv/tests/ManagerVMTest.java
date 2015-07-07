package mv.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mv.Disk;
import mv.Frame;
import mv.ManagerVM;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManagerVMTest {

	private ManagerVM managerVm;
	
	@Before
	public void initObjects() {
		
	}
	
	@After
	public void cleanObjects() {
		managerVm.clearMV();
		new Disk().clearAllFiles();
	}
	
	@Test
	public void adicionaFrameAcimaLimiteNaMV() {
		managerVm = ManagerVM.getInstance(2);
		
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 2));
		managerVm.addFrame(new Frame(1, 3));
		
		assertEquals("verificando limite superior", 2, managerVm.getFrames().size() );
	}

	@Test
	public void adicionaFrameAcimaLimiteERemoveMaisAntigo() {
		
		managerVm = ManagerVM.getInstance(2);
		
		List<Frame> frames = new ArrayList<Frame>();
		
		frames.add(new Frame(1, 2));
		frames.add(new Frame(1, 3));
		
		
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 2));
		managerVm.addFrame(new Frame(1, 3));
		
		assertEquals("verificando se os frames na lista estao corretos", 
				frames,
				managerVm.getFrames()
				);
	}

	@Test
	public void adicionaFrameAcimaLimiteEVerificaSwipe() {
		managerVm = ManagerVM.getInstance(2);
		
		managerVm.addFrame(new Frame(2, 1));
		managerVm.addFrame(new Frame(2, 2));
		managerVm.addFrame(new Frame(2, 3));
		managerVm.addFrame(new Frame(2, 4));
		
		List<Frame> frames = new ArrayList<Frame>();
		
		frames.add(new Frame(2, 3));
		frames.add(new Frame(2, 4));
		
		
		assertEquals("verificando se os frames na lista estao corretos", 
				frames,
				managerVm.getFrames()
				);
	}
	
	
	@Test
	public void adicionaFrameAcimaLimiteDaThread() {
		managerVm = ManagerVM.getInstance(3);
		
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 2));
		managerVm.addFrame(new Frame(1, 3));
		
		List<Frame> frames = new ArrayList<Frame>();
		
		frames.add(new Frame(1, 2));
		frames.add(new Frame(1, 3));
		
		assertEquals("verificando limite superior", 2, managerVm.getFrames().size() );
		assertEquals("adicionaFrameAcimaLimiteDaThread.verificando frames",
				frames, 
				managerVm.getFrames() );
	}

	
	@Test
	public void adicionaFramesIguais() {
		managerVm = ManagerVM.getInstance(3);
		
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 1));
		
		List<Frame> frames = new ArrayList<Frame>();
		
		frames.add(new Frame(1, 1));
		
		assertEquals(1, managerVm.getFrames().size() );
		assertEquals("adicionaFrameAcimaLimiteDaThread.verificando frames",
				frames, 
				managerVm.getFrames() );
	}
	
	@Test
	public void adicionaFramesRepetidosComNaoRepetidos() {
		managerVm = ManagerVM.getInstance(3);
		
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 2));
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 1));
		
		List<Frame> frames = new ArrayList<Frame>();
		
		frames.add(new Frame(1, 2));
		frames.add(new Frame(1, 1));
		
		assertEquals(2, managerVm.getFrames().size() );
		assertEquals("adicionaFrameAcimaLimiteDaThread.verificando frames",
				frames, 
				managerVm.getFrames() );
	}

	
	@Test
	public void adicionaFramesMultiplasThreads() {
		managerVm = ManagerVM.getInstance(5);
		
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 2));
		managerVm.addFrame(new Frame(2, 1));
		managerVm.addFrame(new Frame(2, 2));
		managerVm.addFrame(new Frame(3, 1));
		managerVm.addFrame(new Frame(3, 2));
		
		List<Frame> frames = new ArrayList<Frame>();
		
		frames.add(new Frame(1, 2));
		frames.add(new Frame(2, 1));
		frames.add(new Frame(2, 2));
		frames.add(new Frame(3, 1));
		frames.add(new Frame(3, 2));
		
		
		
		assertEquals(5, managerVm.getFrames().size() );
		assertEquals("adicionaFrameAcimaLimiteDaThread.verificando frames",
				frames, 
				managerVm.getFrames() );
	}

	@Test
	public void adicionaFramesMultiplasThreads2() {
		managerVm = ManagerVM.getInstance(5);
		
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 2));
		
		managerVm.addFrame(new Frame(2, 1));
		managerVm.addFrame(new Frame(2, 2));
		
		managerVm.addFrame(new Frame(3, 1));
		managerVm.addFrame(new Frame(3, 2));
		managerVm.addFrame(new Frame(3, 3));
		
		managerVm.addFrame(new Frame(1, 1));
		
		List<Frame> frames = new ArrayList<Frame>();
		
		frames.add(new Frame(2, 1));
		frames.add(new Frame(2, 2));
		frames.add(new Frame(3, 2));
		frames.add(new Frame(3, 3));
		frames.add(new Frame(1, 1));
		
		
		assertEquals(5, managerVm.getFrames().size() );
		assertEquals("adicionaFrameAcimaLimiteDaThread.verificando frames",
				frames, 
				managerVm.getFrames() );
	}

	
	@Test
	public void adicionaFramesMultiplasThreads3() {
		managerVm = ManagerVM.getInstance(5);
		
		managerVm.addFrame(new Frame(1, 1));
		managerVm.addFrame(new Frame(1, 2));
		
		managerVm.addFrame(new Frame(2, 1));
		managerVm.addFrame(new Frame(2, 2));
		
		managerVm.addFrame(new Frame(3, 1));
		managerVm.addFrame(new Frame(3, 2));
		managerVm.addFrame(new Frame(3, 3));
		
		managerVm.addFrame(new Frame(1, 1));

		managerVm.addFrame(new Frame(3, 4));
		
		List<Frame> frames = new ArrayList<Frame>();
		
		frames.add(new Frame(2, 1));
		frames.add(new Frame(2, 2));
		frames.add(new Frame(3, 3));
		frames.add(new Frame(1, 1));
		frames.add(new Frame(3, 4));
		
		assertEquals(5, managerVm.getFrames().size() );
		assertEquals("adicionaFrameAcimaLimiteDaThread.verificando frames",
				frames, 
				managerVm.getFrames() );
	}
}

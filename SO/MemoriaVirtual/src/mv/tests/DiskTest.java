package mv.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import mv.Disk;
import mv.Frame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiskTest {
	
	Disk disk;
	
	@Before
	public void init() {
		disk = new Disk();
	}
	
	@After
	public void afterAll() {
		disk.clearAllFiles();
	}
	
	@Test
	public void testNumberFiles() {
		
		disk.saveFrameInDisk(new Frame(1, 1));
		disk.saveFrameInDisk(new Frame(1, 2));
		
		File dir = new File("arquivos");
		File[] files = dir.listFiles();
	
		assertEquals("numero de arquivos criados foi incorreto", 1, files.length);
	}

	@Test
	public void testNumberFilesMultipleFiles() {
		
		disk.saveFrameInDisk(new Frame(1, 1));
		disk.saveFrameInDisk(new Frame(2, 2));
		disk.saveFrameInDisk(new Frame(1, 2));
		disk.saveFrameInDisk(new Frame(4, 2));
		disk.saveFrameInDisk(new Frame(4, 2));
		
		File dir = new File("arquivos");
		File[] files = dir.listFiles();
		
		assertEquals("numero de arquivos criados foi incorreto", 3, files.length);
	}

}

package mv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

public class Disk {

	public void saveFrameInDisk(Frame frame) {
		if(frame == null) {
			return;
		}
		
		File file = new File("arquivos/" + frame.threadId);
		try {
			// Create file if it does not exist
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
			
			out.write(new Gson().toJson(frame));
			out.newLine();
			out.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearAllFiles() {
		File dir = new File("arquivos");
		for(File file: dir.listFiles()) {
			file.delete();
		}
	}
	
	public List<Frame> getFramesFromDisk() {
		
		return null;
	}
	
}

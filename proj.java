import java.io.*;

public class proj{
	public proj() throws Exception {
		FileReader file = null;
		try {
			file = new proj("text.txt");
		}
		catch(FileNotFoundException ae) {
			System.out.println("File not Found");
		}
		
		BufferedReader br = new BufferedReader(file);
		System.out.println(br);
		
	}
	
	public static void main(String args[]) throws Exception {
		proj test = new proj();
	}
	
	
}

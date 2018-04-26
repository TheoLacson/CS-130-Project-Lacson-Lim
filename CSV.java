package projtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSV {
	public String write(ArrayList<String> args) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("output.csv"));
		StringBuilder sb = new StringBuilder();
		String out = "";
		
		for(int i = 0; i < args.size(); i++) {
			sb.append(args.get(i));
			sb.append('\n');
		}
		
		pw.write(sb.toString());
		out = sb.toString();
		pw.close();
		System.out.println("Done!");
		return out;
	}
}

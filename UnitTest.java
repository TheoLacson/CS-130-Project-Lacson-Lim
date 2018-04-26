package projtest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.*;

public class UnitTest {
	JRReaderFinal Testreader = new JRReaderFinal();
	private Scanner sc;
	@SuppressWarnings("static-access")
	@Test
	public void testMain() {
		String s;
		FileReader file = null;
		String[] lines = null;
		JRReaderFinal reader = new JRReaderFinal();
		try {
			file = new FileReader("finaltest.txt");
		}
		catch(FileNotFoundException ae) {
			System.out.println("Error: File not found.");
			System.exit(0);
		}
		try {
			BufferedReader br = new BufferedReader(file);
			StringBuilder content = new StringBuilder(1024);
			while((s = br.readLine()) != null) {
			    content.append(s + "\n");
			}
			System.out.println("Token      Lexeme");
			System.out.println("-----------------------------------");
			lines = content.toString().split("\n");
		}
		catch(Exception e) {
			System.out.println("Error: File could not be read.");
			System.exit(0);
		}
		for(String r : lines) {
			try {
				reader.analyze(r);
			}
			catch(Exception e) {
				System.out.println("Error: Line could not be analyzed.");
				System.exit(0);
			}
		}
		reader.parse();
		sc = new Scanner(System.in);
		int i = 0;
		boolean Tester = true;
		while(i != reader.output.size()) {
			String Checker = sc.nextLine();
			if(!Checker.equals(reader.output.get(i))) {
				Tester = false;
				System.out.println("Input is incorrect.");
				System.exit(0);
				
			}
			i++;
		}
		if(Tester) System.out.println("Input is correct");
	}

}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Reader {
	static boolean comment = false;
	public static void analyze(String text){
		char[] chars = text.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			int ascii = (int) chars[i];
			if(comment == true) {
				int z = i + 1;
				while(z < chars.length && chars[z] != '>') {
					System.out.print("");
					z++;
				}
				if(chars[z - 1] == '>')
					comment = false;
				i = z - 1;
			}
			else if(ascii == 43)	{
				System.out.print("PLUS       ");
				System.out.println(chars[i]);
			}
			else if(ascii == 45) {
				System.out.print("MINUS      ");
				System.out.println(chars[i]);
			}
			else if(ascii == 42) {
				if(chars[i + 1] == '*') {
					System.out.print("EXP        ");
					System.out.println("**");
					i += 1;
				}
				else {
					System.out.print("MULT       ");
					System.out.println(chars[i]);
				}
			}
			else if(ascii == 47) {
				System.out.print("DIVIDE     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 40) {
				System.out.print("LPAREN     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 41) {
				System.out.print("RPAREN     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 37) {
				System.out.print("MODULO     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 61) {
				System.out.print("EQUALS     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 62) {
				System.out.print("GTHAN      ");
				System.out.println(chars[i]);
			}
			else if(ascii == 58) {
				System.out.print("COLON      ");
				System.out.println(chars[i]);
			}
			else if(ascii == 59) {
				System.out.print("SCOLON     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 44) {
				System.out.print("COMA       ");
				System.out.println(chars[i]);
			}
			else if(ascii == 59) {
				System.out.print("SCOLON     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 46) {
				System.out.print("PERIOD     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 39) {
				System.out.print("QUOTE      ");
				System.out.println(chars[i]);
			}
			else if(ascii == 34) {
				System.out.print("DQUOTE     ");
				System.out.println(chars[i]);
			}
			else if(ascii == 60) {
				if((i + 1 < chars.length) && (chars[i + 1] == '/')) {
					System.out.print("ENDTAGHEAD ");
					System.out.println("</");
					i += 1;
				}
				else if ((i + 1 < chars.length) && ((chars[i + 1] >= 'a' && chars[i + 1] <= 'z') || (chars[i + 1] >= 'A' && chars[i + 1] <= 'Z'))) {
					System.out.print("TAGINDENT  ");
					int z = i + 1;
					System.out.print(chars[i]);
					while((chars[z] >= 'a' && chars[z] <= 'z') || (chars[z] >= 'A' && chars[z] <= 'Z')) {
						System.out.print(chars[z]);
						z++;
					}
					System.out.println();
					i = z - 1;
				}
				else if ((i + 1 < chars.length) && (chars[i + 1] == '!')) {
					int z = i + 1;
					System.out.print("");
					comment = true;
					while(z < chars.length && chars[z] != '>') {
						System.out.print("");
						z++;
					}
					if(chars[z - 1] == '>')
						comment = false;
					System.out.print("");
					i = z - 1;
				}
				else {
					System.out.print("LTHAN      ");
					System.out.println(chars[i]);
				}
			}
			else if ((chars[i] >= '0' && chars[i] <= '9')) {
				System.out.print("NUMBER     ");
				System.out.print(chars[i]);
				int y = i + 1;
				while((y < chars.length) && ((chars[y] >= '0' && chars[y] <= '9' ) || chars[y] == 'e' || chars[y] == '.')) {
					System.out.print(chars[y]);
					y++;
				}
				System.out.println("");
				i = y-1;
			}
			else if(chars[i] == ' ' || chars[i] == 9)
				System.out.print("");
			else {
				System.out.print("IDENT      ");
				System.out.print(chars[i]);
				int y = i + 1;
				while((y < chars.length) && ((chars[y] >= '@' && chars[y] <= '~' ) || chars[y] == '#' || chars[y] == '$')) {
					System.out.print(chars[y]);
					y++;
				}
				System.out.println("");
				i = y - 1;
			}
		}
	}
	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {
		String s;
		//Scanner scanner = new Scanner(System.in);
		//String usertext = scanner.nextLine();
		FileReader file = null;
		String[] lines = null;
		try {
			file = new FileReader(args[0]);
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
				analyze(r);
			}
			catch(Exception e) {
				System.out.println("Error: Line could not be analyzed.");
				System.exit(0);
			}
		}
		System.out.println("Table generated.");
		System.exit(0);
	}
}

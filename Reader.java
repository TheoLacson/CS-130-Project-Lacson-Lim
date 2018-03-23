import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader {
	static boolean comment = false;
	static boolean closed = true;
	static boolean badnum = false;
	static boolean badnumcheck = false;
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
				closed = true;
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
					closed = false;
				}
				else if ((i + 1 < chars.length) && ((chars[i + 1] >= 'a' && chars[i + 1] <= 'z') || (chars[i + 1] >= 'A' && chars[i + 1] <= 'Z'))) {
					System.out.print("TAGIDENT   ");
					int z = i + 1;
					System.out.print(chars[i]);
					while((z < chars.length) && ((chars[z] >= 'a' && chars[z] <= 'z') || (chars[z] >= 'A' && chars[z] <= 'Z') || (chars[z] >= '0' && chars[z] <= '9'))) {
						System.out.print(chars[z]);
						z++;
					}
					System.out.println("");
					i = z - 1;
					closed = false;
				}
				else if ((i + 3 < chars.length) && (chars[i + 1] == '!') && (chars[i + 2] == '-') && (chars[i + 3] == '-')) {
					int z = i + 1;
					System.out.print("");
					comment = true;
					while(z < chars.length && chars[z] != '>') {
						System.out.print("");
						z++;
					}
					closed = false;
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
			else if (Character.isDigit(chars[i])) {
				String number = "";
				int y = i;
				boolean isNum = true;
				int state = 0;
				while(y < chars.length && !badnum && isNum) {
					number += chars[y];
					switch (state) {
					case 0:
						if (Character.isDigit(chars[y])) {
							y++;
							continue;
						}
						else if (chars[y] == '.')
							state = 1;
						else if (chars[y] == 'e' || chars[y] == 'E')
							state = 3;
						else {
							isNum = false;
							i = y - 1;
						}
						break;
					case 1:
						if (Character.isDigit(chars[y]))
							state = 2;
						else {
							badnum = true;
							i = y;
						}
						break;
					case 2:
						if (chars[y] == 'e' || chars[y] == 'E')
							state = 3;
						else if (Character.isDigit(chars[y])) {
							y++;
							continue;
						}
						else {
							isNum = false;
							i = y - 1;
						}
						break;
					case 3:
						if (chars[y] == '-')
							state = 4;
						else if (Character.isDigit(chars[y])) 
							state = 5;
						else {
							badnum = true;
							i = y;
						}
						break;
					case 4:
						if (Character.isDigit(chars[y]))
							state = 5;
						else {
							badnum = true;
							i = y;
						}
						break;
					case 5:
						if (!Character.isDigit(chars[y])) {
							i = y - 1;
							isNum = false;
						}
						break;
					}
					y++;
				}
				if (badnum) 
					System.out.println("Lexical error: Bad number format");
				if (!isNum)
					number = number.substring(0, number.length() - 1);
				System.out.println("NUMBER     " + number);
				badnum = false;
			}
			else if(chars[i] == ' ' || chars[i] == 9)
				System.out.print("");
			else if (Character.isLetter(chars[i])){
				System.out.print("IDENT      ");
				System.out.print(chars[i]);
				int y = i + 1;
				while(y < chars.length && Character.isLetter(chars[y])) {
					System.out.print(chars[y]);
					y++;
				}
				System.out.println("");
				i = y - 1;
			}
			else {
				System.out.println("Lexical Error: Illegal Character (" + chars[i] + ")");
			}
		}
	}
	public static void main(String args[]) throws Exception {
		String s;
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
		if(comment && !closed)
			System.out.println("Lexical Error: Unexpected End-of-file.");
		System.out.println("Table generated.");
		System.exit(0);
	}
}

package projtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.*;

public class JRReaderFinal {
	boolean comment = false;
	boolean closed = true;
	boolean badnum = false;
	boolean badnumcheck = false;
	
	private static final List<String> symbols = Arrays.asList(new String[] {
		"PLUS",
		"MINUS",
		"MULT",
		"DIVIDE",
		"MODULO",
		"EXP",
		"LPAREN",
		"RPAREN",
		"EQUALS",
		"GTHAN",
		"LTHAN",
		"LBRACKET",
		"RBRACKET",
		"COLON",
		"SCOLON",
		"COMA",
		"PERIOD",
		"QUOTE",
		"DQUOTE"
		
	});
	public static ArrayList<String> lexemes = new ArrayList<String>();
	public static ArrayList<String> tokens = new ArrayList<String>();
	private String token;
	private int pos = -1;
	
	public void analyze(String text){
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
				tokens.add("PLUS");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 91) {
				System.out.print("LBRACKET   ");
				tokens.add("LBRACKET");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 93) {
				System.out.print("RBRACKET   ");
				tokens.add("RBRACKET");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 45) {
				System.out.print("MINUS      ");
				tokens.add("MINUS");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 42) {
				if(chars[i + 1] == '*') {
					System.out.print("EXP        ");
					tokens.add("EXP");
					System.out.println("**");
					lexemes.add("**");
					i += 1;
				}
				else {
					System.out.print("MULT       ");
					tokens.add("MULT");
					System.out.println(chars[i]);
					lexemes.add(chars[i] + "");
				}
			}
			else if(ascii == 47) {
				System.out.print("DIVIDE     ");
				tokens.add("DIVIDE");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 40) {
				System.out.print("LPAREN     ");
				tokens.add("LPAREN");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 41) {
				System.out.print("RPAREN     ");
				tokens.add("RPAREN");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 37) {
				System.out.print("MODULO     ");
				tokens.add("MODULO");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 61) {
				System.out.print("EQUALS     ");
				tokens.add("EQUALS");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 62) {
				System.out.print("GTHAN      ");
				tokens.add("GTHAN");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
				closed = true;
			}
			else if(ascii == 58) {
				System.out.print("COLON      ");
				tokens.add("COLON");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 59) {
				System.out.print("SCOLON     ");
				tokens.add("SCOLON");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 44) {
				System.out.print("COMA       ");
				tokens.add("COMA");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 46) {
				System.out.print("PERIOD     ");
				tokens.add("PERIOD");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 39) {
				System.out.print("QUOTE      ");
				tokens.add("QUOTE");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 34) {
				System.out.print("DQUOTE     ");
				tokens.add("DQUOTE");
				System.out.println(chars[i]);
				lexemes.add(chars[i] + "");
			}
			else if(ascii == 60) {
				if((i + 1 < chars.length) && (chars[i + 1] == '/')) {
					System.out.print("ENDTAGHEAD ");
					tokens.add("ENDTAGHEAD");
					System.out.println("</");
					lexemes.add("</");
					i += 1;
					closed = false;
				}
				else if ((i + 1 < chars.length) && ((chars[i + 1] >= 'a' && chars[i + 1] <= 'z') || (chars[i + 1] >= 'A' && chars[i + 1] <= 'Z'))) {
					String tagident= "";
					System.out.print("TAGIDENT   ");
					tokens.add("TAGIDENT");
					int z = i + 1;
					System.out.print(chars[i]);
					tagident += chars[i] + "";
					while((z < chars.length) && ((chars[z] >= 'a' && chars[z] <= 'z') || (chars[z] >= 'A' && chars[z] <= 'Z') || (chars[z] >= '0' && chars[z] <= '9'))) {
						System.out.print(chars[z]);
						tagident += chars[z] + "";
						z++;
					}
					lexemes.add(tagident);
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
					tokens.add("LTHAN");
					System.out.println(chars[i]);
					lexemes.add(chars[i] + "");
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
				tokens.add("NUMBER");
				lexemes.add(number);
				badnum = false;
			}
			else if(chars[i] == ' ' || chars[i] == 9)
				System.out.print("");
			else if (Character.isLetter(chars[i])){
				String ident = "";
				System.out.print("IDENT      ");
				tokens.add("IDENT");
				System.out.print(chars[i]);
				ident += chars[i] + "";
				int y = i + 1;
				while(y < chars.length && Character.isLetter(chars[y])) {
					System.out.print(chars[y]);
					ident += chars[y] + "";
					y++;
				}
				lexemes.add(ident);
				System.out.println("");
				i = y - 1;
			}
			else {
				System.out.println("Lexical Error: Illegal Character (" + chars[i] + ")");
			}
		}
	}

	
	public void parse() {
		pos = -1;
		nextToken();
		block();
	
	}
	
	public boolean isSym() {
		return symbols.contains(token);
	}
	
	public boolean accept(String s) {
		if (token.equals(s)) {
			nextToken();
			return true;
		}
		return false;
	}
	public boolean peek(String s) {
		return token.equals(s);
	}
	
	public void nextToken() {
		if (pos == tokens.size() - 1)
			token = "end";
		else 
			token = tokens.get(++pos);
	}
	
	public static ArrayList<String> output = new ArrayList<String>();
	String s = "";
	public void block() {
		if (peek("TAGIDENT"))
			while (accept("TAGIDENT") && accept("GTHAN")){
				block();
				accept("ENDTAGHEAD");
				if (lexemes.get(pos).equals("tr")) {
					s = s.substring(0, s.length()-1);
					output.add(s);
					s = "";
				}
				accept("IDENT");
				accept("GTHAN");
			}
		else statement();
	}
	
	boolean calc = false;
	public void statement() {
		// =equation
		// [expression]
		// (ident | number | symbol)*
		calc = false;
		if (accept("EQUALS")) {
			calc = true;
			s += equation();
		} else if (accept("LBRACKET")) {
			s += equation();
			if (accept("RBRACKET"));
			else ;//error
		}
		else {
			while (peek("IDENT") || peek("NUMBER") || isSym()) {
				if (!isSym())
					s += lexemes.get(pos) + " ";
				else {
					if (!s.isEmpty()) s = s.substring(0, s.length()-1);
					s += lexemes.get(pos) + " ";
				}
				nextToken();
			}
			if (!s.isEmpty()) s = s.substring(0, s.length()-1);			
		}
		s +=  ",";
		
	}
	
	public String equation() {
		// term , ((+ | -), term)*
		String temp = term();
		while (peek("PLUS") || peek("MINUS")) {
			temp += " " + lexemes.get(pos) + " ";
			nextToken();
			temp += term();
		}
		if (calc)
			temp = Calculator.addSub(temp.split(" "));
		else 
			temp = temp.replaceAll(" ", "");
		return temp;
	}
	
	public String term() {
		// term , ((+ | -), term)*
		String temp = factor();
		while (peek("MULT") || peek("DIVIDE") || peek("MODULO")) {
			temp += " " + lexemes.get(pos) + " ";
			nextToken();
			temp += factor();
		}
		if(calc)
			temp = Calculator.multDiv(temp.split(" "));
		else 
			temp = temp.replaceAll(" ", "");
		return temp;
	}
	
	public String factor() {
		String temp = base();
		while (peek("EXP")) {
			temp += " " + lexemes.get(pos) + " ";
			nextToken();
			temp += base();
		}
		if(calc)
			temp = Calculator.exp(temp.split(" "));
		else 
			temp = temp.replaceAll(" ", "");
		return temp;
	}
	public String base() {
		String temp = "";
		if (peek("NUMBER")) {
			temp += lexemes.get(pos);
			nextToken();
		} else if (peek("LPAREN")) {
			accept("LPAREN");
			temp += equation();
			if (accept("RPAREN"));
			else ; 
		}
		else ;// error
		return temp;
	}
	
	public static void main(String args[]) throws Exception {
		String s;
		FileReader file = null;
		String[] lines = null;
		JRReaderFinal reader = new JRReaderFinal();
		CSV csv = new CSV();
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
				reader.analyze(r);
			}
			catch(Exception e) {
				System.out.println("Error: Line could not be analyzed.");
				System.exit(0);
			}
		}
		reader.parse();
		csv.write(output);
		System.exit(0);
	}
}

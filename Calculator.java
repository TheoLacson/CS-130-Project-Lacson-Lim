package projtest;

public class Calculator {
	public static String addSub(String[] input) {
		String op1 = input[0];
		for(int i = 1; i < input.length; i++) {
			String op = input[i++];
			String op2 = input[i];
			float c = 0;
			if(op.equals("+")) c = Float.parseFloat(op1) + Float.parseFloat(op2);
			else if(op.equals("-")) c = Float.parseFloat(op1) - Float.parseFloat(op2);
			op1 = c + "";
		}
		return op1;
	}
	public static String multDiv(String[] input) {
		String op1 = input[0];
		for(int i = 1; i < input.length; i++) {
			String op = input[i++];
			String op2 = input[i];
			float c = 0;
			if(op.equals("*")) c = Float.parseFloat(op1) * Float.parseFloat(op2);
			else if(op.equals("/")) c = Float.parseFloat(op1) / Float.parseFloat(op2);
			else if(op.equals("%")) c = Float.parseFloat(op1) % Float.parseFloat(op2);
			op1 = c + "";
		}
		return op1;
	}
	public static String exp(String[] input) {
		String op1 = input[0];
		for(int i = 1; i < input.length; i++) {
			String op2 = input[i];
			double c = Math.pow(Float.parseFloat(op1), Float.parseFloat(op2));
			op1 = c + "";
		}
		return op1;
	}
}

import java.util.*;
import java.awt.Point;

public class graphingcalc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Equation: y = ");
		String equation = input.nextLine();
		System.out.println("Start Value: x = ");
		String start = input.nextLine();
		System.out.println("End Value: x = ");
		String end = input.nextLine();
		int startVal = Integer.parseInt(start);
		int endVal = Integer.parseInt(end);
		for(int i = startVal ; i<=endVal;i++) {
			System.out.println(calculate(equation,i));
		}
		while(input.nextLine().toLowerCase() !="stop") {
			System.out.println("Equation: y = ");
			 equation = input.nextLine();
			
			System.out.println("Start Value: x = ");
			 start = input.nextLine();
			 
			System.out.println("End Value: x = ");
			 end = input.nextLine();
			 
			 startVal = Integer.parseInt(start);
			 endVal = Integer.parseInt(end);
			for(int i = startVal ; i<=endVal;i++) {
				System.out.println(calculate(equation,i));
			}
		}
		System.out.println("Finished!");
	}
	
	
	public static Point calculate(String equation, double value) {
		double slope;
		double yint;
		double power = 0;
		double x = value;
		double y = 0;
		Point p = new Point();
		if(!equation.contains("^")) {
			if(equation.contains("+")) {
				try {
				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
				}catch(Exception e) {
					slope = 1;
				}
				yint = Double.parseDouble(equation.substring(equation.indexOf("+")+1, equation.length()));
				
			}
			if(equation.contains("-")) {
				try {
				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
				}catch(Exception e) {
					slope = 1;
				}
				yint = Double.parseDouble(equation.substring(equation.indexOf("-")+1, equation.length()));
				yint*=-1;
				
			}else {
				try {
					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
					}catch(Exception e) {
						slope = 1;
					}
				yint = 0;
			}
			y = slope*x + yint;
			p.setLocation((int)x,(int)y);
		}
		else {
			if(equation.contains("+")) {
				try {
					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
					}catch(Exception e) {
						slope = 1;
					}
				yint = Double.parseDouble(equation.substring(equation.indexOf("+")+1, equation.length()));
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.indexOf("+")-1));
			}
			if(equation.contains("-")) {
				try {
				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
				}catch(Exception e) {
					slope = 1;
				}
				yint = Double.parseDouble(equation.substring(equation.indexOf("-")+1, equation.length()));
				yint*=-1;
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.indexOf("-")-1));
				
			}else {
				try {
					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
					}catch(Exception e) {
						slope = 1;
					}
				yint = 0;
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.length()));
			}
			y = slope*(Math.pow(x, power)) + yint;
			p.setLocation((int)x,(int)y);
		}
		return p;
	
	}

}

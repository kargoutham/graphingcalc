import java.util.*;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JFrame;
public class graphingcalc extends JFrame{
	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	
	
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("NOTE: If polynomial, write in this form: \"A B C\" ->  Ax^2 + Bx + C");
		System.out.println("Equation: y = ");
		String equation = input.nextLine();
		System.out.println("Start Value: x = ");
		String start = input.nextLine();
		System.out.println("End Value: x = ");
		String end = input.nextLine();
		int startVal = Integer.parseInt(start);
		int endVal = Integer.parseInt(end);
		for(int i = startVal ; i<=endVal;i++) {
			
			coordinates.add(calculate(equation,i));
			System.out.println(calculate(equation,i));
		}		
//		System.out.println(max);
		graphingcalc c = new graphingcalc();
	}
	
	
	public static Point calculate(String equation, double value) {
		double slope;
		double yint;
		double power = 0;
		double x = value;
		double y = 0;
		Point p = new Point();
		if(equation.contains("x")) { //linear
			if(equation.contains("+")) {
				try {
				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
				}catch(Exception e) {
					slope = 1;
				}
				yint = Double.parseDouble(equation.substring(equation.indexOf("+")+2, equation.length()));
//				System.out.println("slope: "+slope+"yint: "+yint);
				
			}
			else if(equation.contains("-")) {
				try {
				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
				}catch(Exception e) {
					slope = 1;
				}
				yint = Double.parseDouble(equation.substring(equation.indexOf("-")+2, equation.length()));
				yint*=-1;
//				System.out.println("slope: "+slope+"yint: "+yint);
				
			}else {
				try {
					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
					}catch(Exception e) {
						slope = 1;
					}
				yint = 0;
//				System.out.println("slope: "+slope+"yint: "+yint);
			}
			y = slope*x + yint;
			p.setLocation((int)x,(int)y);
		}
		else { //polynomial
//			if(equation.contains("+")) {
//				try {
//					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
//					}catch(Exception e) {
//						slope = 1;
//					}
//				yint = Integer.parseInt(equation.substring(equation.indexOf("+")+2, equation.length()));
//				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.indexOf("+")-1));
////				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
//			}
//			else if(equation.contains("-")) {
//				try {
//				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
//				}catch(Exception e) {
//					slope = 1;
//				}
//				yint = Integer.parseInt(equation.substring(equation.indexOf("-")+2, equation.length()));
//				yint*=-1;
//				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.indexOf("-")-1));
////				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
//			
//			}else {
//				try {
//					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
//					}catch(Exception e) {
//						slope = 1;
//					}
//				yint = 0;
//				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.length()));
////				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
//			}
//			y = slope*(Math.pow(x, power)) + yint;
			int numSpaces = 0;
			for(int i = 0;i<equation.length();i++) {
				if(equation.charAt(i)==' ') {
					numSpaces++;
				}
			}
			if(numSpaces==2) {
				int a = Integer.parseInt(equation.substring(0,1));
				int b = Integer.parseInt(equation.substring(2,3));
				int c = Integer.parseInt(equation.substring(4,5));
				y = (int)(a*Math.pow(x, 2) + b*Math.pow(x, 1) + c);
			}
			else if (numSpaces == 3){
				int a = Integer.parseInt(equation.substring(0,1));
				int b = Integer.parseInt(equation.substring(2,3));
				int c = Integer.parseInt(equation.substring(4,5));
				int d = Integer.parseInt(equation.substring(6,7));
				y = (int)(a*Math.pow(x, 3) + b*Math.pow(x, 2) + c*Math.pow(x, 1)+d);
			}
			else {
				int a = Integer.parseInt(equation.substring(0,1));
				int b = Integer.parseInt(equation.substring(2,3));
				int c = Integer.parseInt(equation.substring(4,5));
				int d = Integer.parseInt(equation.substring(6,7));
				int e = Integer.parseInt(equation.substring(8,9));
				y = (int)(a*Math.pow(x, 4) + b*Math.pow(x, 3) + c*Math.pow(x, 2)+d*Math.pow(x, 1)+e);
			}
			
			p.setLocation((int)x,(int)y);
		}
		return p;
	
	}
	public static int findMax(ArrayList<Point> coordinates) {
		int max = 0;
		for(Point p: coordinates) {
			if((int)p.getY()>max) {
				max = (int)p.getY();
			}
		}
		return max;
	}
	public graphingcalc() {
		setTitle("Graph"); 
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public void paint(Graphics g) {
		
		
		
		int minX = (int)coordinates.get(0).getX();
		int minY = (int)coordinates.get(0).getY();
		for(int i = 0;i<coordinates.size();i++) {
			if((int)coordinates.get(i).getY()<minY) {
				minY = (int)coordinates.get(i).getY();
			}
		}
		int maxY = (int)coordinates.get(0).getY();
		for(int i = 0;i<coordinates.size();i++) {
			if((int)coordinates.get(i).getY()>maxY) {
				maxY = (int)coordinates.get(i).getY();
			}
		}
		double scaleY = 1000/(maxY-minY);
		double scaleX = 1000/(coordinates.get(coordinates.size()-1).getX()-coordinates.get(0).getX());
//		System.out.println(scaleY);
		ArrayList<Point> newCoordinates = new ArrayList<Point>(coordinates);
		for(int i = 0;i<newCoordinates.size();i++) {
			newCoordinates.get(i).setLocation((int)(coordinates.get(i).getX()-minX)*scaleX,1000-(int)((coordinates.get(i).getY()-minY)*scaleY));
//			System.out.println(newCoordinates.get(i));
		}

//		g.drawLine(500,0,500,1000);   //axis
//		g.drawLine(0,500,1000,500);
		
		
		for(int i = 0; i<coordinates.size()-1;i++) {
//			System.out.println(coordinates.get(i));	
			g.drawLine((int)newCoordinates.get(i).getX(), (int)newCoordinates.get(i).getY(), (int)newCoordinates.get(i+1).getX(), (int)newCoordinates.get(i+1).getY());
			//			g.drawLine((int)(coordinates.get(i).getX()*scaleX)-minX, 1000-(int)((coordinates.get(i).getY())*scaleY), (int)(coordinates.get(i+1).getX()*scaleX)-minX, 1000-(int)((coordinates.get(i+1).getY())*scaleY));
		}
//		for(int i = 0; i<coordinates.size();i++) {
//			g.drawLine((int)newCoordinates.get(i).getX(), 0, (int)newCoordinates.get(i).getX(), 1000);
//					
//		}
//		for(int i = 0; i<coordinates.size();i++) {
//			g.drawLine(0, (int)newCoordinates.get(i).getY(), 1000, (int)newCoordinates.get(i).getY());
//					
//		}
		
		
	}
	


}

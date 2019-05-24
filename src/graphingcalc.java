import java.util.*;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JFrame;
public class graphingcalc extends JFrame{
	public static ArrayList<Point> coordinates = new ArrayList<Point>();
	
	
	public static void main(String[] args) {
		
		int max = 0;
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
			coordinates.add(calculate(equation,i));
			max = findMax(coordinates);
			System.out.println(calculate(equation,i));
		}		
//		System.out.println(max);
		graphingcalc c = new graphingcalc(max);
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
				yint = Double.parseDouble(equation.substring(equation.indexOf("+")+2, equation.length()));
				System.out.println("slope: "+slope+"yint: "+yint);
				
			}
			if(equation.contains("-")) {
				try {
				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
				}catch(Exception e) {
					slope = 1;
				}
				yint = Double.parseDouble(equation.substring(equation.indexOf("-")+2, equation.length()));
				yint*=-1;
				System.out.println("slope: "+slope+"yint: "+yint);
				
			}else {
				try {
					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
					}catch(Exception e) {
						slope = 1;
					}
				yint = 0;
				System.out.println("slope: "+slope+"yint: "+yint);
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
				yint = Integer.parseInt(equation.substring(equation.indexOf("+")+2, equation.length()));
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.indexOf("+")-1));
				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
			}
			if(equation.contains("-")) {
				try {
				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
				}catch(Exception e) {
					slope = 1;
				}
				yint = Integer.parseInt(equation.substring(equation.indexOf("-")+2, equation.length()));
				yint*=-1;
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.indexOf("-")-1));
				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
			
			}else {
				try {
					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
					}catch(Exception e) {
						slope = 1;
					}
				yint = 0;
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.length()));
				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
			}
			y = slope*(Math.pow(x, power)) + yint;
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
	public graphingcalc(int y) {
		setTitle("Graph"); 
		setSize(1000,1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public void paint(Graphics g) {
		double scaleY = 1000/(coordinates.get(coordinates.size()-1).getY()-coordinates.get(0).getY());
		double scaleX = 1000/(coordinates.get(coordinates.size()-1).getX()-coordinates.get(0).getX());
		int minX = (int)coordinates.get(0).getX();
		
		for(int i = 0; i<coordinates.size()-1;i++) {
			g.drawLine((int)(coordinates.get(i).getX()*scaleX)-minX, 1000-(int)((coordinates.get(i).getY())*scaleY), (int)(coordinates.get(i+1).getX()*scaleX)-minX, 1000-(int)((coordinates.get(i+1).getY())*scaleY));
		}
		
	}
	


}

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
		else {
			if(equation.contains("+")) {
				try {
					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
					}catch(Exception e) {
						slope = 1;
					}
				yint = Integer.parseInt(equation.substring(equation.indexOf("+")+2, equation.length()));
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.indexOf("+")-1));
//				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
			}
			else if(equation.contains("-")) {
				try {
				slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
				}catch(Exception e) {
					slope = 1;
				}
				yint = Integer.parseInt(equation.substring(equation.indexOf("-")+2, equation.length()));
				yint*=-1;
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.indexOf("-")-1));
//				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
			
			}else {
				try {
					slope = Double.parseDouble(equation.substring(0, equation.indexOf("x")));
					}catch(Exception e) {
						slope = 1;
					}
				yint = 0;
				power = Double.parseDouble(equation.substring(equation.indexOf("^")+1, equation.length()));
//				System.out.println("slope: "+slope+"yint: "+yint+"power: "+power);
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
		for(int i = 0; i<coordinates.size();i++) {
			g.drawLine((int)newCoordinates.get(i).getX(), 0, (int)newCoordinates.get(i).getX(), 1000);
					
		}
		for(int i = 0; i<coordinates.size();i++) {
			g.drawLine(0, (int)newCoordinates.get(i).getY(), 1000, (int)newCoordinates.get(i).getY());
					
		}
		
		
	}
	


}

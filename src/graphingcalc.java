import java.util.*;
import java.awt.Point;
import java.awt.Graphics;
import javax.swing.JFrame;
public class graphingcalc extends JFrame{
	 public static ArrayList<Point> coordinates = new ArrayList<Point>();
//	 int max = getMax(coordinates);

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
			coordinates.add(calculate(equation,i));
//			System.out.println(calculate(equation,i));
		}		
		System.out.println("Finished!");
		graphingcalc c = new graphingcalc();
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
	public int findMax(ArrayList<Point> coordinates) {
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
		for(int i = 0; i<coordinates.size();i++) {
			g.drawLine((int)coordinates.get(i).getX(), (int)coordinates.get(i).getY(), (int)coordinates.get(i+1).getX(), (int)coordinates.get(i+1).getY());
		}
	}
	


}

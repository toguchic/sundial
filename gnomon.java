package sundial;


//TEST GIT UPDATE
public class gnomon {
	public gnomon(){
		/*
		 * Equation
		 * c
		 * 
		 * d -> angle which the hour line makes with the gnomon
		 * t -> time measured from noon in degrees of arc
		 * phi -> latitude of the place the sundial is located
		 * 
		 * 12 noon is 180* of arc
		 */
		
		//double d = 0;
		//double t = 9;
		//double phi = 11;
		
		//d = Math.tan(t) * Math.sin(phi);
		//d = Math.tan(d);
		
		//System.out.println(d);
		
		System.out.println(calculateArc(0,4));
	}
	
	private double calculateArc(int h, int m){
		int convertToMin = h * 60;
		m = m + convertToMin;
		return minToDegree(m);
	}
	
	private double hourToDegree(double h){
		double htd = h * 15;
		return htd;
	}
	
	private double minToDegree(double m){
		double mtd = m / 4;
		return mtd;
	}
	
	private double minToFt(double m){
		double mtf = m * 15;
		return mtf;
	}
	
	private double secToFt(double s){
		double stf = s / 4;
		return stf;
	}
	
}

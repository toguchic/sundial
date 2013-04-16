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
	}
	
	
	/*
	 * Takes in latitude, hour, and minutes and returns the angle 
	 * of the hour line with respect to the gnomon
	 * 
	 * @param lat latitude of the place the sundial is located
	 * @param h   hour
	 * @param m   minute
	 * @return    angle of hour line
	 */
	public double calculate(double lat, int h, int m){
		double d = Math.tan(Math.toRadians(calculateArc(h,m))) * Math.sin(Math.toRadians(lat));
		System.out.println("calculateArc is reporting -> " + calculateArc(h,m));
		System.out.println("Math.tan is reporting     -> " + Math.tan(Math.toRadians(calculateArc(h,m))));
		System.out.println("Math.sin is reporting     -> " + Math.sin(Math.toRadians(lat)));
		double radians = Math.atan(d);
		double degree = degreeConvert(radians);
		return degree;
		
		
	}
	
//	public double calculate(double lat, int h, int m){
//		double d = Math.tan(h(h,m)) * Math.sin(lat);
//		d = Math.atan(d);
//		
//		return d;
//	}
	
//	public double h(double h, double m){
//		m = m / 60;
//		h = h + m;
//		
//		double hla = (h - 12) * 15;
//		return hla;
//	}
	/*
	 * Converts the time to all minutes to be converted to arc
	 * @param h hour
	 * @param m minute
	 * @return arc
	 */
	private double degreeConvert(double radians){
		double degree = radians * (180/3.14);
		return degree;
	}
	
	private double calculateArc(int h, int m){
		int convertToMin = h * 60;
		m = m + convertToMin;
		return minToDegree(m);
	}
	
	
	
	public double hourToDegree(double h){
		double htd = h * 15;
		return htd;
	}
	
	public double minToDegree(double m){
		double mtd = m / 4;
		return mtd;
	}
	
	public double minToFt(double m){
		double mtf = m * 15;
		return mtf;
	}
	
	public double secToFt(double s){
		double stf = s / 4;
		return stf;
	}
	
}

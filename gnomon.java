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
	public double calculate(int lat, int h, int m){
		double d = Math.tan(calculateArc(h,m) - 180) * Math.sin(lat);
		d = Math.tan(d);
		
		return d;
	}
	
	/*
	 * Converts the time to all minutes to be converted to arc
	 * @param h hour
	 * @param m minute
	 * @return arc
	 */
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

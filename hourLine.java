package sundial;
import java.util.*;

//TEST GIT UPDATE
public class hourLine{
	
	private double [] degreeArray = new double [7];
	private int [] stdMeridian = {-135, 75, 90, 105, 150};
	private String [] stdMeridianLocations = {"Japan", "New York", "Chicago", "Denver", "Hawaii"};
	
	public hourLine(){
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
	 *  Takes in a latitude, and returns an array with degrees from 6 to 12 oclock
	 *  
	 *  @param lat latitude of the place the sundial is located
	 *  @param lng longitude of the place the sundial is located to calculate time correction
	 *  @return double array of degrees
	 */
	public double [] calculateTime(double lat){
		for(int h = 6; h <= 12; h++){
			degreeArray[h-6] = calculate(lat, h , 0);
		}
		return degreeArray;
	}
	
	/*
	 * Takes in a longitude and a String timezone and returns the degree correction
	 * 
	 * @param lng longitude
	 * @param timezone timezone entered by the user
	 * @returns correction in degrees
	 */
	public double longitudeCorrection(double lng, String timeZone){
		int stdMeridianIndex = 0;
		for(int i = 0; i < stdMeridianLocations.length; i++){
			if(timeZone.equalsIgnoreCase(stdMeridianLocations[i])){
				stdMeridianIndex = i;
				System.out.println("Calculating for timezone: " + stdMeridianLocations[i]);
			}
			else{
				return 0;
			}
		}
		return stdMeridian[stdMeridianIndex] - Math.abs(lng);
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
	private double calculate(double lat, int h, int m){
		double d = Math.tan(Math.toRadians(calculateArc(h,m))) * Math.sin(Math.toRadians(lat));
	//	System.out.println("calculateArc is reporting -> " + calculateArc(h,m));
	//	System.out.println("Math.tan is reporting     -> " + Math.tan(Math.toRadians(calculateArc(h,m))));
	//	System.out.println("Math.sin is reporting     -> " + Math.sin(Math.toRadians(lat)));
		double radians = Math.atan(d);
		double degree = Math.toDegrees(radians);
		return degree;
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
		return 180 - minToDegree(m);
	}
	
	 public double gnomonAngles(double lat){
		  double splitangles = (180 - lat)/2; 
		  return splitangles;
		 }
		 
	 public double gnomonSides(double side1, double angle1, double angle2){
		  double angle1rad = (Math.sin(Math.toRadians(angle1)));
		  double angle1degree = Math.toDegrees(angle1rad);
		  System.out.println("Angle 1 -> " + angle1degree);
		  double angle2rad = (Math.sin(Math.toRadians(angle2)));
		  double angle2degree = Math.toDegrees(angle2rad);
		  System.out.println("Angle 2 -> " + angle2degree);
		  double side = (side1 * angle1degree)/angle2degree;
		  
		  return side;
	 }
	
	/*
	 * Other methods that can be used outside of the object
	 * to do calculations
	 */
	 
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



/**********************************
 * Calculates hour lines for the sundial
 * Calculates the longitude correction using standard meridiam
 * 
 * 
 * @ Authors: Chriselle TOguchi, Matt Kanda, Samuel Kim
 * @ Date: 4/30/2013
 * @ Version: 1.0
 ****************************/
package sundial;
import java.util.*;


public class hourLine{
	
	private double [] degreeArray = new double [7];
	private int [] stdMeridian = {-135, 75, 90, 105, 150};
	private String [] stdMeridianLocations = {"Japan", "New York", "Chicago", "Denver", "Hawaii"};
	
	public hourLine(){

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



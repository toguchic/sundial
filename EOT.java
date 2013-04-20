import java.util.*;

public class EOT {

    public static double EqOfT(int month, int day, int year) {
		double DOY = dayOfYear(month,day,year);
        double B = 360 * (DOY-81) / 365;
        B = Math.toRadians(B);
        double E = 9.87 * Math.sin(2*B) - 7.53*Math.cos(B) - 1.5*Math.sin(B);
        return E;
    }
   	
	public static int dayOfYear(double month, double day, double year) {
	    short[] daysInMonth = new short[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	    short[] daysInMonthLeap = new short[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0)||(year % 400 == 0);
	    int yearDay = 0;
	    for(int i=0; i<month-1; i++)
	    {
	        if(isLeapYear) yearDay += daysInMonthLeap[i];
	        else yearDay += daysInMonth[i];
	    }
	    yearDay+=day;
	    return yearDay;
	}

	public static void main (String[] args) {
		int year = 2013;
		int month = 2;
		int day = 14;
		System.out.println("Number day " + dayOfYear(month,day,year));
		System.out.println("EOT " + (EqOfT(month,day,year)/4) + " degrees");
	}
}


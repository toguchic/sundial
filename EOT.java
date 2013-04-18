import java.util.Calendar;

public class EOT {
    private Calendar calendarDate;
  public double EqOfT(){
        int DOY = caledarDate.get(Calendar.DAY_OF_YEAR);
        double B = ((DOY - 81) / 365) * 360;
        B = Math.toRadians(B);
        double E = 9.87 * Math.sin(2 * B) - 7.53 * Math.cos(B) - 1.5 * Math.sin(B);
        return E;
    }
}

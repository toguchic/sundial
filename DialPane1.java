package sundial;

/********************************
 * User Interface where users can input the Month, Day, Year,
 * Latitude, Longitude, and Time Zone. 
 * 
 * Sources include:
 * http://www.dreamincode.net/forums/topic/17705-basic-gui-concepts/
 * 
 */




import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.print.*;

public class DialPane1 extends JFrame {
  
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;
	
	private JLabel monthL, dayL, yearL, timeZoneL, latL, longL;
	private JTextField monthTF, dayTF, yearTF, timeZoneTF, latTF, longTF;
	private JButton submitB, exitB, printB;
	
	
	//Button handlers:
	private SubmitButtonHandler sbHandler;
	private ExitButtonHandler ebHandler;
	private PrintButtonHandler pbHandler;
	
	
	
	public DialPane1()
	{
		monthL = new JLabel("Month: ", SwingConstants.CENTER);
		dayL = new JLabel("Day: ", SwingConstants.CENTER);
		yearL = new JLabel("Year: ", SwingConstants.CENTER);
		timeZoneL = new JLabel("Time Zone: ", SwingConstants.CENTER);
		latL = new JLabel("Latitude: ", SwingConstants.CENTER);
		longL = new JLabel("Longitude: ", SwingConstants.CENTER);
		
		monthTF = new JTextField(10);
		dayTF = new JTextField(10);
		yearTF = new JTextField(10);
		timeZoneTF = new JTextField(10);
		latTF = new JTextField(10);
		longTF = new JTextField(10);
		
		//Specify handlers for each button and add (register) ActionListeners to each button.
		submitB = new JButton("Submit");
		sbHandler = new SubmitButtonHandler();
		submitB.addActionListener(sbHandler);
		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);
		
		setTitle("Sundial Input");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(4, 2));
		
	
		
		
		//Add things to the pane in the order you want them to appear (left to right, top to bottom)
		pane.add(monthL);
		pane.add(monthTF);
		pane.add(dayL);
		pane.add(dayTF);
		pane.add(yearL);
		pane.add(yearTF);
		pane.add(timeZoneL);
		pane.add(timeZoneTF);
		pane.add(longL);
		pane.add(longTF);
		pane.add(latL);
		pane.add(latTF);
		pane.add(submitB);
		pane.add(exitB);
		
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	JFrame frameToPrint;
	public DialPane1(JFrame f){
		frameToPrint = f;
	}
	
	public void Printpage(){
		JFrame frame = new JFrame("Sundial Print");
		frame.setContentPane(new DrawPane());
		frame.setSize(700,700);
		frame.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel buttonCenter = new JPanel( new FlowLayout(FlowLayout.CENTER) );
	   printB = new JButton("Print This Window");
	   pbHandler = new PrintButtonHandler();
	   printB.addActionListener(pbHandler);
			
			
		
	    //JButton printButton = new JButton("Print This Window");
	   // printButton.addActionListener(new DialPane1(frame));
	    printB.setVisible(true);
	    buttonCenter.add(printB);
	 
	    frame.add(buttonCenter, BorderLayout.SOUTH);
		
	}

	
	

	    
	  
	 
	private class SubmitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		
			//Opens new window to print Sundial
			if(e.getSource() instanceof JButton){
				Printpage();
			}
		
		}
		
	}
	
	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
		
	}
	
	private class PrintButtonHandler implements Printable, ActionListener
	{
		public void actionPerformed(ActionEvent e) {
	        PrinterJob job = PrinterJob.getPrinterJob();
	        job.setPrintable(this);
	        boolean ok = job.printDialog();
	        if (ok) {
	            try {
	                 job.print();
	            } catch (PrinterException ex) {
	             /* The job did not successfully complete */
	            }
	        }
	   
	    }
		
		public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
				DrawPane d = new DrawPane();
			
		    if (page > 0) { 
		         return NO_SUCH_PAGE;
		    }
		       d.paint(g);
		
		        return PAGE_EXISTS;
		}
	}
	
	
	//Calculates Adjusted Hour lines and Draws
	public class DrawPane extends JPanel{
		public void paint(Graphics g){
		g.drawArc(90,90,500,500,0,180);//Semi Circle
		
		double longitude, latitude, hour, gnomonAngle, eotAdjust, timeZoneAdjust;
		double [] hourAngle = new double [7];
		int m , d, yr;
		String tzone;
		//boolean check = true;
		//Create instance of gnomon
		hourLine gMon = new hourLine();
				
		//Assigns data that is entered into the fields.
		
			
			
		//}catch(NumberFormatException e){
			//check = false;
			//g.setColor(Color.black);
			//g.drawString("Please use correct input format", 340,90);
			//JOptionPane.showMessageDialog(null, "ERROR", "Error window", JOptionPane.ERROR_MESSAGE);
		//}
		
		try{
			longitude = Double.parseDouble(longTF.getText()); 
			latitude = Double.parseDouble(latTF.getText());
			m = Integer.parseInt(monthTF.getText());
			d = Integer.parseInt(dayTF.getText());
			yr = Integer.parseInt(yearTF.getText());
			tzone = timeZoneTF.getText();
			
		}
		catch(NumberFormatException e){
	    	Font font = new Font("Arial", Font.PLAIN, 20);
		    g.setFont(font);
			g.drawString("Input Error! Please close window", 200, 340);
			return;
		}
		
		
	
		
		//Calculates the Equation of Time given the date
		EOT eot = new EOT();
		eotAdjust = eot.EqOfT(m, d, yr);
		//Calculates Time Zone Adjustments
		timeZoneAdjust = gMon.longitudeCorrection(longitude, tzone);
		
		//Loop through array and transfer to hourAngle array
			for(int i = 6; i <= 12 ; i++){
				hour = gMon.calculateTime(latitude) [i-6];
				hourAngle[i-6] = hour;
			//System.out.println("Calculated Hour " + i + " is: " + hourAngle[i-6]);
			}
		
		
		if(timeZoneAdjust == 0){
	    	Font font = new Font("Arial", Font.PLAIN, 20);
		    g.setFont(font);
			g.drawString("Time Zone Not In Program! Please close window", 100, 340);
			return;
		}
		else{
		//Draws hour lines
			for(int i = 0; i <= 6 ; i++){
				int x = (int) (340 - 250 * Math.cos(Math.toRadians(hourAngle[i])) - eotAdjust + timeZoneAdjust);
				System.out.println("x -> " + x);
				int y = (int) (340 - 250 * Math.sin(Math.toRadians(hourAngle[i])) - eotAdjust + timeZoneAdjust);
				System.out.println("y-> " + y);
				g.drawLine(x, y, 340, 340);
				x = (int) (340 + 250 * Math.cos(Math.toRadians(hourAngle[i])) - eotAdjust + timeZoneAdjust);
				g.drawLine(x, y, 340, 340);
			}
		}
		
		
		
		
		int gx = (int) (340 - 250 * Math.cos(Math.toRadians(latitude)));
		  int gy = (int) (590 - 250 * Math.sin(Math.toRadians(latitude)));
		  
		  System.out.println("x cos: " + 250 * Math.cos(Math.toRadians(latitude)));
		  System.out.println("y sin: " + Math.sin(Math.toRadians(latitude)));
		  System.out.println("x axis: " + gx);
		  System.out.println("y axis: " + gy);
		  
		  /**Hawaii Timezone gnomon
		  g.drawLine(gx,gy,400,400);
		     g.drawLine(100, 500,gx,gy);
		  g.drawLine(400,400,100,500);
		  **/
		  
		  /**Denver Timezone Gnomon
		  g.drawLine(340,340,340,590);
		  g.drawLine(gx + 64,gy-122,340,590);
		  g.drawLine(340, 340, 500, 340);
		  
		  
		  
		  double equalangles = gMon.gnomonAngles(latitude);
		  System.out.println("Equalangles-> " + equalangles);
		  
		  double gSide1 = gMon.gnomonSides(250, latitude, equalangles);
		  int gSideInt = (int) gSide1;
		  System.out.println("Gnomon Sides-> " + gSideInt);
		  
		  double gSide2 = gMon.gnomonSides(gSide1, latitude, equalangles);
		  int gSide2Int = (int) gSide2;
		  System.out.println("Gnomon Sides-> " + gSide2Int);
		  */
		  
		  g.drawLine(90,590,340,590);
		  g.drawLine(340, 590, gx, gy);
		  g.drawLine(gx,gy,90,590);

		}
	}
	
	
	
	public static void main(String[] args)
	{
		DialPane1 input = new DialPane1();
		
	}
	
	
	
	
}
package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int MAXBARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + MAXBARHEIGHT);
		
		showSpeedProfile(MARGIN + MAXBARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {

    	double[] speeds = gpscomputer.speeds();
    	double maxSpeed = GPSUtils.findMax(speeds);
    	double minSpeed = GPSUtils.findMin(speeds);

    	int barWidth = 2;
    	int visibilityTolerance = 10;

    	setColor(0, 0, 255);
    	for (int i = 0; i < speeds.length; i++) {
    	    double normalizedSpeed = (Math.max(speeds[i], 0) - minSpeed) / (maxSpeed - minSpeed);
    	    int barStartHeight = MAXBARHEIGHT - (int) (normalizedSpeed * MAXBARHEIGHT);

    	    int barDepth = ybase - barStartHeight;
    	    if (barDepth <= visibilityTolerance) {
    	        barStartHeight -= visibilityTolerance;
    	        barDepth += visibilityTolerance;
    	    }

    	    drawRectangle(
    	        MARGIN + barWidth * i,
    	        barStartHeight,
    	        barWidth,
    	        barDepth
    	    );
    	}

    	setColor(0, 0, 0);

    	int stepSize = 50;  
	    for (int position = ybase; position >= 0; position -= stepSize) {
	        double normalizedValue = (double) (position - ybase) / (0 - ybase);
	        double speed = minSpeed + normalizedValue * (maxSpeed - minSpeed);

	        int drawPosition = position;
	        drawString(Double.toString(Math.round(speed)) + "km/h", 0, drawPosition);
	    }

	    drawString("Max: " + Double.toString(Math.round(maxSpeed)) + "km/h | Min: " + Double.toString(Math.round(minSpeed)) + "km/h", MARGIN, ybase + 11);	

	    double sum = 0;
	    for (double speed : speeds) {
	        sum += speed;
	    }
	    double averageSpeed = sum / speeds.length;
	    int averageHeight = MAXBARHEIGHT - (int) ((averageSpeed - minSpeed) / (maxSpeed - minSpeed) * MAXBARHEIGHT);

		drawString("Average: " + Double.toString(averageSpeed) + "km/h", MARGIN, ybase + 22);

	    setColor(0, 255, 0);
	    drawLine(MARGIN, ybase - averageHeight, MARGIN + barWidth * speeds.length, ybase - averageHeight);
	}	
}

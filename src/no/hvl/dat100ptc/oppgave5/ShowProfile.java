package no.hvl.dat100ptc.oppgave5;

import no.hvl.dat100ptc.TODO;

import easygraphics.EasyGraphics;
import java.lang.reflect.Array;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50;		// margin on the sides 
	
	private static final int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn (uten .csv): ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {
		// 
		// int x = MARGIN; // første høyde skal tegnes ved MARGIN
		// int y;
		// 
		// TODO 
		// throw new UnsupportedOperationException(TODO.method());
		double[] heights = GPSUtils.getHeights(gpspoints);
		double maxHeight = GPSUtils.findMax(heights);
		double minHeight = GPSUtils.findMin(heights);
		
		int barWidth = 3;
		int visibilityTolerance = 10;
		
		setColor(0, 0, 255);	
		for (int i = 0; i < heights.length; i++) {
			// I steden for at 1 px = 1 m har eg her gjort slik at høgdene er relativ til max og minimum i gpsdataen 
			// Dette gjør at høgdeprofilen ser annerledes ut så hvis det ikke er sånn det burde være
			// har jeg lagt til en ekstra showHeightProfile metode under som bruker 1px = 1m
			double normalizedHeight = (Math.max(heights[i], 0) - minHeight) / (maxHeight - minHeight);
			int barStartHeight = MAXBARHEIGHT - (int) (normalizedHeight * MAXBARHEIGHT);
			// I det tilfelle at vi tegner baren som har høgd = min så tegner vi den ikke med høgd = 0 fordi da kan du ikke se den
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
            
            double height = minHeight + normalizedValue * (maxHeight - minHeight);
			int drawPosition = position;
			drawString(Double.toString(Math.round(height)) + "m", MARGIN / 5, drawPosition);
        }

		drawString("Max: " + Double.toString(Math.round(maxHeight)) + "m | Min : " + Double.toString(Math.round(minHeight)) + "m", MARGIN, ybase + 11);
	}

	// alternativ showHeightProfile metode som kanskje passer bedre med oppgaveteksten
// 	public void showHeightProfile(int ybase) {
// 		// 
// 		// int x = MARGIN; // første høyde skal tegnes ved MARGIN
// 		// int y;
// 		// 
// 		// TODO 
// 		// throw new UnsupportedOperationException(TODO.method());
// // 
// 		double[] heights = GPSUtils.getHeights(gpspoints);
// // 
// 		int barWidth = 3;
// 		for (int i = 0; i < heights.length; i++) {
// 			int barStartHeight = Math.max((int) (ybase - heights[i]), 0);
// 			int barDepth = ybase - barStartHeight;
// 			drawRectangle(
// 				MARGIN + barWidth * i, 
// 				barStartHeight, 
// 				barWidth,
// 				(int) heights[i]
// 			);
// 		}
// 	}

}

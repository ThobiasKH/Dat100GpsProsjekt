package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		// TODO 
		// throw new UnsupportedOperationException(TODO.method());

		setColor(0, 0, 255);
		for (int i = 0; i < gpspoints.length - 1; i++) {
			GPSPoint gpPoint1 = gpspoints[i];
			GPSPoint gpPoint2 = gpspoints[i + 1];

			// int x1 = (int) (xstep * (gpPoint1.getLongitude() - minlon));
			// int y1 = (int) (ystep * (gpPoint1.getLatitude() - minlat));
			// int x2 = (int) (xstep * (gpPoint2.getLongitude() - minlon));
			// int y2 = (int) (ystep * (gpPoint2.getLatitude() - minlat));

			int x1 = (int) (MARGIN + (xstep * (gpPoint1.getLongitude() - minlon ) ) );
			int y1 = (int) (ybase - (ystep * (gpPoint1.getLatitude() - minlat ) ) );
			int x2 = (int) (MARGIN + (xstep * (gpPoint2.getLongitude() - minlon ) ) );
			int y2 = (int) (ybase - (ystep * (gpPoint2.getLatitude() - minlat ) ) );

			// System.out.println(x1 + " | " + y1);

			drawLine(
				x1, 
				y1, 
				x2, 
				y2
			);

			fillCircle(x1, y1, 5);
			fillCircle(x2, y2, 5);
		}
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		// setFont("Courier",12);
		
		// TODO
		// throw new UnsupportedOperationException(TODO.method());
		String totalTime = ("Total Time          : " + GPSUtils.formatTime(gpscomputer.totalTime()));
		String totalDst =  ("Total distance    : " + Math.round(gpscomputer.totalDistance() / 10) + "km");
		String totalElv =  ("Total elevation   : " + Math.round(gpscomputer.totalElevation()) + "m");
		String maxSpeed =  ("Max Speed         : " + Math.round(gpscomputer.maxSpeed() * 3.6) + "km/t");
		String avgSpeed =  ("Average Speed  : " + Math.round(gpscomputer.averageSpeed() * 3.6) + "km/t");
		String energy =    ("Energy               : " + Math.round(gpscomputer.totalKcal(80)) + "kcal");

		drawString(totalTime, MARGIN, 10);
		drawString(totalDst,  MARGIN, 20);
		drawString(totalElv,  MARGIN, 30);
		drawString(maxSpeed,  MARGIN, 40);
		drawString(avgSpeed,  MARGIN, 50);
		drawString(energy,    MARGIN, 60);


		
	}

	public void replayRoute(int ybase) {

		// TODO 
		// throw new UnsupportedOperationException(TODO.method());
		setColor(255, 0, 0);

		setSpeed(4);
		int id = fillCircle(
			(int) (MARGIN + (xstep * (gpspoints[0].getLongitude() - minlon ) ) ),
			(int) (ybase - (ystep * (gpspoints[0].getLatitude() - minlat) ) ),
			8
		);
		for (int i = 1; i < gpspoints.length; i++) {
			GPSPoint gpPoint1 = gpspoints[i];
			// GPSPoint gpPoint2 = gpspoints[i + 1];

			// int x1 = (int) (xstep * (gpPoint1.getLongitude() - minlon));
			// int y1 = (int) (ystep * (gpPoint1.getLatitude() - minlat));
			// int x2 = (int) (xstep * (gpPoint2.getLongitude() - minlon));
			// int y2 = (int) (ystep * (gpPoint2.getLatitude() - minlat));

			int x1 = (int) (MARGIN + (xstep * (gpPoint1.getLongitude() - minlon ) ) );
			int y1 = (int) (ybase - (ystep * (gpPoint1.getLatitude() - minlat ) ) );

			moveCircle(id, x1, y1);
		}
	}

}

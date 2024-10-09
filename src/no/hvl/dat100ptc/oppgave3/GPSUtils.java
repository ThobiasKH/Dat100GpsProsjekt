package no.hvl.dat100ptc.oppgave3;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO 
		// throw new UnsupportedOperationException(TODO.method());
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO
		double[] latitudes = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}

		return latitudes;

	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		
		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO 
		double[] longitudes = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}

		return longitudes;

	}

	//* EKSTRA FUNKSJONALITET */
	public static double[] getHeights(GPSPoint[] gpspoints) {
		double[] heights = new double[gpspoints.length];

		for (int i = 0; i < gpspoints.length; i++) {
			heights[i] = gpspoints[i].getElevation();
		}

		return heights;
	} 

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		// double d;
		double latitude1, longitude1, latitude2, longitude2;

		// throw new UnsupportedOperationException(TODO.method());

		// TODO 

		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();

		double phi1 = Math.toRadians(latitude1);
		double phi2 = Math.toRadians(latitude2);
		double deltaPhi = Math.toRadians(latitude2 - latitude1);
		double deltaLambda = Math.toRadians(longitude2 - longitude1);

		double a = compute_a(phi1, phi2, deltaPhi, deltaLambda);
		double c = compute_c(a);

		return R * c;

	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO
		
		double sinDeltaPhiSq = Math.sin(deltaphi / 2) * Math.sin(deltaphi / 2);
		double sinDeltaLambdaSq = Math.sin(deltadelta / 2) * Math.sin(deltadelta / 2);

		return sinDeltaPhiSq + Math.cos(phi1) * Math.cos(phi2) * sinDeltaLambdaSq;

	}

	private static double compute_c(double a) {

		
		// throw new UnsupportedOperationException(TODO.method());
		
		
		// TODO 
		return 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO

		secs = Math.abs(gpspoint2.getTime() - gpspoint1.getTime()); 

		double dst = Math.abs(distance(gpspoint1, gpspoint2));

		speed = (double) dst / secs;

		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO
		
		int seconds2Use = secs;

		// rotetete, rotete, rotete
		int hours = seconds2Use / (60 * 60);
		seconds2Use -= hours * 60 * 60;
		int min = seconds2Use / 60;
		seconds2Use -= min * 60;
		int sec = seconds2Use;

		boolean isSingleDigitH = hours < 10;
		boolean isSingleDigitM = min < 10;
		boolean isSingleDigitS = sec < 10;

		timestr =  "  " + (isSingleDigitH ? "0" : "") + Integer.toString(hours);
		timestr += 		  TIMESEP;		
		timestr += 		  (isSingleDigitM ? "0" : "") + Integer.toString(min);
		timestr += 		  TIMESEP;
		timestr += 		  (isSingleDigitS ? "0" : "") + Integer.toString(sec);

		return timestr;
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		
		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO
		double rounded = (double) Math.round(d * 100) / 100;
		String roundedStr = Double.toString(rounded);

		str = "      ";
		str += roundedStr;
		return str;

	}
}

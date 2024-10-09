package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0;

		// throw new UnsupportedOperationException(TODO.method());

		// TODO

		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}

		return distance;

	}

	public double totalElevation() {

		// double elevation = 0;

		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO 
		//* Dette er en ekstra metode implementert i GPSUtils */
		double[] heights = GPSUtils.getHeights(gpspoints);

		double maxElevation = GPSUtils.findMax(heights);
		// double minElevation = GPSUtils.findMin(heights);

		return maxElevation;
		
	}

	public int totalTime() {

		// TODO
		// throw new UnsupportedOperationException(TODO.method());
		
		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();

	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		// TODO
		// throw new UnsupportedOperationException(TODO.method());

		for (int i = 0; i < gpspoints.length - 1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}

		return speeds;
		
	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO 
		// throw new UnsupportedOperationException(TODO.method());

		double[] speeds = speeds();
		return GPSUtils.findMax(speeds);
	
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO
		// throw new UnsupportedOperationException(TODO.method());
		average = totalDistance() / totalTime();

		return average;
		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;

		// TODO 
		// throw new UnsupportedOperationException(TODO.method());

		if 		(speedmph  > 20) met = 16;
		else if (speedmph >= 16) met = 12;
		else if (speedmph >= 14) met = 10;
		else if (speedmph >= 12) met =  8;
		else if (speedmph >= 10) met =  6;
		else 				  	 met =  4;
		
		double hours = (double) secs / (60 * 60);
		kcal = met * weight * hours;

		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO 
		// throw new UnsupportedOperationException(TODO.method());
		for (int i = 0; i < gpspoints.length - 1; i++) {
			GPSPoint gp1 = gpspoints[i];
			GPSPoint gp2 = gpspoints[i + 1];
			totalkcal += kcal(
				weight, 
				gp2.getTime() - gp1.getTime(),
				GPSUtils.speed(gp1, gp2)
			);
		}

		return totalkcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		// TODO 
		// throw new UnsupportedOperationException(TODO.method());

		System.out.println("==============================================");

		System.out.println("Total Time     :" + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance :" + totalDistance() / 10 + "km");
		System.out.println("Total elevation:" + totalElevation() + "m");
		System.out.println("Max Speed      :" + maxSpeed() * 3.6 + "km/t");
		System.out.println("Average Speed  :" + averageSpeed() * 3.6 + "km/t");
		System.out.println("Energy         :" + totalKcal(WEIGHT) + "kcal");

		System.out.println("==============================================");
		
	}

}

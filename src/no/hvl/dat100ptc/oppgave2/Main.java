package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		// TODO
		GPSPoint p1 = new GPSPoint(1, 2.5, 2.5, 4);
		GPSPoint p2 = new GPSPoint(2, 2.9, 2.5, 4.5);

		GPSData data = new GPSData(2);
		data.insertGPS(p1);
		data.insertGPS(p2);

		data.print();
		
	}
}

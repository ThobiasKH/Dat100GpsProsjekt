package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {

		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO
		gpspoints = new GPSPoint[antall];
		this.antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;
		
		// throw new UnsupportedOperationException(TODO.method());
		
		// TODO

		if (antall >= gpspoints.length) {
			return inserted;
		}

		gpspoints[antall] = gpspoint;
		antall++;
		inserted = true;
		
		return inserted;
	
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// throw new UnsupportedOperationException(TODO.method());

		// TODO 
		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		
		return insertGPS(gpspoint);
	}

	public void print() {

		// throw new UnsupportedOperationException(TODO.method());

		// TODO 

		System.out.println("====== GPS Data - START ======");
		for (int i = 0; i < antall; i++) {
			String gpsPointString = gpspoints[i].toString();
			System.out.println(gpsPointString);
		}
		System.out.println("====== GPS Data - SLUTT ======");
	}
}

package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;

		// TODO
		// throw new UnsupportedOperationException(TODO.method());
		
		// ex : 2017-08-13T08:52:26.000Z

		// Stoler veldig på at formateringen holder seg lik her
		// Det hadde vel berre blitt et problem viss noen la inn gps data fra år 999 eller før så det går fint 🤷
		hr  = Integer.parseInt( timestr.substring(11, 13) );	
		min = Integer.parseInt( timestr.substring(14, 16) );
		sec = Integer.parseInt( timestr.substring(17, 19) );

		secs = hr * 60 * 60 + min * 60 + sec;

		return secs;

	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		// TODO 
		// throw new UnsupportedOperationException(TODO.method());
		
		int time = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
		
		return new GPSPoint(time, latitude, longitude, elevation);
	}
	
}

package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		GPSPoint p1 = new GPSPoint(1, 2.0, 3.0, 5);
		System.out.println(p1.getTime());	
		p1.setTime(2);
		System.out.println(p1.getTime());
		System.out.println(p1.toString());
		// TODO
		
	}

}

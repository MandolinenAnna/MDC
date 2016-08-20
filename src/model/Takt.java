package model;

public class Takt {

	private int Id;
	private static String Taktart;
	private int Zaehlmuster;
	
	@Override
	public String toString() {
		return "MDC [Id=" + Id + ", Taktart=" + Taktart
				+ ", Zaehlmuster=" + Zaehlmuster + "]";
	}
	public Takt( String taktart, int zaehlmuster) {
		super();
		
		Taktart = taktart;
		Zaehlmuster = zaehlmuster;
	}
	
	public static String getTaktart() {
		return Taktart;
	}
	public void setTaktart(String taktart) {
		Taktart = taktart;
	}
	public int getZaehlmuster() {
		return Zaehlmuster;
	}
	public void setZaehlmuster(int zaehlmuster) {
		Zaehlmuster = zaehlmuster;
	}

	
	

}

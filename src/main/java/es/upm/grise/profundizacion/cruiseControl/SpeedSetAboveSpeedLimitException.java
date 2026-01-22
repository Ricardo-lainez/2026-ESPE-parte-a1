package es.upm.grise.profundizacion.cruiseControl;

public class SpeedSetAboveSpeedLimitException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SpeedSetAboveSpeedLimitException(String message) {
		super(message);
	}
}

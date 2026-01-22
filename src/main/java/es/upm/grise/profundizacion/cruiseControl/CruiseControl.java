package es.upm.grise.profundizacion.cruiseControl;

public class CruiseControl {
	
	@SuppressWarnings("unused")
	private Speedometer speedometer;
	private Integer speedSet;
	private Integer speedLimit;

	/*
	 * Constructor
	 */
	public CruiseControl(Speedometer speedometer) {
		this.speedometer = speedometer;
		this.speedSet = null;
		this.speedLimit = null;
	}
	
	
	
	/*
	 * Method to code / test
	 */
	public void setSpeedSet(int speedSet) throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		// Validar que speedSet sea positivo (estrictamente mayor que cero)
		if (speedSet <= 0) {
			throw new IncorrectSpeedSetException("La velocidad a mantener debe ser un valor positivo mayor que cero");
		}
		
		// Si speedLimit está establecido, speedSet no puede superarlo
		if (this.speedLimit != null && speedSet > this.speedLimit) {
			throw new SpeedSetAboveSpeedLimitException("La velocidad a mantener no puede superar el límite de velocidad");
		}
		
		// Si pasa todas las validaciones, establecer speedSet
		this.speedSet = speedSet;
	}
	
	

	/*
	 * Other setters & getters
	 */
	public Integer getSpeedLimit() {
		
		return speedLimit;
		
	}

	public void setSpeedLimit(Integer speedLimit) {
		
		this.speedLimit = speedLimit;
		
	}

	public Integer getSpeedSet() {
		
		return speedSet;
		
	}

}

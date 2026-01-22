package es.upm.grise.profunduzacion.cruiseController;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.upm.grise.profundizacion.cruiseControl.CruiseControl;
import es.upm.grise.profundizacion.cruiseControl.Speedometer;
import es.upm.grise.profundizacion.cruiseControl.IncorrectSpeedSetException;
import es.upm.grise.profundizacion.cruiseControl.SpeedSetAboveSpeedLimitException;

class CruiseControlTest {
	
	private CruiseControl cruiseControl;
	private Speedometer speedometer;
	
	@BeforeEach
	public void setUp() {
		speedometer = new Speedometer();
		cruiseControl = new CruiseControl(speedometer);
	}
	
	// Pruebas del constructor
	
	@Test
	public void testConstructorInitializesSpeedSetToNull() {
		assertNull(cruiseControl.getSpeedSet(), "speedSet debe inicializarse a null");
	}
	
	@Test
	public void testConstructorInitializesSpeedLimitToNull() {
		assertNull(cruiseControl.getSpeedLimit(), "speedLimit debe inicializarse a null");
	}
	
	// Pruebas de setSpeedSet con valores válidos
	
	@Test
	public void testSetSpeedSetWithValidPositiveValue() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(50);
		assertEquals(50, cruiseControl.getSpeedSet(), "speedSet debe establecerse correctamente con un valor positivo");
	}
	
	@Test
	public void testSetSpeedSetWithMinimumValidValue() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(1);
		assertEquals(1, cruiseControl.getSpeedSet(), "speedSet debe establecerse correctamente con el valor mínimo válido (1)");
	}
	
	// Pruebas de setSpeedSet con valores inválidos (cero o negativos)
	
	@Test
	public void testSetSpeedSetWithZeroThrowsException() {
		Exception exception = assertThrows(IncorrectSpeedSetException.class, () -> {
			cruiseControl.setSpeedSet(0);
		});
		assertNotNull(exception.getMessage(), "La excepción debe tener un mensaje descriptivo");
	}
	
	@Test
	public void testSetSpeedSetWithNegativeValueThrowsException() {
		Exception exception = assertThrows(IncorrectSpeedSetException.class, () -> {
			cruiseControl.setSpeedSet(-10);
		});
		assertNotNull(exception.getMessage(), "La excepción debe tener un mensaje descriptivo");
	}

	
	// Pruebas de setSpeedSet en relación con speedLimit
	
	@Test
	public void testSetSpeedSetWithoutSpeedLimitAllowsAnyPositiveValue() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(200);
		assertEquals(200, cruiseControl.getSpeedSet(), "Sin speedLimit establecido, debe aceptar cualquier valor positivo");
	}
	
	@Test
	public void testSetSpeedSetBelowSpeedLimit() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedLimit(100);
		cruiseControl.setSpeedSet(80);
		assertEquals(80, cruiseControl.getSpeedSet(), "speedSet debe establecerse correctamente cuando es menor que speedLimit");
	}
	
	@Test
	public void testSetSpeedSetEqualToSpeedLimit() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedLimit(100);
		cruiseControl.setSpeedSet(100);
		assertEquals(100, cruiseControl.getSpeedSet(), "speedSet debe establecerse correctamente cuando es igual a speedLimit");
	}
	
	@Test
	public void testSetSpeedSetAboveSpeedLimitThrowsException() {
		cruiseControl.setSpeedLimit(100);
		Exception exception = assertThrows(SpeedSetAboveSpeedLimitException.class, () -> {
			cruiseControl.setSpeedSet(120);
		});
		assertNotNull(exception.getMessage(), "La excepción debe tener un mensaje descriptivo");
	}
	
	// Pruebas combinadas
	
	@Test
	public void testMultipleSetSpeedSetCalls() throws IncorrectSpeedSetException, SpeedSetAboveSpeedLimitException {
		cruiseControl.setSpeedSet(50);
		assertEquals(50, cruiseControl.getSpeedSet());
		
		cruiseControl.setSpeedSet(70);
		assertEquals(70, cruiseControl.getSpeedSet(), "speedSet debe actualizarse con el nuevo valor");
	}
}

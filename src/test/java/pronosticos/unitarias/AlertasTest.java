package pronosticos.unitarias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pronosticos.CalculadoraPronosticos;

class AlertasTest {

    @Test
    @DisplayName("Genera alerta cuando el error supera el umbral")
    void generaAlertaCuandoElErrorSuperaElUmbral() {
        boolean resultado = new CalculadoraPronosticos().debeGenerarAlerta(12, 10);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Genera alerta cuando el error es igual al umbral")
    void generaAlertaCuandoElErrorEsIgualAlUmbral() {
        boolean resultado = new CalculadoraPronosticos().debeGenerarAlerta(10, 10);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("No genera alerta cuando el error no alcanza el umbral")
    void noGeneraAlertaCuandoElErrorNoAlcanzaElUmbral() {
        boolean resultado = new CalculadoraPronosticos().debeGenerarAlerta(7, 10);

        assertFalse(resultado);
    }

    @Test
    @DisplayName("Rechaza un umbral mayor que cien")
    void rechazaUnUmbralMayorQueCien() {
        assertThrows(IllegalArgumentException.class,
                () -> new CalculadoraPronosticos().debeGenerarAlerta(10, 150));
    }
}

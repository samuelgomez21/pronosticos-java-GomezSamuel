package pronosticos.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import pronosticos.CalculadoraPronosticos;

class SuavizacionExponencialTest {

    private static final List<Double> SERIE = List.of(120.0, 132.0, 128.0, 141.0, 138.0, 150.0);

    @Test
    @DisplayName("Pronostica el siguiente periodo con alfa 0.3")
    void pronosticaElSiguientePeriodoConAlfa03() {
        double resultado = new CalculadoraPronosticos().suavizacionExponencial(SERIE, 0.3);

        assertEquals(137.5546, resultado, 0.01);
    }

    @Test
    @DisplayName("Con alfa cero conserva el primer valor de la serie")
    void conAlfaCeroConservaElPrimerValorDeLaSerie() {
        double resultado = new CalculadoraPronosticos().suavizacionExponencial(SERIE, 0);

        assertEquals(120.00, resultado, 0.01);
    }

    @Test
    @DisplayName("Con alfa uno pronostica la última demanda")
    void conAlfaUnoPronosticaLaUltimaDemanda() {
        double resultado = new CalculadoraPronosticos().suavizacionExponencial(SERIE, 1);

        assertEquals(150.00, resultado, 0.01);
    }

    @ParameterizedTest(name = "alfa = {0}")
    @ValueSource(doubles = {-0.1, 1.5})
    @DisplayName("Rechaza un alfa fuera del rango permitido")
    void rechazaUnAlfaFueraDelRangoPermitido(double alfa) {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> new CalculadoraPronosticos().suavizacionExponencial(SERIE, alfa));

        assertEquals("Alfa debe estar entre 0 y 1", error.getMessage());
    }
}

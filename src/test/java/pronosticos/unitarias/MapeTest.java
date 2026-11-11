package pronosticos.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pronosticos.CalculadoraPronosticos;

class MapeTest {

    private static final double TOLERANCIA = 0.01;

    @Test
    @DisplayName("Calcula el MAPE para una serie con demandas distintas de cero")
    void calculaElMapeParaUnaSerieConDemandasDistintasDeCero() {
        double resultado = new CalculadoraPronosticos().mape(
                List.of(141.0, 138.0, 150.0), List.of(126.67, 133.67, 135.67));

        assertEquals(7.62, resultado, TOLERANCIA);
    }

    @Test
    @DisplayName("Excluye los periodos con demanda real igual a cero")
    void excluyeLosPeriodosConDemandaRealIgualACero() {
        double resultado = new CalculadoraPronosticos().mape(
                List.of(80.0, 0.0, 95.0), List.of(80.0, 0.0, 100.0));

        assertEquals(2.63, resultado, TOLERANCIA);
    }

    @Test
    @DisplayName("Rechaza el MAPE cuando no hay periodos válidos")
    void rechazaElMapeCuandoNoHayPeriodosValidos() {
        assertThrows(IllegalArgumentException.class, () -> new CalculadoraPronosticos().mape(
                List.of(0.0, 0.0), List.of(10.0, 20.0)));
    }
}

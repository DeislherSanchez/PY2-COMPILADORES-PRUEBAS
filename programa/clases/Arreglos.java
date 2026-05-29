/**
 * Estado temporal para validar la declaracion y la asignacion de arreglos.
 */
public class Arreglos {
    public String tipo_arreglo_actual;
    public int cantidad_filas_esperadas;
    public int cantidad_columnas_esperadas;
    public int filas_encontradas;
    public int columnas_encontradas;
    public boolean arreglo_valido;

    /**
     * Inicializa el estado de validacion de arreglos.
     */
    public Arreglos() {
        reiniciar();
    }

    /**
     * Restablece el estado para iniciar una nueva declaracion de arreglo.
     */
    public void reiniciar() {
        tipo_arreglo_actual = "";
        cantidad_filas_esperadas = 0;
        cantidad_columnas_esperadas = 0;
        filas_encontradas = 0;
        columnas_encontradas = 0;
        arreglo_valido = true;
    }
}
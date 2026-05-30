/**
 * Clase encargada de almacenar y controlar la información relacionada con la validación y 
 * procesamiento de arreglos multidimensionales.
 */
public class Arreglos {

    public String tipo_arreglo_actual;
    public int cantidad_filas_esperadas;
    public int cantidad_columnas_esperadas;
    public int filas_encontradas;
    public int columnas_encontradas;
    public boolean arreglo_valido;

    /**
     * Constructor de la clase.
     * Inicializa todos los atributos con sus valores por defecto
     * mediante la llamada al método reiniciar().
     */
    public Arreglos() {
        reiniciar();
    }

    /**
     * Restablece todos los atributos de control del arreglo a sus valores iniciales.
     *
     * Este método se utiliza cuando se inicia el análisis de un nuevo arreglo para evitar 
     * que la información de análisis anterior afecte el procesamiento actual.
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
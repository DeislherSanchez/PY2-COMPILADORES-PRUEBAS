/**
 * Clase que representa un símbolo en la tabla de símbolos, con su nombre, categoría, tipo y posición (fila y columna).
 */
public class Symbols {
    private String nombre;
    private String categoria;
    private String tipo;
    private int fila;
    private int columna;
    private int cantidad_parametros;


    /**
     * Constructor de la clase Symbols.
     * @param nombre El nombre del símbolo
     * @param categoria La categoría del símbolo (variable, función, etc.)
     * @param tipo El tipo del símbolo (int, string, etc.)
     * @param fila La fila donde se encuentra el símbolo en el código fuente
     * @param columna La columna donde se encuentra el símbolo en el código fuente
     */
    public Symbols(String nombre, String categoria, String tipo, int fila, int columna) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
        this.cantidad_parametros = 0;
    }

    /**
     * Obtiene el nombre del símbolo.
     * @return El nombre del símbolo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la categoría del símbolo.
     * @return La categoría del símbolo
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Obtiene el tipo del símbolo.
     * @return El tipo del símbolo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene la fila donde se encuentra el símbolo en el código fuente.
     * @return La fila del símbolo
     */
    public int getFila() {
        return fila;
    }

    /**
     * Obtiene la columna donde se encuentra el símbolo en el código fuente.
     * @return La columna del símbolo
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Obtiene la cantiad de parámetros de una función
     * @return cantidad de parametros
     */
    public int getCantidadParametros() {
        return cantidad_parametros;
    }

    /**
     * Establece la cantidad de parámetros de una función.
     * @param cantidad_parametros cantidad de parámetros
     */
    public void setCantidadParametros(int cantidad_parametros) {
        this.cantidad_parametros = cantidad_parametros;
    }

    @Override
    /**
     * Muestra la información del símbolo en un formato legible, con el nombre, categoría, tipo y posición (fila y columna).
     * @return Una cadena con la información del símbolo
     */
    public String toString() {
        return String.format("  %-20s %-12s %-10s %-8d %d", nombre, categoria, tipo, cantidad_parametros, fila, columna);
    } 
}
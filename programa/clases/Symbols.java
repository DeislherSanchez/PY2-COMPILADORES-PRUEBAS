import java.util.ArrayList;

/**
 * Representa un simbolo en la tabla de simbolos con nombre, categoria, tipo,
 * posicion (fila y columna) y datos de parametros cuando aplica a funciones.
 */
public class Symbols {
    private String nombre;
    private String categoria;
    private String tipo;
    private int fila;
    private int columna;
    private int cantidad_parametros;
    private ArrayList<String> tipos_parametros;


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
        this.tipos_parametros = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del símbolo.
     * @return El nombre del símbolo
     */
    public String get_nombre() {
        return nombre;
    }

    /**
     * Obtiene la categoría del símbolo.
     * @return La categoría del símbolo
     */
    public String get_categoria() {
        return categoria;
    }

    /**
     * Obtiene el tipo del símbolo.
     * @return El tipo del símbolo
     */
    public String get_tipo() {
        return tipo;
    }

    /**
     * Obtiene la fila donde se encuentra el símbolo en el código fuente.
     * @return La fila del símbolo
     */
    public int get_fila() {
        return fila;
    }

    /**
     * Obtiene la columna donde se encuentra el símbolo en el código fuente.
     * @return La columna del símbolo
     */
    public int get_columna() {
        return columna;
    }

    /**
     * Obtiene la cantidad de parametros de una funcion.
     * @return cantidad de parametros
     */
    public int get_cantidad_parametros() {
        return cantidad_parametros;
    }

    /**
     * Establece la cantidad de parámetros de una función.
     * @param cantidad_parametros cantidad de parámetros
     */
    public void set_cantidad_parametros(int cantidad_parametros) {
        this.cantidad_parametros = cantidad_parametros;
    }

    /**
     * Obtiene la lista de tipos de parametros de una funcion.
     * @return lista de tipos de parametros
     */
    public ArrayList<String> get_tipos_parametros() {
        return tipos_parametros;
    }

    /**
     * Agrega un tipo de parametro a la lista de la funcion.
     * @param tipo tipo del parametro
     */
    public void agregar_tipo_parametro(String tipo) {
        tipos_parametros.add(tipo);
    }

    @Override
    /**
     * Muestra la informacion del simbolo en un formato legible con nombre,
     * categoria, tipo, cantidad de parametros, fila y columna.
     * @return Una cadena con la informacion del simbolo
     */
    public String toString() {
        return String.format("  %-20s %-12s %-10s %-8d %-8d %-8d", nombre, categoria, tipo, cantidad_parametros, fila, columna);
    } 
}
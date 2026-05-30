import java.util.ArrayList;

/**
 * Clase encargada de almacenar información temporal relacionada con la declaración y llamada de 
 * funciones durante el análisis semántico.
 */
public class Funciones {

    public int parametros_actuales;
    public ArrayList<String> tipos_parametros_actuales;
    public boolean declaracion_valida;


    /**
     * Constructor de la clase.
     * Inicializa los contadores de parámetros y argumentos mediante la llamada al método reiniciar().
     */
    public Funciones() {
        reiniciar();
    }

    /**
     * Restablece los contadores de parámetros y argumentos a sus valores iniciales.
     */
    public void reiniciar() {
        parametros_actuales = 0;
        tipos_parametros_actuales = new ArrayList<>();
        declaracion_valida = true;
    }
}

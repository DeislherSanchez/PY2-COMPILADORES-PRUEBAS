import java.util.ArrayList;

/**
 * Estado temporal para validar declaraciones y llamadas de funciones.
 */
public class Funciones {
    public int parametros_actuales;
    public int argumentos_actuales;
    public ArrayList<String> tipos_parametros_actuales;
    public ArrayList<String> tipos_argumentos_actuales;

    /**
     * Inicializa el estado de validacion de funciones.
     */
    public Funciones() {
        reiniciar();
    }

    /**
     * Restablece el estado para una nueva declaracion o llamada de funcion.
     */
    public void reiniciar() {
        parametros_actuales = 0;
        argumentos_actuales = 0;
        tipos_parametros_actuales = new ArrayList<>();
        tipos_argumentos_actuales = new ArrayList<>();
    }
}
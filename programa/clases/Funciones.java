import java.util.ArrayList;

public class Funciones {
    public int parametros_actuales;
    public int argumentos_actuales;
    public ArrayList<String> tipos_parametros_actuales;
    public ArrayList<String> tipos_argumentos_actuales;

    public Funciones() {
        reiniciar();
    }

    public void reiniciar() {
        parametros_actuales = 0;
        argumentos_actuales = 0;
        tipos_parametros_actuales = new ArrayList<>();
        tipos_argumentos_actuales = new ArrayList<>();
    }
}
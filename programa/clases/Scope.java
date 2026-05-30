import java.util.ArrayList;

/**
 * Clase Scope, representa el alcance o scope de un bloque de código, como una función, un bloque if, etc.
 * Contiene una lista de símbolos (variables, funciones, etc.) que están declarados dentro de ese scope, así como referencias al scope padre y a los scopes hijos.
 */
public class Scope {
    private String nombre; 
    private ArrayList<Symbols> simbolos;

    private Scope padre;
    private ArrayList<Scope> hijos;
    private boolean flujo_terminado;
    private String sentencia_terminal;
    private int linea_terminal;
    private int columna_terminal;

    /**
     * Crea un nuevo scope con el nombre dado y el scope padre asociado.
     * @param nombre El nombre del scope
     * @param padre El scope padre al que pertenece este scope, puede ser null si es el scope global
     */
    public Scope(String nombre, Scope padre) {
        this.nombre = nombre;
        this.simbolos = new ArrayList<Symbols>();
        this.padre = padre;
        this.hijos = new ArrayList<Scope>();
        this.flujo_terminado = false;
        this.sentencia_terminal = null;
        this.linea_terminal = -1;
        this.columna_terminal = -1;
    }

    /**
     * Obtiene el nombre del scope.
     * @return El nombre del scope
     */
    public String get_nombre() {
        return nombre;
    }

    /**
     * Establece el nombre del scope.
     * @param nombre El nombre del scope
     */
    public void set_nombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de símbolos del scope.
     * @return La lista de símbolos del scope
     */
    public ArrayList<Symbols> get_simbolos() {
        return simbolos;
    }


    /**
     * Establece la lista de símbolos del scope.
     * @param simbolos La lista de símbolos del scope
     */
    public void set_simbolos(ArrayList<Symbols> simbolos) {
        this.simbolos = simbolos;
    }

    /**
     * Obtiene el scope padre.
     * @return El scope padre
     */
    public Scope get_padre() {
        return padre;
    }

    /**
     * Establece el scope padre.
     * @param padre El scope padre
     */
    public void set_padre(Scope padre) {
        this.padre = padre;
    }

    /**
     * Obtiene la lista de scopes hijos.
     * @return La lista de scopes hijos
     */
    public ArrayList<Scope> get_hijos() {
        return hijos;
    }

    /**
     * Establece la lista de scopes hijos.
     * @param hijos La lista de scopes hijos
     */
    public void set_hijos(ArrayList<Scope> hijos) {
        this.hijos = hijos;
    }

    /**
     * Busca un símbolo por su nombre en el scope actual y en sus scopes padres.
     * @param nombre_simbolo El nombre del símbolo a buscar
     * @return El símbolo encontrado o null si no se encuentra
     */
    public Symbols buscar_simbolo(String nombre_simbolo) {
        for (Symbols s : simbolos) {
            if (s.get_nombre().equals(nombre_simbolo)) {
                return s;
            }
        }
        if (padre != null) {
            return padre.buscar_simbolo(nombre_simbolo);
        }
        return null;
    }

    /**
     * Busca un símbolo por su nombre en el scope actual.
     * @param nombre_simbolo El nombre del símbolo a buscar
     * @return El símbolo encontrado o null si no se encuentra
     */
    public Symbols buscar_en_scope_actual(String nombre) {
        for (Symbols s : simbolos) {
            if (s.get_nombre().equals(nombre)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Verifica si un simbolo ya existe en el scope actual.
     * @param nombre_simbolo Nombre del simbolo a buscar
     * @return true si el simbolo existe en el scope actual, false en caso contrario
     */
    public boolean existe_en_scope_actual(String nombre_simbolo) {
        for (Symbols s : simbolos) {
            if (s.get_nombre().equals(nombre_simbolo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si el flujo del bloque ya terminó.
     */
    public boolean get_flujo_terminado() {
        return flujo_terminado;
    }

    public void marcar_flujo_terminado(String sentencia, int linea, int columna) {
        this.flujo_terminado = true;
        this.sentencia_terminal = sentencia;
        this.linea_terminal = linea;
        this.columna_terminal = columna;
    }

    public void reiniciar_flujo() {
        flujo_terminado = false;
        sentencia_terminal = null;
        linea_terminal = -1;
        columna_terminal = -1;
    }

    public String get_sentencia_terminal() {
        return sentencia_terminal;
    }

    public int get_linea_terminal() {
        return linea_terminal;
    }

    public int get_columna_terminal() {
        return columna_terminal;
    }

}
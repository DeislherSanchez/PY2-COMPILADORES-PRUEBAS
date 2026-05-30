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
    private boolean return_encontrado;
    private boolean break_contenedor;
    private boolean break_local;
    private int linea_break;
    private int columna_break;
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
        this.return_encontrado = false;
        this.break_contenedor = false;
        this.break_local = false;
        this.linea_break = -1;
        this.columna_break = -1;
    }

    /**
     * Obtiene el nombre del scope.
     * @return El nombre del scope
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del scope.
     * @param nombre El nombre del scope
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de símbolos del scope.
     * @return La lista de símbolos del scope
     */
    public ArrayList<Symbols> getSimbolos() {
        return simbolos;
    }


    /**
     * Establece la lista de símbolos del scope.
     * @param simbolos La lista de símbolos del scope
     */
    public void setSymbols(ArrayList<Symbols> simbolos) {
        this.simbolos = simbolos;
    }

    /**
     * Obtiene el scope padre.
     * @return El scope padre
     */
    public Scope getPadre() {
        return padre;
    }

    /**
     * Establece el scope padre.
     * @param padre El scope padre
     */
    public void setPadre(Scope padre) {
        this.padre = padre;
    }

    /**
     * Obtiene la lista de scopes hijos.
     * @return La lista de scopes hijos
     */
    public ArrayList<Scope> getHijos() {
        return hijos;
    }

    /**
     * Establece la lista de scopes hijos.
     * @param hijos La lista de scopes hijos
     */
    public void setHijos(ArrayList<Scope> hijos) {
        this.hijos = hijos;
    }

    /**
     * Busca un símbolo por su nombre en el scope actual y en sus scopes padres.
     * @param nombre_simbolo El nombre del símbolo a buscar
     * @return El símbolo encontrado o null si no se encuentra
     */
    public Symbols buscar_simbolo(String nombre_simbolo) {
        for (Symbols s : simbolos) {
            if (s.getNombre().equals(nombre_simbolo)) {
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
            if (s.getNombre().equals(nombre)) {
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
            if (s.getNombre().equals(nombre_simbolo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene si ya se encontró un return en este scope directo.
     * @return true si ya hubo un return en este bloque
     */
    public boolean getReturnEncontrado() {
        return return_encontrado;
    }

    /**
     * Establece si ya se encontró un return en este scope directo.
     * @param v, valor a asignar
     */
    public void setReturnEncontrado(boolean v) {
        this.return_encontrado = v;
    }

    /**
     * Obtiene si este scope ya fue marcado como contenedor de un break.
     * Relevante solo en scopes "case", "default" y "do_while".
     * @return true si ya hay un break en este contenedor
     */
    public boolean getBreakContenedor() {
        return break_contenedor;
    }

    /**
     * Marca este scope como contenedor de un break.
     * @param v valor a asignar
     */
    public void setBreakContenedor(boolean v) {
        this.break_contenedor = v;
    }

    /**
     * Obtiene si ya se encontró un break local en este scope.
     * Se usa para detectar código inalcanzable dentro del mismo bloque.
     * @return true si hay un break local en este scope
     */
    public boolean getBreakLocal() {
        return break_local;
    }

    /**
     * Marca este scope con un break local para detectar unreachable code.
     * @param v valor a asignar
     */
    public void setBreakLocal(boolean v) {
        this.break_local = v;
    }

    /**
     * Obtiene la línea del break que marcó este scope como local.
     * @return línea del break, o -1 si no fue marcado
     */
    public int getLineaBreak() { return linea_break; }
    public void setLineaBreak(int v) { this.linea_break = v; }
 
    /**
     * Obtiene la columna del break que marcó este scope como local.
     * @return columna del break, o -1 si no fue marcado
     */
    public int getColumnaBreak() { return columna_break; }
    public void setColumnaBreak(int v) { this.columna_break = v; }
}
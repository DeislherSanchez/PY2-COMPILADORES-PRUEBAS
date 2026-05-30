import java.util.ArrayList;

/**
 * Tabla de simbolos con scopes de funciones y bloques, y funciones globales.
 * Permite abrir y cerrar scopes, agregar simbolos y consultar funciones.
 */
public class SymbolsTable {
    private ArrayList<Scope> funciones;
    private ArrayList<Symbols> funciones_globales;
    private Scope scope_actual;

    /**
     * Constructor de la clase SymbolsTable, inicializa la lista de funciones y el scope actual como null.
     */
    public SymbolsTable() {
        this.funciones = new ArrayList<Scope>();
        this.funciones_globales = new ArrayList<Symbols>();
        this.scope_actual = null;
    }

    /**
     * Obtiene el scope actual.
     * @return scope actual o null si no hay un scope abierto
     */
    public Scope getScopeActual() {
        return scope_actual;
    }

    /**
     * Abre un nuevo scope para una función.
     * @param nombre El nombre de la función
     */
    public void abrir_scope_funcion(String nombre) {
        Scope nueva_funcion = new Scope(nombre, null);
        funciones.add(nueva_funcion);
        scope_actual = nueva_funcion;
    }

    /**
     * Abre un nuevo scope para un bloque de código.
     * @param nombre El nombre del bloque
     */
    public void abrir_scope(String nombre) {
        if (scope_actual != null) {
            Scope nuevo_bloque = new Scope(nombre, scope_actual);
            scope_actual.getHijos().add(nuevo_bloque);
            scope_actual = nuevo_bloque;
        }
    }

    /**
     * Cierra el scope actual.
     */
    public void cerrar_scope() {
        if (scope_actual != null && scope_actual.getPadre() != null) {
            scope_actual = scope_actual.getPadre();
        } else {
            scope_actual = null;
        }
    }

    /**
     * Agrega un nuevo símbolo al scope actual.
     * @param s El símbolo a agregar
     */
    public boolean agregar_simbolo(Symbols s) {
        if (scope_actual != null) {
            if (scope_actual.existe_en_scope_actual(s.getNombre())) {
                return false;
            }
            scope_actual.getSimbolos().add(s);
            return true;
        }
        return false;
    }

    /**
     * Agrega una nueva función global a la tabla.
     * @param s La función a agregar
     * @return true si la función fue agregada, false si ya existe
     */
    public boolean agregar_funcion(Symbols s) {
        for (Symbols f : funciones_globales) {
            if (f.getNombre().equals(s.getNombre())) {
                return false;
            }
        }

        funciones_globales.add(s);
        return true;
    }

    /**
     * Busca una función global por su nombre.
     * @param nombre_funcion Nombre de la función a buscar
     * @return La función encontrada o null si no existe
     */
    public Symbols buscar_funcion(String nombre_funcion) {
        for (Symbols f : funciones_globales) {
            if (f.getNombre().equals(nombre_funcion)) {
                return f;
            }
        }

        return null;
    }

    /**
     * Verifica si un simbolo ya existe en el scope actual de la tabla.
     * @param nombre_simbolo Nombre del simbolo a buscar
     * @return true si el simbolo existe en el scope actual, false en caso contrario
     */
    public boolean existe_simbolo_en_scope_actual(String nombre_simbolo) {
        if (scope_actual != null) {
            return scope_actual.existe_en_scope_actual(nombre_simbolo);
        }
        return false;
    }

    /**
     * Busca un simbolo desde el scope actual y sus padres.
     * @param nombre_simbolo Nombre del simbolo a buscar
     * @return El simbolo encontrado o null si no existe
     */
    public Symbols buscar_simbolo(String nombre_simbolo) {
        if (scope_actual != null) {
            return scope_actual.buscar_simbolo(nombre_simbolo);
        }
        return null;
    }

    /**
     * Indica si ya se registró un return en el scope actual directo.
     * @return true si ya hubo un return en el bloque actual
     */
    public boolean return_ya_declarado() {
        if (scope_actual != null) {
            return scope_actual.getReturnEncontrado();
        }
        return false;
    }

    /**
     * Marca el scope actual como que ya contiene un return.
     */
    public void marcar_return() {
        if (scope_actual != null) {
            scope_actual.setReturnEncontrado(true);
        }
    }

    /**
     * Determina si un nombre de scope es un contenedor válido para break.
     */
    private boolean es_contenedor_break(String nombre) {
        return nombre.equals("case") || nombre.equals("default") || nombre.equals("do_while");
    }

    /**
     * Busca el scope contenedor más cercano (case, default, do_while)
     * sin cruzar la raíz de una función.
     */
    private Scope buscar_scope_break() {
        Scope s = scope_actual;
        while (s != null) {
            if (es_contenedor_break(s.getNombre())) return s;
            if (s.getPadre() == null) return null;
            s = s.getPadre();
        }
        return null;
    }

    /**
     * Indica si ya se registró un break en el contenedor más cercano.
     * Usado para detectar breaks duplicados.
     */
    public boolean break_ya_declarado() {
        Scope contenedor = buscar_scope_break();
        return contenedor != null && contenedor.getBreakContenedor();
    }

    /**
     * Marca el contenedor más cercano (case, default, do_while) con
     * break_contenedor = true. Además guarda la línea y columna del break
     * para que el error de unreachable code indique exactamente esa posición.
     *
     * @param linea  línea del token BREAK en el fuente
     * @param columna columna del token BREAK en el fuente
     */
    public void marcar_break_contenedor(int linea, int columna) {
        Scope contenedor = buscar_scope_break();
        if (contenedor != null) {
            contenedor.setBreakContenedor(true);
        }
    }

    /**
     * Marca el scope actual con break_local = true y guarda la posición del
     * break para reportar errores de unreachable code con línea y columna exactas.
     *
     * @param linea   línea del token BREAK en el fuente
     * @param columna columna del token BREAK en el fuente
     */
    public void marcar_break_local(int linea, int columna) {
        if (scope_actual != null) {
            scope_actual.setBreakLocal(true);
            scope_actual.setLineaBreak(linea);
            scope_actual.setColumnaBreak(columna);
        }
    }

    /**
     * Indica si el scope actual inmediato tiene un break local activo, lo que hace que la siguiente sentencia sea código inalcanzable.
     * Solo revisa scope_actual, nunca sube a padres.
     */
    public boolean hay_break_activo() {
        if (scope_actual == null) return false;
        return scope_actual.getBreakLocal();
    }

    /**
     * Retorna la línea del break que hace inalcanzable la siguiente sentencia.
     * Solo tiene sentido llamarlo cuando hay_break_activo() == true.
     * @return línea del break previo, o -1 si no aplica
     */
    public int get_linea_break_activo() {
        if (scope_actual == null) return -1;
        return scope_actual.getLineaBreak();
    }

    /**
     * Retorna la columna del break que hace inalcanzable la siguiente sentencia.
     *
     * @return columna del break previo, o -1 si no aplica
     */
    public int get_columna_break_activo() {
        if (scope_actual == null) return -1;
        return scope_actual.getColumnaBreak();
    }

    /**
     * Imprime la tabla de símbolos.
     * Muestra cada scope con su nombre y los símbolos declarados en ese scope, con su nombre, categoría, tipo y posición (fila y columna).
     */
    public void imprimir_tabla() {
        System.out.println("========== TABLA DE SIMBOLOS ==========");
        System.out.println("Funciones globales:");
        if (funciones_globales.isEmpty()) {
            System.out.println("  (vacío)");
        } else {
            for (Symbols f : funciones_globales) {
                System.out.println("  ├── " + f.toString());
            }
        }
        System.out.println();
        for (Scope funcion : funciones) {
            imprimir_scope(funcion, 0);
            System.out.println();
        }
    }

    /**
     * Método recursivo para imprimir un scope y sus hijos, con indentación para mostrar la jerarquía de los scopes.
     * @param actual El scope actual a imprimir
     */
    private void imprimir_scope(Scope actual, int nivel) {
        String indent = "  ".repeat(nivel);
        String prefijo = (nivel == 0) ? "Scope: [" : "└── Scope: [";
        System.out.println(indent + prefijo + actual.getNombre() + "]");
        
        if (actual.getSimbolos().isEmpty() && actual.getHijos().isEmpty()) {
            System.out.println(indent + "  (vacío)");
        }

        for (Symbols s : actual.getSimbolos()) {
            System.out.println(indent + "  ├── " + s.toString());
        }
        for (Scope hijo : actual.getHijos()) {
            imprimir_scope(hijo, nivel + 1);
        }
    }
}
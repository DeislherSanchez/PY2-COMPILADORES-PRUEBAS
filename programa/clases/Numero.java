 /**
 * Clase que representa un número con su tipo y su valor.
 */
public class Numero {
    private String tipo;
    private String valor;

    /**
     * Constructor de la clase Numero.
     * @param tipo El tipo del número
     * @param valor El valor del número como cadena
     */
    public Numero(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    /**
     * Obtiene el tipo del número.
     * @return El tipo del número
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el valor del número.
     * @return El valor del número
     */
    public String getValor() {
        return valor;
    }

    @Override
    /**
     * Muestra la información del número en un formato legible.
     * @return Una cadena con la información del número
     */
    public String toString() {
        return "Numero{tipo='" + tipo + "', valor='" + valor + "'}";
    }
}
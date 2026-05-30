import java.io.*;
import java_cup.runtime.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Uso: java Main <archivo_entrada>");
            return;
        }

        String archivo_tokens = "resultado/archivo_tokens.txt";

        try {

            Reader reader_lex = new BufferedReader(new FileReader(args[0]));
            PrintWriter writer = new PrintWriter(new FileWriter(archivo_tokens));
            Lexer lexer_1 = new Lexer(reader_lex);
            Symbol token;

            while ((token = lexer_1.next_token()).sym != sym.EOF) {
                String nombre_token = sym.terminalNames[token.sym];
                String lexema = (token.value != null) ? token.value.toString() : "";
                writer.println("Token: " + nombre_token + ", Lexema: " + lexema);
            }
            writer.close();
            reader_lex.close();

            Reader reader_par = new BufferedReader(new FileReader(args[0]));
            Lexer lexer_2 = new Lexer(reader_par);
            lexer_2.set_notificar_errores(true);
            parser parser = new parser(lexer_2);
            parser.parse();
            reader_par.close();

            int errores_lexicos = lexer_1.get_errores();
            int errores_sintacticos = parser.get_errores_sintacticos();
            int errores_semanticos = parser.get_errores_semanticos();

            if (errores_lexicos > 0 || errores_sintacticos > 0 || errores_semanticos > 0) {
                System.out.println("Errores léxicos: " + errores_lexicos);
                System.out.println("Errores sintácticos: " + errores_sintacticos);
                System.out.println("Errores semánticos: " + errores_semanticos);
                System.out.println("El archivo no puede ser generado por la gramática.");
            } else {
                System.out.println("El archivo puede ser generado por la gramática.");
                parser.tabla.imprimir_tabla();
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo de entrada o escribir el de salida.");
            e.printStackTrace();
        }
    }
}
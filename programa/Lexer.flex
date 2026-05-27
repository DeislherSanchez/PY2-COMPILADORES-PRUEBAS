import java_cup.runtime.*;

%%
%class Lexer
%unicode
%cup                    
%line                   
%column
%{
  private int errores = 0;

  private boolean notificacion = false;

  public void set_notificar_errores(boolean notificacion) {
    this.notificacion = notificacion;
  }

  public int get_errores() { return errores; }

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn, yytext());
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

DIGIT = [0-9]
DIGIT_NO_ZERO = [1-9]

LETTER = [a-zA-Z_]
LETTERS = {LETTER}+

WHITESPACE = [ \t\r\n]+

INTEGER = "0" | {DIGIT_NO_ZERO} {DIGIT}*

DECIMAL = "." {DIGIT}+

FLOAT_NUMBER = {INTEGER}{DECIMAL}

FRAC_NUMBER = {INTEGER} "//" {INTEGER}

EXPONENTIAL = {INTEGER} "e" {INTEGER}

CHAR = \'[^\']\'
STRING = \"[^\"]*\"
ID = {LETTERS}({LETTERS}|{DIGIT})*
%%

<YYINITIAL> {
  {WHITESPACE}  { /* ignorar */ }
  "¡¡"[^\n]*              { /* comentario línea, ignorar */ }
  "{-" ~"-}"              { /* comentario bloque, ignorar */ }

  "int"       {return symbol(sym.INT);}
  "float"     {return symbol(sym.FLOAT);}
  "char"      {return symbol(sym.CHAR);}
  "string"    {return symbol(sym.STRING);}
  "bool"      {return symbol(sym.BOOL);}
  "empty"     {return symbol(sym.EMPTY);}
  "true"      {return symbol(sym.TRUE);}
  "false"     {return symbol(sym.FALSE);}
  "if"        {return symbol(sym.IF);}
  "else"      {return symbol(sym.ELSE);}
  "do"        {return symbol(sym.DO); }
  "while"     {return symbol(sym.WHILE);}
  "switch"    {return symbol(sym.SWITCH);}
  "case"      {return symbol(sym.CASE);}
  "default"   {return symbol(sym.DEFAULT);}
  "break"     {return symbol(sym.BREAK);}
  "return"    {return symbol(sym.RETURN);}
  "cin"       {return symbol(sym.CIN);}
  "cout"      {return symbol(sym.COUT);}
  "__main__"  {return symbol(sym.MAIN);}

  /* Operadores de comparacion */

  "less_t"    {return symbol(sym.LESS_T);}
  "less_te"   {return symbol(sym.LESS_TE);}
  "greather_t"    {return symbol(sym.GREATHER_T);}
  "greather_te"   {return symbol(sym.GREATHER_TE);}
  "equal"     {return symbol(sym.EQUAL);}
  "n_equal"   {return symbol(sym.N_EQUAL);}

  /* Operadores aritmeticos */
  "++"         {return symbol(sym.INCREMENT);}
  "--"         {return symbol(sym.DECREMENT);}
  "+"          {return symbol(sym.PLUS);}
  "-"          {return symbol(sym.MINUS);}
  "*"          {return symbol(sym.MULT);}
  "/"          {return symbol(sym.DIV);}
  "%"          {return symbol(sym.MOD);}
  "^"          {return symbol(sym.POW);}

  /* Operadores logicos */
  "@"          {return symbol(sym.AND);}
  "#"          {return symbol(sym.OR);}
  "$"          {return symbol(sym.NOT);}

  /* Asignacion */
  "<-"         {return symbol(sym.ASSIGN);}

  /* delimitadores de bloque*/
  "|:"         {return symbol(sym.BLOCK_START);}
  ":|"         {return symbol(sym.BLOCK_END);}

  /* Parentesis especiales*/
  "<|"         {return symbol(sym.PAREN_OPEN);}
  "|>"         {return symbol(sym.PAREN_CLOSE);}

  /* delimitadores de indice*/
  "<<"         {return symbol(sym.INDEX_START);}
  ">>"         {return symbol(sym.INDEX_END);}

  /* Otros simbolos */
  "~" {return symbol(sym.SEPARATOR);}
  "," {return symbol(sym.COMMA);}
  "!" {return symbol(sym.END_EXPR);}
  ":" {return symbol(sym.COLON);}


  /* literales */
  {EXPONENTIAL} { return symbol(sym.EXP_LITERAL);}
  {FLOAT_NUMBER} {return symbol(sym.FLOAT_LITERAL);}
  {FRAC_NUMBER} {return symbol(sym.FRAC_LITERAL);}
  {INTEGER} {return symbol(sym.INT_LITERAL);}
  {STRING} {return symbol(sym.STRING_LITERAL);}
  {CHAR} {return symbol(sym.CHAR_LITERAL);}
  {ID} {return symbol(sym.ID);}

  [^]  { errores++; if (!notificacion) System.err.println("Error léxico en línea " + (yyline+1) + ", columna " + (yycolumn+1) + ": caracter desconocido '" + yytext() + "'"); }
}

package trab2.grupo1;

import java.io.*;
import java.util.function.BiConsumer;
import java.util.function.Function;


public class StreamsUtils {
    /**
     * @Author: 47597 - João Fernandes
     */

    // 1
    // Recebendo por parâmetro um stream de texto, contendo código fonte na linguagem Java, retorna true se para o
    // carácter '}' existiu anteriormente um carácter '{' e se para o carácter carácter '{' existe posteriormente um carácter '}', caso
    // contrário false. Considere que os literais do tipo String e os comentários não contêm chavetas.
    public static boolean validate(Reader in) throws IOException {
        int charct;
        int open = 0;
        int close = 0;
        while ((charct = in.read()) != -1) {
            if (open >= close) {
                if (charct == '{') {
                    open++;
                } else if (charct == '}') {
                    close++;
                }
            } else return false;

        }
        //têm de possuir o mesmo número de caracteres
        return open == close;

    }


    /**
     * @Author: 47597 - João Fernandes
     */

    // 2
    // Recebendo por parâmetro um stream de texto, contendo código fonte na linguagem Java, copia para o stream de texto
    // out os comentários de linha indicando em que linha ocorrem. Assume que nem os literais do tipo string nem os
    // comentários de bloco contêm a sequência de caracteres que define o início de um comentário de linha ("//").
    public static void copyCom(BufferedReader in, java.io.PrintWriter out) throws IOException {
        int linha = 0;
        int ci; //character index
        String cl; //current line
        String cm; //comments
        while ((cl = in.readLine()) != null) { //reads until there is no more file to read
            linha++; //line need to be incremented as the lines are read
            if (cl.contains("//")) {//found the char while reading the line
                ci = cl.indexOf("//");//obtain the index of the sequence
                cm = cl.substring(ci, cl.length()); //creates the string from the located char to the end of the line (where teh comments encounter them self)1
                out.println(cm + " " + linha); //prints the comments and the line they occurred
            }
        }
    }

    /**
     * @Author: 47597 - João Fernandes
     */

    // 3.a
    // Que cada linha do stream in execute a ação binária action passando por parâmetro a linha e o resultado da
    // operação mapping sobre a linha. Para obter o mapeamento duma linha deve ser chamado o método apply sobre o objeto
    // função mapping passando-lhe por parâmetro a linha. Executar a ação binária é chamar o método accept passando dois
    // parâmetros: a linha lida e string que resultou do mapeamento.
    public static <R> void mapper(BufferedReader in, Function<String, R> mapping, BiConsumer<String, R> action) throws IOException {
        String line;
        R r;
        while ((line = in.readLine()) != null) { //reading a line of the string
            r = mapping.apply(line);//to obtain the mapping of a line call the method apply over the mapping with line inside
            action.accept(line, r); //for each readed line ecexecute action with the line and the result of mapping over the line
        }
    }

    /**
     * @Author: 47597 - João Fernandes
     */

    // 3.b
    // Retorna o resultado da avaliação da expressão aritmética recebida por parâmetro. Uma expressão aritmética é
    // constituída, por um carácter dígito seguido de uma sequência de zero ou mais pares(operador, carácter dígito), seguidos
    // do carácter '='. Os operadores são o '+' ou '-'. Caso a expressão esteja incorreta retorna null;
    // <expression> ::= <digit> {<operator> <digit>}* '='
    // <operator> ::= '+' | '-'
    public static Integer evaluate(String expression) {
        int result = 0;
        int operator = 1; //define se é +(1) ou -(-1)
        int valor = 0;

        // Verificar o =
        if (expression.charAt(expression.length() - 1) != '=')
            return null;

        // Verificar regra: digito e operador
        for (int i = 0; i < expression.length() - 2; i++) {
            if (i % 2 == 0) {                                 // par -> só pode ser digito
                if (!Character.isDigit(expression.charAt(i))) return null;
            } else {                                          // impar -> só pode ser operador (+ ou -)
                if (expression.charAt(i) != '+' && expression.charAt(i) != '-') return null;
            }

        }

        // Fazer o calculo
        for (int i = 0; i < expression.length() - 1; i++) {
            if (i % 2 == 0) {                               // par -> Somar ou Subtrair dependendo do operador
                valor = expression.charAt(i) - '0';
                if (operator == 1) result = result + valor;
                if (operator == -1) result = result - valor;
            } else {                                          // impar -> verificação do operador + ou -
                if (expression.charAt(i) == '+') operator = 1;
                if (expression.charAt(i) == '-') operator = -1;
            }
        }
        return result;
    }

    /**
     * @Author: 47597 - João Fernandes
     */

    // 3.c
    // Que recebendo por parâmetro um ficheiro de texto com o nome filenameIn, em que cada linha contém uma expressão
    // aritmética, execute a action sobre cada linha do ficheiro e o resultado do cálculo da expressão.
    // Utilize o método da alínea a) e implemente um Function<String, Integer>
    public static void evaluate(String filenameIn, BiConsumer<String, Integer> action) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(filenameIn))) {
            Function<String, Integer> mapping = (String expression) -> {
                return evaluate(expression); //evaluates the expression
            };
            mapper(reader, mapping, action);

        }
    }

    /**
     * @Author: 47597 - João Fernandes
     */

    // 3.d
    // Que recebendo por parâmetro um ficheiro de texto com nome filenameIn, em que cada linha contém uma expressão
    // aritmética, que escreva no ficheiro de texto com nome filenameOut as expressões com o resultado do cálculo.
    public static void copyEvaluate(String filenameIn, String filenameOut) throws IOException {
        if (filenameIn.isEmpty()) throw new IOException();
        try (
                PrintWriter write = new PrintWriter(new FileWriter(filenameOut))) {
            BiConsumer<String, Integer> action = (String consume, Integer number) -> {
                if (number != null) {
                    write.println(consume + number);
                } else write.println(consume + "Error");
            };
            evaluate(filenameIn, action);
        }
    }


    /**
     * @Author: 47597 - João Fernandes
     */

    // 3.e
    // Que recebendo por parâmetro o stream de texto in em que cada linha contém uma expressão aritmética, escreva no stream
    // de texto out a expressão com o resultado do cálculo.
    // Utilize o método da alínea a) e implemente uma Function<String, Integer> e um BiConsumer<String,Integer>
    public static void copyEvaluate(BufferedReader in, Writer out) throws IOException {

        Function<String, Integer> mapping = (String expression) -> {
            return evaluate(expression); //evaluates the expression
        };

        BiConsumer<String, Integer> action = (String consume, Integer number) -> {
            try {
                if (number != null) {
                    out.write(consume + number);
                } else out.write(consume + "Error");

            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        };

        mapper(in, mapping, action);
    }


    /**
     * @Author: 47597 - João Fernandes
     */

//3.f
    public static String stringEvaluate(String expression) {
        BufferedReader reader = new BufferedReader(new StringReader(expression)); // instanciar stream reader
        StringWriter writer = new StringWriter();
        try {
            copyEvaluate(reader, writer);
            return writer.toString();       // return string of writer
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}

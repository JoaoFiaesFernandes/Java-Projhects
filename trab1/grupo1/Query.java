package trab1.grupo1;

import java.util.Arrays;
import java.util.Scanner;

public class Query {
    private final String text;
    private final String correctAnswer;
    private final int points;

    public Query(String txt) {
        this.text = txt;
        this.points = 5;
        this.correctAnswer = "yes";
    }


    //toString

    public String toString() { //de modo a apresentar os dados passados
        return text + " " + "[" + points + "]" + "?" + " " + correctAnswer;
    }

    //metodo equals

    public boolean equals(Object obj ) {
        if(!(obj instanceof Query)){
            return false;
        }
        Query q = (Query) obj; //cast realizado
        return text.equals(q.text)
                && points == q.points
                && correctAnswer.equals(q.correctAnswer);
    }


    //2

    //Complete a classe Query, acrescentando:
    //Construtor com três parâmetros o texto, a resposta, e número de pontos da questão.

    public Query(String txt, String ca, int p) {
        text = txt;
        correctAnswer = ca;
        points = p;
    }


    //Construtor com dois parâmetros o texto e um valor boolean
    //que se a true significa que a resposta correta é “yes” a false significa “no”

    public Query(String txt, Boolean bool){ //duvida Perguntar á prof
        text = txt;
        points = 5;
        if(bool == true){ //se o bool for true muda-se o valor do correctAnswer
            correctAnswer = "yes";
            getPoints();
        } else correctAnswer = "no";
    }


    //Os métodos de instância (getters) para obter o texto e o número de pontos
    //Getters

    public String getText() {
        return this.text;
    } //para o texto

    public Integer getPoints() {
        return this.points;
    } //para o numero de pontos

   // public String getCorrectAnswer() {return this.correctAnswer;} //para a resposta


    //O método instância checkAnswer que recebendo por parâmetro uma string com a resposta, retorna o número de
    //pontos da questão se a resposta estiver correta ou zero caso contrário.

    public Integer checkAnswer(String ca) {
        if (ca.equals("yes")) { //verificar o conteudo da String
            return getPoints();
        } else return getPoints(); //retorna o valor da pergunta
    }

    //O método de instância compareTo que define a relação de ordem sobre as instâncias da classe Query. Sejam q1 e
    //q2 dois objetos do tipo Query e x um valor inteiro tal que x = q1.compareTo(q2). Se:
    //x<0, significa que o número de pontos da questão q1 é inferior ao número de pontos da questão q2;
    //x>0, significa que o número de pontos da questão q1 é superior ao número de pontos da questão q2;
    //x==0, significa que o número de pontos da questão q1 é igual número de pontos da questão q2.

    public int compareTo(Query q2) {
        //x=q1.compareTo(q2);
        int x; //fator de valor
        x = this.points - q2.points;
        return x;
    }


    // O método estático parse que recebendo por parâmetro uma instância de java.lang.String retorna a
    //correspondente instância de Query. O formato da string recebida por parâmetro é:
    //<param>::= <text> ‘?’ <correct answer> | <text> ‘[’ <points> “]?” <correct answer>
    //Usar os métodos de instância da classe java.lang.String:
    //‒ int indexOf(int ch, int fromIndex) – para obter os índices dos caracteres de separação;
    //‒ int lastIndexOf(int ch ) – para obter o índice do caractere de separação da resposta;
    //‒ String substring(int beginIndex, int endIndex) – para individualizar as strings com o texto, a
    //resposta e o número de pontos;
    //o método estático da classe java.lang.Integer
    // int parseInt(String strNumber) – para obter o valor inteiro correspondente aos pontos.

    //A dimensão do int é 32 bits [5]? yes
    //01234567890123456789012345678


   public static Query parse(String s) {
       //fazer a verificaação pelo caracter de separação
            int sp0 = s.indexOf('[', 0);
        if(sp0 == -1) { //caso não exista procuro pelo ?
            //primeira substring com o text
            int sp = s.indexOf('?', 0);
            String text = s.substring(0, sp);

            //segunda substring com a resposta
            String ca = s.substring(sp + 1, s.length());
            return new Query(text,"", 5);

        } else {

            //terceira substring com o text
            String text2 = s.substring(0, sp0);
            text2=text2.trim();

            //quarta substring com o valor de points
            int sp3 = s.lastIndexOf(']');
            String p = s.substring(sp0 + 1, sp3).trim();

            int pi = Integer.parseInt(p); //passar para inteiro

            //quinta substring com a resposta correta
            int sp4 = s.lastIndexOf('?');
            String ca1 = s.substring(sp4 + 1, s.length()).trim();

            return new Query(text2,ca1,pi);
        }


    }

    //O método estático quiz que recebendo por parâmetro um parâmetro de dimensão variável de elementos do tipo
    //Query, instância um Scanner e para cada Query: faz a pergunta; lê a resposta; e caso esteja correta acumula os
    //pontos. Retorna os pontos acumulados.

   public static Integer quiz(Query[] q){ //retorna me um inteiro numero de pontos acumulados

       int g = 0; //variavel para acumular o valor dos pontos totais
       Scanner sc = new Scanner(System.in);//criação do scanner
       for(int i = 0; i<q.length;  i++) { //percorrer o array
            if (sc.hasNext() == false) { //se o scanner não estiver a ler mais nada é fechado retorna false significa que não tem conteudo
                sc.close(); //fecha o scanner não tem mais nada para ler
            } else if (sc.equals(q[i].correctAnswer)) {//verificar se a resposta esta correta e acumula os pontos
                g = g + q[i].points; //soma dos pontos
            }
        }
       return g;
    }


    //O método estático growingQueries que recebe por parâmetro um array de questões produz um array ordenado. O
    //array recebido por parâmetro é percorrido sequencialmente e a questão é adicionada no fim do novo array se: for a
    //1ª questão; ou se for maior ou igual à última adicionada. Para comparar as Query utilize o método compareTo

    public static Query[] growingQueries(Query[] q){

        int index = 0;//posição do array copia
        Query fn[] = new Query[q.length];//array de copia

        for(int i = 0; i < q.length; i++){ //percorrer o array
                if(i == 0){
                    fn[index]= q[i];
                    index++;
            } else if(q[i].compareTo(fn[index -1]) >= 0){
                    fn[index] = q[i];
                    index++;
            }
        }
        Query copy [] = Arrays.copyOf(fn, index);// redimensionar o tamnho do array
        return copy;
    }
}
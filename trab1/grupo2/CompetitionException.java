package trab1.grupo2;

public class CompetitionException extends Exception {

    // retorna a mensagem que é passada por parametro no construtor
    public CompetitionException(String msg) {
        super(msg);
    }

    // retorna no caso do construtor sem parametros "Competição inválida"
    public CompetitionException() {
        super("Competição inválida");
    }

}

/*
5.
Implemente a classe CompetitionException para que o método getMessage herdado
retorne a mensagem que é passada por parâmetro no construtor, ou no caso do
construtor sem parâmetros "Competição inválida".
 */
package trab1.grupo3;


public class StateException extends Exception{

    State aux;
    // retorna a string que é passada por parametro no construtor, caso tenha sido
    // instanciado com o construtor com parametro do tipo string
    public StateException(String msg) {
        super (msg);
    };

    // retorna o nome do State passado por parametro no construtor concatenado com a string "-Estado inválido"
    // caso tenha sido instanciado com o construtor com parâmetro do tipo State
    public StateException(State s) {
        super (s.name + " - Estado inválido");
        aux = s;
    };

    //retorna o state passado por parametro no construtor
    public State getState(){
        return aux;
    };

}

package trab1.grupo4;

public class PublicationException extends Exception{

    // 2)

    // String "Erro:" concatenada com a descrição passada por parametro caso tenha
    // sido instanciada com o construtor com um parametro
    public PublicationException(String description){
        super("Error: " + description);
    }

    // Tem que retornar a string "Invalid publication" caso a exceção tenha sido
    // instanciada com o construtor sem parametros
    public PublicationException(){
        super("Invalid publication");
    }

}

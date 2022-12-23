package trab1.grupo4;

abstract public class Publication {

    // 1)
    private final String prefix;

    private final String title;

    // Ao construtor é passado o titulo e um prefixo ao titulo.
    public Publication(String prefix, String title){
        this.prefix=prefix;
        this.title=title;
    }

    // O método getNumberOfPages é abstrato
    public abstract int getNumberOfPages();

    // O metodo getTitle retorna o titulo recebido por parametro no construtor e não pode ser redefinido.
    public final String getTitle(){
        return title;
    }

    // O metodo toString retorna o prefixo seguindo do titulo entre aspas
    public String toString(){
        return prefix + " " + "\"" + title + "\"";
    }

}

